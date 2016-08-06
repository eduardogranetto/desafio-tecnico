package br.com.desafiotecnico.controller.api.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.desafiotecnico.service.ClienteService;

@RestController
@RequestMapping("/api/handler")
public class ControllerTestHandler {

	@Autowired
	private ClienteService clienteService;
	
	@RequestMapping(value="/{key}", method=RequestMethod.GET)	
	public ResponseEntity<?> responseEntity(@PathVariable("key") String key, String upper){
		if(key.equals("BAD_REQUEST")){
			throw new KeyBadRequestException(key); 
		}
		if(key.equals("NOT_FOUND")){
			throw new KeyNotFoundException(key);
		}
		
		if(key.equals("UNPROCESSABLE_ENTITY")){
			throw new KeyUnprocessabelExcepion(key); 
		}
		if(upper != null){
			throw new KeyUnprocessabelExcepion(upper);
		}
		
		ResponseEntity<?> response = new ResponseEntity<>(clienteService.buscarTodos(), HttpStatus.OK);
		return response;
	}
	
}