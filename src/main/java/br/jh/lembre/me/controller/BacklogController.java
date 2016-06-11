package br.jh.lembre.me.controller;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.jh.lembre.me.model.Backlog;
import br.jh.lembre.me.service.AnnotationService;
import br.jh.lembre.me.service.BacklogService;

@Controller
@RequestMapping("/backlog")
public class BacklogController {
	
	private static final String HOME = "backlog/home";
	private static final String FORM = "backlog/form";

	@Autowired
	private BacklogService backlogService;
	
	@Autowired
	private AnnotationService annotationService;
	
	@RequestMapping(value="/home", method=RequestMethod.GET)
	public ModelAndView home() throws InterruptedException{
		Thread.sleep(5000);
		return new ModelAndView(HOME)
				.addObject("backlogs", backlogService.find());
	}

	@RequestMapping(value="/form", method=RequestMethod.GET)
	public ModelAndView form(@RequestParam(required=false) String text){
		final Backlog backlog = new Backlog();
		backlog.setOrigin(text);
		return form(backlog);
	}

	@RequestMapping(value="/", method=RequestMethod.POST)
	public ModelAndView submit(@Valid @ModelAttribute Backlog backlog, BindingResult result, HttpServletResponse response){
		if(result.hasErrors()){
			response.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
			return form(backlog);
		}
		return null;
	}

	private ModelAndView form(final Backlog backlog) {
		return new ModelAndView(FORM)
				.addObject("backlog", backlog);
	}
	
}