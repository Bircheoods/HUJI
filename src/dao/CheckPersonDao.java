package dao;

import entity.CheckPerson;
import entity.Person;

import java.util.List;

public interface CheckPersonDao {
    /**
    *@description: 新增一条数据
    *@author: summerHouAnNing
    *@creatTime: 2021/4/27--10:04
    *@Param:
    *@return:
    **/
    public int insertOneData(CheckPerson checkPerson);
    /**
    *@description: 根据id查询人员
    *@author: summerHouAnNing
    *@creatTime: 2021/4/28--18:40
    *@Param:
    *@return:
    **/
    public CheckPerson queryCheckUserById(int id);

    public int queryCheckPersonTotalCount();

    public List<CheckPerson> queryCheckPersonLimit(int begin, int pageSize, String param);

    public int checkPerson(CheckPerson checkPerson);
}
