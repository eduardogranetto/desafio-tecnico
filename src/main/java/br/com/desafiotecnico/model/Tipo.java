package br.com.desafiotecnico.model;

import java.math.BigDecimal;

import br.com.desafiotecnico.utils.Percentual;

public enum Tipo {
	OURO("Ouro", BigDecimal.valueOf(10)), PRATA("Prata", BigDecimal.valueOf(5));
	
	private final String nome;
	private final BigDecimal desconto;

	private Tipo(String nome, BigDecimal desconto) {
		this.nome = nome;
		this.desconto = desconto;
	}

	public String getNome() {
		return nome;
	}

	public BigDecimal getPercentualDesconto() {
		return Percentual.calcular(desconto);
	}
	
}
