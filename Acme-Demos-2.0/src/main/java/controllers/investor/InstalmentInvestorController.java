package controllers.investor;

import java.util.Collection;
import java.util.Date;

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

@Controller
@RequestMapping("/instalment/investor")
public class InstalmentInvestorController extends AbstractController {

	// Supporting services --------------------------------
	@Autowired
	private InstalmentService instalmentService;

	
	// Constructors ---------------------------------------
	public InstalmentInvestorController() {
		super();
	}
	//List--------------------------------------------------
	//Listing
		@RequestMapping(value="/list")
		public ModelAndView list(@RequestParam int investmentId){
			ModelAndView res = new ModelAndView("instalment/list");
			Collection<Instalment> ins = instalmentService.findByInvestment(investmentId);
			res.addObject("instalments", ins);
			res.addObject("requestUri", "instalment/investor/list.do");
			res.addObject("investmentId", investmentId);
			return res;
		}
	
	// Create ---------------------------------------------
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam Integer investmentId) {
		ModelAndView result;
		Instalment instalment;

		instalment = instalmentService.create(investmentId);

		result = createEditModelAndView(instalment);

		return result;
	}
	// Edit -----------------------------------------------------------------
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int instalmentId, RedirectAttributes redir) {
		ModelAndView result; 
		Date actual = new Date(System.currentTimeMillis() - 100);
		Instalment i = instalmentService.findOne(instalmentId);
		
		if(actual.before(i.getInstalmentDate())){
			result = createEditModelAndView(i);
			result.addObject("instalment", i);
		}else{
			result = new ModelAndView("redirect:/instalment/investor/list.do?investmentId=" + i.getInvestment().getId());
			redir.addFlashAttribute("message", "instalment.started");
		}
		return result;
	}
	 

	// Save -----------------------------------------------
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Instalment instalment, BindingResult binding,RedirectAttributes redir) {
		ModelAndView result;

		if (binding.hasErrors()) {
			result = createEditModelAndView(instalment);
		} else {
			try {
				instalmentService.save(instalment);
				result = new ModelAndView("redirect:/instalment/investor/list.do?investmentId=" + instalment.getInvestment().getId());
				redir.addFlashAttribute("message", "instalment.ok");
			} catch (Throwable oops) {
				result = createEditModelAndView(instalment, "instalment.commit.error");
			}
		}

		return result;
	}
	
	// Ancillary methods ----------------------------------
	protected ModelAndView createEditModelAndView(Instalment instalment) {
		ModelAndView result;

		result = createEditModelAndView(instalment, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(Instalment instalment, String message) {
		ModelAndView result;

		result = new ModelAndView("instalment/edit");
		result.addObject("instalment", instalment);
		result.addObject("message", message);

		return result;
	}

}
