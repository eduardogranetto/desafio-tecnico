package br.com.desafiotecnico.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import br.com.desafiotecnico.model.Cliente;
import br.com.desafiotecnico.model.OrdemServico;
import br.com.desafiotecnico.model.Servico;
import br.com.desafiotecnico.repository.OrdemServicoRepository;

@RunWith(MockitoJUnitRunner.class)
public class OrdemServicoServiceImplTest {

	private static final Long ID_CLIENTE = Long.valueOf(1);

	private static final Long ID_SERVICO = Long.valueOf(1);

	private static final Long ID_ORDEM_SERVICO = Long.valueOf(2);;

	@Mock 
	private OrdemServicoRepository ordemServicoRepository;

	@Mock
	private ClienteService clienteService;
	
	@Mock
	private ServicoService servicoService;
	
	@InjectMocks
	private OrdemServicoService ordemServicoService = new OrdemServicoServiceImpl(); 
	
	private ArgumentCaptor<OrdemServico> captorOrdemServico = ArgumentCaptor.forClass(OrdemServico.class);
	
	@Test
	public void deveCriarUmaOrdemServicoBaseadoNoCliente() {
		when(clienteService.buscarPorId(ID_CLIENTE)).thenReturn(mock(Cliente.class));
		OrdemServico ordemServico = ordemServicoService.criar(ID_CLIENTE, null);
		assertNotNull(ordemServico.getCliente());
		assertNull(ordemServico.getServico());
	}

	@Test
	public void deveCriarUmaOrdemServicoBaseadoNoServico() {
		when(servicoService.buscarPorId(ID_SERVICO)).thenReturn(mock(Servico.class));
		OrdemServico ordemServico = ordemServicoService.criar(null, ID_SERVICO);
		assertNotNull(ordemServico.getServico());
		assertNull(ordemServico.getCliente());
	}
	
	@Test
	public void deveEvetuarOPagamentoDeUmaOrdemDeServico(){
		when(ordemServicoService.buscarPorId(ID_ORDEM_SERVICO)).thenReturn(mock(OrdemServico.class));
		ordemServicoService.pagar(ID_ORDEM_SERVICO);
		verify(ordemServicoRepository).save(captorOrdemServico.capture());
		assertNotNull(captorOrdemServico.getValue());
	}

	@Test
	public void deveCancelarOPagamentoDeUmaOrdemDeServico(){
		when(ordemServicoService.buscarPorId(ID_ORDEM_SERVICO)).thenReturn(mock(OrdemServico.class));
		ordemServicoService.cancelarPagamento(ID_ORDEM_SERVICO);
		verify(ordemServicoRepository).save(captorOrdemServico.capture());
		assertNotNull(captorOrdemServico.getValue());
	}

}