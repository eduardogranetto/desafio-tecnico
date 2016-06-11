package br.jh.lembre.me.service;

import java.util.List;

import br.jh.lembre.me.model.Annotation;

public interface AnnotationService {

	List<Annotation> find();

	List<Annotation> find(String text);

	void create(Annotation annotation);

}