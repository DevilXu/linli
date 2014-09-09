package org.app.users.service;

import java.util.List;

import org.app.users.bean.User;

public interface UserService{
	/**
	 * 搜索用户对象
	 * @param user
	 * @return
	 */
	public User selectUser(User user);
	/**
	 * 获取用户列表信息
	 * @param user
	 * @return
	 */
	public List<User> selectUserList(User user,boolean isPage);
}
