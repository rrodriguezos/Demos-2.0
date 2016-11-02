package controllers.administrator;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.CommentService;
import services.DemoService;
import services.DeveloperService;
import services.InstalmentService;
import services.InvestmentService;
import controllers.AbstractController;
import domain.Demo;
import domain.Developer;

@Controller
@RequestMapping("/dashboard/administrator")
public class DashboardAdministratorController extends AbstractController {

	// Services -------------------------------------------
	@Autowired
	private CommentService commentService;
	
	@Autowired
	private DemoService demoService;
	
	@Autowired
	private DeveloperService developerService;
	
	@Autowired
	private InvestmentService investmentService;
	
	@Autowired
	private InstalmentService instalmentService;

	
	// Constructor ----------------------------------------
	public DashboardAdministratorController() {
		super();
	}

	// Dashboard ------------------------------------------
	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public ModelAndView dashboard() {
		ModelAndView result;
		Double avgNumberCommentsPerDemo;
		Collection<Demo> demos25PercentageMoreCommentsThanAvg;
		Collection<Developer> developersMoreCommentsThanAvg;
		Double ratioDemosWithInvestment;
		Collection<Object[]> avgStddevInvestmentsPerInvestor;
		Double avgMoneyInvestInDemos;
		Double avgNumberInstalmentsPerInvestment;
		
		
		avgNumberCommentsPerDemo = commentService.averageCommentsPerDemo();
		
		demos25PercentageMoreCommentsThanAvg = demoService.demos25PercentageMoreCommentsThanAvg();
		
		developersMoreCommentsThanAvg = developerService.developersMoreCommentsThanAvg();
		
		ratioDemosWithInvestment = demoService.ratioDemosWithInvestment();
		
		avgStddevInvestmentsPerInvestor = investmentService.avgStddevInvestmentsPerInvestor();
		
		avgMoneyInvestInDemos = instalmentService.avgMoneyInvestInDemos();
		
		avgNumberInstalmentsPerInvestment = instalmentService.avgNumberInstalmentsPerInvestment();

		result = new ModelAndView("administrator/dashboard");
		result.addObject("avgNumberCommentsPerDemo", avgNumberCommentsPerDemo);
		result.addObject("demos25PercentageMoreCommentsThanAvg", demos25PercentageMoreCommentsThanAvg);
		result.addObject("developersMoreCommentsThanAvg", developersMoreCommentsThanAvg);
		
		result.addObject("ratioDemosWithInvestment", ratioDemosWithInvestment);
		result.addObject("avgStddevInvestmentsPerInvestor", avgStddevInvestmentsPerInvestor);
		result.addObject("avgMoneyInvestInDemos", avgMoneyInvestInDemos);
		result.addObject("avgNumberInstalmentsPerInvestment", avgNumberInstalmentsPerInvestment);
		
		result.addObject("requestURI", "/dashboard/administrator/dashboard.do");

		return result;
	}

}
