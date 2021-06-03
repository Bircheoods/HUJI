package entity;

import java.sql.Date;

/**
 * @description �����ʵ����
 * @user summerHouAnNing
 * @creatTime 2021/4/21--1:45
 */
public class Notice {
    private Integer id;//����id
    private String noticeTitle;//�������
    private String noticeContent;//��������
    private String noticeTime;//��������
    private Integer releaseId;//������Ա�����û��������id

    public Notice() {
    }

    public Notice(Integer id, String noticeTitle, String noticeContent, String noticeTime, Integer releaseId) {
        this.id = id;
        this.noticeTitle = noticeTitle;
        this.noticeContent = noticeContent;
        this.noticeTime = noticeTime;
        this.releaseId = releaseId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNoticeTitle() {
        return noticeTitle;
    }

    public void setNoticeTitle(String noticeTitle) {
        this.noticeTitle = noticeTitle;
    }

    public String getNoticeContent() {
        return noticeContent;
    }

    public void setNoticeContent(String noticeContent) {
        this.noticeContent = noticeContent;
    }

    public String getNoticeTime() {
        return noticeTime;
    }

    public void setNoticeTime(String noticeTime) {
        this.noticeTime = noticeTime;
    }

    public Integer getReleaseId() {
        return releaseId;
    }

    public void setReleaseId(Integer releaseId) {
        this.releaseId = releaseId;
    }

    @Override
    public String toString() {
        return "Notice{" +
                "id=" + id +
                ", noticeTitle='" + noticeTitle + '\'' +
                ", noticeContent='" + noticeContent + '\'' +
                ", noticeTime=" + noticeTime +
                ", releaseId=" + releaseId +
                '}';
    }
}
