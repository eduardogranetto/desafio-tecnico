package br.com.desafiotecnico.config;

import java.util.List;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;

import br.com.desafiotecnico.controller.api.handler.CustomHandlerMethodArgumentResolver;
import br.com.desafiotecnico.interceptor.LayoutInterceptor;

@SpringBootApplication(scanBasePackages={WebConfig.DEFAULT_PACKAGE})
public class WebConfig extends WebMvcConfigurerAdapter {
	
	public static final String DEFAULT_PACKAGE = "br.com.desafiotecnico";
	
	private static final String RESOURCES = "/resources/**";
	private static final String[] CLASSPATH_RESOURCE_LOCATIONS = {
	        "classpath:/META-INF/resources/", "classpath:/resources/",
	        "classpath:/static/", "classpath:/public/" 
	        };
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
	    registry.addResourceHandler(RESOURCES).addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS);
	}
	
	@Bean
    public Java8TimeDialect java8TimeDialect() {
        return new Java8TimeDialect();
    }
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new LayoutInterceptor());
	}
	
	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
		argumentResolvers.add(new CustomHandlerMethodArgumentResolver());
	}
	
}