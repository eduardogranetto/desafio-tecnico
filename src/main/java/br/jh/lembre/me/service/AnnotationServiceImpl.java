package br.jh.lembre.me.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.data.querydsl.QSort;
import org.springframework.data.repository.query.parser.OrderBySource;
import org.springframework.stereotype.Service;

import br.jh.lembre.me.model.Annotation;
import br.jh.lembre.me.repository.AnnotationRepository;
import br.jh.lembre.me.repository.AnnotationRepositoryCustom;

@Service
public class AnnotationServiceImpl implements AnnotationService{
	
	private static final Integer LIMIT = 10;
	
	@Autowired
	private AnnotationRepository annotationRepository;
	
	@Autowired
	private AnnotationRepositoryCustom annotationRepositoryCustom;
	
	@Override
	public List<Annotation> find() {
		return annotationRepositoryCustom.find();
	}

	@Override
	public void create(Annotation annotation) {
		annotationRepository.save(annotation);
	}

	@Override
	public List<Annotation> find(String text) {
		return annotationRepositoryCustom.findByTestOrLink(text);
	}
	
}