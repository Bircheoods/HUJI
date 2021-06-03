package entity;

import java.util.Date;

/**
 * @description user表对应的实体类
 * @user summerHouAnNing
 * @creatTime 2021/4/21--1:28
 */
public class User {
    private Integer id;
    private Integer userId;
    private String phone;
    private String email;
    private Integer power;
    private String registeredTime;
    private String userProblem;
    private String userPassword;
    private String userAnswer;

    public User() {
    }

    public User(Integer id, Integer userId, String phone, String email, Integer power, String registeredTime, String userProblem, String userPassword, String userAnswer) {
        this.id = id;
        this.userId = userId;
        this.phone = phone;
        this.email = email;
        this.power = power;
        this.registeredTime = registeredTime;
        this.userProblem = userProblem;
        this.userPassword = userPassword;
        this.userAnswer = userAnswer;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getPower() {
        return power;
    }

    public void setPower(Integer power) {
        this.power = power;
    }

    public String getRegisteredTime() {
        return registeredTime;
    }

    public void setRegisteredTime(String registeredTime) {
        this.registeredTime = registeredTime;
    }

    public String getUserProblem() {
        return userProblem;
    }

    public void setUserProblem(String userProblem) {
        this.userProblem = userProblem;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserAnswer() {
        return userAnswer;
    }

    public void setUserAnswer(String userAnswer) {
        this.userAnswer = userAnswer;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userId=" + userId +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", power=" + power +
                ", registeredTime=" + registeredTime +
                ", userProblem='" + userProblem + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userAnswer='" + userAnswer + '\'' +
                '}';
    }
}
