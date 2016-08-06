package br.com.desafiotecnico.controller.api.handler;

import org.springframework.http.HttpStatus;

public class KeyNotFoundException extends HttpException {

	public KeyNotFoundException(String key) {
		super("Not found");
	}

	private static final long serialVersionUID = 1L;

	@Override
	public HttpStatus httpStatus() {
		return HttpStatus.NOT_FOUND;
	}
		
}
