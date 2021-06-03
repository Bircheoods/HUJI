package service;

import entity.Household;

import java.util.List;

public interface HouseholdService {
    /**
     *@description: 根据人员id查询户口信息
     *@author: summerHouAnNing
     *@creatTime: 2021/4/27--16:40
     *@Param:
     *@return:
     **/
    public Household queryListHouseholdById(int userId);
    /**
     *@description: 根据户主号查询户籍信息
     *@author: summerHouAnNing
     *@creatTime: 2021/4/28--6:00
     *@Param:
     *@return:
     **/
    public List<Household> queryListHouseholdByNum(String num);
    /**
     *@description: 插入一条记录
     *@author: summerHouAnNing
     *@creatTime: 2021/4/28--6:08
     *@Param:
     *@return:
     **/
    public int insertOneHousehold(Household household);
    /**
     *@description: 删除一条记录
     *@author: summerHouAnNing
     *@creatTime: 2021/4/28--17:53
     *@Param:
     *@return:
     **/
    public int deleteOneData(int personId);

    /**
    *@description: 修改人员相关户籍信息
    *@author: summerHouAnNing
    *@creatTime: 2021/5/4--12:01
    *@Param:
    *@return:
    **/
    public int updatePersonHouseholdByUserId(Household household);
}
