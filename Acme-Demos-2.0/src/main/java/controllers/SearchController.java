package controllers;

import java.util.Collection;
import java.util.HashSet;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import domain.Demo;

import forms.SearchForm;

import services.DemoService;

@Controller
@RequestMapping("/search")
public class SearchController extends AbstractController {
	
	@Autowired
	private DemoService demoService;
	
	@RequestMapping(value = "/buscar", method = RequestMethod.GET)
	public ModelAndView buscar() {
		ModelAndView result;
		SearchForm searchForm = new SearchForm();
		result = new ModelAndView("search/buscar");
		result.addObject("searchForm", searchForm);
		result.addObject("requestUri", "search/buscar.do");

		return result;
	}
	
	@RequestMapping(value = "/buscar", method = RequestMethod.POST, params = "search")
	public ModelAndView list(@Valid SearchForm searchForm,BindingResult binding) {
		ModelAndView result;
		if (binding.hasErrors()) {
			result = new ModelAndView("search/buscando");
			result.addObject("searchForm", searchForm);
		}else{
			try{
		
		Assert.notNull(searchForm);
		String text = searchForm.getText();
		Collection<Demo> demos = new HashSet<Demo>();
		demos = demoService.search(text);

		result = new ModelAndView("search/buscando");
		result.addObject("demos", demos);
		result.addObject("searchForm", searchForm);
		result.addObject("requestUri", "search/buscando.do");
			} catch (Throwable oops) {

				result = new ModelAndView("search/buscando");
				result.addObject("searchForm", searchForm);

			}
		}
		return result;
	}
	
	@RequestMapping(value = "/buscando", method = RequestMethod.GET)
	public ModelAndView lista(@Valid SearchForm searchForm) {
		ModelAndView result;

		Assert.notNull(searchForm);
		String text = searchForm.getText();
		Collection<Demo> demos = new HashSet<Demo>();
		demos = demoService.search(text);

		result = new ModelAndView("search/buscando");
		result.addObject("demos", demos);
		result.addObject("searchForm", searchForm);
		result.addObject("requestUri", "search/buscando.do");

		return result;
	}
}
