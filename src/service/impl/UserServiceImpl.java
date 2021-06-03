package service.impl;

import dao.UserDao;
import dao.impl.UserDaoImpl;
import entity.Page;
import entity.User;
import service.UserService;

import java.util.List;

/**
 * @description
 * @user summerHouAnNing
 * @creatTime 2021/4/21--2:57
 */
public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDaoImpl();
    @Override
    public User queryUserByNumAndPsw(String userIdNum, String userPassword,int power) {
        User user = userDao.queryOneUserByNumAndPsw(userIdNum, userPassword,power);
        return user;
    }

    @Override
    public int insertOneUser(User user) {
       return userDao.insertOneUser(user);
    }

    @Override
    public User queryOneUser(int userId) {
        return userDao.queryUserByUserId(userId);
    }

    @Override
    public User queryUserByProblemAndAnswer(String problem, String answer) {
        return userDao.queryUserByProblemAndAnswer(problem,answer);
    }

    @Override
    public int updatePassword(String newPassword, int id) {
        return userDao.updateUserPassword(newPassword,id);
    }

    @Override
    public Page<User> queryAllUser(int pageNo,int pageSize,String userName) {
        Page<User> page = new Page<>();
        page.setPageNo(pageNo);//设置当前页码
        page.setPageSize(pageSize);//设置当前页记录数

        //求当前页数据的开始索引
        int begin = (page.getPageNo() - 1) * pageSize;

        //求当前查询出的总记录数
        int pageTotalCount = userDao.queryPageTotalCount(begin,pageSize,userName);
        page.setGetPageTotalCount(pageTotalCount);

        //求总页数
        int pageTotal =  pageTotalCount / pageSize;
        if ((pageTotalCount%pageSize) > 0)
            pageTotal+=1;
        //设置总页码
        page.setPageTotal(pageTotal);

        List<User> users = userDao.queryAllUser(begin, pageSize, userName);

        page.setItems(users);
        return page;
    }

    @Override
    public int updateUserPower(int id) {
        return userDao.updateUserPower(id);
    }

    @Override
    public int deleteUserById(int id) {
        return userDao.deleteUserById(id);
    }

    @Override
    public int updateUserInfo(User user) {
        return userDao.updateUserInfo(user);
    }


}
