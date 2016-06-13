package br.com.desafiotecnico.controller;

import static br.com.desafiotecnico.controller.ServicoController.ATRIBUTO_SERVICO;
import static br.com.desafiotecnico.controller.ServicoController.ATRIBUTO_SERVICOS;
import static br.com.desafiotecnico.controller.ServicoController.FORM;
import static br.com.desafiotecnico.controller.ServicoController.INDEX;
import static br.com.desafiotecnico.controller.ServicoController.REMOVER;
import static java.math.RoundingMode.HALF_UP;
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

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;

import br.com.desafiotecnico.model.Servico;
import br.com.desafiotecnico.service.ServicoService;

@RunWith(MockitoJUnitRunner.class)
public class ServicoControllerTest {

	private static final String NOME_SERVICO = "Huguinho de Souza";
	private static final String URL_REDIRECT = "/servicos/";
	private static final String VALOR_SERVICO = "10.00";
	private static final Long ID_SERVICO = Long.valueOf(1);
	private static final Integer SCALE = 2;

	private MockMvc mockMvc;

	@Mock
	private ServicoService servicoService;
	
	@InjectMocks
	private ServicoController servicoController = new ServicoController();
	
	private ArgumentCaptor<Servico> captorServico = ArgumentCaptor.forClass(Servico.class);
	
	@Before
	public void init(){
		when(servicoService.buscarTodos()).thenReturn(asList(mock(Servico.class)));
		when(servicoService.buscarPorId(ID_SERVICO)).thenReturn(mock(Servico.class));

		this.mockMvc = standaloneSetup(servicoController).build();
	}
	
	@Test
	public void deveRetornarIndexComListagemDeServicos() throws Exception {
		mockMvc.perform(get("/servicos/"))
		.andExpect(status().isOk())
		.andExpect(view().name(INDEX))
		.andExpect(model().attributeExists(ATRIBUTO_SERVICOS));
		
	}

	@Test
	public void deveRetornarFormularioParaNovoServico() throws Exception {
		mockMvc.perform(get("/servicos/novo"))
		.andExpect(status().isOk())
		.andExpect(view().name(FORM))
		.andExpect(model().attributeExists(ATRIBUTO_SERVICO));
	}

	@Test
	public void deveRetornarFormularioParaEdicaoDoServico() throws Exception {
		mockMvc.perform(get(format("/servicos/{0}/editar", ID_SERVICO)))
		.andExpect(status().isOk())
		.andExpect(view().name(FORM))
		.andExpect(model().attributeExists(ATRIBUTO_SERVICO));
	}

	@Test
	public void deveExecutarMetodoParaSalvarUmServicoComSucesso() throws Exception {
		mockMvc.perform(post("/servicos/salvar")
				.param("nome", NOME_SERVICO)
				.param("valor", VALOR_SERVICO)
		)
		.andExpect(status().is3xxRedirection())
		.andExpect(redirectedUrl(URL_REDIRECT));
		verify(servicoService).salvar(captorServico.capture());
		assertThat(captorServico.getValue().getNome(), is(NOME_SERVICO));
		assertThat(captorServico.getValue().getValor(), is(BigDecimal.TEN.setScale(SCALE, HALF_UP)));
	}

	@Test
	public void deveRetornarOFormularioParaServicoComFalhaNaValidacao() throws Exception {
		mockMvc.perform(post("/servicos/salvar")
				.param("valor", VALOR_SERVICO)
		)
		.andExpect(status().isOk())
		.andExpect(view().name(FORM));
	}
	
	@Test
	public void deveRetornarPaginaDeConfirmacaoParaRemoverServico() throws Exception {
		mockMvc.perform(get(format("/servicos/{0}/remover", ID_SERVICO)))
		.andExpect(status().isOk())
		.andExpect(view().name(REMOVER))
		.andExpect(model().attributeExists(ATRIBUTO_SERVICO));
	}
	
	@Test
	public void deveExecutarMetodoParaRemoverServico() throws Exception {
		mockMvc.perform(delete(format("/servicos/{0}/remover", ID_SERVICO))
		)
		.andExpect(status().is3xxRedirection())
		.andExpect(redirectedUrl(URL_REDIRECT));
	}

}