package br.com.desafiotecnico.service;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.desafiotecnico.model.Cliente;

@Service
public class ClienteServiceImpl implements ClienteService{

	@Override
	public List<Cliente> buscarTodos() {
		return Collections.emptyList();
	}

}
