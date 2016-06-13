package br.com.desafiotecnico.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.desafiotecnico.model.OrdemServico;

@Repository
@Transactional
public interface OrdemServicoRepository extends JpaRepository<OrdemServico, Long>{

}