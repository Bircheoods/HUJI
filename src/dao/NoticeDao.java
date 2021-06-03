package dao;

import entity.Notice;

import java.util.List;

public interface NoticeDao {
    /**
     * @description: 添加一条公告
     * @author: summerHouAnNing
     * @creatTime: 2021/4/29--13:06
     * @Param:
     * @return:
     **/
    public int insertNotice(Notice notice);

    /**
     * @description: 查询所有公告信息
     * @author: summerHouAnNing
     * @creatTime: 2021/4/29--13:34
     * @Param:
     * @return:
     **/
    public List<Notice> queryAllNotice(int begin, int pageSize,String param);

    /**
     * @description: 获取公告的总记录数
     * @author: summerHouAnNing
     * @creatTime: 2021/4/30--14:18
     * @Param:
     * @return:
     **/
    public Integer getPageTotalCount();

    /**
     * @description: 根据id删除一条公告
     * @author: summerHouAnNing
     * @creatTime: 2021/5/3--2:05
     * @Param:
     * @return:
     **/

    public int deleteOneNoticeById(String noticeId);
    /**
    *@description: 根据id查找一条哦公告
    *@author: summerHouAnNing
    *@creatTime: 2021/5/4--3:04
    *@Param:
    *@return:
    **/
    public Notice findNoticeById(int id);
    /**
    *@description: 修改一条公告的信息
    *@author: summerHouAnNing
    *@creatTime: 2021/5/4--3:05
    *@Param:
    *@return:
    **/
    public int updateNoticeById(Notice notice);

}
