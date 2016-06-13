package br.com.desafiotecnico.controller;

import static br.com.desafiotecnico.controller.OrdemServicoController.ATRIBUTO_CLIENTES;
import static br.com.desafiotecnico.controller.OrdemServicoController.ATRIBUTO_SERVICOS;
import static br.com.desafiotecnico.controller.OrdemServicoController.ATTRIBUTO_ORDEM_SERVICO;
import static br.com.desafiotecnico.controller.OrdemServicoController.FORM;
import static br.com.desafiotecnico.controller.OrdemServicoController.REMOVER;
import static br.com.desafiotecnico.utils.Constantes.MENSAGEM_SUCESSO;
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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import br.com.desafiotecnico.model.OrdemServico;
import br.com.desafiotecnico.service.ClienteService;
import br.com.desafiotecnico.service.OrdemServicoService;
import br.com.desafiotecnico.service.ServicoService;

@RunWith(MockitoJUnitRunner.class)
public class OrdemServicoControllerTest {

	private static final String ID_CLIENTE = "1";
	private static final String ID_SERVICO = "2";
	private static final String INICIO = "01/04/2016";
	private static final String FIM = "01/07/2016";
	private static final String URL_REDIRECT_SALVAR = "/ordens_servico/null";
	private static final String URL_REDIRECT_HOME = "/";
	private static final Long ID_ORDEM_SERVICO = Long.valueOf(1);
	

	private MockMvc mockMvc;

	@Mock
	private OrdemServicoService ordemServicoService;
	
	@Mock
	private ClienteService clienteService;
	
	@Mock
	private ServicoService servicoService;
	
	@InjectMocks
	private OrdemServicoController ordemServicoController = new OrdemServicoController();
	
	private ArgumentCaptor<OrdemServico> captorOrdemServico = ArgumentCaptor.forClass(OrdemServico.class);
	
	@Before
	public void init(){
		when(ordemServicoService.buscarTodos()).thenReturn(asList(mock(OrdemServico.class)));
		when(ordemServicoService.buscarPorId(ID_ORDEM_SERVICO)).thenReturn(mock(OrdemServico.class));
		this.mockMvc = standaloneSetup(ordemServicoController).build();
	}
	
	@Test
	public void deveRetornarFormularioParaNovaOrdemServico() throws Exception {
		when(ordemServicoService.criar(null, null)).thenReturn(mock(OrdemServico.class));
		mockMvc.perform(get("/ordens_servico/novo"))
		.andExpect(status().isOk())
		.andExpect(view().name(FORM))
		.andExpect(model().attributeExists(ATTRIBUTO_ORDEM_SERVICO, ATRIBUTO_CLIENTES, ATRIBUTO_SERVICOS));
	}

	@Test
	public void deveRetornarFormularioParaEdicaoDaOrdemServico() throws Exception {
		mockMvc.perform(get(format("/ordens_servico/{0}/editar", ID_ORDEM_SERVICO)))
		.andExpect(status().isOk())
		.andExpect(view().name(FORM))
		.andExpect(model().attributeExists(ATTRIBUTO_ORDEM_SERVICO, ATRIBUTO_CLIENTES, ATRIBUTO_SERVICOS));
	}

	@Test
	public void deveExecutarMetodoParaSalvarUmaOrdemServicoComSucesso() throws Exception {
		mockMvc.perform(post("/ordens_servico/salvar")
				.param("cliente.id", ID_CLIENTE)
				.param("servico.id", ID_SERVICO)
				.param("inicio", INICIO)
				.param("fim", FIM)
		)
		.andExpect(status().is3xxRedirection())
		.andExpect(redirectedUrl(URL_REDIRECT_SALVAR));
		verify(ordemServicoService).salvar(captorOrdemServico.capture());
		assertThat(captorOrdemServico.getValue().getCliente().getId(), is(Long.valueOf(ID_CLIENTE)));
		assertThat(captorOrdemServico.getValue().getServico().getId(), is(Long.valueOf(ID_SERVICO)));
	}

	@Test
	public void deveRetornarOFormularioParaOrdemServicoComFalhaNaValidacao() throws Exception {
		mockMvc.perform(post("/ordens_servico/salvar"))
		.andExpect(status().isOk())
		.andExpect(view().name(FORM));
	}

	@Test
	public void deveRetornarPaginaDeConfirmacaoParaRemoverOrdemServico() throws Exception {
		mockMvc.perform(get(format("/ordens_servico/{0}/remover", ID_ORDEM_SERVICO)))
		.andExpect(status().isOk())
		.andExpect(view().name(REMOVER))
		.andExpect(model().attributeExists(ATTRIBUTO_ORDEM_SERVICO));
	}
	
	@Test
	public void deveExecutarMetodoParaRemoverOrdemServico() throws Exception {
		mockMvc.perform(delete(format("/ordens_servico/{0}/remover", ID_ORDEM_SERVICO))
		)
		.andExpect(status().is3xxRedirection())
		.andExpect(redirectedUrl(URL_REDIRECT_HOME));
	}

	@Test
	public void deveExecutarMetodoPagarOrdemDeServicoERedirecionarParaUrlDaHome() throws Exception{
		mockMvc.perform(get(format("/ordens_servico/{0}/pagar", ID_ORDEM_SERVICO)))
		.andExpect(status().is3xxRedirection())
		.andExpect(redirectedUrl(URL_REDIRECT_HOME))
		.andExpect(MockMvcResultMatchers.flash().attributeExists(MENSAGEM_SUCESSO));
		
	}

	@Test
	public void deveExecutarMetodoCancelarPagamentoOrdemDeServicoERedirecionarParaUrlDaHome() throws Exception{
		mockMvc.perform(get(format("/ordens_servico/{0}/cancelar_pagamento", ID_ORDEM_SERVICO)))
		.andExpect(status().is3xxRedirection())
		.andExpect(redirectedUrl(URL_REDIRECT_HOME));
	}
	
}