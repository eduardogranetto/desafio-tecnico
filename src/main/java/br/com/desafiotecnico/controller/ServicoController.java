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

import br.com.desafiotecnico.model.Servico;
import br.com.desafiotecnico.service.ServicoService;

@Controller
@RequestMapping("/servicos")
public class ServicoController{
	
	public static final String ATRIBUTO_SERVICO = "servico";
	public static final String ATRIBUTO_SERVICOS = "servicos";
	public static final String REDIRECT_INDEX = "redirect:/servicos/";
	public static final String INDEX = "servicos/index";
	public static final String FORM = "servicos/form";
	public static final String REMOVER = "servicos/remover";
	
	@Autowired
	private ServicoService servicoService;

	@RequestMapping(value="/", method=RequestMethod.GET)
	public ModelAndView index(){
		return new ModelAndView(INDEX).addObject(ATRIBUTO_SERVICOS, servicoService.buscarTodos());
	}
	
	@RequestMapping(value="/novo", method=RequestMethod.GET)
	public ModelAndView novo(){
		return form(new Servico()); 
	}

	@RequestMapping(value="/salvar", method=RequestMethod.POST)
	public ModelAndView salvar(@Valid @ModelAttribute Servico servico, BindingResult result, RedirectAttributes attributes){
		if(result.hasErrors()){
			return form(servico);
		}
		servicoService.salvar(servico);
		attributes.addFlashAttribute("msgSuccess", "Serviço salvo com sucesso!");
		return new ModelAndView(REDIRECT_INDEX);
	}

	@RequestMapping(value="/{id}/editar", method=RequestMethod.GET)
	public ModelAndView editar(@PathVariable(value="id") Long id){
		return form(servicoService.buscarPorId(id)); 
	}
	
	@RequestMapping(value="/{id}/remover", method=RequestMethod.GET)
	public ModelAndView remover(@PathVariable(value="id") Long id){
		return new ModelAndView(REMOVER).addObject(ATRIBUTO_SERVICO, servicoService.buscarPorId(id));
	}

	@RequestMapping(value="/{id}/remover", method=RequestMethod.DELETE)
	public ModelAndView excluir(@PathVariable(value="id") Long id, RedirectAttributes redirectAttributes){
		redirectAttributes.addFlashAttribute("msgSuccess", "Serviço removido com sucesso!");
		servicoService.remover(id);
		return new ModelAndView(REDIRECT_INDEX);
	}
	
	private ModelAndView form(Servico servico) {
		return new ModelAndView(FORM).addObject(ATRIBUTO_SERVICO, servico);
	}
	
}