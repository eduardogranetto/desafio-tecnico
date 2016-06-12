package br.com.desafiotecnico.util;

import org.thymeleaf.util.StringUtils;

public final class UrlUtils {

	public static final String removePrefix(final String url){
		return StringUtils.replace(url, "http://", "")
		.replace("https://", "");
	}
	
}