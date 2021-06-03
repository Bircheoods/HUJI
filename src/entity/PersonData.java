package entity;

/**
 * @description 人员资料实体类
 * @user summerHouAnNing
 * @creatTime 2021/4/21--1:33
 */
public class PersonData {
    private Integer id;//主键id
    private Integer personId;//人员id
    private String personData;//资料路径

    //空参构造器
    public PersonData() {
    }

    //带参构造器

    public PersonData(Integer id, Integer personId, String personData) {
        this.id = id;
        this.personId = personId;
        this.personData = personData;
    }

    //相应的get、set方法

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public String getPersonData() {
        return personData;
    }

    public void setPersonData(String personData) {
        this.personData = personData;
    }

    //重写toString方法

    @Override
    public String toString() {
        return "PersonData{" +
                "id=" + id +
                ", personId=" + personId +
                ", personData='" + personData + '\'' +
                '}';
    }
}
