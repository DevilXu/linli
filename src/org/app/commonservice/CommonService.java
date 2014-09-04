package org.app.commonservice;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
/**
 * 处理一些不需要经过controller但是需要使用后台参数的问题
 * @author josatokiri
 *
 */
@Controller
public class CommonService {
	/**
	 * 通用头部模版的访问路径
	 * @return
	 */
	@RequestMapping(value="header_common")
	public ModelAndView header_common(){
		return new ModelAndView("common/header");
	}
	/**
	 * 通用底部模版的访问路径
	 * @return
	 */
	@RequestMapping(value="footer_common")
	public ModelAndView footer_common(){
		return new ModelAndView("common/footer");
	}	
}
