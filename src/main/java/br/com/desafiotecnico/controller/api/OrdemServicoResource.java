package br.com.desafiotecnico.controller.api;

import org.springframework.hateoas.ResourceSupport;

import br.com.desafiotecnico.model.OrdemServico;

public class OrdemServicoResource extends ResourceSupport{
	
	private OrdemServico ordemServico;

	public OrdemServico getOrdemServico() {
		return ordemServico;
	}

	public void setOrdemServico(OrdemServico ordemServico) {
		this.ordemServico = ordemServico;
	}
}
