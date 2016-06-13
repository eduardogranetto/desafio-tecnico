package br.com.desafiotecnico.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.desafiotecnico.model.Cliente;
import br.com.desafiotecnico.model.Tipo;
import br.com.desafiotecnico.service.ClienteService;

@Controller
@RequestMapping("/clientes")
public class ClienteController {
	
	private static final String REDIRECT_INDEX = "redirect:/clientes/";
	private static final String INDEX = "clientes/index";
	private static final String FORM = "clientes/form";
	private static final String REMOVER = "clientes/remover";
	private static final String ORDEM_SERVICO = "clientes/ordem_servico";
	
	@Autowired
	private ClienteService clienteService;

	@RequestMapping(value="/", method=RequestMethod.GET)
	public ModelAndView index(){
		return new ModelAndView(INDEX).addObject("clientes", clienteService.buscarTodos());
	}
	
	@RequestMapping(value="/novo", method=RequestMethod.GET)
	public ModelAndView novo(){
		return form(new Cliente()); 
	}

	@RequestMapping(value="/salvar", method=RequestMethod.POST)
	public ModelAndView salvar(@Valid @ModelAttribute Cliente cliente, BindingResult result, RedirectAttributes attributes){
		if(result.hasErrors()){
			return form(cliente);
		}
		clienteService.salvar(cliente);
		attributes.addFlashAttribute("msgSuccess", "Cliente salvo com sucesso!");
		return new ModelAndView(REDIRECT_INDEX);
	}

	@RequestMapping(value="/{id}/editar", method=RequestMethod.GET)
	public ModelAndView editar(@PathVariable(value="id") Long id){
		return form(clienteService.buscarPorId(id)); 
	}

	@RequestMapping(value="/{id}/ordens_servico", method=RequestMethod.GET)
	public ModelAndView ordensServico(@PathVariable(value="id") Long id){
		return new ModelAndView(ORDEM_SERVICO).addObject("cliente", clienteService.buscarPorId(id)); 
	}
	
	@RequestMapping(value="/{id}/remover", method=RequestMethod.GET)
	public ModelAndView remover(@PathVariable(value="id") Long id){
		return new ModelAndView(REMOVER).addObject("cliente", clienteService.buscarPorId(id));
	}

	@RequestMapping(value="/{id}/remover", method=RequestMethod.DELETE)
	public ModelAndView excluir(@PathVariable(value="id") Long id, RedirectAttributes redirectAttributes){
		redirectAttributes.addFlashAttribute("msgSuccess", "Cliente removido com sucesso!");
		clienteService.remover(id);
		return new ModelAndView(REDIRECT_INDEX);
	}
	
	private ModelAndView form(Cliente cliente) {
		return new ModelAndView(FORM).addObject("cliente", cliente).addObject("tipos", Tipo.values());
	}
	
}