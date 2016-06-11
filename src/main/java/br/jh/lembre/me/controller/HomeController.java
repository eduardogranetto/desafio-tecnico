package br.jh.lembre.me.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.jh.lembre.me.model.Annotation;
import br.jh.lembre.me.service.AnnotationService;

@Controller
public class HomeController {

	private static final String ROOT = "home/root";

	@Autowired
	private AnnotationService annotationService;
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public ModelAndView home(@RequestParam(required=false) String msg){
		return home(msg, null, annotationService.find());
	}
	
	private ModelAndView home(String msg, String link,  List<Annotation> annotations) {
		ModelAndView modelAndView = new ModelAndView(ROOT); 
		modelAndView.addObject("msg", msg);
		modelAndView.addObject("link", link);
		return modelAndView.addObject("annotations", annotations);
	}
	
}