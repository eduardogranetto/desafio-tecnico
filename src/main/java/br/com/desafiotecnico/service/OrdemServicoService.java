package br.com.desafiotecnico.service;

import br.com.desafiotecnico.model.OrdemServico;

public interface OrdemServicoService extends GenericService<OrdemServico>{

	OrdemServico criar(Long clienteId, Long servicoId);

	void pagar(Long id);

	void cancelarPagamento(Long id);

}