package org.app.users.service.impl;

import java.util.List;

import org.common.base.BaseDao;
import org.springframework.stereotype.Repository;
import org.app.users.bean.User;
import org.app.users.service.UserService;
@Repository
public class UserServiceImpl extends BaseDao implements UserService{
	/**
     * 查询
     * 
     * @param uid
     * @return
     */
    public List<User> getUserByUid() {
        return getSqlMapClientTemplate().queryForList("selectDemo");
    }

}	
