package br.com.desafiotecnico.controller.api.handler;

import static java.text.MessageFormat.format;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomRestExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(HttpException.class)
	public ResponseEntity<ResponseError> handlerHttpException(HttpException ex){
		return new ResponseEntity<>(new ResponseError(format(ex.getMessage(), ex.getKey())), ex.httpStatus());
	}
	
}