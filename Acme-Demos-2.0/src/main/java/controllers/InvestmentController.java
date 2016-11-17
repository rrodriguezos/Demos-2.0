package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.InvestmentService;
import services.InvestorService;
import domain.Investment;
import domain.Investor;

@Controller
@RequestMapping("/investment")
public class InvestmentController extends AbstractController {

	// Supporting services ----------------------------------------------------
	@Autowired
	private InvestmentService investmentService;

	@Autowired
	private InvestorService investorService;

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
		Boolean myinvestment;
		Investor investor;

		myinvestment = false;
		investment = investmentService.findOne(investmentId);

		try {
			investor = investorService.findByPrincipal();

			if (investor.equals(investment.getInvestor())) {
				myinvestment = true;
			}
		} catch (Throwable oops) {
			myinvestment = false;

		}

		result = new ModelAndView("investment/display");
		result.addObject("investment", investment);
		result.addObject("myinvestment", myinvestment);

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
