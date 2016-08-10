package br.com.desafiotecnico.controller.api;


import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import br.com.desafiotecnico.model.OrdemServico;

@Component
public class OrdemServicoAssembler extends ResourceAssemblerSupport<OrdemServico, OrdemServicoResource>{

	public OrdemServicoAssembler() {
		super(OrdemServicoController.class, OrdemServicoResource.class);
	}

	@Override
	public List<OrdemServicoResource> toResources(Iterable<? extends OrdemServico> ordensServico) {
		List<OrdemServicoResource> resources = new ArrayList<>();
		ordensServico.forEach(ordemServico -> resources.add(toResource(ordemServico)));
		return resources; 
	}
	
	@Override
	public OrdemServicoResource toResource(OrdemServico ordemServico) {
		OrdemServicoResource resource = new OrdemServicoResource();
		resource.add(linkTo(OrdemServicoController.class).withSelfRel());
		resource.setOrdemServico(ordemServico);
		return resource;
	}

}
