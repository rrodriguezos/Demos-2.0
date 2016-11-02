package controllers.developer;

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

import services.DemoService;
import controllers.AbstractController;
import domain.Demo;

@Controller
@RequestMapping("/demo/developer")
public class DeveloperDemoController extends AbstractController {

	// Supporting services -------------------------------

	@Autowired
	private DemoService demoService;

	// Constructors --------------------------------------

	public DeveloperDemoController() {
		super();
	}

	// List ----------------------------------------------

	@RequestMapping(value = "/mylist")
	public ModelAndView list() {

		ModelAndView result;
		Collection<Demo> demos;

		demos = demoService.demoByDeveloperLogged();

		result = new ModelAndView("demo/developer/mylist");
		result.addObject("demos", demos);
		result.addObject("requestUri", "/demo/developer/mylist.do");
		return result;
	}

	// Create --------------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Demo demo;

		demo = demoService.create();

		result = createEditModelAndView(demo);

		return result;
	}

	// Edit -----------------------------------------------------------------
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int demoId) {
		ModelAndView result;
		Demo demo;
		demo = demoService.findOne(demoId);

		result = createEditModelAndView(demo);
		result.addObject("demo", demo);
		return result;
	}

	// Save --------------------------------------------------------------
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Demo demo, BindingResult binding,
			RedirectAttributes redir) {
		ModelAndView result;

		if (binding.hasErrors()) {
			result = createEditModelAndView(demo);

		} else {
			try {
				demoService.save(demo);
				result = new ModelAndView("redirect:/demo/developer/mylist.do");
				result.addObject("requestUri", "/demo/developer/mylist.do");
				redir.addFlashAttribute("message", "demo.commit.ok");

			} catch (Throwable oops) {
				result = createEditModelAndView(demo, "demo.commit.error");
			}
		}

		return result;
	}

	// Delete --------------------------------------------------------------
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(@Valid Demo demo, BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			System.out.print(binding.hasErrors());
			System.out.print(binding.getGlobalErrorCount());
			System.out.print(binding.getFieldError());
			result = createEditModelAndView(demo, binding.toString());
		} else {
			try {
				demoService.delete(demo);
				result = new ModelAndView("redirect:/demo/developer/mylist.do");
				result.addObject("requestUri", "/demo/developer/mylist.do");
			} catch (Throwable oops) {
				result = createEditModelAndView(demo, "demo.commit.error");
				System.out.print(binding.hasErrors());
				System.out.print(binding.getGlobalErrorCount());
				System.out.print(binding.getFieldError());
			}
			System.out.print(binding.hasErrors());
			System.out.print(binding.getGlobalErrorCount());
			System.out.print(binding.getFieldError());
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

		result = new ModelAndView("demo/developer/edit");
		result.addObject("demo", demo);
		result.addObject("message", message);

		return result;
	}

}
