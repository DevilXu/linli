package org.common.base;

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
}
