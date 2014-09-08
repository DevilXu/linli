package org.app.users.service.impl;

import java.sql.SQLException;
import java.util.List;

import org.common.base.BaseDao;
import org.springframework.stereotype.Repository;
import org.app.users.bean.User;
import org.app.users.service.UserService;
@Repository
public class UserServiceImpl extends BaseDao implements UserService{
	@Override
	public User selectUser(User user) {
		// TODO Auto-generated method stub
		try {
			return (User) this.select("selectDemo", user);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<User> selectUserList(User user) {
		// TODO Auto-generated method stub
		try {
			return this.selectList("selectDemo",user);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}	
