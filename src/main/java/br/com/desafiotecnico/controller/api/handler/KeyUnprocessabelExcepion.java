package br.com.desafiotecnico.controller.api.handler;

import org.springframework.http.HttpStatus;

public class KeyUnprocessabelExcepion extends HttpException {

	private static final long serialVersionUID = 2180596159043457971L;

	public KeyUnprocessabelExcepion(String key) {
		super(key);
	}

	@Override
	public HttpStatus httpStatus() {
		return HttpStatus.UNPROCESSABLE_ENTITY;
	}

}
