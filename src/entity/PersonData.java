package entity;

/**
 * @description ��Ա����ʵ����
 * @user summerHouAnNing
 * @creatTime 2021/4/21--1:33
 */
public class PersonData {
    private Integer id;//����id
    private Integer personId;//��Աid
    private String personData;//����·��

    //�ղι�����
    public PersonData() {
    }

    //���ι�����

    public PersonData(Integer id, Integer personId, String personData) {
        this.id = id;
        this.personId = personId;
        this.personData = personData;
    }

    //��Ӧ��get��set����

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

    //��дtoString����

    @Override
    public String toString() {
        return "PersonData{" +
                "id=" + id +
                ", personId=" + personId +
                ", personData='" + personData + '\'' +
                '}';
    }
}
