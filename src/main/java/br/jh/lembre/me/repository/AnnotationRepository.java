package br.jh.lembre.me.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import br.jh.lembre.me.model.Annotation;

public interface AnnotationRepository extends PagingAndSortingRepository<Annotation, String>{

	@Query("{'$or':[ {'description':{$regex: '/?0/i'}}, {'link':{$regex: '/?0/i'}} ] }")
	List<Annotation> findByText(String text);
	
}