package utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @description 封装一些工具类,数据库连接使用了德鲁伊数据库连接池
 * @user summerHouAnNing
 * @creatTime 2021/4/18--20:37
 */
public class JDBCUtils {
    //使用数据库连接池来创建一个连接对象
    //创建一个DruidDataSource对象
    private static DruidDataSource dataSource;

    //使用threadLocal来确保每个事务用的是同一个链接，就是说如果一个事务进行到一半发生了错误
    //能确保事务进行回滚（在错误之前发生的改变都变为原来的状态）
    private static ThreadLocal<Connection> threadConn = new ThreadLocal<Connection>();

    static{//静态代码块，在类加载的时候就会调用，当类加载时创建一个数据库连接池
        try {
            //1.读取数据库配置文件，生成一个流对象
            Properties properties=new Properties();
            InputStream inputStream = JDBCUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
            //2.从流中读入数据
            properties.load(inputStream);
            //3.创建数据库连接池
            dataSource= (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
    *@description: 使用数据库连接池获取一个连接
    *@author: summerHouAnNing
    *@creatTime: 2021/4/18--20:49
    *@Param:
    *@return:
    **/
    public static Connection getConnection(){
        Connection connection = threadConn.get();
        if (connection == null){//如果连接为空，从数据库连接池中获取
            try {
                connection = dataSource.getConnection();
                //获取之后存入到threadlocal中
                threadConn.set(connection);
                //设置数据库事务为手动提交
                connection.setAutoCommit(false);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return connection;
    }
    /**
     *@Author summer_HouAnNing
     *@Description 用来提交事务并且关闭连接
     *@Date 2021/4/1-3:21
     *@Param
     *@Return
     **/
    public static void commitAndClose(){
       //从threadLocal中获取到连接
        Connection connection = threadConn.get();
        if (connection != null){//如果不为空说明之前使用过
            try {
                connection.commit();//提交事务
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }finally {
                try {
                    connection.close();//关闭数据库连接
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
        threadConn.remove();//使用完之后一定要移除，tomcat服务器底层使用了线程池技术
    }
    /**
     *@Author summer_HouAnNing
     *@Description 用来提交事务并且关闭连接
     *@Date 2021/4/1-3:21
     *@Param
     *@Return
     **/
    public static void rollBackAndClose(){
        //从threadLocal中获取到连接
        Connection connection = threadConn.get();
        if (connection != null){//如果不为空说明之前使用过
            try {
                connection.rollback();//提交事务
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }finally {
                try {
                    connection.close();//关闭数据库连接
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
        threadConn.remove();//使用完之后一定要移除，tomcat服务器底层使用了线程池技术
    }
}
