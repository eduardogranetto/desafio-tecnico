package br.com.desafiotecnico.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.desafiotecnico.model.Servico;

@Repository
@Transactional
public interface ServicoRepository extends JpaRepository<Servico, Long>{

}