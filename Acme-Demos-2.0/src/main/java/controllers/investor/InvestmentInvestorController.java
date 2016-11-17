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

import services.BankService;
import services.InvestmentService;
import controllers.AbstractController;
import domain.Bank;
import domain.Investment;

@Controller
@RequestMapping("/investment/investor")
public class InvestmentInvestorController extends AbstractController {

	// Supporting services --------------------------------
	@Autowired
	private InvestmentService investmentService;
	@Autowired
	private BankService bankService;

	// Constructors ---------------------------------------
	public InvestmentInvestorController() {
		super();
	}

	// List -----------------------------------------------
	@RequestMapping(value = "/list")
	public ModelAndView list() {

		ModelAndView result;
		Collection<Investment> investments;

		investments = investmentService.investmentsByInvestorLogged();

		result = new ModelAndView("investment/list");
		result.addObject("investments", investments);
		result.addObject("requestUri", "/investment/investor/list.do");

		return result;
	}

	// Create ---------------------------------------------
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam Integer demoId) {
		ModelAndView result;
		Investment investment;
		Collection<Bank> banks;

		banks = bankService.findAll();

		investment = investmentService.create(demoId);

		result = createEditModelAndView(investment);
		result.addObject("banks", banks);

		return result;
	}

	// Edit -----------------------------------------------------------------
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int investmentId) {
		ModelAndView result;
		Investment investment;
		Collection<Bank> banks;

		banks = bankService.findAll();
		investment = investmentService.findOne(investmentId);

		result = createEditModelAndView(investment);
		result.addObject("investment", investment);
		result.addObject("banks", banks);
		return result;
	}

	// Save -----------------------------------------------
	@RequestMapping(value = "/create", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Investment investment, BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			result = createEditModelAndView(investment);
		} else {
			try {
				investmentService.save(investment);

				result = new ModelAndView(
						"redirect:/investment/investor/list.do");
			} catch (Throwable oops) {
				result = createEditModelAndView(investment,
						"investment.commit.error");
			}
		}

		return result;
	}

	// Ancillary methods ----------------------------------
	protected ModelAndView createEditModelAndView(Investment investment) {
		ModelAndView result;

		result = createEditModelAndView(investment, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(Investment investment,
			String message) {
		ModelAndView result;

		result = new ModelAndView("investment/edit");
		result.addObject("investment", investment);
		result.addObject("message", message);

		return result;
	}

}
