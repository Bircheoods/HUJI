package entity;

import java.sql.Date;

/**
 * @description
 * @user summerHouAnNing
 * @creatTime 2021/4/21--9:48
 */
public class Result {
    private Integer id;
    private Integer personId;
    private String resultType;
    private String resultAnswer;
    private Date resultTime;
    private Date answerTime;
    private Integer answerId;

    public Result() {
    }

    public Result(Integer id, Integer personId, String resultType, String resultAnswer, Date resultTime, Date answerTime, Integer answerId) {
        this.id = id;
        this.personId = personId;
        this.resultType = resultType;
        this.resultAnswer = resultAnswer;
        this.resultTime = resultTime;
        this.answerTime = answerTime;
        this.answerId = answerId;
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

    public String getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }

    public String getResultAnswer() {
        return resultAnswer;
    }

    public void setResultAnswer(String resultAnswer) {
        this.resultAnswer = resultAnswer;
    }

    public Date getResultTime() {
        return resultTime;
    }

    public void setResultTime(Date resultTime) {
        this.resultTime = resultTime;
    }

    public Date getAnswerTime() {
        return answerTime;
    }

    public void setAnswerTime(Date answerTime) {
        this.answerTime = answerTime;
    }

    public Integer getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Integer answerId) {
        this.answerId = answerId;
    }

    @Override
    public String toString() {
        return "Result{" +
                "id=" + id +
                ", personId=" + personId +
                ", resultType='" + resultType + '\'' +
                ", resultAnswer='" + resultAnswer + '\'' +
                ", resultTime=" + resultTime +
                ", answerTime=" + answerTime +
                ", answerId=" + answerId +
                '}';
    }
}
