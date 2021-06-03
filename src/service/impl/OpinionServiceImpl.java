package service.impl;

import dao.OpinionDao;
import dao.impl.OpinionDaoImpl;
import entity.Opinion;
import entity.Page;
import service.OpinionService;

import java.util.List;

/**
 * @description
 * @user summerHouAnNing
 * @creatTime 2021/4/21--2:58
 */
public class OpinionServiceImpl implements OpinionService {

    private OpinionDao opinionDao = new OpinionDaoImpl();

    @Override
    public int insertOpinion(Opinion opinion) {
        return opinionDao.insertOpinion(opinion);
    }

    @Override
    public Page<Opinion> queryAllOpinion(int pageNo, int pageSize, String param,int opinionId) {

        Page<Opinion> page = new Page<>();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        //查询总的记录数
        int pageTotalCount = opinionDao.queryOpinionCountById(opinionId);
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

        //查询当前页的数据
        List<Opinion> opinions = opinionDao.queryOpinionByPage(begin, pageSize, opinionId, param);

        page.setItems(opinions);
        return page;
    }

    @Override
    public int deleteOpinion(int opinionId) {
        return opinionDao.deleteOpinion(opinionId);
    }

    @Override
    public Opinion queryOneOpinionById(int parseInt) {
        return opinionDao.queryOneOpinionById(parseInt);
    }

    @Override
    public int updateOpinion(Opinion opinion) {
        return opinionDao.updateOpinion(opinion);
    }

    @Override
    public Page<Opinion> queryAllOpinion(int pageNo, int pageSize, String param) {
        Page<Opinion> page = new Page<>();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        //查询总的记录数
        int pageTotalCount = opinionDao.queryOpinionCountById();
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

        //查询当前页的数据
        List<Opinion> opinions = opinionDao.queryOpinionByPage(begin, pageSize, param);

        page.setItems(opinions);
        return page;
    }

    @Override
    public int checkOpinion(Opinion opinion) {
        return opinionDao.checkOpinion(opinion);
    }
}
