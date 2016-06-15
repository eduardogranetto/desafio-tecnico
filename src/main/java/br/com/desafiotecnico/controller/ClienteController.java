package br.com.desafiotecnico.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.desafiotecnico.model.Cliente;
import br.com.desafiotecnico.model.Tipo;
import br.com.desafiotecnico.service.ClienteService;
import br.com.desafiotecnico.service.GenericService;

@Controller
@RequestMapping("/clientes")
public class ClienteController extends CRUDController<Cliente>{
	
	public static final String ATRIBUTO_CLIENTES = "clientes";
	public static final String ATRIBUTO_TIPOS = "tipos";
	public static final String ATRIBUTO_CLIENTE = "cliente";
	public static final String REDIRECT_INDEX = "redirect:/clientes/";
	public static final String INDEX = "clientes/index";
	public static final String FORM = "clientes/form";
	public static final String REMOVER = "clientes/remover";
	public static final String ORDEM_SERVICO = "clientes/ordem_servico";
	private static final String MENSAGEM_SALVO = "Cliente salvo com sucesso!";
	private static final String MENSAGEM_REMOVIDO = "Cliente removido com sucesso!";
	
	@Autowired
	private ClienteService clienteService;

	@Override
	GenericService<Cliente> getService() {
		return clienteService;
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
		return ATRIBUTO_CLIENTES;
	}

	@Override
	String getNomeAtributoFormulario() {
		return ATRIBUTO_CLIENTE;
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
	Cliente criarInstancia() {
		return new Cliente();
	}
	
	@Override
	protected ModelAndView adicionarParametrosFormulario(ModelAndView modelAndView) {
		return modelAndView.addObject(ATRIBUTO_TIPOS, Tipo.values());
	}
	
	@RequestMapping(value="/{id}/ordens_servico", method=RequestMethod.GET)
	public ModelAndView ordensServico(@PathVariable(value="id") Long id){
		return new ModelAndView(ORDEM_SERVICO).addObject(ATRIBUTO_CLIENTE, clienteService.buscarPorId(id)); 
	}

	@Override
	String getMensagemSalvo() {
		return MENSAGEM_SALVO;
	}

	@Override
	String getMensagemRemovido() {
		return MENSAGEM_REMOVIDO;
	}
	
}