package service.impl;

import dao.HouseholdDao;
import dao.impl.HouseholdDaoImpl;
import entity.Household;
import service.HouseholdService;

import java.util.List;

/**
 * @description
 * @user summerHouAnNing
 * @creatTime 2021/4/21--2:59
 */
public class HouseholdServiceImpl implements HouseholdService {
    private HouseholdDao householdDao = new HouseholdDaoImpl();
    @Override
    public Household queryListHouseholdById(int userId) {
        return householdDao.queryListHouseholdById(userId);
    }

    @Override
    public List<Household> queryListHouseholdByNum(String num) {
        return householdDao.queryListHouseholdByNum(num);
    }

    @Override
    public int insertOneHousehold(Household household) {
        return householdDao.insertOneHousehold(household);
    }

    @Override
    public int deleteOneData(int personId) {
        return householdDao.deleteOneData(personId);
    }

    @Override
    public int updatePersonHouseholdByUserId(Household household) {
        return householdDao.updatePersonHouseholdByUserId(household);
    }
}
