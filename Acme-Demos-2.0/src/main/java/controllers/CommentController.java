package controllers;

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

import domain.Actor;
import domain.Comment;
import forms.CommentForm;

import security.LoginService;
import security.UserAccount;
import services.ActorService;
import services.CommentService;
import services.DemoService;

@Controller
@RequestMapping("/comment")
public class CommentController extends AbstractController {

	// Constructor -----------------------------------------------------
	public CommentController() {
		super();
	}

	// Services --------------------------------------------------------
	@Autowired
	private CommentService commentService;
	@Autowired
	private ActorService actorService;
	@Autowired
	private DemoService demoService;
	
	private Integer demoAtrId;
	
	//Display----------------------------------------------------------------
	@RequestMapping(value="/display", method=RequestMethod.GET)
	public ModelAndView display(@RequestParam int commentId){
		ModelAndView result;
		Comment comment;
				
		comment = commentService.findOne(commentId);
		
		
		String estrellas = null;
		int stars = comment.getStars();
		//Estrellitas
		
		/*if (comment.getStars() == 1){
			estrellas = "https://fandangogroovers.files.wordpress.com/2010/01/1star.jpg";
		}else if(comment.getStars() == 2)*/
			
		switch(stars){
			case 1:
				estrellas = "https://fandangogroovers.files.wordpress.com/2010/01/1star.jpg?w=20&h=19";
				break;
			case 2:
				estrellas = "https://fandangogroovers.files.wordpress.com/2010/01/2star.jpg?w=50&h=19";
				break;
			case 3:
				estrellas = "https://fandangogroovers.files.wordpress.com/2010/01/3star.jpg?w=50&h=19";
				break;
		}
		
		result = new ModelAndView("comment/display");
		result.addObject("id",comment.getId());
		result.addObject("author",comment.getAuthor());
		result.addObject("moment",comment.getMoment());
		result.addObject("text",comment.getText());
		result.addObject("estrellas", estrellas);
				
		return result;
	}
	
	//Listing
	@RequestMapping(value="/list")
	public ModelAndView list(@RequestParam int demoId){
		ModelAndView res = new ModelAndView("comment/list");
		Collection<Comment>c = commentService.findCommentsByDemoId(demoId);
		res.addObject("comments", c);
		res.addObject("requestUri", "comment/list.do");
		res.addObject("demoId", demoId);
		return res;
	}
	
	// Edit--------------------------------------------------------------------------------
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int demoId) {
		ModelAndView result;
		CommentForm commentForm = new CommentForm();
		demoAtrId = demoId;	 
		try{
			Actor a = actorService.findByPrincipal();
			commentForm.setAuthor(a.getName() +" "+ a.getSurname());
			result = createEditModelAndView(commentForm);
		}catch(Throwable opps){
			result = createEditModelAndView(commentForm);
		}
		return result;
	}
	
	// Save------------------------------------------------------------------------------
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView edit(@Valid CommentForm commentForm, BindingResult binding,RedirectAttributes redir) {
		ModelAndView result = null;
		Comment c;
		if (binding.hasErrors()) {
			result = createEditModelAndView(commentForm,"comment.commit.error");
		} else {
			try {
				c =  commentService.reconstruct(commentForm);
				c.setDemo(demoService.findOne(demoAtrId));
				commentService.save(c);
				result = new ModelAndView("redirect:/comment/list.do?demoId="+demoAtrId);
				redir.addFlashAttribute("message", "comment.ok");
			}catch (Throwable oops) {
				result = createEditModelAndView(commentForm, "comment.commit.error");
			}
		}
		return result;
	}
	
	// Ancillary methods ----------------------------------------------------------------------------
	protected ModelAndView createEditModelAndView(CommentForm comment) {
		ModelAndView result;

		result = createEditModelAndView(comment, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(CommentForm comment,
			String message) {
		ModelAndView result;

		result = new ModelAndView("comment/edit");
		result.addObject("commentForm", comment);
		result.addObject("actionUri", "comment/edit.do");
		result.addObject("message", message);

		return result;
	}
		
}
