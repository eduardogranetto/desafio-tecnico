package br.com.desafiotecnico.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.desafiotecnico.service.OrdemServicoService;

@Controller
public class HomeController {

	private static final String ROOT = "home/index";
	
	@Autowired
	private OrdemServicoService ordemServicoService;
	
	@GetMapping(value="/")
	public ModelAndView home(){
		return new ModelAndView(ROOT).addObject("ordensServico", ordemServicoService.buscarTodos());
	}
	
}