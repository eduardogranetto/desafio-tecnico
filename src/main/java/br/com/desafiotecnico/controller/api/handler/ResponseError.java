package br.com.desafiotecnico.controller.api.handler;

public class ResponseError {
	
	public String message;
	
	public ResponseError(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
	
}
