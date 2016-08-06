package br.com.desafiotecnico.controller.api.handler;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class CustomHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver{

	@Override
	public Object resolveArgument(MethodParameter arg0, ModelAndViewContainer arg1, NativeWebRequest request,
			WebDataBinderFactory arg3) throws Exception {
		String path = request.getParameter("path");
		String upper = "";
		if(path != null){
			upper = path.toUpperCase();
		}
		return upper;
	}

	@Override
	public boolean supportsParameter(MethodParameter methodParameter) {
		return methodParameter.getParameterName().equals("upper");
	}

}