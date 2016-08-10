package br.com.desafiotecnico.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.desafiotecnico.model.OrdemServico;

public interface OrdemServicoService extends GenericService<OrdemServico>{

	OrdemServico criar(Long clienteId, Long servicoId);

	void pagar(Long id);

	void cancelarPagamento(Long id);

	Page<OrdemServico> buscarTodos(Pageable pageable);

}