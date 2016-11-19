package services;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.WhitePaper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class WhitePaperServiceTest extends AbstractTest {
	// Service under test ------------------------
	
	@Autowired
	private WhitePaperService whitePaperService;
	
	@Autowired
	private HelpDatesConvertService helpDatesConvertService;

	// 10.1 A user who is not authenticated must be able to:
	// Search for white papers using a single keyword that must appear somewhere
	// in its title and a range for the publication date; the range may be
	// empty, which means that every paper that includes the key word must be
	// returned.
	@Test
	public void testSearchWhitePaper() {
		String strDate1= "01/01/2015";
		String strDate2= "01/11/2016";
		
		Date range1 =helpDatesConvertService.formatStringToDateWithoutHour(strDate1);
		Date range2 =helpDatesConvertService.formatStringToDateWithoutHour(strDate2);
		
		List<WhitePaper> wPapers = (List<WhitePaper>) whitePaperService
				.searchByKeyword("white", range1, range2);
		Assert.isTrue(wPapers.size() == 2);
	}
	
	/*
	 * An actor who is authenticated as an investor must be able to
	 * manage his or her white papers, which includes, creating, listing, 
	 * editing, and deleting them
	 */
	
	@Test
	public void testPositiveCreateWhitePaper() {
		WhitePaper whitePaper;
		Integer nOld;
		Integer nNew;
		Calendar cal;
		
		authenticate("investor1");
		
		whitePaper = whitePaperService.create();
		cal = Calendar.getInstance();
		cal.set(2016, 2, 31, 12, 00);
		
		whitePaper.setAbstractReview("prueba");
		whitePaper.setTitle("prueba");
		whitePaper.setPublishedDate(cal.getTime());
		
		nOld = whitePaperService.findAll().size();
		whitePaperService.save(whitePaper);
		nNew = whitePaperService.findAll().size();
		
		Assert.isTrue(whitePaper.getId() == 0);
		Assert.isTrue(nNew == nOld + 1);
		
		unauthenticate();
	}
	

	/*
	 * An actor who is authenticated as an investor must be able to
	 * manage his or her white papers, which includes, creating, listing, 
	 * editing, and deleting them
	 * 
	 * Error: WhitePaper of other investor
	 */
	
	@Test(expected = java.lang.IllegalArgumentException.class)
	public void testNegativeDelteWhitePaperOtherInversor() {
		WhitePaper whitePaper;
		
		authenticate("investor1");
		
		whitePaper = whitePaperService.findOne(68);
	
		whitePaperService.delete(whitePaper);
		
		unauthenticate();
	}
	
	/*
	 * An actor who is authenticated as an investor must be able to
	 * manage his or her white papers, which includes, creating, listing, 
	 * editing, and deleting them
	 * 
	 * Error: WhitePaper of other investor
	 */
	
	@Test(expected = java.lang.IllegalArgumentException.class)
	public void testNegativeEditWhitePaperAsAdmin() {
		WhitePaper whitePaper;
		
		authenticate("admin");
		
		whitePaper = whitePaperService.findOne(68);
		whitePaper.setTitle("aaa");
	
		whitePaperService.save(whitePaper);
		
		unauthenticate();
	}

}
