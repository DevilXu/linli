package org.app.map;



import org.common.base.AbstractBaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class BaiduMap extends AbstractBaseController{
	@RequestMapping("baiduMapPage")
	public String baiduMapPage(){
		return "baidumap/index";//会跳转页面
	}
	
}
