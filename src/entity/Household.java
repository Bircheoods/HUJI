package entity;

import java.sql.Date;

/**
 * @description
 * @user summerHouAnNing
 * @creatTime 2021/4/21--1:51
 */
public class Household {
    private Integer id;//����������id
    private Integer personId;//��Աid
    private String householdNum;//������
    private String householdType;//��������
    private String householdRelation;//�뻧����ϵ
    private String checkTime;//�������
    private Integer checkId;//�����Ա�����û��������id
    private String checkOpinion;

    public Household() {
    }

    public Household(Integer id, Integer personId, String householdNum, String householdType, String householdRelation, String checkTime, Integer checkId, String checkOpinion) {
        this.id = id;
        this.personId = personId;
        this.householdNum = householdNum;
        this.householdType = householdType;
        this.householdRelation = householdRelation;
        this.checkTime = checkTime;
        this.checkId = checkId;
        this.checkOpinion = checkOpinion;
    }

    public String getCheckOpinion() {
        return checkOpinion;
    }

    public void setCheckOpinion(String checkOpinion) {
        this.checkOpinion = checkOpinion;
    }

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

    public String getHouseholdNum() {
        return householdNum;
    }

    public void setHouseholdNum(String householdNum) {
        this.householdNum = householdNum;
    }

    public String getHouseholdType() {
        return householdType;
    }

    public void setHouseholdType(String householdType) {
        this.householdType = householdType;
    }

    public String getHouseholdRelation() {
        return householdRelation;
    }

    public void setHouseholdRelation(String householdRelation) {
        this.householdRelation = householdRelation;
    }

    public String getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(String checkTime) {
        this.checkTime = checkTime;
    }

    public Integer getCheckId() {
        return checkId;
    }

    public void setCheckId(Integer checkId) {
        this.checkId = checkId;
    }

    @Override
    public String toString() {
        return "Household{" +
                "id=" + id +
                ", personId=" + personId +
                ", householdNum='" + householdNum + '\'' +
                ", householdType='" + householdType + '\'' +
                ", householdRelation='" + householdRelation + '\'' +
                ", checkTime='" + checkTime + '\'' +
                ", checkId=" + checkId +
                ", checkOpinion='" + checkOpinion + '\'' +
                '}';
    }
}
