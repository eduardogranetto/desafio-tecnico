package br.jh.lembre.me.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.NOT_ACCEPTABLE, reason="Test")
public class ValidatorException extends RuntimeException{

	private static final long serialVersionUID = -7782240648050700016L;

}