package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.InvestorService;
import services.SectionService;
import services.WhitePaperService;
import domain.Investor;
import domain.Section;
import domain.WhitePaper;

@Controller
@RequestMapping("/section")
public class SectionController extends AbstractController {

	// Supporting services ----------------------------------------------------
	@Autowired
	private SectionService sectionService;
	@Autowired
	private WhitePaperService whitePaperService;
	@Autowired
	private InvestorService investorService;

	// Constructors -----------------------------------------------------------
	public SectionController() {
		super();
	}

	// List
	// ---------------------------------------------------------------------------
	@RequestMapping(value = "/list")
	public ModelAndView list(@RequestParam int whitePaperId) {

		ModelAndView result;
		Collection<Section> sections;
		WhitePaper whitePaper;
		Investor investor;
		Boolean mywhitePaper;
		sections = sectionService.sectionsByWhitePaper(whitePaperId);
		result = new ModelAndView("section/list");
		result.addObject("sections", sections);
		result.addObject("whitePaperId", whitePaperId);
		mywhitePaper = false;
		try {
			investor = investorService.findByPrincipal();
			whitePaper = whitePaperService.findOne(whitePaperId);

			mywhitePaper = investor.equals(whitePaper.getInvestor());

		} catch (Throwable oops) {

		}
		result.addObject("mywhitePaper", mywhitePaper);
		return result;
	}

	// Display --------------------------------------------------------
	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(int sectionId) {
		ModelAndView result;
		Section section;

		section = sectionService.findOne(sectionId);

		result = new ModelAndView("section/display");
		result.addObject("section", section);

		return result;
	}

}
