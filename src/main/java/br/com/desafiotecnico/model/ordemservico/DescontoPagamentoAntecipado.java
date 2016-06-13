package br.com.desafiotecnico.model.ordemservico;

import static java.time.LocalDate.now;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;

import br.com.desafiotecnico.model.OrdemServico;
import br.com.desafiotecnico.utils.Percentual;

public class DescontoPagamentoAntecipado implements PoliticaDesconto{

	private static final BigDecimal DESCONTO_ANTECIPADO = BigDecimal.valueOf(5);
	
	private final OrdemServico ordemServico;

	public DescontoPagamentoAntecipado(OrdemServico ordemServico) {
		this.ordemServico = ordemServico;
	}

	@Override
	public void aplicarDesconto(CalculadorPreco calculadorPreco) {
		if(!now().isAfter(ordemServico.getFim().minus(10L, ChronoUnit.DAYS))){
			calculadorPreco.descontar(calculadorPreco.getValorTotal().multiply(Percentual.calcular(DESCONTO_ANTECIPADO)));
		}
	}

}
