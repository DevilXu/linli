package org.common.exceptionHandler;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

/**
 * 统一异常处理
 * @author xlt 
 * @deprecated 可以通过增加各种异常类型进行 各类异常的捕获控制!
 */
public class MyExceptionHandler implements HandlerExceptionResolver {  
	@Override
	public ModelAndView resolveException(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3) {
		// TODO Auto-generated method stub
		ModelAndView mav = new ModelAndView("error");
        // 数据库语句错误 
        if(arg3 instanceof BadSqlGrammarException) {  
        	mav.addObject("error", "数据库操作异常!");
        }
        //空指针异常
        else if(arg3 instanceof NullPointerException){
        	mav.addObject("error", "参数空指针异常!");
        }
        else if(arg3 instanceof Exception){
        	mav.addObject("error", "出错啦!");
        }
		return mav; 
	}  
}  