package controllers.investor;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.WhitePaperService;
import controllers.AbstractController;
import domain.WhitePaper;

@Controller
@RequestMapping("/whitePaper/investor")
public class WhitePaperInvestorController extends AbstractController {

	// Constructors -------------------------------------------------
	public WhitePaperInvestorController() {
		super();
	}
	
	// Supporting services --------------------------------
	@Autowired
	private WhitePaperService whitePaperService;

	
	// List -----------------------------------------------
	@RequestMapping(value = "/list")
	public ModelAndView list() {

		ModelAndView result;
		Collection<WhitePaper> whitePapers;

		whitePapers = whitePaperService.findAllByPrincipal();

		result = new ModelAndView("whitePaper/investor/mylist");
		result.addObject("whitePapers", whitePapers);
		result.addObject("requestUri", "/whithePaper/investor/list.do");
		
		return result;
	}
	
	// Create -------------------------------------------------------
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		WhitePaper whitePaper;

		whitePaper = whitePaperService.create();

		result = createEditModelAndView(whitePaper);

		return result;
	}

	// Edit -----------------------------------------------------------------
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int whitePaperId) {
		ModelAndView result;
		WhitePaper whitePaper;
		
		whitePaper = whitePaperService.findOne(whitePaperId);

		result = createEditModelAndView(whitePaper);
		result.addObject("whitePaper", whitePaper);
		
		return result;
	}
	
	// Save -----------------------------------------------
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid WhitePaper whitePaper, BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			result = createEditModelAndView(whitePaper);
		} else {
			try {
				whitePaperService.save(whitePaper);
				
				result = new ModelAndView("redirect:/whitePaper/investor/list.do");
			} catch (Throwable oops) {
				result = createEditModelAndView(whitePaper, "whitePaper.commit.error");
			}
		}

		return result;
	}
	
	// Delete --------------------------------------------------------------
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(@Valid WhitePaper whitePaper, BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			result = createEditModelAndView(whitePaper);
		} else {
			try {
				whitePaperService.delete(whitePaper);
				result = new ModelAndView("redirect:/whitePaper/investor/list.do");
			} catch (Throwable oops) {
				result = createEditModelAndView(whitePaper, "whitePaper.commit.error");
			}
		}
		return result;
	}
	
	// Ancillary methods --------------------------------------------

	protected ModelAndView createEditModelAndView(WhitePaper whitePaper) {
		ModelAndView result;

		result = createEditModelAndView(whitePaper, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(WhitePaper whitePaper, String message) {
		ModelAndView result;

		result = new ModelAndView("whitePaper/investor/edit");
		result.addObject("whitePaper", whitePaper);
		result.addObject("message", message);

		return result;
	}
	
}
