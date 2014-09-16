package org.app.users.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.app.users.bean.User;
import org.app.users.service.UserService;
import org.common.base.BaseDao;
import org.common.base.Util;
import org.common.redis.RedisClientTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
@Repository
public class UserServiceImpl extends BaseDao implements UserService{
	@Autowired
	private RedisClientTemplate redisClientTemplate;
	@Override
	public User selectUser(User user) {
		// TODO Auto-generated method stub
		try {
			User result=(User) Util.unserialize(redisClientTemplate.get(getRedisKey(user)));
			if(result==null){
				result=(User) this.select("selectDemo", user);
			}
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<User> selectUserList(User user,boolean isPage) {
		// TODO Auto-generated method stub
		List<User> listUser=new ArrayList<User>();
		try {
			if(redisClientTemplate.get(Util.serialize(user))==null){
				listUser=this.selectList("selectDemo",user,isPage);
				redisClientTemplate.set(Util.serialize(user), Util.serialize(listUser));
			}
			else{
				listUser=(List<User>) Util.unserialize(redisClientTemplate.get(Util.serialize(user)));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listUser;
	}

	@Override
	public long insertUser(User user) throws Exception {
		// TODO Auto-generated method stub
		long i=(Long) this.insert("insertUser", user);
		user.setId(i);
		if(i>0){
			redisClientTemplate.set(getRedisKey(user),Util.serialize(user));
		}
		return i;
	}
	/***
	 * 获取username+password的redis中存储的key
	 * @param user
	 * @return
	 */
	public byte[] getRedisKey(User user){
		return (user.getUsername()+":"+user.getPassword()).getBytes();
	}
}	
