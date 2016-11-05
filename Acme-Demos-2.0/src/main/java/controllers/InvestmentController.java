package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.InvestmentService;
import domain.Investment;

@Controller
@RequestMapping("/investment")
public class InvestmentController extends AbstractController {

	// Supporting services ----------------------------------------------------
	@Autowired
	private InvestmentService investmentService;

	// Constructors -----------------------------------------------------------
	public InvestmentController() {
		super();
	}

	// List -----------------------------------------------------------
	@RequestMapping(value = "/list")
	public ModelAndView list(@RequestParam int bankId) {

		ModelAndView result;
		Collection<Investment> investments;

		investments = investmentService.findInvestmentsByBank(bankId);

		result = new ModelAndView("investment/list");
		result.addObject("investments", investments);
		result.addObject("bankId", bankId);

		return result;
	}

	// Display
	// ---------------------------------------------------------------------------
	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(int investmentId) {
		ModelAndView result;
		Investment investment;

		investment = investmentService.findOne(investmentId);

		result = new ModelAndView("investment/display");
		result.addObject("investment", investment);

		return result;
	}

	// Listing by navigate from Demo
	// -----------------------------------------------
	@RequestMapping(value = "/navigateByDemo", method = RequestMethod.GET)
	public ModelAndView navigateByDemo(@RequestParam int demoId) {
		ModelAndView result;
		Collection<Investment> investments = investmentService
				.investmentByDemo(demoId);
		result = new ModelAndView("investment/listAll");
		result.addObject("investments", investments);
		result.addObject("requestURI", "investments/navigateByDemo.do");
		return result;
	}

}
