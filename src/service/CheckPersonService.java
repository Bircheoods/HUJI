package service;

import entity.CheckPerson;
import entity.Page;
import entity.Person;

public interface CheckPersonService {
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

    public Page<CheckPerson> queryAllPerson(int pageNo, int pageSize, String param);

    public int checkPerson(CheckPerson checkPerson);
}
