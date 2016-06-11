package br.jh.lembre.me.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.jh.lembre.me.model.Backlog;
import br.jh.lembre.me.repository.BacklogRepository;

@Service
public class BacklogServiceImpl implements BacklogService{

	@Autowired
	private BacklogRepository backlogRepository;
	
	@Override
	public List<Backlog> find() {
		return backlogRepository.findAll();
	}

}