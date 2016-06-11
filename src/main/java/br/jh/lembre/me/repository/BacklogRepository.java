package br.jh.lembre.me.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.jh.lembre.me.model.Backlog;

public interface BacklogRepository extends MongoRepository<Backlog, String>{

}