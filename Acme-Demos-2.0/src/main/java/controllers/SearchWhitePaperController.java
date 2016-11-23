package controllers;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.WhitePaperService;
import domain.WhitePaper;
import forms.SearchFormWhitePaper;

@Controller
@RequestMapping("/searchWhitePaper")
public class SearchWhitePaperController extends AbstractController {

	@Autowired
	private WhitePaperService whitePaperService;

	@RequestMapping(value = "/buscar", method = RequestMethod.GET)
	public ModelAndView buscar() {
		ModelAndView result;
		SearchFormWhitePaper searchFormWhitePaper = new SearchFormWhitePaper();
		result = new ModelAndView("searchWhitePaper/buscar");
		result.addObject("searchFormWhitePaper", searchFormWhitePaper);
		result.addObject("requestUri", "searchWhitePaper/buscar.do");

		return result;
	}

	@RequestMapping(value = "/buscar", method = RequestMethod.POST, params = "search")
	public ModelAndView list(@Valid SearchFormWhitePaper searchFormWhitePaper,
			BindingResult binding) {
		ModelAndView result;
		if (binding.hasErrors()) {
			result = new ModelAndView("searchWhitePaper/buscando");
			result.addObject("searchFormWhitePaper", searchFormWhitePaper);
		} else {
			try {

				Assert.notNull(searchFormWhitePaper);
				String text = searchFormWhitePaper.getText();
				Date date1 = searchFormWhitePaper.getDateFirst();
				Date date2 = searchFormWhitePaper.getDateSecond();
				Collection<WhitePaper> whitePapers = new HashSet<WhitePaper>();
				whitePapers = whitePaperService.searchByKeyword(text, date1,
						date2);

				result = new ModelAndView("searchWhitePaper/buscando");
				result.addObject("whitePapers", whitePapers);
				result.addObject("searchFormWhitePaper", searchFormWhitePaper);
				result.addObject("requestUri", "searchWhitePaper/buscando.do");
			} catch (Throwable oops) {

				result = new ModelAndView("searchWhitePaper/buscando");
				result.addObject("searchFormWhitePaper", searchFormWhitePaper);

			}
		}
		return result;
	}

	@RequestMapping(value = "/buscando", method = RequestMethod.GET)
	public ModelAndView lista(@Valid SearchFormWhitePaper searchFormWhitePaper) {
		ModelAndView result;

		Assert.notNull(searchFormWhitePaper);
		String text = searchFormWhitePaper.getText();
		Date date1 = searchFormWhitePaper.getDateFirst();
		Date date2 = searchFormWhitePaper.getDateSecond();
		Collection<WhitePaper> whitePapers = new HashSet<WhitePaper>();
		whitePapers = whitePaperService.searchByKeyword(text, date1, date2);

		result = new ModelAndView("searchWhitePaper/buscando");
		result.addObject("whitePapers", whitePapers);
		result.addObject("searchFormWhitePaper", searchFormWhitePaper);
		result.addObject("requestUri", "searchWhitePaper/buscando.do");

		return result;
	}

}
