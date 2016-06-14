package br.com.desafiotecnico.service;

import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import br.com.desafiotecnico.repository.ClienteRepository;

@RunWith(MockitoJUnitRunner.class)
public class ClienteServiceImplTest {

	private static final Long ID = Long.valueOf(1);

	@Mock
	private ClienteRepository clienteRepository;
	
	@InjectMocks
	private ClienteService clienteService = new ClienteServiceImpl();
	
	@Test
	public void deveVerificarAcessoAoRepositorio() {
		clienteService.remover(ID);
		verify(clienteRepository).delete(ID);
	}

}
