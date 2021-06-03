package service.impl;

import dao.NoticeDao;
import dao.impl.NoticeDaoImpl;
import entity.Notice;
import entity.Page;
import service.NoticeService;

import java.util.List;

/**
 * @description
 * @user summerHouAnNing
 * @creatTime 2021/4/21--3:00
 */
public class NoticeServiceImpl implements NoticeService {
    private NoticeDao noticeDao = new NoticeDaoImpl();

    @Override
    public int insertNotice(Notice notice) {
        return noticeDao.insertNotice(notice);
    }

    @Override
    public Page<Notice> queryNoticeNowPage(int pageNo, int pageSize,String param) {
        Page<Notice> page = new Page<Notice>();
        page.setPageNo(pageNo);//当前页码
        page.setPageSize(pageSize);//当前页显示的数量
        Integer pageTotalCount = noticeDao.getPageTotalCount();//求总记录数

//        System.out.println("当前页码："+pageNo);
//        System.out.println("当前总记录数："+pageTotalCount);

        page.setGetPageTotalCount(pageTotalCount);//设置总的记录数

        //求总页码
        int pageTotal =  pageTotalCount / pageSize;
        if ((pageTotalCount%pageSize) > 0)
            pageTotal+=1;
       // System.out.println(pageTotal);
        //设置总页码
        page.setPageTotal(pageTotal);


        //求当前页数据的开始索引
        int begin = (page.getPageNo() - 1) * pageSize;
        //获取总的notice

        List<Notice> notice = noticeDao.queryAllNotice(begin, pageSize,param);
//        for (Notice notice1 : notice) {
//            notice1.setNoticeContent(notice1.getNoticeContent().replaceAll("</?[a-zA-Z]+[^><]*>",""));
//        }
        //设置notice
        page.setItems(notice);
        return page;
    }

    @Override
    public int deleteOneNotice(String noticeId) {
        return noticeDao.deleteOneNoticeById(noticeId);
    }

    @Override
    public Notice findNoticeById(int id) {
        return noticeDao.findNoticeById(id);
    }

    @Override
    public int updateNoticeById(Notice notice) {
        return noticeDao.updateNoticeById(notice);
    }

}
