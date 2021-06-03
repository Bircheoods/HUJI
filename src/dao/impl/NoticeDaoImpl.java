package dao.impl;

import dao.NoticeDao;
import entity.Notice;

import java.util.List;

/**
 * @description
 * @user summerHouAnNing
 * @creatTime 2021/4/21--2:52
 */
public class NoticeDaoImpl extends BaseDao implements NoticeDao {
    @Override
    public int insertNotice(Notice notice) {
        String sql = "insert into notice(notice_title,notice_content,notice_time,release_id) values(?,?,now(),?)";
        return dbUtilsUpdate(sql,notice.getNoticeTitle(),notice.getNoticeContent(),notice.getReleaseId());
    }

    @Override
    public List<Notice> queryAllNotice(int begin,int pageSize,String param) {
        String sql = "select id,notice_title noticeTitle,notice_content noticeContent,notice_time noticeTime,release_id releaseId" +
                " from notice where notice_title like ? or notice_content like ?" +
                " order by id desc " +
                " limit ?,?";
        return queryForList(Notice.class,sql,"%"+param+"%","%"+param+"%",begin,pageSize);
    }

    @Override
    public Integer getPageTotalCount() {
        String sql = "select count(*) from notice";
        return queryForSingleValue(sql).intValue();
    }

    @Override
    public int deleteOneNoticeById(String noticeId) {
        String sql = "delete from notice where id = ?";
        return dbUtilsUpdate(sql,noticeId);
    }

    @Override
    public Notice findNoticeById(int id) {
        String sql = "select id,notice_title noticeTitle,notice_content noticeContent,notice_time noticeTime,release_id releaseId" +
                " from notice " +
                "where id = ?";
        return queryForOne(Notice.class,sql,id);
    }

    @Override
    public int updateNoticeById(Notice notice) {
        String sql = "update notice set notice_title = ?,notice_content = ? where id = ?";
        return dbUtilsUpdate(sql,notice.getNoticeTitle(),notice.getNoticeContent(),notice.getId());
    }

}
