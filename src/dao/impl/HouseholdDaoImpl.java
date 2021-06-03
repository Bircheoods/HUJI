package dao.impl;

import dao.HouseholdDao;
import entity.Household;

import java.util.List;

/**
 * @description
 * @user summerHouAnNing
 * @creatTime 2021/4/21--2:54
 */
public class HouseholdDaoImpl extends BaseDao implements HouseholdDao {
    @Override
    public Household queryListHouseholdById(int userId) {
        String sql = "select id,person_id personId,household_num householdNum,household_type householdType" +
                ",household_relation householdRelation,check_time checkTime,check_id checkId from household where person_id = ?";

        return queryForOne(Household.class,sql,userId);
    }

    @Override
    public List<Household> queryListHouseholdByNum(String num) {
        String sql = "select id,person_id personId,household_num householdNum,household_type householdType" +
                ",household_relation householdRelation,check_time checkTime,check_id checkId from household where household_num = ?";

        return queryForList(Household.class,sql,num);
    }

    @Override
    public int insertOneHousehold(Household household) {
        String sql = "insert into household(person_id,household_num,household_type,household_relation,check_time) values(?,?,?,?,?)";
        return dbUtilsUpdate(sql,household.getPersonId(),household.getHouseholdNum(),household.getHouseholdType(),household.getHouseholdRelation(),household.getCheckTime());
    }

    @Override
    public int deleteOneData(int personId) {
        String sql = "delete from household where person_id = ?";
        return dbUtilsUpdate(sql,personId);
    }

    @Override
    public int updatePersonHouseholdByUserId(Household household) {
        String sql = "update household set household_num = ?,household_type = ?,household_relation = ? where person_id = ?";
        return dbUtilsUpdate(sql,household.getHouseholdNum(),household.getHouseholdType(),household.getHouseholdRelation(),household.getPersonId());
    }
}
