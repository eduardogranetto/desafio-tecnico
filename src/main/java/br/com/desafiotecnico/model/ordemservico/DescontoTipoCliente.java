package br.com.desafiotecnico.model.ordemservico;

import br.com.desafiotecnico.model.Cliente;

public class DescontoTipoCliente implements PoliticaDesconto{

	private final Cliente cliente;

	public DescontoTipoCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	@Override
	public void aplicarDesconto(CalculadorPreco calculadorPreco) {
		calculadorPreco.descontar(calculadorPreco.getValorTotal().multiply(cliente.getTipo().getPercentualDesconto()));
	}

}