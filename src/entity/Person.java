package entity;

import java.sql.Date;

/**
 * @description
 * @user summerHouAnNing
 * @creatTime 2021/4/21--1:16
 */
public class Person {

    private Integer id;//����id
    private String name;//��Ա����
    private String oldName;//������
    private Integer sex;//�Ա�0Ů��1��
    private String nation;//����
    private Date birthday;//��������
    private String nativePlace;//����
    private String identityNum;//���֤��
    private String address;//��ǰסַ
    private String political;//������ò
    private String professional;//ְҵ
    private String education;//�Ļ��̶�
    private String religious;//�ڽ�����
    private String bloodType;//Ѫ��
    private Integer marriage;//�������
    private Integer militaryService;//�������
    private Integer status;//��ǰ״̬��0����1����Ĭ��Ϊ0��

    //�ղι�����
    public Person() {
    }
    //���ι�����
    public Person(Integer id, String name, String oldName, Integer sex, String nation, Date birthday, String nativePlace, String identityNum, String address, String political, String professional, String education, String religious, String bloodType, Integer marriage, Integer militaryService, Integer status) {
        this.id = id;
        this.name = name;
        this.oldName = oldName;
        this.sex = sex;
        this.nation = nation;
        this.birthday = birthday;
        this.nativePlace = nativePlace;
        this.identityNum = identityNum;
        this.address = address;
        this.political = political;
        this.professional = professional;
        this.education = education;
        this.religious = religious;
        this.bloodType = bloodType;
        this.marriage = marriage;
        this.militaryService = militaryService;
        this.status = status;
    }

    //��Ӧ��get��set����
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOldName() {
        return oldName;
    }

    public void setOldName(String oldName) {
        this.oldName = oldName;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getNativePlace() {
        return nativePlace;
    }

    public void setNativePlace(String nativePlace) {
        this.nativePlace = nativePlace;
    }

    public String getIdentityNum() {
        return identityNum;
    }

    public void setIdentityNum(String identityNum) {
        this.identityNum = identityNum;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPolitical() {
        return political;
    }

    public void setPolitical(String political) {
        this.political = political;
    }

    public String getProfessional() {
        return professional;
    }

    public void setProfessional(String professional) {
        this.professional = professional;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getReligious() {
        return religious;
    }

    public void setReligious(String religious) {
        this.religious = religious;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public Integer getMarriage() {
        return marriage;
    }

    public void setMarriage(Integer marriage) {
        this.marriage = marriage;
    }

    public Integer getMilitaryService() {
        return militaryService;
    }

    public void setMilitaryService(Integer militaryService) {
        this.militaryService = militaryService;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    //��дtoString����
    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", oldName='" + oldName + '\'' +
                ", sex=" + sex +
                ", nation='" + nation + '\'' +
                ", birthday=" + birthday +
                ", nativePlace='" + nativePlace + '\'' +
                ", identityNum='" + identityNum + '\'' +
                ", address='" + address + '\'' +
                ", political='" + political + '\'' +
                ", professional='" + professional + '\'' +
                ", education='" + education + '\'' +
                ", religious='" + religious + '\'' +
                ", bloodType='" + bloodType + '\'' +
                ", marriage=" + marriage +
                ", militaryService=" + militaryService +
                ", status=" + status +
                '}';
    }
}
