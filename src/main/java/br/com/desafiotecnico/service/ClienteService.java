package br.com.desafiotecnico.service;

import java.util.List;

import br.com.desafiotecnico.model.Cliente;

public interface ClienteService {

	List<Cliente> buscarTodos();

	void salvar(Cliente cliente);

	Cliente buscarPorId(Long id);

	void remover(Long id);

}