package org.common.base;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.common.ibatis.LimitSqlExecutor;
import org.common.ibatis.ReflectUtil;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.engine.execution.SqlExecutor;
import com.ibatis.sqlmap.engine.impl.ExtendedSqlMapClient;

public abstract class BaseDao extends SqlMapClientDaoSupport{
	/**
	 * BaseDao,Dao需继承此Dao
	 */
    @Resource
    private SqlMapClient sqlMapClient;
    
    @Resource
    private SqlExecutor sqlExecutor;  
    
  
    public void setEnableLimit(boolean enableLimit) {  
        if (sqlExecutor instanceof LimitSqlExecutor) {  
            ((LimitSqlExecutor) sqlExecutor).setEnableLimit(enableLimit);  
        }  
    }  
    @PostConstruct
    public void initialize() throws Exception { 
    	super.setSqlMapClient(sqlMapClient);
        if (sqlExecutor != null) {    
            if (sqlMapClient instanceof ExtendedSqlMapClient) {  
                ReflectUtil.setFieldValue(((ExtendedSqlMapClient) sqlMapClient).getDelegate(), "sqlExecutor", SqlExecutor.class,sqlExecutor);  
            }  
        }  
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
	public <T> List<T> selectList(String sqlMap,T entityClass,boolean isPage) throws SQLException{
    	if(isPage){
    		return (List<T>) sqlMapClient.queryForList(sqlMap, entityClass,0,1);
    	}
    	return (List<T>) sqlMapClient.queryForList(sqlMap, entityClass);
    }
}
