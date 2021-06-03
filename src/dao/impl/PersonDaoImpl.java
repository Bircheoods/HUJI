package dao.impl;

import dao.PersonDao;
import entity.Area;
import entity.Person;

import java.util.List;

/**
 * @description
 * @user summerHouAnNing
 * @creatTime 2021/4/21--2:50
 */
public class PersonDaoImpl extends BaseDao implements PersonDao {
    @Override
    public int insertOnePerson(Person person) {
        String sql = "insert into person(`political`,`name`,`old_name`,`sex`,`nation`,`birthday`," +
                "`native_place`,`identity_num`,`address`,`professional`,`education`,`religious`," +
                "`blood_type`,`marriage`,`military_service`,`status`) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        return dbUtilsUpdate(sql,person.getPolitical(),person.getName(),person.getOldName(),person.getSex(),person.getNation(),person.getBirthday(),
                person.getNativePlace(),person.getIdentityNum(),person.getAddress(),person.getProfessional(),
                person.getEducation(),person.getReligious(),person.getBloodType(),person.getMarriage(),person.getMilitaryService(),person.getStatus());

    }

    @Override
    public Person queryPersonById(Integer id) {
        String sql = "select id,name,old_name oldName,sex,nation,birthday,native_place nativePlace,identity_num identityNum,address,political,professional,education,religious,blood_type bloodType,marriage,military_service militaryService,status from person where id = ?";
        return queryForOne(Person.class,sql,id);
    }

    @Override
    public Person queryPersonByIDNum(String idNum) {
        String sql = "select id,name,old_name oldName,sex,nation,birthday,native_place nativePlace,identity_num identityNum,address,political,professional,education,religious,blood_type bloodType,marriage,military_service militaryService,status from person where identity_num = ?";
        return queryForOne(Person.class,sql,idNum);
    }

    @Override
    public int deletePersonById(Integer id) {
        String sql = "delete from person where id = ?";
        return dbUtilsUpdate(sql,id);
    }

    @Override
    public int updatePersonStatus(Person person) {
        String sql = "update person set `status` = ? where `id` = ?";
        return dbUtilsUpdate(sql,person.getStatus(),person.getId());
    }

    @Override
    public List<Person> queryAllPerson() {
        String sql = "select id,name,old_name oldName,sex,nation,birthday,native_place nativePlace,identity_num identityNum,address,political,professional,education,religious,blood_type bloodType,marriage,military_service militaryService,status from person";
        return queryForList(Person.class,sql);
    }

    @Override
    public List<Person> queryPersonByPlace(String place) {
        String sql = "select id,name,old_name oldName,sex,nation,birthday,native_place nativePlace,identity_num identityNum,address,political,professional,education,religious,blood_type bloodType,marriage,military_service militaryService,status from person where native_place like %?%";
        return queryForList(Person.class,sql,place);
    }

    @Override
    public List<Area> queryArea(String fatherNum, String grandfatherNum) {
        String sql = "select id,area_id areaId,father_id fatherId,grandfather_id grandfatherId,area_name areaName from area where father_id = ? and grandfather_id = ?";
        return queryForList(Area.class,sql,fatherNum,grandfatherNum);
    }

    @Override
    public Area queryOneArea(String fatherNum, String grandfatherNum, String num) {
        //System.out.println("我被调用了");
        String sql = "select id,area_id areaId,father_id fatherId,grandfather_id grandfatherId,area_name areaName from area " +
                "where (father_id = ? and grandfather_id = ?) and area_id = ?";
        return queryForOne(Area.class,sql,fatherNum,grandfatherNum,num);
    }

    @Override
    public Person queryPersonByNameAndNum(String name, String num) {
        String sql = "select * from person where name = ? and identity_num = ?";
        return queryForOne(Person.class,sql,name,num);
    }

    @Override
    public int updatePersonNativePlace(Person person) {
        String sql = "update person set native_place = ? where id = ?";
        return dbUtilsUpdate(sql,person.getNativePlace(),person.getId());
    }
}
