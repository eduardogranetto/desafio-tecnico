package br.com.desafiotecnico.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.desafiotecnico.model.Cliente;
import br.com.desafiotecnico.repository.ClienteRepository;

@Service
public class ClienteServiceImpl implements ClienteService{

	@Autowired 
	private ClienteRepository clienteRepository;
	
	@Override
	public List<Cliente> buscarTodos() {
		return clienteRepository.findAll();
	}

	@Override
	@Transactional
	public void salvar(Cliente cliente) {
		clienteRepository.save(cliente);
	}

	@Override
	public Cliente buscarPorId(Long id) {
		return clienteRepository.findOne(id);
	}

	@Override
	public void remover(Long id) {
		clienteRepository.delete(id);
	}

}