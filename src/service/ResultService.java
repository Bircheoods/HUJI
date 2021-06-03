package service;

import entity.Result;

import javax.servlet.http.HttpSession;

public interface ResultService {
    /**
     *@description: 新增一条申请的记录
     *@author: summerHouAnNing
     *@creatTime: 2021/4/27--13:58
     *@Param:
     *@return:
     **/
    public int insertOneResult(HttpSession session,String type);
}
