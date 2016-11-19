package services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml", "classpath:spring/config/packages.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class CommentServiceTest extends AbstractTest {
	
	// Services test ------------------------------------------------
	@Autowired
	private CommentService commentService;
	
	
	/*
	 * An actor who is authenticated as an administrator must be able to
	 * delete a comment that he or she finds inappropriate
	 */
	@Test
	public void testPositiveDelete(){
		Integer numCommentsOld;
		Integer numCommentsNew;
		
		authenticate("admin");
		
		numCommentsOld = commentService.findAll().size();
		commentService.delete(43);
		numCommentsNew = commentService.findAll().size();
		
		Assert.isTrue(numCommentsNew == numCommentsOld - 1);
		
		unauthenticate();
	}
	
	/*
	 * An actor who is authenticated as an administrator must be able to
	 * delete a comment that he or she finds inappropriate
	 * 
	 * Error: Admin not logged
	 */
	@Test(expected = java.lang.IllegalArgumentException.class)
	public void testNegativeDeleteWithoutAdmin(){
		Integer numCommentsOld;
		Integer numCommentsNew;
		
		unauthenticate();
		
		numCommentsOld = commentService.findAll().size();
		commentService.delete(43);
		numCommentsNew = commentService.findAll().size();
		
		Assert.isTrue(numCommentsNew == numCommentsOld - 1);
		
		unauthenticate();
	}
	
	/*
	 * An actor who is authenticated as an administrator must be able to
	 * delete a comment that he or she finds inappropriate
	 * 
	 * Error: Comment not exist
	 */
	@Test(expected = java.lang.IllegalArgumentException.class)
	public void testNegativeDeleteCommentNotExist(){
		Integer numCommentsOld;
		Integer numCommentsNew;
		
		unauthenticate();
		
		numCommentsOld = commentService.findAll().size();
		commentService.delete(1000);
		numCommentsNew = commentService.findAll().size();
		
		Assert.isTrue(numCommentsNew == numCommentsOld - 1);
		
		unauthenticate();
	}
}
