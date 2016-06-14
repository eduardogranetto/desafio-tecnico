package br.com.desafiotecnico.service;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import br.com.desafiotecnico.model.Servico;
import br.com.desafiotecnico.repository.ServicoRepository;

@RunWith(MockitoJUnitRunner.class)
public class ServicoServiceImplTest {

	@Mock
	private ServicoRepository clienteRepository;
	
	@InjectMocks
	private ServicoService servicoService = new ServicoServiceImpl();
	
	@Test
	public void deveVerificarAcessoAoRepositorio() {
		when(clienteRepository.findAll()).thenReturn(asList(mock(Servico.class)));
		assertNotNull(servicoService.buscarTodos());
	}

}
