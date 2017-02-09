package br.com.desafiotecnico.controller;

import static br.com.desafiotecnico.utils.Constantes.MENSAGEM_SUCESSO;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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

	public static final String ATRIBUTO_SERVICOS = "servicos";
	public static final String ATRIBUTO_CLIENTES = "clientes";
	public static final String ATTRIBUTO_ORDEM_SERVICO = "ordemServico";
	public static final String FORM = "ordens_servico/form";
	public static final String REMOVER = "ordens_servico/remover";
	public static final String VISUALIZAR = "ordens_servico/visualizar";
	public static final String EXIBIR = "redirect:/ordens_servico/";

	@Autowired
	private OrdemServicoService ordemServicoService;
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private ServicoService servicoService;
	
	@GetMapping(value="/novo")
	public ModelAndView novo(@RequestParam(name="id_cliente", required=false) Long clienteId, 
			@RequestParam(name="id_servico", required=false) Long servicoId){
		return form(ordemServicoService.criar(clienteId, servicoId));
	}
	
	@PostMapping(value="/salvar")
	public ModelAndView salvar(@Valid @ModelAttribute OrdemServico ordemServico, BindingResult result, RedirectAttributes attributes){
		if(result.hasErrors()){
			return form(ordemServico);
		}
		ordemServicoService.salvar(ordemServico);
		attributes.addFlashAttribute(MENSAGEM_SUCESSO, "Ordem de serviço gerada com sucesso!");
		return new ModelAndView(EXIBIR + ordemServico.getId());
	}

	@GetMapping(value="/{id}")
	public ModelAndView visualizar(@PathVariable(value="id") Long id){
		return new ModelAndView(VISUALIZAR).addObject(ATTRIBUTO_ORDEM_SERVICO, ordemServicoService.buscarPorId(id)); 
	}

	
	@GetMapping(value="/{id}/editar")
	public ModelAndView editar(@PathVariable(value="id") Long id){
		return form(ordemServicoService.buscarPorId(id)); 
	}
	
	@GetMapping(value="/{id}/remover")
	public ModelAndView remover(@PathVariable(value="id") Long id){
		return new ModelAndView(REMOVER).addObject(ATTRIBUTO_ORDEM_SERVICO, ordemServicoService.buscarPorId(id));
	}

	@DeleteMapping(value="/{id}/remover")
	public ModelAndView excluir(@PathVariable(value="id") Long id, RedirectAttributes redirectAttributes){
		redirectAttributes.addFlashAttribute(MENSAGEM_SUCESSO, "Ordem de serviço excluída com sucesso!");
		ordemServicoService.remover(id);
		return new ModelAndView("redirect:/");
	}
	
	@GetMapping(value="/{id}/pagar")
	public ModelAndView pagar(@PathVariable(value="id") Long id, RedirectAttributes redirectAttributes){
		redirectAttributes.addFlashAttribute(MENSAGEM_SUCESSO, "Ordem de serviço paga com sucesso!");
		ordemServicoService.pagar(id);
		return new ModelAndView("redirect:/");
	}
	
	@GetMapping(value="/{id}/cancelar_pagamento")
	public ModelAndView cancelarPagamento(@PathVariable(value="id") Long id, RedirectAttributes redirectAttributes){
		redirectAttributes.addFlashAttribute(MENSAGEM_SUCESSO, "pagamento cancelado com sucesso!");
		ordemServicoService.cancelarPagamento(id);
		return new ModelAndView("redirect:/");
	}
	
	private ModelAndView form(OrdemServico ordemServico) {
		return new ModelAndView(FORM)
				.addObject(ATTRIBUTO_ORDEM_SERVICO, ordemServico)
				.addObject(ATRIBUTO_CLIENTES, clienteService.buscarTodos())
				.addObject(ATRIBUTO_SERVICOS, servicoService.buscarTodos());
	}
	
}