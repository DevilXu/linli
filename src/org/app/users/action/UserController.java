package org.app.users.action;


import java.util.ArrayList;
import java.util.List;

import org.common.base.AbstractBaseController;
import org.common.encrypt.EncrypDES3;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
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
			ModelAndView mav = new ModelAndView("redirect:toIndex.action");
			user=userService.selectUser(user);
			if(user!=null){
				this.getSession().setAttribute("user", user);
				User user1=new User();
				listUser=userService.selectUserList(user1,true);
				mav.addObject("listUser", listUser);
				mav.addObject("pages", user1);
				return mav;
			}
		}
		return new ModelAndView("login");
	}
	
	@RequestMapping(value="toIndex")
	public ModelAndView toIndex() throws Exception {  
		return new ModelAndView("index");
	}  
	
	@RequestMapping(value="getList")
	public void getList(User user) throws Exception {  
		listUser=userService.selectUserList(user,false);
		this.outputAjaxJsonData(listUser);
	}  
	@RequestMapping(value="registerPage")
	public ModelAndView registerPage(){
		return new ModelAndView("register");
	}
	@RequestMapping(value="register")
	public ModelAndView register(@ModelAttribute(" user ") User user) throws Exception{
		userService.insertUser(user);
		return new ModelAndView("login");
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
