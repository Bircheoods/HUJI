package dao.impl;

import dao.CheckPersonDao;
import entity.CheckPerson;
import entity.Person;

import java.util.List;

/**
 * @description 这个是checkpersonDao接口的具体
 * * @user summerHouAnNing
 * @creatTime 2021/4/21--2:54
 */
public class CheckPersonDaoImpl extends BaseDao implements CheckPersonDao {
    @Override
    public int insertOneData(CheckPerson checkPerson) {
        String sql = "insert into check_person(person_id,check_status,check_time) " +
                "values(?,?,?)";
        return dbUtilsUpdate(sql,checkPerson.getPersonId(),checkPerson.getCheckStatus(),checkPerson.getCheckTime());
    }

    @Override
    public CheckPerson queryCheckUserById(int id) {
        String sql = "select `id`,`person_id` personId,`check_status` checkStatus,`user_id` userId,`check_time` checkTime from check_person where person_id = ?";
        return queryForOne(CheckPerson.class,sql,id);
    }

    @Override
    public int queryCheckPersonTotalCount() {
        String sql = "select count(*) from check_person ";
        return queryForSingleValue(sql).intValue();
    }

    @Override
    public List<CheckPerson> queryCheckPersonLimit(int begin, int pageSize, String param) {
        String sql = "select id,person_id personId,check_status checkStatus,user_id userId,check_time checkTime,check_opinion checkOpinion " +
                "from check_person order by check_status desc limit ?,?";
        return queryForList(CheckPerson.class,sql,begin,pageSize);
    }

    @Override
    public int checkPerson(CheckPerson checkPerson) {
        String sql = "update check_person set check_status =?,user_id=?,check_time=?,check_opinion=? where person_id=?";
        return dbUtilsUpdate(sql,checkPerson.getCheckStatus(),checkPerson.getUserId(),checkPerson.getCheckTime(),checkPerson.getCheckOpinion(),checkPerson.getPersonId());
    }
}
