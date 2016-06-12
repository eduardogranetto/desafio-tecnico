package br.com.desafiotecnico.config;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

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
	
}