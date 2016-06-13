package br.com.desafiotecnico.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import br.com.desafiotecnico.model.OrdemServico;
import br.com.desafiotecnico.repository.OrdemServicoRepository;

@Service
public class OrdemServicoServiceImpl extends GenericServiceImpl<OrdemServico> implements OrdemServicoService{

	@Autowired 
	private OrdemServicoRepository ordemServicoRepository;

	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private ServicoService servicoService;
	
	@Override
	JpaRepository<OrdemServico, Long> getRepository() {
		return ordemServicoRepository;
	}

	@Override
	public OrdemServico criar(Long clienteId, Long servicoId) {
		OrdemServico ordemServico = new OrdemServico();
		ordemServico.setCliente(clienteId != null ? clienteService.buscarPorId(clienteId) : null);
		ordemServico.setServico(servicoId != null ? servicoService.buscarPorId(servicoId) : null);
		return ordemServico;
	}

}