package br.com.desafiotecnico.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import br.com.desafiotecnico.model.Servico;
import br.com.desafiotecnico.repository.ServicoRepository;

@Service
public class ServicoServiceImpl extends GenericServiceImpl<Servico> implements ServicoService{

	@Autowired 
	private ServicoRepository servicoRepository;

	@Override
	JpaRepository<Servico, Long> getRepository() {
		return servicoRepository;
	}

}