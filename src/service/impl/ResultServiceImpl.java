package service.impl;

import dao.ResultDao;
import dao.impl.ResultDaoImpl;
import entity.Result;
import entity.User;
import service.ResultService;

import javax.servlet.http.HttpSession;
import java.sql.Date;

/**
 * @description
 * @user summerHouAnNing
 * @creatTime 2021/4/21--9:51
 */
public class ResultServiceImpl implements ResultService {

    private ResultDao resultDao = new ResultDaoImpl();
    @Override
    public int insertOneResult(HttpSession session, String type) {
        User user = (User) session.getAttribute("user");
        Result r = new Result();
        r.setPersonId(user.getUserId());
        r.setResultTime(new Date(new java.util.Date().getTime()));
        r.setResultType(type);
        return resultDao.insertOneResult(r);
    }
}
