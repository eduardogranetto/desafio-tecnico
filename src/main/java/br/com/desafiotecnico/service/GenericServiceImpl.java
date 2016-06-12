package br.com.desafiotecnico.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.desafiotecnico.model.GenericEntity;

public abstract class GenericServiceImpl<Entidade extends GenericEntity> implements GenericService<Entidade>{
	
	abstract JpaRepository<Entidade, Long> getRepository();
	
	@Override
	public List<Entidade> buscarTodos() {
		return getRepository().findAll();
	}

	@Override
	public void salvar(Entidade entidade) {
		getRepository().save(entidade);
	}

	@Override
	public Entidade buscarPorId(Long id) {
		return getRepository().findOne(id);
	}

	@Override
	public void remover(Long id) {
		getRepository().delete(id);
	}

}