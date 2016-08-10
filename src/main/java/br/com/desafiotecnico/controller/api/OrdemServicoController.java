package br.com.desafiotecnico.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.desafiotecnico.model.OrdemServico;
import br.com.desafiotecnico.service.OrdemServicoService;

@RequestMapping("/api")
@RestController("ORDEM_SERVICO_API_CONTROLLER")
public class OrdemServicoController {

	@Autowired
	private OrdemServicoService ordemServicoService;
	
	@Autowired
	private OrdemServicoAssembler ordemServicoAssembler;
	
	@ResponseBody
	@RequestMapping(value="/ordens_servico", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Page<OrdemServico> listar(Pageable pageable){
		return ordemServicoService.buscarTodos(pageable);
	}

	@ResponseBody
	@RequestMapping(value="/ordens_servico/hateoas", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public PagedResources<OrdemServico> listarHateoas(Pageable pageable,  PagedResourcesAssembler assembler){
		return assembler.toResource(ordemServicoService.buscarTodos(pageable), ordemServicoAssembler);
	}

}