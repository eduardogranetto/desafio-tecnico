package br.com.desafiotecnico.service;

import java.util.List;

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

}
