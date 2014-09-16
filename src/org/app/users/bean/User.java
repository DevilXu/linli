package org.app.users.bean;

import java.io.Serializable;

import org.common.base.BaseBean;
import org.springframework.stereotype.Component;
@Component
public class User extends BaseBean implements Serializable{
	private Long id;
	private String username;
	private String password;
	private String email;
	private String sex;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	
	
}
