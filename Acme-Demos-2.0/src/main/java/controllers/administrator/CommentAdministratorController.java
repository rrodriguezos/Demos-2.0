package controllers.administrator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.CommentService;
import controllers.AbstractController;
import domain.Comment;

@Controller
@RequestMapping("/comment/administrator")
public class CommentAdministratorController extends AbstractController {
	
	// Supporting services --------------------------------
	@Autowired
	private CommentService commentService;
	
	// Constructor ----------------------------------------
	public CommentAdministratorController() {
		super();
	}
	
	
	// Delete ---------------------------------------------
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam int commentId) {
		ModelAndView result;
		Comment comment;

		comment = commentService.delete(commentId);

		result = new ModelAndView("redirect:/comment/list.do?demoId=" + comment.getDemo().getId());
		
		return result;
	}

}
