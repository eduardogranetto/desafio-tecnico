package br.com.desafiotecnico.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.desafiotecnico.service.ClienteService;

@Controller
@RequestMapping("/clientes")
public class ClienteController {
	
	private static final String INDEX = "clientes/index";
	
	@Autowired
	private ClienteService clienteService;

	@RequestMapping(value = "/clientes", method = RequestMethod.GET)
	public ModelAndView index(){
		return new ModelAndView(INDEX).addObject("clientes", clienteService.buscarTodos());
	}
	
}