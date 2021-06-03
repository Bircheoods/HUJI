package service;

import entity.Migration;
import entity.Page;

public interface MigrationService {
    /**
    *@description: 向迁徙表中新增一条数据
    *@author: summerHouAnNing
    *@creatTime: 2021/5/6--15:34
    *@Param:
    *@return:
    **/
    public int insertMigration(Migration migration);

    public Page<Migration> queryAllMigration(int pageNo, int pageSize, String param);

    public Migration queryMigrationById(int migrationId);

    public int updateMigration(Migration migration);
}
