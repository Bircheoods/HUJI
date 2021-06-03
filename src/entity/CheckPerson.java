package entity;

import java.sql.Date;

/**
 * @description
 * @user summerHouAnNing
 * @creatTime 2021/4/21--1:53
 */
public class CheckPerson {
    private Integer id; //����id
    private Integer personId;//���˲���Աid
    private Integer checkStatus;//�˲�״̬��0������1��������
    private Integer userId;//�˲���Ա�����û��������id
    private String checkTime;
    private String checkOpinion;

    public CheckPerson() {
    }

    public CheckPerson(Integer id, Integer personId, Integer checkStatus, Integer userId, String checkTime, String checkOpinion) {
        this.id = id;
        this.personId = personId;
        this.checkStatus = checkStatus;
        this.userId = userId;
        this.checkTime = checkTime;
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

    public Integer getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(Integer checkStatus) {
        this.checkStatus = checkStatus;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(String checkTime) {
        this.checkTime = checkTime;
    }

    public String getCheckOpinion() {
        return checkOpinion;
    }

    public void setCheckOpinion(String checkOpinion) {
        this.checkOpinion = checkOpinion;
    }

    @Override
    public String toString() {
        return "CheckPerson{" +
                "id=" + id +
                ", personId=" + personId +
                ", checkStatus=" + checkStatus +
                ", userId=" + userId +
                ", checkTime='" + checkTime + '\'' +
                ", checkOpinion='" + checkOpinion + '\'' +
                '}';
    }
}
