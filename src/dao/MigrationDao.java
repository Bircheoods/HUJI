package dao;

import entity.Migration;

import java.util.List;

public interface MigrationDao {
    /**
    *@description: 向迁徙表中添加一条数据
    *@author: summerHouAnNing
    *@creatTime: 2021/5/6--15:35
    *@Param:
    *@return:
    **/
    public int insertMigration(Migration migration);

    public int queryAllMigrationCount();

    public List<Migration> queryAllMigration(int begin, int pageSize, String param);

    public Migration queryMigrationById(int migrationId);

    public int updateMigration(Migration migration);
}
