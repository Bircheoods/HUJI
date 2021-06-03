package service;

import entity.Page;
import entity.User;

import java.util.List;

public interface UserService {
    /**
     * @description: 根据账号、密码和权限查找人员
     * @author: summerHouAnNing
     * @creatTime: 2021/4/24--7:41
     * @Param:
     * @return:
     **/
    public User queryUserByNumAndPsw(String userIdNum, String userPassword, int power);

    /**
     * @description: 向user表中新添加一条数据
     * @author: summerHouAnNing
     * @creatTime: 2021/4/25--23:23
     * @Param:
     * @return:
     **/
    public int insertOneUser(User user);

    /**
     * @description: 根据userId查询出一条记录
     * @author: summerHouAnNing
     * @creatTime: 2021/4/26--6:30
     * @Param:
     * @return:
     **/
    public User queryOneUser(int userId);

    /**
     * @description: 根据问题和答案查找人员
     * @author: summerHouAnNing
     * @creatTime: 2021/4/26--7:30
     * @Param:
     * @return:
     **/
    public User queryUserByProblemAndAnswer(String problem, String answer);

    /**
     * @description: 根据id修改密码
     * @author: summerHouAnNing
     * @creatTime: 2021/4/26--7:35
     * @Param:
     * @return:
     **/
    public int updatePassword(String newPassword, int id);

    /**
     * @description: 查询所有用户
     * @author: summerHouAnNing
     * @creatTime: 2021/5/4--6:50
     * @Param:
     * @return:
     **/
    public Page<User> queryAllUser(int pageNo, int pageSize, String userName);
    /**
    *@description: 设为管理员
    *@author: summerHouAnNing
    *@creatTime: 2021/5/4--7:55
    *@Param:
    *@return:
    **/
    public int updateUserPower(int id);
    /**
    *@description: 删除普通用户
    *@author: summerHouAnNing
    *@creatTime: 2021/5/4--7:55
    *@Param:
    *@return:
    **/
    public int deleteUserById(int id);

    public int updateUserInfo(User user);
}
