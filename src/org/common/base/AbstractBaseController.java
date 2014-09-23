package org.common.base;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.alibaba.fastjson.JSONArray;

/***
 * controller 类中的基本对象及方法初始化
 * @author hegang
 *
 */
public abstract class AbstractBaseController {
	protected HttpServletRequest request ; 
	protected HttpServletResponse response ;
	protected HttpSession session ;
	protected ServletContext application;
	/**
	 * 初始话获取request,response,session对象
	 * @param request
	 * @param response
	 */
	@ModelAttribute
	public void initParam(HttpServletRequest request,HttpServletResponse response){
		this.request=request;
		this.response=response;
		this.session=request.getSession(true);
		this.application=this.session.getServletContext();
	}
	/**
	 * 将对象转化成json格式数据以ajax方式输出到页面
	 * @param Object
	 */
	protected void outputAjaxJsonData(Object obj)throws Exception{
		String json=null;
		if (obj == null){
			json = "[{\"success\":false}]"; 
		}else{			
			json = JSONArray.toJSONString(obj);		
		}

		this.getResponse().setContentType("text/json");
		this.getResponse().setCharacterEncoding("utf-8");
		this.getResponse().setHeader("Cache-Control","no-cache");
		this.getResponse().getWriter().println(json);
		this.getResponse().getWriter().close();
	}
	/**
	 * 将数据以ajax方式输出到页面
	 * @param String
	 */
	protected void outputAjaxJsonData(String outputString)throws Exception{ 		
		this.getResponse().setContentType("text/json");
		this.getResponse().setCharacterEncoding("utf-8");
		this.getResponse().setHeader("Cache-Control","no-cache");
		this.getResponse().getWriter().println(outputString);
		this.getResponse().getWriter().close();
		System.out.println(outputString);
	}	
	public HttpSession getSession() {
		return session;
	}
	public void setSession(HttpSession session) {
		this.session = session;
	}
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public HttpServletResponse getResponse() {
		return response;
	}
	public ServletContext getApplication() {
		return application;
	}
	public void setApplication(ServletContext application) {
		this.application = application;
	}	
}
