package controllers.investor;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import services.BannerService;

import controllers.AbstractController;
import domain.Banner;


@Controller
@RequestMapping("/banner/investor")
public class BannerInvestorController extends AbstractController {

	public BannerInvestorController() {
		super();
	}
	
	
	// Supporting services --------------------------------
	@Autowired
	private BannerService bannerService;
	
	
	// Create ---------------------------------------------
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Banner b;

		b = bannerService.create();

		result = createEditModelAndView(b);

		return result;
	}
	
	// Save -----------------------------------------------
	@RequestMapping(value = "/create", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Banner banner, BindingResult binding,RedirectAttributes redir) {
		ModelAndView result;

		if (binding.hasErrors()) {
			result = createEditModelAndView(banner);
		} else {
			try {
				bannerService.save(banner);
				result = new ModelAndView("redirect:/welcome/index.do");
				redir.addFlashAttribute("message", "banner.ok");
			} catch (Throwable oops) {
				result = createEditModelAndView(banner, "banner.commit.error");
			}
		}

		return result;
	}
	
	
	// Ancillary methods ----------------------------------
	protected ModelAndView createEditModelAndView(Banner banner) {
		ModelAndView result;

		result = createEditModelAndView(banner, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(Banner banner, String message) {
		ModelAndView result;

		result = new ModelAndView("banner/create");
		result.addObject("banner", banner);
		result.addObject("message", message);

		return result;
	}

	

}
