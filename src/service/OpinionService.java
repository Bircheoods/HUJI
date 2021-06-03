package service;

import entity.Opinion;
import entity.Page;

public interface OpinionService {
    /**
    *@description: 添加一条意见
    *@author: summerHouAnNing
    *@creatTime: 2021/5/5--16:22
    *@Param:
    *@return:
    **/
    public int insertOpinion(Opinion opinion);
    /**
    *@description: 分页查询意见
    *@author: summerHouAnNing
    *@creatTime: 2021/5/5--17:04
    *@Param:
    *@return:
    **/
    public Page<Opinion> queryAllOpinion(int pageNo,int pageSize,String param,int opinionId);

    /**
    *@description: 删除一条意见
    *@author: summerHouAnNing
    *@creatTime: 2021/5/5--18:32
    *@Param:
    *@return:
    **/
    public int deleteOpinion(int parseInt);

    public Opinion queryOneOpinionById(int parseInt);

    public int updateOpinion(Opinion opinion);

    public Page<Opinion> queryAllOpinion(int pageNo, int pageSize, String param);

    public int checkOpinion(Opinion opinion);
}
