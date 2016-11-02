package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.DemoService;
import services.DescriptionService;
import services.DeveloperService;
import domain.Demo;
import domain.Description;
import domain.Developer;

@Controller
@RequestMapping("/description")
public class DescriptionController extends AbstractController {

	// Supporting services ----------------------------------------------------
	@Autowired
	private DescriptionService descriptionService;
	@Autowired
	private DemoService demoService;
	@Autowired
	private DeveloperService developerService;

	// Constructors -----------------------------------------------------------
	public DescriptionController() {
		super();
	}

	// List
	// ---------------------------------------------------------------------------
	@RequestMapping(value = "/list")
	public ModelAndView list(@RequestParam int demoId) {

		ModelAndView result;
		Collection<Description> descriptions;
		Demo demo;
		Developer developer;
		Boolean mydemo;
		descriptions = descriptionService.findDescriptionsByDemoId(demoId);
		result = new ModelAndView("description/list");
		result.addObject("descriptions", descriptions);
		result.addObject("demoId", demoId);
		mydemo = false;
		try {
			developer = developerService.findByPrincipal();
			demo = demoService.findOne(demoId);

			mydemo = developer.equals(demo.getDeveloper());

		} catch (Throwable oops) {

		}
		result.addObject("mydemo", mydemo);

		return result;
	}

	// Display --------------------------------------------------------
	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(int descriptionId) {
		ModelAndView result;
		Description description;
		Demo demo;
		Boolean mydemo;
		Boolean logeado;
		Developer developer;
		logeado = false;
		
		
		description = descriptionService.findOne(descriptionId);
		demo = demoService.findOne(description.getDemo().getId());
		mydemo = false;
		
		try {
			developer = developerService.findByPrincipal();
			if (developer != null) {
				logeado = true;
			}
			if (developer.equals(demo.getDeveloper())) {
				mydemo = true;
			}
		} catch (Throwable oops) {
			mydemo = false;
			logeado = false;
		}

		description = descriptionService.findOne(descriptionId);

		result = new ModelAndView("description/display");
		result.addObject("description", description);
		result.addObject("mydemo", mydemo);
		result.addObject("logeado", logeado);

		return result;
	}

}
