/* UserController.java
 *
 * Copyright (C) 2013 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 * 
 */

package controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import domain.Developer;

import forms.DeveloperRegisterForm;

import security.UserAccountRepository;

import services.DeveloperService;

@Controller
@RequestMapping("/developer")
public class DeveloperController extends AbstractController {

	// Services ---------------------------------------------------------------
	@Autowired
	private DeveloperService developerService;
	
	
	@Autowired
	private UserAccountRepository userAccountRepository;

	// Constructors -----------------------------------------------------------

	public DeveloperController() {
		super();
	}
	
	
	//Register-----------------------------------------------------------------
	@RequestMapping(value="/register", method=RequestMethod.GET)
	public ModelAndView create(){
		DeveloperRegisterForm df = new DeveloperRegisterForm();
		ModelAndView res = createEditModelAndView(df);
		return res;
	}
	
	@RequestMapping(value="/register", method=RequestMethod.POST, params="save")
	public ModelAndView save(@Valid DeveloperRegisterForm df, BindingResult binding){
		ModelAndView res;
		Developer d;
		if(binding.hasErrors()){
			res = createEditModelAndView(df,"developer.error");
		}else{
			try{
				d = developerService.reconstruct(df);
				developerService.save(d);
				res = new ModelAndView("redirect:../");
			}catch(Throwable oops){
				if(userAccountRepository.findByUsername(df.getUsername())!=null){
					res = createEditModelAndView(df,"developer.duplicated");
				}else if(!df.getAccept()){
					res = createEditModelAndView(df,"developer.conditionsNotAcepted");
				}else if(df.getEmailAddress().isEmpty() && df.getPhone().isEmpty()){
					res = createEditModelAndView(df,"developer.not.phone.neither.mail");
				}else if(!df.getPhone().isEmpty() && df.getPhone().length()!=9){
					res = createEditModelAndView(df,"developer.phone.badLenght");
				}else{
					res = createEditModelAndView(df,"developer.invalid.password");
				}
			}
		}
		return res;
	}
	
	//Ancillary methods
	protected ModelAndView createEditModelAndView(DeveloperRegisterForm df){
		ModelAndView res = createEditModelAndView(df,null);
		return res;
	}
	
	protected ModelAndView createEditModelAndView(DeveloperRegisterForm df,String message){
		
		ModelAndView res = new ModelAndView("developer/register");
		res.addObject("developerRegisterForm", df);
		res.addObject("message", message);
		res.addObject("requestUri","developer/register.do");
		return res;
	}

}