package services;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.CommentRepository;
import security.Authority;
import domain.Actor;
import domain.Comment;
import domain.Demo;
import domain.Developer;
import forms.CommentForm;

@Transactional
@Service
public class CommentService {

	// Constructor
	// ---------------------------------------------------------------
	public CommentService() {
		super();
	}

	// Managed
	// Repository-----------------------------------------------------------
	@Autowired
	private CommentRepository commentRepository;

	// Supported
	// Services------------------------------------------------------------

	@Autowired
	private ActorService actorService;
	@Autowired
	private DeveloperService developerService;

	// CRUD methods-------------------------------------------------------------
	public Collection<Comment> findAll() {
		Collection<Comment> result;

		result = commentRepository.findAll();

		return result;
	}

	public Comment findOne(int commentId) {
		Assert.isTrue(commentId != 0);

		Comment result;

		result = commentRepository.findOne(commentId);
		Assert.notNull(result);

		return result;
	}
	
	public Comment create(){
		Comment res = new Comment();
		res.setMoment(new Date(System.currentTimeMillis() - 100));
		return res;
	}
	
	public void save(Comment comment) {
		Assert.notNull(comment);
		commentRepository.saveAndFlush(comment);
	}	

	// Other Business Methods
	// ------------------------------------------------------

	private void checkPrincipalAdministrator() {
		Actor actor;
		Authority authority;

		actor = actorService.findByPrincipal();
		Assert.isTrue(actor != null);

		authority = new Authority();
		authority.setAuthority("ADMINISTRATOR");

		Assert.isTrue(actor.getUserAccount().getAuthorities()
				.contains(authority));
	}

	public Collection<Comment> findCommentsByDemoId(int demoId) {
		Collection<Comment> result;

		result = commentRepository.findCommentsByDemoId(demoId);
		return result;
	}

    public Double averageCommentsPerDemo() {
    	Double result;

    	result = commentRepository.averageCommentsPerDemo();

        return result;
    }

	public Double averageCommentsPerDemoByDeveloperId() {
		Double result;
		Developer developer;
		developer = developerService.findByPrincipal();
		int developerId;
		developerId = developer.getId();
		result = commentRepository.averageCommentsPerDemoByDeveloperId(developerId);

		return result;
	}

	public Double averageStarsPerDemoByDeveloper() {
		Double result;
		Developer developer;
		int developerId;
		developer = developerService.findByPrincipal();
		developerId = developer.getId();
		result = commentRepository.averageStarsPerDemoByDeveloper(developerId);

		return result;
	}

	public Collection<Demo> demoSortedAverageNumberStarsByDeveloper() {
		Collection<Demo> result;
		Developer developer;
		int developerId;
		developer = developerService.findByPrincipal();
		developerId = developer.getId();
		result = commentRepository.demoSortedAverageNumberStarsByDeveloper(developerId);

		return result;
	}
	
	public Comment reconstruct(CommentForm cf){
		Comment res = create();
		res.setText(cf.getText());
		res.setAuthor(cf.getAuthor());
		res.setStars(cf.getStars());
		return res;
	}

	public Comment delete(int commentId) {
		Comment result;
		
		this.checkPrincipalAdministrator();
	
		result = this.findOne(commentId);
		commentRepository.delete(result);
		
		return result;
	}
}
