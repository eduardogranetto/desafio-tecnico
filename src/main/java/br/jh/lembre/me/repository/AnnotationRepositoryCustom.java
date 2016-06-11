package br.jh.lembre.me.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import br.jh.lembre.me.model.Annotation;

@Repository
public class AnnotationRepositoryCustom {

	@Autowired
	private MongoTemplate mongoTemplate;
	
	public List<Annotation> findByTestOrLink(String text){
		return mongoTemplate.find(
				Query.query(new Criteria().orOperator(
						Criteria.where("description").regex(text, "i"),
						Criteria.where("link").regex(text, "i")
				)), 
				Annotation.class);
	}

	public List<Annotation> find() {
		return mongoTemplate.find(new Query().with(new Sort(Direction.DESC, "createdDate")), Annotation.class);
	}
	
}