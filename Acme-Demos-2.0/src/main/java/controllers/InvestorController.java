package controllers;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.InvestmentService;
import services.InvestorService;
import domain.Investment;
import domain.Investor;
import forms.InvestorRegisterForm;

@Controller
@RequestMapping("/investor")
public class InvestorController extends AbstractController {
	// Services ---------------------------------------------------------------
		@Autowired
		private InvestorService investorService;
		@Autowired
		private InvestmentService investmentService;

		// Constructors -----------------------------------------------------------

		public InvestorController() {
			super();
		}

		// List ------------------------------------------------------------------
		@RequestMapping(value = "/list", method = RequestMethod.GET)
		public ModelAndView list() {
			ModelAndView result;
			Collection<Investor> investors;

			investors = investorService.findAll();

			result = new ModelAndView("investor/list");
			result.addObject("investors", investors);
			result.addObject("requestUri", "investor/list.do");

			return result;
		}

		// Create ---------------------------------------------------------------

		@RequestMapping(value = "/register", method = RequestMethod.GET)
		public ModelAndView create() {
			ModelAndView result;
			InvestorRegisterForm investorRegisterForm;

			investorRegisterForm = new InvestorRegisterForm();

			result = new ModelAndView("investor/register");
			result.addObject("investorRegisterForm", investorRegisterForm);
			return result;
		}

		// Save ----------------------------------------------------------------

		@RequestMapping(value = "/register", method = RequestMethod.POST, params = "save")
		public ModelAndView register(@Valid InvestorRegisterForm investorRegisterForm,
				BindingResult binding) {
			ModelAndView result;
			Investor investor;
			Boolean verificarContrasenas;

			verificarContrasenas = investorRegisterForm.getPassword().equals(
					investorRegisterForm.getConfirmPassword());

			if (binding.hasErrors() || !verificarContrasenas
					|| !investorRegisterForm.getAccept()) {
				result = createEditModelAndView(investorRegisterForm);
				if (!verificarContrasenas) {
					result.addObject("message", "register.commit.password");
				}
				if (!investorRegisterForm.getAccept()) {
					result.addObject("message", "register.commit.condition");
				}
			} else {
				try {
					investor = investorService.reconstruct(investorRegisterForm);
					investorService.save(investor);
					result = new ModelAndView("redirect:/");
				} catch (Throwable oops) {
					result = new ModelAndView("investor/register");
					result.addObject("investorRegisterForm", investorRegisterForm);

					if (oops instanceof DataIntegrityViolationException) {
						result.addObject("message",
								"register.commit.duplicatedInvestorname");
					} else {
						result.addObject("message", "register.commit.error");
					}
				}
			}

			return result;
		}

		// Display -----------------------------------------------------------------
		@RequestMapping(value = "/display", method = RequestMethod.GET)
		public ModelAndView display(int investorId) {
			ModelAndView result;
			Investor investor;
			Collection<Investment> investments;

			investor = investorService.findOne(investorId);
			investments = investmentService.findInvestmentByInvestor(investorId);

			result = new ModelAndView("investor/display");
			result.addObject("investor", investor);
			result.addObject("investments", investments);

			return result;
		}

		// Ancillary methods
		// --------------------------------------------------------

		protected ModelAndView createEditModelAndView(InvestorRegisterForm investorForm) {
			ModelAndView result;

			result = createEditModelAndView(investorForm, null);

			return result;
		}

		protected ModelAndView createEditModelAndView(InvestorRegisterForm investorForm,
				String message) {
			ModelAndView result;

			result = new ModelAndView("investor/register");

			result.addObject("investor", investorForm);
			result.addObject("message", message);
			return result;
		}


}
