package controllers;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import domain.Bank;


import domain.Loan;

import services.BankService;
import services.LoanService;

@Controller
@RequestMapping("/loan")
public class LoanController extends AbstractController {

	public LoanController() {
		super();
	}
	
	// Services --------------------------------------------------------
	@Autowired
	private LoanService loanService;
	@Autowired
	private BankService bankService;
	
	//List--------------------------------------------------
	@RequestMapping(value="/list")
	public ModelAndView list(){
		ModelAndView res = new ModelAndView("loan/list");
		Collection<Loan>l = loanService.findLoansByDevPrincipal();
		res.addObject("loans", l);
		res.addObject("requestUri", "loan/list.do");
		return res;
	}
	
	//Cancel----------------------------------------------------
	@RequestMapping(value = "/cancel", method = RequestMethod.GET)
	public ModelAndView cancel(@RequestParam int loanId,RedirectAttributes redir) {
		ModelAndView result;
		Loan loan = loanService.findOne(loanId);
		try{
			loanService.cancel(loan);
			result = new ModelAndView("redirect:/loan/list.do");
			redir.addFlashAttribute("message", "loan.cancelled");
		}catch(Throwable oops){
			result = new ModelAndView("redirect:/loan/list.do");
			redir.addFlashAttribute("message", "loan.cancelled.error");
		}
		return result;
	}
	
	// Edit--------------------------------------------------------------------------------
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int bankId) {	
		ModelAndView result;
		Loan l = loanService.create();
		Bank b = bankService.findOne(bankId);
		l.setBank(b);
		result = createEditModelAndView(l);
		return result;
	}
	
	
	// Save -----------------------------------------------
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Loan loan, BindingResult binding,RedirectAttributes redir) {
		ModelAndView result;
		if (binding.hasErrors()) {
			result = createEditModelAndView(loan);
		}else {
			try {
				loanService.save(loan);
				result = new ModelAndView("redirect:/loan/list.do");
				redir.addFlashAttribute("message", "loan.ok");
			}catch (Throwable oops) {
				result = createEditModelAndView(loan, "loan.commit.error");
			}
		}
		return result;
	}
	
	// Ancillary methods ----------------------------------
	protected ModelAndView createEditModelAndView(Loan loan) {
		ModelAndView result;
		result = createEditModelAndView(loan, null);

		return result;
	}
	protected ModelAndView createEditModelAndView(Loan loan, String message) {
		ModelAndView result;

		result = new ModelAndView("loan/edit");
		result.addObject("loan", loan);
		result.addObject("message", message);

		return result;
	}
	

}
