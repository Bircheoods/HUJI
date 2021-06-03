package service.impl;

import dao.CheckPersonDao;
import dao.impl.CheckPersonDaoImpl;
import entity.CheckPerson;
import entity.Page;
import entity.Person;
import service.CheckPersonService;

import java.util.List;

/**
 * @description
 * @user summerHouAnNing
 * @creatTime 2021/4/21--3:00
 */
public class CheckPersonServiceImpl implements CheckPersonService {
    private CheckPersonDao checkPersonDao = new CheckPersonDaoImpl();
    @Override
    public int insertOneData(CheckPerson checkPerson) {
        return checkPersonDao.insertOneData(checkPerson);
    }

    @Override
    public CheckPerson queryCheckUserById(int id) {
        return checkPersonDao.queryCheckUserById(id);
    }

    @Override
    public Page<CheckPerson> queryAllPerson(int pageNo, int pageSize, String param) {
        Page<CheckPerson> page = new Page<>();

        page.setPageNo(pageNo);
        page.setPageSize(pageSize);

        //求当前页数据的开始索引
        int begin = (page.getPageNo()-1)*pageSize;
        //求当前查询出的总记录数
        int pageTotalCount = checkPersonDao.queryCheckPersonTotalCount();
        page.setGetPageTotalCount(pageTotalCount);
        //求总页数
        int pageTotal =  pageTotalCount / pageSize;
        if ((pageTotalCount%pageSize) > 0)
            pageTotal+=1;

        //设置总页码
        page.setPageTotal(pageTotal);

        List<CheckPerson> checkPeople = checkPersonDao.queryCheckPersonLimit(begin, pageSize, param);
        page.setItems(checkPeople);
        return page;
    }

    @Override
    public int checkPerson(CheckPerson checkPerson) {
        return checkPersonDao.checkPerson(checkPerson);
    }
}
