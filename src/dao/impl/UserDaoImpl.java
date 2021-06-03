package dao.impl;

import dao.UserDao;
import entity.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @description
 * @user summerHouAnNing
 * @creatTime 2021/4/21--2:49
 */
public class UserDaoImpl extends BaseDao implements UserDao {
    /**
     * @description: 根据账号和密码查询user
     * @author: summerHouAnNing
     * @creatTime: 2021/4/24--7:35
     * @Param:
     * @return:
     **/
    @Override
    public User queryOneUserByNumAndPsw(String userIdNum, String userPassword, int power) {
        String sql = "select id,user_id userId,registered_time registeredTime,user_problem userProblem," +
                "user_password userPassword,user_answer userAnswer,phone,email,power" +
                " from user where (phone = ? or email = ?) and user_password = ? and power = ?";
        User user = queryForOne(User.class, sql, userIdNum, userIdNum, userPassword, power);
        return user;
    }

    @Override
    public int insertOneUser(User user) {
        String sql = "insert into user(user_id,phone,email,power,registered_time,user_password,user_problem,user_answer)" +
                " values(?,?,?,?,?,?,?,?)";
        //处理日期
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        try {
            Date date = format.parse(user.getRegisteredTime());
            return dbUtilsUpdate(sql, user.getUserId(), user.getPhone(), user.getEmail(), user.getPower(), new java.sql.Date(date.getTime()), user.getUserPassword(), user.getUserProblem()
                    , user.getUserAnswer());
        } catch (ParseException e) {
            System.out.println("日期转化出现错误！");
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public User queryUserByUserId(int userId) {
        String sql = "select id,user_id userId,registered_time registeredTime,user_problem userProblem," +
                "user_password userPassword,user_answer userAnswer,phone,email,power" +
                " from user where user_id = ?";
        return queryForOne(User.class, sql, userId);
    }

    @Override
    public User queryUserByProblemAndAnswer(String problem, String answer) {
        String sql = "select id,user_id userId,registered_time registeredTime,user_problem userProblem," +
                "user_password userPassword,user_answer userAnswer,phone,email,power" +
                " from user where user_answer = ? and user_problem = ?";
        return queryForOne(User.class, sql, answer, problem);
    }

    @Override
    public int updateUserPassword(String newPassword, int id) {
        String sql = "update user set user_password = ? where id = ?";
        return dbUtilsUpdate(sql, newPassword, id);
    }

    @Override
    public List<User> queryAllUser(int pageNo, int pageSize, String userName) {
        String sql = "select u.id,u.user_id userId,u.registered_time registeredTime,u.user_problem userProblem," +
                "u.user_password userPassword,u.user_answer userAnswer,u.phone,u.email,u.power" +
                " from user u left join person p on p.id = u.user_id " +
                "where p.name like ?" +
                "order by u.power desc " +
                "limit ?,?";
        return queryForList(User.class, sql, "%" + userName + "%", pageNo, pageSize);
    }

    @Override
    public int queryPageTotalCount(int pageNo, int pageSize, String userName) {
        String sql = "select count(*)" +
                " from user u left join person p on p.id = u.user_id " +
                "where p.name like ?" +
                "order by u.power desc " +
                "limit ?,?";
        return queryForSingleValue(sql, "%" + userName + "%", pageNo, pageSize).intValue();
    }

    @Override
    public int updateUserPower(int id) {
        String sql = "update `user` set power = 1 where id =?";
        return dbUtilsUpdate(sql, id);
    }

    @Override
    public int deleteUserById(int id) {
        String sql = "delete from `user` where id =?";
        return dbUtilsUpdate(sql, id);
    }

    @Override
    public int updateUserInfo(User user) {
        String sql = "update user set `phone`=?,`email`=?,`user_password`=?,`user_problem`=?,`user_answer`=? where id=?";
        return dbUtilsUpdate(sql, user.getPhone(), user.getEmail(), user.getUserPassword(), user.getUserProblem(), user.getUserAnswer(), user.getId());
    }

}
