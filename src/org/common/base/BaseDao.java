package org.common.base;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ibatis.sqlmap.client.SqlMapClient;

public class BaseDao extends SqlMapClientDaoSupport{
	/**
	 * BaseDao,Dao需继承此Dao
	 * 
	 * @author archie2010 since 2011-3-3 下午10:52:36
	 */
    @Resource(name = "sqlMapClient")
    private SqlMapClient sqlMapClient;

    @PostConstruct
    public void initSqlMapClient() {
        super.setSqlMapClient(sqlMapClient);
    }
    /**
     * 对于新增操作的封装
     * @param sqlMap
     * @param entityClass
     * @return
     * @throws SQLException
     */
    public <T> Object insert(String sqlMap,Class<T> entityClass) throws SQLException{
    	return sqlMapClient.insert(sqlMap, entityClass);
    }
    /**
     * 删除已有数据的封装
     * @param sqlMap
     * @param entityClass
     * @return
     * @throws SQLException
     */
    public <T> Object delete(String sqlMap,T entityClass) throws SQLException{
    	return sqlMapClient.delete(sqlMap, entityClass);
    }
    /**
     * 更新已有数据的封装
     * @param sqlMap
     * @param entityClass
     * @return
     * @throws SQLException
     */
    public <T> Object update(String sqlMap,T entityClass) throws SQLException{
    	return sqlMapClient.update(sqlMap, entityClass);
    }
    /**
     * 搜索已有数据的封装
     * @param sqlMap
     * @param entityClass
     * @return
     * @throws SQLException
     */
    @SuppressWarnings("unchecked")
	public <T> T select(String sqlMap,T entityClass) throws SQLException{
    	return (T) sqlMapClient.queryForObject(sqlMap, entityClass);
    }
    /**
     * 搜索数据列表
     * @param sqlMap
     * @param entityClass
     * @return
     * @throws SQLException
     */
    @SuppressWarnings("unchecked")
	public <T> List<T> selectList(String sqlMap,T entityClass) throws SQLException{
    	return (List<T>) sqlMapClient.queryForList(sqlMap, entityClass);
    }
}
