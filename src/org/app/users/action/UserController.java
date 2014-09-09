package org.app.users.action;


import java.util.ArrayList;
import java.util.List;

import org.common.base.AbstractBaseController;
import org.common.encrypt.EncrypDES3;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.app.users.bean.User;
import org.app.users.service.UserService;
@Controller
public class UserController extends AbstractBaseController{
	@Autowired   
	private UserService userService;
	private List<User> listUser=new ArrayList<User>();
	@RequestMapping(value="login")
	public ModelAndView login(@ModelAttribute(" user ") User user){
		if(this.checkParams(new String[]{user.getUsername(),user.getPassword()})){
			ModelAndView mav = new ModelAndView("index");
			user=userService.selectUser(user);
			if(user!=null){
				this.getSession().setAttribute("user", user);
				listUser=userService.selectUserList(user,true);
				mav.addObject("listUser", listUser);
				return mav;
			}
		}
		return new ModelAndView("login");
	}
	 
	@RequestMapping(value="getList")
	public void getList() throws Exception {  
		EncrypDES3 des3=new EncrypDES3("");
		String data=des3.Encrytor("123");
		this.outputAjaxJsonData("{\"data\":\""+data+"\"}");
	}  
	
	@RequestMapping(value="getListResult")
	public void getListResult(String data) throws Exception {  
		EncrypDES3 des3=new EncrypDES3("");
		this.outputAjaxJsonData("{\"data\":\""+des3.Decryptor(data)+"\"}");
	}  
	/**
	 * 用户登出方法
	 * @return
	 */
	@RequestMapping(value="signOut")
	public final ModelAndView signOut(){
		this.getSession().removeAttribute("user");
		this.getSession().invalidate();
		return new ModelAndView("login");
	}
	/***
	 * ��֤����
	 * @param params
	 * @return
	 */
	private boolean checkParams(String[] params){
		for(String param:params){
			if(param==""||param==null||param.isEmpty()){
				return false;
			}
		}
		return true;
	}
	/**
	 * 判断session中是否存在登录用户信息 保证登录的唯一性
	 * @param username
	 */
	private final void sessionControll(String username){
		if(username.equals(this.getSession().getAttribute("user"+username))){
			this.getSession().removeAttribute("user"+username);
			this.getSession().setAttribute("user"+username, username);
		}
	}
}
