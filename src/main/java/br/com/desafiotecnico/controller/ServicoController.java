package br.com.desafiotecnico.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.desafiotecnico.model.Servico;
import br.com.desafiotecnico.service.GenericService;
import br.com.desafiotecnico.service.ServicoService;

@Controller
@RequestMapping("/servicos")
public class ServicoController extends CRUDController<Servico>{
	
	public static final String ATRIBUTO_SERVICO = "servico";
	public static final String ATRIBUTO_SERVICOS = "servicos";
	public static final String REDIRECT_INDEX = "redirect:/servicos/";
	public static final String INDEX = "servicos/index";
	public static final String FORM = "servicos/form";
	public static final String REMOVER = "servicos/remover";
	
	@Autowired
	private ServicoService servicoService;

	@Override
	GenericService<Servico> getService() {
		return servicoService;
	}

	@Override
	String getIndexPathView() {
		return INDEX;
	}

	@Override
	String getRemovePathView() {
		return REMOVER;
	}

	@Override
	String getFormularioPathView() {
		return FORM;
	}

	@Override
	String getNomeAtributoIndex() {
		return ATRIBUTO_SERVICOS;
	}

	@Override
	String getNomeAtributoFormulario() {
		return ATRIBUTO_SERVICO;
	}

	@Override
	String getUrlRedirectAfterSave() {
		return REDIRECT_INDEX;
	}

	@Override
	String getUrlRedirectAfterRemove() {
		return getUrlRedirectAfterSave();
	}

	@Override
	Servico criarInstancia() {
		return new Servico();
	}
	
}