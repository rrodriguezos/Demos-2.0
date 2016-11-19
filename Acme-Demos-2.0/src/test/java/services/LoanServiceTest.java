package services;

import java.util.Collection;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Loan;

import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml", "classpath:spring/config/packages.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class LoanServiceTest extends AbstractTest {
	
	//BAsed services test---
	@Autowired
	private LoanService loanService;
	@Autowired
	private BankService bankService;
	
	
	/*
	 * An actor who is authenticated as a developer must be able to:
		Request a loan from a bank.
	 */
	//@Test
	public void create(){
		authenticate("developer2");
		System.out.println("ANTES:");
		for(Loan l : loanService.findLoansByDevPrincipal())
			System.out.println(l.getBank().getName() + " - " + l.getStatus() + ": " +l.getAmount() );
		Loan loan = loanService.create();
		loan.setAmount(5001.35);
		loan.setBank(bankService.findOne(20));
		loanService.save(loan);
		System.out.println("DESPU…S:");
		for(Loan l : loanService.findLoansByDevPrincipal())
			System.out.println(l.getBank().getName() + " - " + l.getStatus() + ": " +l.getAmount() );
	}
	
	/*
	An actor who is authenticated as a developer must be able to:
		List the loans that he or shes requested and sort them by status.
	*/
	//@Test
	public void list(){
		authenticate("developer2");
		for(Loan l : loanService.findLoansByDevPrincipal())
			System.out.println(l.getBank().getName() + " - " + l.getStatus() + ": " +l.getAmount() );
	}
	
	
	/*An actor who is authenticated as a developer must be able to:
		Cancel a request for a loan as long as its not been processed.*/
	//@Test
	public void cancel(){
		authenticate("developer2");
		System.out.println("ANTES:");
		for(Loan l : loanService.findLoansByDevPrincipal())
			System.out.println(l.getBank().getName() + " - " + l.getStatus() + ": " +l.getAmount() );
		
		List<Loan> list = (List<Loan>) loanService.findLoansByDevPrincipal();
		loanService.cancel(list.get(0));
		
		System.out.println("DESPu…S:");
		for(Loan l : loanService.findLoansByDevPrincipal())
			System.out.println(l.getBank().getName() + " - " + l.getStatus() + ": " +l.getAmount() );
	}
	
	@Test
	public void ratioLoanDev(){
		System.out.println(loanService.ratioApprovedToDeniedLoansPerDeveloper(13));
	}
	
	/*
	 * An actor who is authenticated as a bank must be able to
	 * list the loans that they manage and sort them by status
	*/
	@Test
	public void testPositivelistLoansAsBank(){
		Collection<Loan> loans;
		
		authenticate("bank1");
		
		loans = loanService.findLoansByBankPrincipal();
		Assert.isTrue(loans.size() == 2);
		
		unauthenticate();
	}
	
	/*
	 * An actor who is authenticated as a bank must be able to
	 * list the loans that they manage and sort them by status
	*/
	@Test(expected = java.lang.IllegalArgumentException.class)
	public void testNegativelistLoansAsUnauthenticate(){
		Collection<Loan> loans;
		
		unauthenticate();
		
		loans = loanService.findLoansByBankPrincipal();
		Assert.isTrue(loans.size() == 2);
		
		unauthenticate();
	}
	
	
	/*
	 * An actor who is authenticated as a bank must be able to
	 * decide on whether a pending loan must be approved or denied
	*/
	@Test
	public void testPositiveApproveLoansAsBank(){
		Loan loan;
		
		authenticate("bank1");
		
		loanService.approve(23);
		
		loan = loanService.findOne(23);
		Assert.isTrue(loan.getStatus().equals("APPROVED"));
		
		unauthenticate();
	}
	
	/*
	 * An actor who is authenticated as a bank must be able to
	 * decide on whether a pending loan must be approved or denied
	 * 
	 * Error: Loan with status approved
	*/
	@Test(expected = java.lang.IllegalArgumentException.class)
	public void testNegativeApproveLoansAsBank(){
		Loan loan;
		
		authenticate("bank1");
		
		loanService.approve(21);
		
		loan = loanService.findOne(21);
		Assert.isTrue(loan.getStatus().equals("APPROVED"));
		
		unauthenticate();
	}
	
	/*
	 * An actor who is authenticated as a bank must be able to
	 * decide on whether a pending loan must be approved or denied
	 * 
	 * Error: Loan with status approved
	*/
	@Test(expected = java.lang.IllegalArgumentException.class)
	public void testNegativeDenyLoansAsBank(){
		Loan loan;
		
		authenticate("bank1");
		
		loanService.deny(21);
		
		loan = loanService.findOne(21);
		Assert.isTrue(loan.getStatus().equals("APPROVED"));
		
		unauthenticate();
	}

}
