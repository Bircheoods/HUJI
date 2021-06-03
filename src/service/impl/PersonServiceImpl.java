package service.impl;

import dao.PersonDao;
import dao.impl.PersonDaoImpl;
import entity.Area;
import entity.Page;
import entity.Person;
import service.PersonService;

import java.util.List;

/**
 * @description
 * @user summerHouAnNing
 * @creatTime 2021/4/21--2:58
 */
public class PersonServiceImpl implements PersonService {
    private PersonDao personDao = new PersonDaoImpl();
    @Override
    public Person queryOnePersonByIdNum(String idNum) {
        Person person = personDao.queryPersonByIDNum(idNum);
        return person;
    }

    @Override
    public List<Area> queryArea(String fatherNum, String grandfatherNum) {
        return personDao.queryArea(fatherNum,grandfatherNum);
    }

    @Override
    public int insertOnePerson(Person person) {
        return personDao.insertOnePerson(person);
    }

    @Override
    public Area queryOneArea(String fatherNum, String grandfatherNum, String num) {
        return personDao.queryOneArea(fatherNum,grandfatherNum,num);
    }

    @Override
    public Person queryPersonByNameAndNum(String name, String num) {
        return personDao.queryPersonByNameAndNum(name,num);
    }

    @Override
    public int updatePersonStatus(Person person) {
        return personDao.updatePersonStatus(person);
    }

    @Override
    public Person queryPersonById(Integer id) {
        return personDao.queryPersonById(id);
    }

    @Override
    public int updatePersonNativePlace(Person person) {
        return personDao.updatePersonNativePlace(person);
    }

}
