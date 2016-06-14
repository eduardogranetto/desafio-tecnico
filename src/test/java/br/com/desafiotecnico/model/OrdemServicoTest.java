package br.com.desafiotecnico.model;

import static java.time.LocalDate.now;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

public class OrdemServicoTest {

	private static final Long QUANTIDADE_DIAS = Long.valueOf(11);
	private static final Long QUANTIDADE_DIAS_ZERADO = Long.valueOf(0);
	private static final Long QUANTIDADE_DIAS_FALTANDO_CONCLUIR = Long.valueOf(10);
	
	private static final LocalDate ONZE_DIAS_PARA_FRENTE = LocalDate.now().plusDays(QUANTIDADE_DIAS);
	private static final LocalDate ONTEM = LocalDate.now().minusDays(-1);

	private static final BigDecimal VALOR_SERVICO = BigDecimal.valueOf(100);

	private static final BigDecimal VALOR_TOTAL_SERVICO_CLIENTE_OURO = BigDecimal.valueOf(90.00).setScale(2, BigDecimal.ROUND_HALF_UP);
	private static final BigDecimal VALOR_TOTAL_SERVICO_CLIENTE_PRATA = BigDecimal.valueOf(95.00).setScale(2, BigDecimal.ROUND_HALF_UP);
	private static final BigDecimal VALOR_TOTAL_SERVICO_CLIENTE_OURO_MAIS_DEZ_DIAS_ANTES_TERMINO = BigDecimal.valueOf(85.50).setScale(2, BigDecimal.ROUND_HALF_UP);
	private static final BigDecimal VALOR_TOTAL_SERVICO_CLIENTE_PRATA_MAIS_DEZ_DIAS_ANTES_TERMINO = BigDecimal.valueOf(90.25).setScale(2, BigDecimal.ROUND_HALF_UP);

	private Cliente clienteOuro = new Cliente();
	private Cliente clientePrata = new Cliente();
	private Servico servico = new Servico();

	@Before
	public void init(){
		clienteOuro.setTipo(Tipo.OURO);
		clientePrata.setTipo(Tipo.PRATA);
		servico.setValor(VALOR_SERVICO);
	}
	
	private OrdemServico mock(Cliente cliente, LocalDate inicio, LocalDate fim){
		OrdemServico ordemServico = new OrdemServico();
		ordemServico.setCliente(cliente);
		ordemServico.setServico(servico);
		ordemServico.setInicio(inicio);
		ordemServico.setFim(fim);
		return ordemServico;
	}
	private OrdemServico mock(Cliente cliente, LocalDate fim){
		return mock(cliente, now(), fim);
	}
	
	@Test
	public void deveCalcularOValorTotalParaClienteOuro() {
		OrdemServico ordemServico = mock(clienteOuro, LocalDate.now());
		assertThat(ordemServico.getTotal(), is(VALOR_TOTAL_SERVICO_CLIENTE_OURO));
	}

	@Test
	public void deveCalcularOValorTotalParaClientePrata() {
		OrdemServico ordemServico = mock(clientePrata, LocalDate.now());
		assertThat(ordemServico.getTotal(), is(VALOR_TOTAL_SERVICO_CLIENTE_PRATA));
	}

	@Test
	public void deveCalcularOValorTotalParaClienteOuroEMaisDeDezDiasParaOTermino() {
		OrdemServico ordemServico = mock(clienteOuro, ONZE_DIAS_PARA_FRENTE);
		assertThat(ordemServico.getTotal(), is(VALOR_TOTAL_SERVICO_CLIENTE_OURO_MAIS_DEZ_DIAS_ANTES_TERMINO));
	}

	@Test
	public void deveCalcularOValorTotalParaClientePrataEMaisDeDezDiasParaOTermino() {
		OrdemServico ordemServico = mock(clientePrata, ONZE_DIAS_PARA_FRENTE);
		assertThat(ordemServico.getTotal(), is(VALOR_TOTAL_SERVICO_CLIENTE_PRATA_MAIS_DEZ_DIAS_ANTES_TERMINO));
	}
	
	@Test
	public void deveEfetuarOPagamentoDaOrdemServico() {
		OrdemServico ordemServico = mock(clienteOuro, LocalDate.now());
		assertThat(ordemServico.getTotal(), is(VALOR_TOTAL_SERVICO_CLIENTE_OURO));
	}
	
	@Test
	public void deveEfetuarOPagamentoERetornarOValorPagoIndependenteDaDataDeTermino(){
		OrdemServico ordemServico = mock(clienteOuro, ONZE_DIAS_PARA_FRENTE);
		ordemServico.pagar();
		ordemServico.setFim(now());
		assertThat(ordemServico.getTotal(), is(ordemServico.getValorPago()));
		assertThat(ordemServico.isPago(), is(true));
	}
	
	@Test
	public void deveRetornarAQuantidadeDeDiasParaOEncerramento(){
		OrdemServico ordemServico = mock(clienteOuro, ONZE_DIAS_PARA_FRENTE);
		assertThat(ordemServico.getDiasParaTermino(), is(QUANTIDADE_DIAS));
	}

	@Test
	public void deveRetornarAQuantidadeDeDiasZeradaParaDataPosteriorAoEncerramento(){
		OrdemServico ordemServico = mock(clienteOuro, ONTEM, ONTEM);
		assertThat(ordemServico.getDiasParaTermino(), is(QUANTIDADE_DIAS_ZERADO));
	}

	@Test
	public void deveRetornarAQuantidadeDeDiasFaltantseParaEncerramento(){
		OrdemServico ordemServico = mock(clienteOuro, ONTEM, ONZE_DIAS_PARA_FRENTE);
		assertThat(ordemServico.getDiasParaTermino(), is(QUANTIDADE_DIAS_FALTANDO_CONCLUIR));
	}

}
