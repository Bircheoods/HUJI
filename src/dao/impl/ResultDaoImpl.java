package dao.impl;

import dao.ResultDao;
import entity.Result;

/**
 * @description
 * @user summerHouAnNing
 * @creatTime 2021/4/21--9:50
 */
public class ResultDaoImpl extends BaseDao implements ResultDao {
    @Override
    public int insertOneResult(Result result) {
        String sql = "insert into result(`person_id`,`result_type`,`result_time`) values(?,?,?)";
        return dbUtilsUpdate(sql,result.getPersonId(),result.getResultType(),result.getResultTime());
    }
}
