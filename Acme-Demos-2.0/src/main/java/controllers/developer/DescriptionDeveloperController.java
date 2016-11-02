package controllers.developer;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import services.DemoService;
import services.DescriptionService;
import controllers.AbstractController;
import domain.Demo;
import domain.Description;

@Controller
@RequestMapping("/description/developer")
public class DescriptionDeveloperController extends AbstractController {

	// Supporting services -------------------------------

	@Autowired
	private DescriptionService descriptionService;
	@Autowired
	private DemoService demoService;

	// Constructors
	// ----------------------------------------------------------------

	public DescriptionDeveloperController() {
		super();
	}

	// Create-----------------------------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int demoId) {
		ModelAndView result;
		Description description;
		Demo demo;

		description = descriptionService.create();
		demo = demoService.findOne(demoId);
		description.setDemo(demo);

		result = createEditModelAndView(description);

		return result;
	}

	// Save-------------------------------------------------------------------------
	@RequestMapping(value = "/create", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Description description,
			BindingResult binding, RedirectAttributes redir) {
		ModelAndView result;

		if (binding.hasErrors()) {
			result = createEditModelAndView(description);

		} else {
			try {
				descriptionService.save(description);
				result = new ModelAndView(
						"redirect:/description/list.do?demoId="
								+ description.getDemo().getId());
				redir.addFlashAttribute("message", "description.commit.ok");

			} catch (Throwable oops) {
				result = createEditModelAndView(description);

				result.addObject("message", "description.commit.error");
			}
		}

		return result;
	}

	// Edit -----------------------------------------------------------------
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView editSave(@RequestParam int descriptionId) {
		ModelAndView result;
		Description description;
		description = descriptionService.findOne(descriptionId);

		result = createEditModelAndView(description);
		result.addObject("description", description);
		return result;
	}

	// Save --------------------------------------------------------------
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "saveEdit")
	public ModelAndView saveEdit(@Valid Description description,
			BindingResult binding, RedirectAttributes redir) {
		ModelAndView result;

		if (binding.hasErrors()) {
			result = createEditModelAndView(description);

		} else {
			try {
				descriptionService.save(description);
				result = new ModelAndView("redirect:/demo/developer/mylist.do");
				result.addObject("requestUri", "/demo/developer/mylist.do");
				redir.addFlashAttribute("message", "description.commit.ok");

			} catch (Throwable oops) {
				result = createEditModelAndView(description,
						"description.commit.error");
			}
		}

		return result;
	}

	// Delete-----------------------------------------------------------------------
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView save(@RequestParam int descriptionId) {
		ModelAndView result;

		Description desccription = descriptionService.findOne(descriptionId);

		descriptionService.delete(desccription);

		result = new ModelAndView("redirect:/demo/developer/mylist.do");
		result.addObject("requestUri", "/demo/developer/mylist.do");

		return result;
	}

	// Ancillary methods--------------------------------------------------------

	protected ModelAndView createEditModelAndView(Description description) {
		ModelAndView result;

		result = createEditModelAndView(description, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(Description description,
			String message) {
		ModelAndView result;

		result = new ModelAndView("description/create");

		result.addObject("description", description);
		result.addObject("message", message);

		return result;
	}

}
