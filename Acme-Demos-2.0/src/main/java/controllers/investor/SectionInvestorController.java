package controllers.investor;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.SectionService;
import controllers.AbstractController;
import domain.Section;

@Controller
@RequestMapping("/section/investor")
public class SectionInvestorController extends AbstractController {

	// Constructors -------------------------------------------------
	public SectionInvestorController() {
		super();
	}
	
	// Supporting services --------------------------------
	@Autowired
	private SectionService sectionService;

	// Create -------------------------------------------------------
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam int whitePaperId) {
		ModelAndView result;
		Section section;

		section = sectionService.create(whitePaperId);

		result = createEditModelAndView(section);

		return result;
	}
	
	// Save -----------------------------------------------
	@RequestMapping(value = "/create", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Section section, BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			result = createEditModelAndView(section);
		} else {
			try {
				sectionService.save(section);
				
				result = new ModelAndView("redirect:/section/list.do?whitePaperId=" + section.getWhitePaper().getId());
			} catch (Throwable oops) {
				result = createEditModelAndView(section, "section.commit.error");
			}
		}

		return result;
	}
	
	// Delete --------------------------------------------------------------
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam int sectionId) {
		ModelAndView result;
		Section section;

		section = sectionService.delete(sectionId);

		result = new ModelAndView("redirect:/section/list.do?whitePaperId=" + section.getWhitePaper().getId());
		
		return result;
	}
	
	
	// Ancillary methods --------------------------------------------

	protected ModelAndView createEditModelAndView(Section section) {
		ModelAndView result;

		result = createEditModelAndView(section, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(Section section, String message) {
		ModelAndView result;

		result = new ModelAndView("section/create");
		result.addObject("section", section);
		result.addObject("message", message);

		return result;
	}
	
}
