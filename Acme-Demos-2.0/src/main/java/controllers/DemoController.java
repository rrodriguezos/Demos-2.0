package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.CommentService;
import services.DemoService;
import services.DescriptionService;
import services.DeveloperService;
import services.ResourceService;
import domain.Comment;
import domain.Demo;
import domain.Description;
import domain.Developer;
import domain.Resource;

@Controller
@RequestMapping("/demo")
public class DemoController extends AbstractController {

	// Supporting services ----------------------------------------------------
	@Autowired
	private DemoService demoService;

	@Autowired
	private CommentService commentService;

	@Autowired
	private DeveloperService developerService;

	@Autowired
	private ResourceService resourceService;

	@Autowired
	private DescriptionService descriptionService;

	// Constructors -----------------------------------------------------------
	public DemoController() {
		super();
	}

	// List -----------------------------------------------------------
	@RequestMapping(value = "/list")
	public ModelAndView list() {

		ModelAndView result;
		Collection<Demo> demos;

		demos = demoService.findAll();

		result = new ModelAndView("demo/list");
		result.addObject("demos", demos);
		result.addObject("requestUri", "/demo/list.do");

		return result;
	}

	// Display -----------------------------------------------------------
	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam int demoId) {
		ModelAndView result;
		Demo demo;
		Collection<Comment> comments;
		Collection<Resource> resources;
		Collection<Description> descriptions;
		Developer developer;
		Boolean mydemo;
		Boolean logeado;
		demo = demoService.findOne(demoId);
		mydemo = false;
		logeado = false;

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

		demo = demoService.findOne(demoId);
		comments = commentService.findCommentsByDemoId(demoId);
		resources = resourceService.findResourcesByDemoId(demoId);
		descriptions = descriptionService.findDescriptionsByDemoId(demoId);

		result = new ModelAndView("demo/display");
		result.addObject("demo", demo);

		result.addObject("mydemo", mydemo);
		result.addObject("logeado", logeado);
		result.addObject("resources", resources);
		result.addObject("descriptions", descriptions);
		result.addObject("comments", comments);

		return result;

	}
}
