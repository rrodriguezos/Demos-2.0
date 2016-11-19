package controllers.bank;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.LoanService;
import controllers.AbstractController;
import domain.Loan;

@Controller
@RequestMapping("/loan/bank")
public class LoanBankController extends AbstractController {

	// Constructors -------------------------------------------------
	public LoanBankController() {
		super();
	}
	
	// Supporting services ------------------------------------------
	@Autowired
	private LoanService loanService;
	
	// List ---------------------------------------------------------
	@RequestMapping(value = "/list")
	public ModelAndView list() {
		ModelAndView result;
		Collection<Loan> loans;

		loans = loanService.findLoansByBankPrincipal();

		result = new ModelAndView("loan/list");
		result.addObject("loans", loans);
		result.addObject("requestUri", "loan/bank/list.do");
		
		return result;
	}
	
	// Approve ------------------------------------------------------
	@RequestMapping(value = "/approve", method = RequestMethod.GET)
	public ModelAndView approve(@RequestParam int loanId) {
		ModelAndView result;
		
		try {
			loanService.approve(loanId);

			result = new ModelAndView("redirect:/loan/bank/list.do");
		} catch (Throwable oops) {
			if (oops.getMessage().startsWith("loan")) {
				result = listModelAndView(oops.getMessage());
			} else {
				result = listModelAndView("loan.commit.error");
			}
		}
		
		return result;
	}
	
	// Deny ---------------------------------------------------------
	@RequestMapping(value = "/deny", method = RequestMethod.GET)
	public ModelAndView deny(@RequestParam int loanId) {
		ModelAndView result;
		
		try {
			loanService.deny(loanId);

			result = new ModelAndView("redirect:/loan/bank/list.do");
		} catch (Throwable oops) {
			if (oops.getMessage().startsWith("loan")) {
				result = listModelAndView(oops.getMessage());
			} else {
				result = listModelAndView("loan.commit.error");
			}
		}

		return result;
	}
	
	// Ancillary methods --------------------------------------------
	public ModelAndView listModelAndView(String message) {
		ModelAndView result;
		Collection<Loan> loans;

		loans = loanService.findLoansByBankPrincipal();
		
		result = new ModelAndView("loan/list");
		result.addObject("loans", loans);
		result.addObject("requestUri", "loan/bank/list.do");
		result.addObject("message", message);

		return result;
	}
	
	
	

}
