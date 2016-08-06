package br.com.desafiotecnico.controller.api.handler;

import org.springframework.http.HttpStatus;

public abstract class HttpException extends RuntimeException {

	private static final long serialVersionUID = -3862167406512773208L;
	
	public abstract HttpStatus httpStatus();
	
	private String key;
	
	public HttpException(String key) {
		super("Key {0}");
		this.key = key;
	}

	public String getKey() {
		return key;
	}
	
}
