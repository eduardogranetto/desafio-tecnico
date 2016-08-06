package br.com.desafiotecnico.controller.api.handler;

import org.springframework.http.HttpStatus;

public class KeyBadRequestException extends HttpException {

	private static final long serialVersionUID = -7010574978269239137L;

	public KeyBadRequestException(String key) {
		super("BAD REQUEST");
	}

	@Override
	public HttpStatus httpStatus() {
		return HttpStatus.BAD_REQUEST;
	}
}
