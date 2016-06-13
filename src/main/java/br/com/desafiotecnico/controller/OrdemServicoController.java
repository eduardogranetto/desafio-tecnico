package br.com.desafiotecnico.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.desafiotecnico.model.OrdemServico;
import br.com.desafiotecnico.service.ClienteService;
import br.com.desafiotecnico.service.OrdemServicoService;
import br.com.desafiotecnico.service.ServicoService;

@Controller
@RequestMapping("/ordens_servico")
public class OrdemServicoController {

	private static final String FORM = "ordens_servico/form";
	private static final String REMOVER = "ordens_servico/remover";
	private static final String VISUALIZAR = "ordens_servico/visualizar";
	private static final String EXIBIR = "redirect:/ordens_servico/";

	@Autowired
	private OrdemServicoService ordemServicoService;
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private ServicoService servicoService;
	
	@RequestMapping(value="/novo", method=RequestMethod.GET)
	public ModelAndView novo(@RequestParam(name="id_cliente", required=false) Long clienteId, 
			@RequestParam(name="id_servico", required=false) Long servicoId){
		return form(ordemServicoService.criar(clienteId, servicoId));
	}
	
	@RequestMapping(value="/salvar", method=RequestMethod.POST)
	public ModelAndView salvar(@Valid @ModelAttribute OrdemServico ordemServico, BindingResult result, RedirectAttributes attributes){
		if(result.hasErrors()){
			return form(ordemServico);
		}
		ordemServicoService.salvar(ordemServico);
		attributes.addFlashAttribute("msgSuccess", "Ordem de serviço gerada com sucesso!");
		return new ModelAndView(EXIBIR + ordemServico.getId());
	}

	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ModelAndView visualizar(@PathVariable(value="id") Long id){
		return new ModelAndView(VISUALIZAR).addObject("ordemServico", ordemServicoService.buscarPorId(id)); 
	}

	
	@RequestMapping(value="/{id}/editar", method=RequestMethod.GET)
	public ModelAndView editar(@PathVariable(value="id") Long id){
		return form(ordemServicoService.buscarPorId(id)); 
	}
	
	@RequestMapping(value="/{id}/remover", method=RequestMethod.GET)
	public ModelAndView remover(@PathVariable(value="id") Long id){
		return new ModelAndView(REMOVER).addObject("ordemServico", ordemServicoService.buscarPorId(id));
	}

	@RequestMapping(value="/{id}/remover", method=RequestMethod.DELETE)
	public ModelAndView excluir(@PathVariable(value="id") Long id, RedirectAttributes redirectAttributes){
		redirectAttributes.addFlashAttribute("msgSuccess", "Ordem de serviço excluída com sucesso!");
		ordemServicoService.remover(id);
		return new ModelAndView("redirect:/");
	}
	
	private ModelAndView form(OrdemServico ordemServico) {
		return new ModelAndView(FORM)
				.addObject("ordemServico", ordemServico)
				.addObject("clientes", clienteService.buscarTodos())
				.addObject("servicos", servicoService.buscarTodos());
	}
	
}