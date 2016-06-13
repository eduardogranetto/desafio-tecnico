package br.com.desafiotecnico.utils;

import java.math.BigDecimal;

public final class Percentual {
	
	private static final BigDecimal CEM = BigDecimal.valueOf(100);
	
	public static final BigDecimal calcular(BigDecimal valor){
		return valor.divide(CEM);
	}
	
}