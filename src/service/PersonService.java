package service;

import entity.Area;
import entity.Page;
import entity.Person;

import java.util.List;

public interface PersonService {
    /**
    *@description: 根据身份证查询人员
    *@author: summerHouAnNing
    *@creatTime: 2021/4/24--12:54
    *@Param:
    *@return:
    **/
    public Person queryOnePersonByIdNum(String idNum);
    /**
    *@description: 查找地区
    *@author: summerHouAnNing
    *@creatTime: 2021/4/26--9:16
    *@Param:
    *@return:
    **/
    public List<Area> queryArea(String fatherNum, String grandfatherNum);
    /**
    *@description: 新添加一条人员记录
    *@author: summerHouAnNing
    *@creatTime: 2021/4/27--8:04
    *@Param: 
    *@return: 
    **/
    public int insertOnePerson(Person person);
    /**
    *@description: 查找具体某个地区
    *@author: summerHouAnNing
    *@creatTime: 2021/4/27--9:17
    *@Param:
    *@return:
    **/
    public Area queryOneArea(String fatherNum,String grandfatherNum,String num);
    /**
     *@description: 根据用户名和身份证号查询人员
     *@author: summerHouAnNing
     *@creatTime: 2021/4/27--14:30
     *@Param:
     *@return:
     **/
    public Person queryPersonByNameAndNum(String name,String num);
    /**
     *@description:  根据id修改人员信息
     *@author: summerHouAnNing
     *@creatTime: 2021/4/22--3:42
     *@Param:
     *@return:
     **/
    public int updatePersonStatus(Person person);
    /**
     *@description: 根据id查找人员
     *@author: summerHouAnNing
     *@creatTime: 2021/4/22--3:41
     *@Param:
     *@return:
     **/
    public Person queryPersonById(Integer id);

    /**
    *@description: 修改人员户籍地
    *@author: summerHouAnNing
    *@creatTime: 2021/5/4--11:55
    *@Param:
    *@return:
    **/
    public int updatePersonNativePlace(Person person);

}
