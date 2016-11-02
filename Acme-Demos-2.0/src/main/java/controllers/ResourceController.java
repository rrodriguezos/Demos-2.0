package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.DemoService;
import services.DeveloperService;
import services.ResourceService;
import domain.Demo;
import domain.Developer;
import domain.Resource;

@Controller
@RequestMapping("/resource")
public class ResourceController extends AbstractController {

	// Supporting services ----------------------------------------------------
	@Autowired
	private ResourceService resourceService;
	@Autowired
	private DemoService demoService;
	@Autowired
	private DeveloperService developerService;

	// Constructors -----------------------------------------------------------
	public ResourceController() {
		super();
	}

	// List
	// ---------------------------------------------------------------------------
	@RequestMapping(value = "/list")
	public ModelAndView list(@RequestParam int demoId) {

		ModelAndView result;
		Collection<Resource> resources;
		Demo demo;
		Developer developer;
		Boolean mydemo;
		resources = resourceService.findResourcesByDemoId(demoId);
		result = new ModelAndView("resource/list");
		result.addObject("resources", resources);
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
	public ModelAndView display(int resourceId) {
		ModelAndView result;
		Resource resource;
		Demo demo;
		Boolean mydemo;
		Boolean logeado;
		Developer developer;
		logeado = false;

		resource = resourceService.findOne(resourceId);
		demo = demoService.findOne(resource.getDemo().getId());
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

		result = new ModelAndView("resource/display");
		result.addObject("resource", resource);
		result.addObject("mydemo", mydemo);
		result.addObject("logeado", logeado);

		return result;
	}

}
