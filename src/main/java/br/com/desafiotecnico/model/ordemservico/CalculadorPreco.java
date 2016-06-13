package br.com.desafiotecnico.model.ordemservico;

import static java.util.Arrays.asList;

import java.math.BigDecimal;
import java.util.List;

import br.com.desafiotecnico.model.OrdemServico;

public class CalculadorPreco {
	
	private static final int SCALE = 2;
	
	private final List<PoliticaDesconto> descontos;
	private BigDecimal valorTotal;
	
	public CalculadorPreco(OrdemServico ordemServico) {
		descontos = asList(
				new DescontoTipoCliente(ordemServico.getCliente()),
				new DescontoPagamentoAntecipado(ordemServico)
			);
		this.valorTotal = ordemServico.getServico().getValor();
	}
	
	public CalculadorPreco calcular(){
		descontos.stream().forEach(desconto -> desconto.aplicarDesconto(this));
		return this;
	}
	
	public void descontar(BigDecimal valorDesconto){
		this.valorTotal = this.valorTotal.subtract(valorDesconto);
	}
	
	public BigDecimal getValorTotal() {
		return this.valorTotal.setScale(SCALE, BigDecimal.ROUND_HALF_UP);
	}

}