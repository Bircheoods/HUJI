package dao.impl;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import utils.JDBCUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @description 抽象基类，封装所有dao可能用到的方法
 * @user summerHouAnNing
 * @creatTime 2021/4/18--20:53
 */
public abstract class BaseDao {

    //使用DbUtils操作数据库,创建queryRunner对象
    private QueryRunner queryRunner=new QueryRunner();
    /**
     *@Author summer_HouAnNing
     *@Description 该方法用来处理 insert（插入）、update（修改）、delete（删除）语句
     *@Date 2021/4/1-11:32
     *@Param sql:需要被执行的sql语句、args：sql语句中占位符的值
     *@Return 如果返回-1，说明执行失败。如果执行成功，则返回受影响的结果的行数
     **/
    public int dbUtilsUpdate(String sql,Object ...args){
        Connection connection=null;//创建connection对象
        try {
            connection = JDBCUtils.getConnection();//获取数据库连接
            return queryRunner.update(connection,sql,args);//执行sql语句，返回查询结果
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            //将异常抛出,让servlet捕获进行事务回滚
            throw new RuntimeException(throwables);
        }
    }
    /**
     *@Author summer_HouAnNing
     *@Description 查找一条数据
     *@Date 2021/4/1-12:30
     *@Params type:返回值的类型，这里使用了泛型参数
     *        sql：需要被执行的sql语句
     *        args：sql语句中的占位符
     *@Return
     **/
    public <T> T queryForOne(Class<T> type,String sql,Object ...args){
        Connection connection= null;
        try {
            connection = JDBCUtils.getConnection();
            return queryRunner.query(connection,sql,new BeanHandler<T>(type),args);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException(throwables);
        }
    }

    /**
     *@Author summer_HouAnNing
     *@Description 查询返回多条数据
     *@Date 2021/4/1-12:37
     *@Params
     *@Return
     **/
    public <T> List<T> queryForList(Class<T> type, String sql, Object ...args){
        Connection connection= null;
        try {
            connection = JDBCUtils.getConnection();
            return queryRunner.query(connection,sql,new BeanListHandler<T>(type),args);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException(throwables);
        }
    }
    /**
     *@Author summer_HouAnNing
     *@Description 执行返回一行一列的sql语句,做统计查询的时候可以使用，比如查询某个表有多少条记录
     *@Date 2021/4/1-12:40
     *@Params
     *@Return
     **/
    public Number queryForSingleValue(String sql,Object ...args){
        Connection connection = null;
        try {
            connection = JDBCUtils.getConnection();
            return (Number) queryRunner.query(connection,sql,new ScalarHandler(),args);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException(throwables);
        }
    }
}
