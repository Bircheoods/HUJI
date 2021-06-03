package dao;

import entity.Opinion;

import java.util.List;

public interface OpinionDao {
    /**
    *@description: 新增一条意见
    *@author: summerHouAnNing
    *@creatTime: 2021/5/5--16:24
    *@Param:
    *@return:
    **/
    public int insertOpinion(Opinion opinion);
    /**
    *@description: 根据id查询总数量
    *@author: summerHouAnNing
    *@creatTime: 2021/5/5--17:25
    *@Param:
    *@return:
    **/
    public int queryOpinionCountById(int id);
    /**
    *@description: 按页查询当前页的数据
    *@author: summerHouAnNing
    *@creatTime: 2021/5/5--17:31
    *@Param:
    *@return:
    **/
    public List<Opinion> queryOpinionByPage(int pageNo,int pageSize,int opinionId,String param);

    public int deleteOpinion(int opinionId);

    public Opinion queryOneOpinionById(int parseInt);

    public int updateOpinion(Opinion opinion);

    public int queryOpinionCountById();

    public List<Opinion> queryOpinionByPage(int begin, int pageSize, String param);

    public int checkOpinion(Opinion opinion);
}
