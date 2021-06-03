package dao.impl;

import dao.MigrationDao;
import entity.Migration;

import java.util.List;

/**
 * @description
 * @user summerHouAnNing
 * @creatTime 2021/4/21--2:53
 */
public class MigrationDaoImpl extends BaseDao implements MigrationDao {
    @Override
    public int insertMigration(Migration migration) {
        String sql = "insert into migration(person_id,migration_type,migration_address,migration_time) values(?,?,?,?)";
        return dbUtilsUpdate(sql,migration.getPersonId(),migration.getMigrationType(),migration.getMigrationAddress(),migration.getMigrationTime());
    }

    @Override
    public int queryAllMigrationCount() {
        String sql = "select count(*) from migration";
        return queryForSingleValue(sql).intValue();
    }

    @Override
    public List<Migration> queryAllMigration(int begin, int pageSize, String param) {
        String sql = "select id,person_id personId,migration_type migrationType,migration_address migrationAddress," +
                "migration_time migrationTime,user_id userId,check_time checkTime,check_opinion checkOpinion from migration " +
                "limit ?,?";
        return queryForList(Migration.class,sql,begin,pageSize);
    }

    @Override
    public Migration queryMigrationById(int migrationId) {
        String sql = "select id,person_id personId,migration_type migrationType,migration_address migrationAddress," +
                "migration_time migrationTime,user_id userId,check_time checkTime,check_opinion checkOpinion from migration " +
                "where id = ?";
        return queryForOne(Migration.class,sql,migrationId);
    }

    @Override
    public int updateMigration(Migration migration) {
        String sql = "update migration set check_time = ?,check_opinion = ?,user_id = ? where id = ?";
        return dbUtilsUpdate(sql,migration.getCheckTime(),migration.getCheckOpinion(),migration.getUserId(),migration.getId());
    }

}
