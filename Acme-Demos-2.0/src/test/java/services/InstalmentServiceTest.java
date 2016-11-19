package services;

import java.util.Calendar;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Instalment;

import utilities.AbstractTest;
import forms.CreateInstalmentsForm;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml", "classpath:spring/config/packages.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class InstalmentServiceTest extends AbstractTest {
	
	//BAsed services test---
	@Autowired
	private InstalmentService instalmentService;
	
	
	
	/*
	 * An actor who is authenticated as an bank must be able to
	 * create an investment regarding an existing demo
	 */
	@Test
	public void testPositiveCreateIntalment(){
		CreateInstalmentsForm form;
		Calendar cal;
		
		authenticate("bank1");
		
		form = new CreateInstalmentsForm();
		cal = Calendar.getInstance();
		cal.set(2016, 12, 04, 12, 00);
		
		form.setLoanId(21);
		form.setNumber(5);
		form.setDate(cal.getTime());
		
		Assert.isTrue(instalmentService.findByLoan(21).size() == 2);
		instalmentService.createAndSave(form);
		
		Assert.isTrue(instalmentService.findByLoan(21).size() == 7);
		
		unauthenticate();
	}
	
	/*
	 * An actor who is authenticated as an bank must be able to
	 * create an investment regarding an existing demo
	 * 
	 * Error: Loan not exist
	 */
	@Test(expected = java.lang.IllegalArgumentException.class)
	public void testNegativeCreateIntalmentLoanNotExist(){
		CreateInstalmentsForm form;
		Calendar cal;
		
		authenticate("bank1");
		
		form = new CreateInstalmentsForm();
		cal = Calendar.getInstance();
		cal.set(2016, 12, 04, 12, 00);
		
		form.setLoanId(210000);
		form.setNumber(5);
		form.setDate(cal.getTime());
		
		Assert.isTrue(instalmentService.findByLoan(21).size() == 2);
		instalmentService.createAndSave(form);
		
		Assert.isTrue(instalmentService.findByLoan(21).size() == 7);
		
		unauthenticate();
	}
	
	/*
	 * An actor who is authenticated as an bank must be able to
	 * create an investment regarding an existing demo
	 * 
	 * Error: Loan not exist
	 */
	@Test(expected = java.lang.IllegalArgumentException.class)
	public void testNegativeCreateIntalmentNumberNegative(){
		CreateInstalmentsForm form;
		Calendar cal;
		
		authenticate("bank1");
		
		form = new CreateInstalmentsForm();
		cal = Calendar.getInstance();
		cal.set(2016, 12, 04, 12, 00);
		
		form.setLoanId(21);
		form.setNumber(-5);
		form.setDate(cal.getTime());
		
		Assert.isTrue(instalmentService.findByLoan(21).size() == 2);
		instalmentService.createAndSave(form);
		
		Assert.isTrue(instalmentService.findByLoan(21).size() == 7);
		
		unauthenticate();
	}
	
	/*
	 * An actor who is authenticated as an bank must be able to
	 * set an instalment as paid
	 */
	@Test
	public void testPositivePayIntalment(){
		Instalment instalment;
		
		authenticate("bank1");
		
		instalmentService.pay(77);
		
		instalment = instalmentService.findOne(77);
		Assert.isTrue(!instalment.getPaid().equals(null));
		
		unauthenticate();
	}
	
	/*
	 * An actor who is authenticated as an bank must be able to
	 * set an instalment as paid
	 * 
	 * Error: Banknot logged
	 */
	@Test(expected = java.lang.IllegalArgumentException.class)
	public void testPositivePayIntalmentWithoutBank(){
		Instalment instalment;
		
		instalmentService.pay(77);
		
		instalment = instalmentService.findOne(77);
		Assert.isTrue(!instalment.getPaid().equals(null));
		
		unauthenticate();
	}
	
}

