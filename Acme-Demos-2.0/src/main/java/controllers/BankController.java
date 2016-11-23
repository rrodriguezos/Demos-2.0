package controllers;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.BankService;
import services.InvestmentService;
import services.LoanService;
import domain.Bank;
import domain.Investment;
import domain.Loan;
import forms.BankRegisterForm;

@Controller
@RequestMapping("/bank")
public class BankController extends AbstractController {

	// Services ---------------------------------------------------------------
	@Autowired
	private BankService bankService;
	@Autowired
	private LoanService loanService;
	@Autowired
	private InvestmentService investmentService;


	// Constructors -----------------------------------------------------------

	public BankController() {
		super();
	}

	// List ------------------------------------------------------------------
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Bank> banks;

		banks = bankService.findAll();

		result = new ModelAndView("bank/list");
		result.addObject("banks", banks);
		result.addObject("requestUri", "bank/list.do");

		return result;
	}
	// Search ------------------------------------------------------------------
	@RequestMapping(value = "/searchResult", method = RequestMethod.GET)
	public ModelAndView searchResult(@RequestParam(defaultValue="") String q) {
		ModelAndView result;
		
		if("".equals(q)) {
			result = new ModelAndView("redirect:list.do");
		} else {
			result = new ModelAndView("bank/list");
			result.addObject("q", q);
			result.addObject("banks", bankService.searchByKeyword(q));
			result.addObject("requestURI", "bank/searchResult.do");
		}
		
		return result;
	}

	// Create ---------------------------------------------------------------

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		BankRegisterForm bankRegisterForm;

		bankRegisterForm = new BankRegisterForm();

		result = new ModelAndView("bank/register");
		result.addObject("bankRegisterForm", bankRegisterForm);
		return result;
	}

	// Save ----------------------------------------------------------------

	@RequestMapping(value = "/register", method = RequestMethod.POST, params = "save")
	public ModelAndView register(@Valid BankRegisterForm bankRegisterForm,
			BindingResult binding) {
		ModelAndView result;
		Bank bank;
		Boolean verificarContrasenas;

		verificarContrasenas = bankRegisterForm.getPassword().equals(
				bankRegisterForm.getConfirmPassword());

		if (binding.hasErrors()
				|| !verificarContrasenas
				|| !bankRegisterForm.getAccept()
				|| (bankRegisterForm.getEmailAddress().isEmpty() && bankRegisterForm
						.getPhone().isEmpty())
				|| (!bankRegisterForm.getEmailAddress().isEmpty() && bankRegisterForm
						.getPhone().length() != 9)) {
			result = createEditModelAndView(bankRegisterForm,
					"register.commit.error");
			if (!verificarContrasenas) {
				result.addObject("message", "register.commit.password");
			}
			if (!bankRegisterForm.getAccept()) {
				result.addObject("message", "register.commit.condition");
			}
			if (bankRegisterForm.getEmailAddress().isEmpty()
					&& bankRegisterForm.getPhone().isEmpty()) {
				result.addObject("message", "register.not.phone.neither.mail");
			}
			if (!bankRegisterForm.getPhone().isEmpty()
					&& bankRegisterForm.getPhone().length() != 9) {
				result.addObject("message", "register.phone.wrongLength");
			}
		} else {
			try {
				bank = bankService.reconstruct(bankRegisterForm);
				bankService.save(bank);
				result = new ModelAndView("redirect:/security/login.do");
			} catch (Throwable oops) {
				result = new ModelAndView("bank/register");
				result.addObject("bankRegisterForm", bankRegisterForm);

				if (oops instanceof DataIntegrityViolationException) {
					result.addObject("message",
							"register.commit.duplicatedBankname");
				} else {
					result.addObject("message", "register.commit.error");
				}
			}
		}

		return result;
	}

	// Display -----------------------------------------------------------------
	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(int bankId) {
		ModelAndView result;
		Bank bank;
		Collection<Loan> loans;
		Collection<Investment> investments;

		bank = bankService.findOne(bankId);
		loans = loanService.findLoansByBank(bankId);
		investments = investmentService.findInvestmentsByBank(bankId);
		
		
		result = new ModelAndView("bank/display");
		result.addObject("id",bank.getId());
		result.addObject("bank", bank);
		result.addObject("loans", loans);
		result.addObject("investments", investments);

		return result;
	}
	
	// ListByInvestment -----------------------------------------------------------
			@RequestMapping(value = "/listByInvestment")
			public ModelAndView listBySlot(@RequestParam int investmentId) {
				ModelAndView result;
				Bank bank;
				bank = bankService.bankByInvestment(investmentId);
				
				

				result = new ModelAndView("bank/list");
				result.addObject("banks", bank);
				result.addObject("requestUri", "bank/list.do");

				return result;
			}

	// Ancillary methods
	// --------------------------------------------------------

	protected ModelAndView createEditModelAndView(BankRegisterForm userForm) {
		ModelAndView result;

		result = createEditModelAndView(userForm, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(
			BankRegisterForm bankRegisterForm, String message) {
		ModelAndView result;

		result = new ModelAndView("bank/register");

		result.addObject("bankRegisterForm", bankRegisterForm);
		result.addObject("message", message);
		return result;
	}

}
