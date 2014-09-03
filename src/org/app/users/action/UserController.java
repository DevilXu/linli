package org.app.users.action;


import java.util.ArrayList;
import java.util.List;

import org.common.base.AbstractBaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.app.users.bean.User;
import org.app.users.service.UserService;
@Controller
public class UserController extends AbstractBaseController{
	@Autowired   
	private User user=new User();
	@Autowired   
	private UserService demoService;
	private List<User> listUser=new ArrayList<User>();
	@RequestMapping(value="login")
	public ModelAndView login(String username,String password){
		if(this.checkParams(new String[]{username,password})){
			ModelAndView mav = new ModelAndView("succ");
			mav.addObject("username",username);
			mav.addObject("password", password);
			this.getSession().setAttribute("user", username);
			listUser=demoService.getUserByUid();
			mav.addObject("listUser", listUser);
			return mav;
		}
		return new ModelAndView("login");
	}
	
	@RequestMapping(value="jump")
	public ModelAndView jump(String username,String password){
		return new ModelAndView("demo/index");
	}
	 
	@RequestMapping(value="getList")
	@ResponseBody 
	public void getList() throws Exception {  
		listUser=demoService.getUserByUid();
		this.outputAjaxJsonData(listUser);
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
