package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.InvestorService;
import services.WhitePaperService;
import domain.Investor;
import domain.WhitePaper;

@Controller
@RequestMapping("/whitePaper")
public class WhitePaperController extends AbstractController {
	// Supporting services ----------------------------------------------------
	@Autowired
	private WhitePaperService whitePaperService;

	@Autowired
	private InvestorService investorService;

	// Constructors -----------------------------------------------------------
	public WhitePaperController() {
		super();
	}

	// List -----------------------------------------------------------
	@RequestMapping(value = "/list")
	public ModelAndView list(@RequestParam int investorId) {

		ModelAndView result;
		Collection<WhitePaper> whitePapers;
		Investor investor;

		investor = investorService.findOne(investorId);

		whitePapers = whitePaperService.whitePapersByInvestor(investorId);

		result = new ModelAndView("whitePaper/list");
		result.addObject("whitePapers", whitePapers);
		result.addObject("investorId", investorId);
		result.addObject("requestUri", "/whitePaper/list.do");

		return result;
	}

	// Display -----------------------------------------------------------
	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam int whitePaperId) {
		ModelAndView result;
		WhitePaper whitePaper;
		Investor investor;
		Boolean mywhitePaper;
		Boolean logeado;
		whitePaper = whitePaperService.findOne(whitePaperId);
		mywhitePaper = false;
		logeado = false;

		try {
			investor = investorService.findByPrincipal();
			if (investor != null) {
				logeado = true;
			}
			if (investor.equals(whitePaper.getInvestor())) {
				mywhitePaper = true;
			}
		} catch (Throwable oops) {
			mywhitePaper = false;
			logeado = false;
		}

		whitePaper = whitePaperService.findOne(whitePaperId);

		result = new ModelAndView("whitePaper/display");
		result.addObject("whitePaper", whitePaper);

		result.addObject("mywhitePaper", mywhitePaper);
		result.addObject("logeado", logeado);

		return result;

	}

}
