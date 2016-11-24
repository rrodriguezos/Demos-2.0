package controllers.bank;

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

import services.InstalmentService;
import controllers.AbstractController;
import domain.Instalment;
import domain.Loan;
import forms.CreateInstalmentsForm;

@Controller
@RequestMapping("/instalment/bank")
public class InstalmentBankController extends AbstractController {

	
	// Constructors -------------------------------------------------
	public InstalmentBankController() {
		super();
	}
	
	
	// Supporting services ------------------------------------------
	@Autowired
	private InstalmentService instalmentService;

	
	//List ----------------------------------------------------------
	@RequestMapping(value="/list")
	public ModelAndView list(@RequestParam int loanId){
		ModelAndView result;
		Collection<Instalment> instalments;
		
		instalments = instalmentService.findByLoan(loanId);
		
		result = new ModelAndView("instalment/list");
		result.addObject("instalments", instalments);
		result.addObject("requestUri", "instalment/bank/list.do");
		result.addObject("loanId", loanId);
		
		return result;
	}
	
	//Pay -----------------------------------------------------------
	@RequestMapping(value="/pay")
	public ModelAndView pay(@RequestParam int instalmentId,RedirectAttributes redir){
		ModelAndView result;
		Instalment instalment;
		Instalment inst2 = instalmentService.findOne(instalmentId);
		try{
			instalment = instalmentService.pay(instalmentId);
			result = new ModelAndView("redirect:/instalment/bank/list.do?loanId=" + instalment.getLoan().getId());
		}catch(Throwable oops){
			result = new ModelAndView("redirect:/instalment/bank/list.do?loanId=" + inst2.getLoan().getId());
			redir.addFlashAttribute("message", "instalment.before");
			
		}
		
		
		return result;
	}
	
	// Create ---------------------------------------------
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam Integer loanId) {
		ModelAndView result;
		CreateInstalmentsForm createInstalmentsForm;

		createInstalmentsForm = new CreateInstalmentsForm();
		createInstalmentsForm.setLoanId(loanId);
		
		result = new ModelAndView("instalment/edit");
		result.addObject("createInstalmentsForm", createInstalmentsForm);

		return result;
	}
	
	// Save -----------------------------------------------
	@RequestMapping(value = "/create", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid CreateInstalmentsForm form, BindingResult binding,RedirectAttributes redir) {
		ModelAndView result;

		if (binding.hasErrors()) {
			result = createEditModelAndView(form);
		} else {
			try {
				instalmentService.createAndSave(form);
				result = new ModelAndView("redirect:/instalment/bank/list.do?loanId=" + form.getLoanId());
			} catch (Throwable oops) {
				result = createEditModelAndView(form, "instalment.commit.error");
			}
		}

		return result;
	}
	
	// Ancillary methods ----------------------------------
	protected ModelAndView createEditModelAndView(CreateInstalmentsForm form) {
		ModelAndView result;

		result = createEditModelAndView(form, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(CreateInstalmentsForm form, String message) {
		ModelAndView result;

		result = new ModelAndView("instalment/edit");
		result.addObject("createInstalmentsForm", form);
		result.addObject("message", message);

		return result;
	}
	
	
}
