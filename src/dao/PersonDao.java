package dao;

import entity.Area;
import entity.Person;

import java.util.List;

public interface PersonDao {

    /**
     * @description: 新添加一条记录
     * @author: summerHouAnNing
     * @creatTime: 2021/4/22--3:41
     * @Param:
     * @return:
     **/
    public int insertOnePerson(Person person);

    /**
     * @description: 根据id查找人员
     * @author: summerHouAnNing
     * @creatTime: 2021/4/22--3:41
     * @Param:
     * @return:
     **/
    public Person queryPersonById(Integer id);

    /**
     * @description: 根据身份正好查找人员
     * @author: summerHouAnNing
     * @creatTime: 2021/4/22--3:41
     * @Param:
     * @return:
     **/
    public Person queryPersonByIDNum(String idNum);

    /**
     * @description: 根据id删除一条记录
     * @author: summerHouAnNing
     * @creatTime: 2021/4/22--3:41
     * @Param:
     * @return:
     **/
    public int deletePersonById(Integer id);

    /**
     * @description: 根据id修改人员状态
     * @author: summerHouAnNing
     * @creatTime: 2021/4/22--3:42
     * @Param:
     * @return:
     **/
    public int updatePersonStatus(Person person);

    /**
     * @description: 查找所有人员记录
     * @author: summerHouAnNing
     * @creatTime: 2021/4/22--3:42
     * @Param:
     * @return:
     **/
    public List<Person> queryAllPerson();

    /**
     * @description: 根据地区查找人员信息
     * @author: summerHouAnNing
     * @creatTime: 2021/4/22--3:42
     * @Param:
     * @return:
     **/
    public List<Person> queryPersonByPlace(String place);

    /**
     * @description: 根据父地区和爷爷地区查找地区信息（用于级联操作）
     * @author: summerHouAnNing
     * @creatTime: 2021/4/26--9:13
     * @Param:
     * @return:
     **/
    public List<Area> queryArea(String fatherNum, String grandfatherNum);

    /**
     * @description: 查找具体某个地区
     * @author: summerHouAnNing
     * @creatTime: 2021/4/27--9:17
     * @Param:
     * @return:
     **/
    public Area queryOneArea(String fatherNum, String grandfatherNum, String num);

    /**
     * @description: 根据用户名和身份证号查询人员
     * @author: summerHouAnNing
     * @creatTime: 2021/4/27--14:30
     * @Param:
     * @return:
     **/
    public Person queryPersonByNameAndNum(String name, String num);

    /**
     * @description: 修改人员户籍地
     * @author: summerHouAnNing
     * @creatTime: 2021/5/4--11:58
     * @Param:
     * @return:
     **/
    public int updatePersonNativePlace(Person person);
}
