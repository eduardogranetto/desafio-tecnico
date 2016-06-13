package br.com.desafiotecnico.controller;

import static br.com.desafiotecnico.utils.Constantes.MENSAGEM_SUCESSO;

import javax.validation.Valid;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.desafiotecnico.model.GenericEntity;
import br.com.desafiotecnico.service.GenericService;

public abstract class CRUDController<T extends GenericEntity> {
	
	abstract GenericService<T> getService();
	
	abstract String getIndexPathView();
	abstract String getRemovePathView();
	abstract String getFormularioPathView();
	
	
	abstract String getNomeAtributoIndex();
	abstract String getNomeAtributoFormulario();
	
	abstract String getUrlRedirectAfterSave();
	abstract String getUrlRedirectAfterRemove();
	
	abstract T criarInstancia();
	
	protected ModelAndView adicionarParametrosFormulario(ModelAndView modelAndView){
		return modelAndView;
	}
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public ModelAndView index(){
		return new ModelAndView(getIndexPathView()).addObject(getNomeAtributoIndex(), getService().buscarTodos());
	}
	
	@RequestMapping(value="/novo", method=RequestMethod.GET)
	public ModelAndView novo(){
		return form(criarInstancia()); 
	}

	@RequestMapping(value="/salvar", method=RequestMethod.POST)
	public ModelAndView salvar(@Valid @ModelAttribute T entidade, BindingResult result, RedirectAttributes attributes){
		if(result.hasErrors()){
			return form(entidade);
		}
		getService().salvar(entidade);
		attributes.addFlashAttribute(MENSAGEM_SUCESSO, "Cliente salvo com sucesso!");
		return new ModelAndView(getUrlRedirectAfterSave());
	}

	@RequestMapping(value="/{id}/editar", method=RequestMethod.GET)
	public ModelAndView editar(@PathVariable(value="id") Long id){
		return form(getService().buscarPorId(id)); 
	}
	
	@RequestMapping(value="/{id}/remover", method=RequestMethod.GET)
	public ModelAndView remover(@PathVariable(value="id") Long id){
		return new ModelAndView(getRemovePathView()).addObject(getNomeAtributoFormulario(), getService().buscarPorId(id));
	}

	@RequestMapping(value="/{id}/remover", method=RequestMethod.DELETE)
	public ModelAndView excluir(@PathVariable(value="id") Long id, RedirectAttributes redirectAttributes){
		redirectAttributes.addFlashAttribute(MENSAGEM_SUCESSO, "Cliente removido com sucesso!");
		getService().remover(id);
		return new ModelAndView(getUrlRedirectAfterRemove());
	}

	private ModelAndView form(T entitade) {
		return adicionarParametrosFormulario(new ModelAndView(getFormularioPathView())).addObject(getNomeAtributoFormulario(), entitade);
	}
	
}
