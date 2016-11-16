package services;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

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

}
