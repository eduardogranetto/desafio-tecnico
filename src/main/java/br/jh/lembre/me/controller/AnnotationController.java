package br.jh.lembre.me.controller;

import static org.springframework.util.StringUtils.isEmpty;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.jh.lembre.me.model.Annotation;
import br.jh.lembre.me.service.AnnotationService;

@Controller
public class AnnotationController {
	
	private static final String HOME = "annotation/home";
	private static final String FORM = "annotation/form";
	
	@Autowired
	private AnnotationService annotationService;
	
	@RequestMapping(value="/annotation/home", method=RequestMethod.GET)
	public ModelAndView home() throws InterruptedException{
		Thread.sleep(5000);
		return new ModelAndView(HOME)
				.addObject("annotations", annotationService.find());
	}

	@RequestMapping(value="/annotation/form", method=RequestMethod.GET)
	public ModelAndView form(@RequestParam(value="text", required=false) String text){
		final Annotation annotation = new Annotation();
		annotation.setLink(text);
		return new ModelAndView(FORM).addObject("annotation", annotation);
	}
	
	@RequestMapping(value="/annotations", method=RequestMethod.POST)
	public ModelAndView create(@Valid @ModelAttribute Annotation annotation, BindingResult result){
		ModelAndView modelAndView = new ModelAndView("annotation/index");
		
		if(!result.hasErrors()){
			annotation.setId(isEmpty(annotation.getId()) ? null : annotation.getId());
			annotationService.create(annotation);
			return null;
		}
		
		modelAndView.addObject("error", result.hasErrors());
		modelAndView.addObject("annotation", annotation);
		return modelAndView.addObject("annotations", annotationService.find());
	}

	@RequestMapping(value="/annotations/search", method=RequestMethod.GET)
	public ModelAndView search(@RequestParam("text") String text){
		return null;
	}
	
}