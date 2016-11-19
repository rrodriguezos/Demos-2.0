package controllers.investor;

import java.util.Collection;


import javax.validation.Valid;

import org.apache.commons.lang.NullArgumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import services.BannerService;
import services.DemoService;
import controllers.AbstractController;
import domain.Banner;
import domain.Demo;


@Controller
@RequestMapping("/demo/investor")
public class DemoInvestorController  extends AbstractController {

	// Supporting services -------------------------------

	@Autowired
	private DemoService demoService;
	@Autowired
	private	BannerService bannerService;
	

	// Constructors --------------------------------------
	public DemoInvestorController() {
		super();
	}
	
	//Sponsor----------------------------------------------
	@RequestMapping(value = "/sponsor", method = RequestMethod.GET)
	public ModelAndView sponsor(@RequestParam int demoId, RedirectAttributes redir) {
		ModelAndView result = null; 
		
		Demo d = demoService.findOne(demoId);
		if(d.getBanner() == null){
			result = createEditModelAndView(d);
		}else{	
			result = new ModelAndView("redirect:/demo/display.do?demoId="+demoId); 
			redir.addFlashAttribute("message", "demo.sponsor.error");
		}	
		return result;
	}
	
	@RequestMapping(value = "/sponsor", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Demo demo, BindingResult binding,
			RedirectAttributes redir) {
		ModelAndView result;

		if (binding.hasErrors()) {
			result = createEditModelAndView(demo, "demo.commit.error");

		} else {
			try {
				if(demo.getBanner() == null)
					throw new Exception();
				
				demoService.sponsor(demo);
				result = new ModelAndView("redirect:/demo/display.do?demoId="+demo.getId());
				result.addObject("requestUri", "/demo/display.do");
				redir.addFlashAttribute("message", "demo.sponsor.ok");

			} catch (Throwable oops) {
				result = createEditModelAndView(demo, "demo.commit.error");
			}
		}

		return result;
	}
	
	
	// Ancillary methods
	// --------------------------------------------------------
	protected ModelAndView createEditModelAndView(Demo demo) {
		ModelAndView result;
		result = createEditModelAndView(demo, null);
		return result;
	}

	protected ModelAndView createEditModelAndView(Demo demo, String message) {
		ModelAndView result;
		result = new ModelAndView("demo/sponsor");
		result.addObject("demo", demo);
		result.addObject("message", message);
		Collection<Banner> bans = bannerService.bannersByPrincipal();
		result.addObject("banners", bans);
		return result;
	}

}
