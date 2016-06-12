package br.com.desafiotecnico.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import br.com.desafiotecnico.model.Cliente;
import br.com.desafiotecnico.repository.ClienteRepository;

@Service
public class ClienteServiceImpl extends GenericServiceImpl<Cliente> implements ClienteService{

	@Autowired 
	private ClienteRepository clienteRepository;

	@Override
	JpaRepository<Cliente, Long> getRepository() {
		return clienteRepository;
	}

}