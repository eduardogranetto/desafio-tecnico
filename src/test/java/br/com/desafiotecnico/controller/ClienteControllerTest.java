package br.com.desafiotecnico.controller;

import static br.com.desafiotecnico.controller.ClienteController.ATRIBUTO_CLIENTE;
import static br.com.desafiotecnico.controller.ClienteController.ATRIBUTO_CLIENTES;
import static br.com.desafiotecnico.controller.ClienteController.ATRIBUTO_TIPOS;
import static br.com.desafiotecnico.controller.ClienteController.FORM;
import static br.com.desafiotecnico.controller.ClienteController.INDEX;
import static br.com.desafiotecnico.controller.ClienteController.ORDEM_SERVICO;
import static br.com.desafiotecnico.controller.ClienteController.REMOVER;
import static br.com.desafiotecnico.model.Tipo.OURO;
import static java.text.MessageFormat.format;
import static java.util.Arrays.asList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;

import br.com.desafiotecnico.model.Cliente;
import br.com.desafiotecnico.service.ClienteService;

@RunWith(MockitoJUnitRunner.class)
public class ClienteControllerTest {

	private static final String TIPO_CLIENTE = "OURO";
	private static final String NOME_CLIENTE = "Huguinho de Souza";
	private static final String URL_REDIRECT = "/clientes/";
	private static final Long ID_CLIENTE = Long.valueOf(1);

	private MockMvc mockMvc;

	@Mock
	private ClienteService clienteService;
	
	@InjectMocks
	private ClienteController clienteController = new ClienteController();
	
	private ArgumentCaptor<Cliente> captorCliente = ArgumentCaptor.forClass(Cliente.class);
	
	@Before
	public void init(){
		when(clienteService.buscarTodos()).thenReturn(asList(mock(Cliente.class)));
		when(clienteService.buscarPorId(ID_CLIENTE)).thenReturn(mock(Cliente.class));

		this.mockMvc = standaloneSetup(clienteController).build();
	}
	
	@Test
	public void deveRetornarIndexComListagemDeClientes() throws Exception {
		mockMvc.perform(get("/clientes/"))
		.andExpect(status().isOk())
		.andExpect(view().name(INDEX))
		.andExpect(model().attributeExists(ATRIBUTO_CLIENTES));
	}

	@Test
	public void deveRetornarFormularioParaNovoCliente() throws Exception {
		mockMvc.perform(get("/clientes/novo"))
		.andExpect(status().isOk())
		.andExpect(view().name("clientes/form"))
		.andExpect(model().attributeExists(ATRIBUTO_CLIENTE, ATRIBUTO_TIPOS));
	}

	@Test
	public void deveRetornarFormularioParaEdicaoDoCliente() throws Exception {
		mockMvc.perform(get(format("/clientes/{0}/editar", ID_CLIENTE)))
		.andExpect(status().isOk())
		.andExpect(view().name(FORM))
		.andExpect(model().attributeExists(ATRIBUTO_CLIENTE, ATRIBUTO_TIPOS));
	}

	@Test
	public void deveExecutarMetodoParaSalvarUmClienteComSucesso() throws Exception {
		mockMvc.perform(post("/clientes/salvar")
				.param("nome", NOME_CLIENTE)
				.param("tipo", TIPO_CLIENTE)
		)
		.andExpect(status().is3xxRedirection())
		.andExpect(redirectedUrl(URL_REDIRECT));
		verify(clienteService).salvar(captorCliente.capture());
		assertThat(captorCliente.getValue().getNome(), is(NOME_CLIENTE));
		assertThat(captorCliente.getValue().getTipo(), is(OURO));
	}

	@Test
	public void deveExecutarMetodoParaSalvarUmClienteEFalharAValidacao() throws Exception {
		mockMvc.perform(post("/clientes/salvar")
				.param("tipo", TIPO_CLIENTE)
		)
		.andExpect(status().isOk())
		.andExpect(view().name(FORM));
	}

	@Test
	public void deveRetornarPaginaComListagemDasOrdensDeServicoPorCliente() throws Exception {
		mockMvc.perform(get(format("/clientes/{0}/ordens_servico", ID_CLIENTE)))
		.andExpect(status().isOk())
		.andExpect(view().name(ORDEM_SERVICO))
		.andExpect(model().attributeExists(ATRIBUTO_CLIENTE));
	}
	
	@Test
	public void deveRetornarPaginaDeConfirmacaoParaRemoverCliente() throws Exception {
		mockMvc.perform(get(format("/clientes/{0}/remover", ID_CLIENTE)))
		.andExpect(status().isOk())
		.andExpect(view().name(REMOVER))
		.andExpect(model().attributeExists(ATRIBUTO_CLIENTE));
	}
	
	@Test
	public void deveExecutarMetodoParaRemoverCliente() throws Exception {
		mockMvc.perform(delete(format("/clientes/{0}/remover", ID_CLIENTE))
		)
		.andExpect(status().is3xxRedirection())
		.andExpect(redirectedUrl(URL_REDIRECT));
	}

}