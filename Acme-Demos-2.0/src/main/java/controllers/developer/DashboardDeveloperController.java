package controllers.developer;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.CommentService;
import controllers.AbstractController;
import domain.Demo;

@Controller
@RequestMapping("/dashboard/developer")
public class DashboardDeveloperController extends AbstractController {

	// Services -----------------------
	@Autowired
	private CommentService commentService;

	// Constructor --------------------
	public DashboardDeveloperController() {
		super();
	}

	// Listing ------------------------
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		// Average number of comments per demo.
		Double averageCommentsPerDemo = commentService.averageCommentsPerDemoByDeveloperId();

		// Average number of stars per demo.
		Double averageStarsPerDemo = commentService.averageStarsPerDemoByDeveloper();

		// List of demos sorted according the average number of stars that
		// they ve got
		Collection<Demo> demoSortedAverageNumberStars = commentService
				.demoSortedAverageNumberStarsByDeveloper();

		result = new ModelAndView("developer/dashboard");
		result.addObject("averageCommentsPerDemo", averageCommentsPerDemo);
		result.addObject("averageStarsPerDemo", averageStarsPerDemo);
		result.addObject("demoSortedAverageNumberStars",
				demoSortedAverageNumberStars);
		result.addObject("requestUri", "/dashboard/developer/list.do");

		return result;
	}

}
