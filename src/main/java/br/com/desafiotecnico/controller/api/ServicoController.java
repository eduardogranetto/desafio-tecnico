package br.com.desafiotecnico.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.desafiotecnico.model.Servico;
import br.com.desafiotecnico.service.ServicoService;

@RequestMapping("/api")
@RestController("SERVICO_API_CONTROLLER")
public class ServicoController {

	@Autowired
	private ServicoService servicoService;
	
	@ResponseBody
	@GetMapping(value="/servicos", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Servico> listar(){
		return servicoService.buscarTodos();
	}
	
}