package entity;

import java.sql.Date;

/**
 * @description Ǩ���ʵ
 * @user summerHouAnNing
 * @creatTime 2021/4/21--1:47
 */
public class Migration {
    private Integer id;//����id
    private Integer personId;//Ǩ����Աid
    private String migrationType;//Ǩ�����ͣ�0Ǩ��1Ǩ����
    private String migrationAddress;//Ǩ���ַ
    private String migrationTime;//Ǩ������
    private Integer userId;//�����Ա�����û��������id
    private String checkTime;//�������
    private String checkOpinion;

    public Migration() {
    }

    public Migration(Integer id, Integer personId, String migrationType, String migrationAddress, String migrationTime, Integer userId, String checkTime, String checkOpinion) {
        this.id = id;
        this.personId = personId;
        this.migrationType = migrationType;
        this.migrationAddress = migrationAddress;
        this.migrationTime = migrationTime;
        this.userId = userId;
        this.checkTime = checkTime;
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

    public String getMigrationType() {
        return migrationType;
    }

    public void setMigrationType(String migrationType) {
        this.migrationType = migrationType;
    }

    public String getMigrationAddress() {
        return migrationAddress;
    }

    public void setMigrationAddress(String migrationAddress) {
        this.migrationAddress = migrationAddress;
    }

    public String getMigrationTime() {
        return migrationTime;
    }

    public void setMigrationTime(String migrationTime) {
        this.migrationTime = migrationTime;
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

    @Override
    public String toString() {
        return "Migration{" +
                "id=" + id +
                ", personId=" + personId +
                ", migrationType='" + migrationType + '\'' +
                ", migrationAddress='" + migrationAddress + '\'' +
                ", migrationTime='" + migrationTime + '\'' +
                ", userId=" + userId +
                ", checkTime=" + checkTime +
                ", checkOpinion='" + checkOpinion + '\'' +
                '}';
    }
}
