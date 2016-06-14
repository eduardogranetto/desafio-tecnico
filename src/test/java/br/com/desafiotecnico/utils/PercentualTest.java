package br.com.desafiotecnico.utils;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;

import org.junit.Test;

public class PercentualTest {

	private static final BigDecimal DEZ_POR_CENTO = BigDecimal.valueOf(0.1);

	@Test
	public void deveRetornarOPercentualCorretamente() {
		assertThat(Percentual.calcular(BigDecimal.TEN), is(DEZ_POR_CENTO));
	}

}
