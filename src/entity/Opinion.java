package entity;

import java.sql.Date;

/**
 * @description �����ʵ����
 * @user summerHouAnNing
 * @creatTime 2021/4/21--1:39
 */
public class Opinion {
    private Integer id;//
    private String opinionTitle;//�������
    private String opinionContent;//�������
    private String opinionTime;//�ύ����
    private Integer opinionId;//�ύ��Աid
    private String solveTime;//��������
    private Integer solveId;//������Ա�����û��������id
    private String solveContent;

    public Opinion() {
    }

    public Opinion(Integer id, String opinionTitle, String opinionContent, String opinionTime, Integer opinionId, String solveTime, Integer solveId, String solveContent) {
        this.id = id;
        this.opinionTitle = opinionTitle;
        this.opinionContent = opinionContent;
        this.opinionTime = opinionTime;
        this.opinionId = opinionId;
        this.solveTime = solveTime;
        this.solveId = solveId;
        this.solveContent = solveContent;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOpinionTitle() {
        return opinionTitle;
    }

    public void setOpinionTitle(String opinionTitle) {
        this.opinionTitle = opinionTitle;
    }

    public String getOpinionContent() {
        return opinionContent;
    }

    public void setOpinionContent(String opinionContent) {
        this.opinionContent = opinionContent;
    }

    public String getOpinionTime() {
        return opinionTime;
    }

    public void setOpinionTime(String opinionTime) {
        this.opinionTime = opinionTime;
    }

    public Integer getOpinionId() {
        return opinionId;
    }

    public void setOpinionId(Integer opinionId) {
        this.opinionId = opinionId;
    }

    public String getSolveTime() {
        return solveTime;
    }

    public void setSolveTime(String solveTime) {
        this.solveTime = solveTime;
    }

    public Integer getSolveId() {
        return solveId;
    }

    public void setSolveId(Integer solveId) {
        this.solveId = solveId;
    }

    public String getSolveContent() {
        return solveContent;
    }

    public void setSolveContent(String solveContent) {
        this.solveContent = solveContent;
    }

    @Override
    public String toString() {
        return "Opinion{" +
                "id=" + id +
                ", opinionTitle='" + opinionTitle + '\'' +
                ", opinionContent='" + opinionContent + '\'' +
                ", opinionTime='" + opinionTime + '\'' +
                ", opinionId=" + opinionId +
                ", solveTime='" + solveTime + '\'' +
                ", solveId=" + solveId +
                ", solveContent='" + solveContent + '\'' +
                '}';
    }
}
