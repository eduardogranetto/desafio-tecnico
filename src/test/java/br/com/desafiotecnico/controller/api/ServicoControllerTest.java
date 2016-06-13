package br.com.desafiotecnico.controller.api;

import static java.util.Arrays.asList;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import org.hamcrest.core.Is;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import br.com.desafiotecnico.model.Servico;
import br.com.desafiotecnico.service.ServicoService;

@RunWith(MockitoJUnitRunner.class)
public class ServicoControllerTest {

	
	private static final String NOME_SERVICO = "Limpeza de Computadores";

	@Mock
	private ServicoService servicoService;

	@InjectMocks
	private ServicoController servicoController;

	private MockMvc mockMvc;
	
	
	@Before
	public void init(){
		Servico servico = new Servico();
		servico.setNome(NOME_SERVICO);
		when(servicoService.buscarTodos()).thenReturn(asList(servico));
		this.mockMvc = standaloneSetup(servicoController).build();
	}
	
	@Test
	public void deveRetornarListaDeServicos() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/api/servicos"))
		.andExpect(status().isOk())
		.andExpect(content().contentType(APPLICATION_JSON_UTF8_VALUE))
		.andExpect(MockMvcResultMatchers.jsonPath("$[0].nome", Is.is(NOME_SERVICO)));
	}

}