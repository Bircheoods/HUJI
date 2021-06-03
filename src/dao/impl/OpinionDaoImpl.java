package dao.impl;

import dao.OpinionDao;
import entity.Opinion;

import java.util.List;

/**
 * @description
 * @user summerHouAnNing
 * @creatTime 2021/4/21--2:51
 */
public class OpinionDaoImpl extends BaseDao implements OpinionDao {
    @Override
    public int insertOpinion(Opinion opinion) {
        String sql = "insert into opinion(opinion_title,opinion_content,opinion_time,opinion_id) " +
                "values(?,?,?,?)";
        return dbUtilsUpdate(sql,opinion.getOpinionTitle(),opinion.getOpinionContent(),
                opinion.getOpinionTime(),opinion.getOpinionId());
    }

    @Override
    public int queryOpinionCountById(int id) {
        String sql = "select count(*) from opinion where opinion_id = ?";
        return ((Number) queryForSingleValue(sql,id)).intValue();
    }

    @Override
    public List<Opinion> queryOpinionByPage(int pageNo, int pageSize, int opinionId, String param) {
        String sql = "select `id`,`opinion_title` opinionTitle,`opinion_id` opinionId,`opinion_content` opinionContent,`opinion_time` opinionTime," +
                "`solve_time` solveTime,`solve_id` solveId,`solve_content` solveContent from opinion where  (`opinion_title` like ? or `opinion_content` like ?) and `opinion_id` = ?" +
                " limit ?,?";
        return queryForList(Opinion.class,sql,"%"+param+"%","%"+param+"%",opinionId,pageNo,pageSize);
    }

    @Override
    public int deleteOpinion(int opinionId) {
        String sql = "delete from opinion where id = ?";
        return dbUtilsUpdate(sql,opinionId);
    }

    @Override
    public Opinion queryOneOpinionById(int parseInt) {
        String sql = "select `id`,`opinion_title` opinionTitle,`opinion_id` opinionId,`opinion_content` opinionContent,`opinion_time` opinionTime,`opinion_content` opinionContent," +
                "`solve_time` solveTime,`solve_id` solveId,`solve_content` solveContent from opinion where `id` = ?" ;
        return queryForOne(Opinion.class,sql,parseInt);
    }

    @Override
    public int updateOpinion(Opinion opinion) {
        String sql = "update opinion set `opinion_title`=?,`opinion_content`=?,`opinion_time`=? where `id`=?";
        return dbUtilsUpdate(sql,opinion.getOpinionTitle(),opinion.getOpinionContent(),opinion.getOpinionTime(),opinion.getId());
    }

    @Override
    public int queryOpinionCountById() {
        String sql = "select count(*) from opinion";
        return ((Number) queryForSingleValue(sql)).intValue();
    }

    @Override
    public List<Opinion> queryOpinionByPage(int begin, int pageSize, String param) {
        String sql = "select `id`,`opinion_title` opinionTitle,`opinion_id` opinionId,`opinion_content` opinionContent,`opinion_time` opinionTime,`opinion_content` opinionContent," +
                "`solve_time` solveTime,`solve_id` solveId,`solve_content` solveContent from opinion where  (`opinion_title` like ? or `opinion_content` like ?) " +
                " limit ?,?";
        return queryForList(Opinion.class,sql,"%"+param+"%","%"+param+"%",begin,pageSize);
    }

    @Override
    public int checkOpinion(Opinion opinion) {
        String sql = "update opinion set `solve_content`=?,`solve_id`=?,`solve_time`=? where `id`=?";
        return dbUtilsUpdate(sql,opinion.getSolveContent(),opinion.getSolveId(),opinion.getSolveTime(),opinion.getId());
    }
}
