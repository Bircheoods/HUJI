package service;

import entity.Notice;
import entity.Page;

import java.util.List;

public interface NoticeService {
    /**
     * @description: 添加一条公告
     * @author: summerHouAnNing
     * @creatTime: 2021/4/29--13:06
     * @Param:
     * @return:
     **/
    public int insertNotice(Notice notice);

    /**
     * @description: 获取当前页的公告
     * @author: summerHouAnNing
     * @creatTime: 2021/4/30--14:17
     * @Param: 参数是当前页码
     * @return:
     **/
    public Page<Notice> queryNoticeNowPage(int pageNo, int pageSize,String param);

    /**
     * @description: 根据id删除一条公告
     * @author: summerHouAnNing
     * @creatTime: 2021/5/3--2:04
     * @Param:
     * @return:
     **/

    public int deleteOneNotice(String noticeId);
    /**
    *@description: 根据id查找一条公告
    *@author: summerHouAnNing
    *@creatTime: 2021/5/4--2:50
    *@Param:
    *@return:
    **/
    public Notice findNoticeById(int id);
    /**
    *@description: 修改公告记录
    *@author: summerHouAnNing
    *@creatTime: 2021/5/4--2:51
    *@Param:
    *@return:
    **/
    public int updateNoticeById(Notice notice);

}
