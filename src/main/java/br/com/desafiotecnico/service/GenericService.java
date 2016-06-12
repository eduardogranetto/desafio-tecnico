package br.com.desafiotecnico.service;

import java.util.List;

import br.com.desafiotecnico.model.GenericEntity;

public interface GenericService<Entidade extends GenericEntity> {

	List<Entidade> buscarTodos();

	void salvar(Entidade entidade);

	Entidade buscarPorId(Long id);

	void remover(Long id);
	
}
