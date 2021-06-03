package dao;

import entity.User;

import java.util.List;

public interface UserDao {
    /**
     * @description: 根据账号、密码和权限查询user
     * @author: summerHouAnNing
     * @creatTime: 2021/4/24--7:35
     * @Param:
     * @return:
     **/
    public User queryOneUserByNumAndPsw(String userIdNum, String userPassword, int power);

    /**
     * @description: 向数据库中新增一条记录
     * @author: summerHouAnNing
     * @creatTime: 2021/4/25--22:51
     * @Param:
     * @return:
     **/
    public int insertOneUser(User user);

    /**
     * @description: 根据userID查询一条user记录
     * @author: summerHouAnNing
     * @creatTime: 2021/4/26--6:27
     * @Param:
     * @return:
     **/
    public User queryUserByUserId(int userId);

    /**
     * @description: 根据问题和答案查找人员
     * @author: summerHouAnNing
     * @creatTime: 2021/4/26--7:28
     * @Param:
     * @return:
     **/
    public User queryUserByProblemAndAnswer(String problem, String answer);

    /**
     * @description: 根据id修改密码
     * @author: summerHouAnNing
     * @creatTime: 2021/4/26--7:33
     * @Param:
     * @return:
     **/
    public int updateUserPassword(String newPassword, int id);

    /**
     * @description: 查询所有用户
     * @author: summerHouAnNing
     * @creatTime: 2021/5/4--6:51
     * @Param:
     * @return:
     **/
    public List<User> queryAllUser(int pageNo, int pageSize, String userName);

    /**
     * @description: 查询总记录数
     * @author: summerHouAnNing
     * @creatTime: 2021/5/4--7:39
     * @Param:
     * @return:
     **/
    public int queryPageTotalCount(int pageNo, int pageSize, String userName);

    /**
     * @description: 修改power为1
     * @author: summerHouAnNing
     * @creatTime: 2021/5/4--7:56
     * @Param:
     * @return:
     **/
    public int updateUserPower(int id);

    /**
     * @description: 根据id删除一条数据
     * @author: summerHouAnNing
     * @creatTime: 2021/5/4--7:56
     * @Param:
     * @return:
     **/
    public int deleteUserById(int id);

    public int updateUserInfo(User user);
}
