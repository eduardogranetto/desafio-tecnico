package br.com.desafiotecnico.interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContextUtils;

public class LayoutInterceptor implements HandlerInterceptor {

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse arg1, Object arg2, ModelAndView modelAndView)
			throws Exception {
		Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);
		if(inputFlashMap != null){
			modelAndView.addObject("msgSuccess", inputFlashMap.get("msgSuccess"));
		}
	}

	@Override
	public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2) throws Exception {
		return true;
	}

}
