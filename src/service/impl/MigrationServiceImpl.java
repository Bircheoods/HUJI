package service.impl;

import dao.MigrationDao;
import dao.impl.MigrationDaoImpl;
import entity.Migration;
import entity.Page;
import service.MigrationService;

import java.util.List;

/**
 * @description
 * @user summerHouAnNing
 * @creatTime 2021/4/21--2:59
 */
public class MigrationServiceImpl implements MigrationService {
    private MigrationDao migrationDao = new MigrationDaoImpl();
    @Override
    public int insertMigration(Migration migration) {
        return migrationDao.insertMigration(migration);
    }

    @Override
    public Page<Migration> queryAllMigration(int pageNo, int pageSize, String param) {
        Page<Migration> page = new Page<>();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);

        //查询总记录条数
        int pageTotalCount = migrationDao.queryAllMigrationCount();
        //设置总记录条数
        page.setGetPageTotalCount(pageTotalCount);
        //求总页数
        int pageTotal =  pageTotalCount / pageSize;
        if ((pageTotalCount%pageSize) > 0)
            pageTotal+=1;

        //设置总页码
        page.setPageTotal(pageTotal);

        //求当前页数据的开始索引
        int begin = (page.getPageNo()-1)*pageSize;

        //按条件查询数据
        List<Migration> migrations = migrationDao.queryAllMigration(begin, pageSize, param);
        page.setItems(migrations);

        return page;
    }

    @Override
    public Migration queryMigrationById(int migrationId) {
        return migrationDao.queryMigrationById(migrationId);
    }

    @Override
    public int updateMigration(Migration migration) {
        return migrationDao.updateMigration(migration);
    }

}
