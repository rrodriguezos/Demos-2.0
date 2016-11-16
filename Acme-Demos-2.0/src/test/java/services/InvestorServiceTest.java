package services;

import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Bank;
import domain.Investment;
import domain.Investor;
import forms.InvestorRegisterForm;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class InvestorServiceTest extends AbstractTest {

	// Service under test ------------------------
	@Autowired
	private InvestorService investorService;

	@Autowired
	private InvestmentService investmentService;

	@Autowired
	private BankService bankService;

	/* *****************************************************
	 * *An actor who is not authenticated must be able to:* Register to the
	 * system as a investor.
	 * ******************************************************
	 */

	/**
	 * Positive: A non authenticated registers as a investor
	 */
	@Test
	public void registerInvestorPositiveTest() {
		int actual = investorService.findAll().size();
		unauthenticate();
		InvestorRegisterForm registerForm = new InvestorRegisterForm();
		registerForm.setName("Name Test");
		registerForm.setSurname("Surname Test");
		registerForm.setPhone("956828271");
		registerForm.setUsername("Username Test");
		registerForm.setPassword("Password Test");
		registerForm.setConfirmPassword("Password Test");
		registerForm.setAccept(true);
		registerForm.setCompany("INDRA");
		registerForm.setPasswordPast("Password Test");
		registerForm.setEmailAddress("test@gmail.com");
		Investor investor = investorService.reconstruct(registerForm);
		investorService.save(investor);
		int expected = investorService.findAll().size();
		Assert.isTrue(expected == actual + 1);
	}

	/**
	 * Negative: An authenticated none attempts to register as a investor
	 */
	@Test(expected = IllegalArgumentException.class)
	@Rollback(true)
	public void registerInvestorAsAdminNegativeTest() {
		authenticate("none");
		InvestorRegisterForm registerForm = new InvestorRegisterForm();
		registerForm.setName("Name Test");
		registerForm.setSurname("Surname Test");
		registerForm.setPhone("+34 (95) 758400");
		registerForm.setUsername("Username Test");
		registerForm.setPassword("Password Test");
		registerForm.setConfirmPassword("Password Test");
		registerForm.setAccept(true);
		registerForm.setEmailAddress("test@gmail.com");
		registerForm.setPasswordPast("Password Test");
		Investor investor = investorService.reconstruct(registerForm);
		investorService.save(investor);
	}

	/**
	 * Negative: A non authenticated attempts to register as a investor
	 * submitting wrong confirmation password
	 */
	@Test(expected = IllegalArgumentException.class)
	@Rollback(true)
	public void registerInvestorWrongFieldsNegativeTest() {
		unauthenticate();
		InvestorRegisterForm registerForm = new InvestorRegisterForm();
		registerForm.setName("Name Test");
		registerForm.setSurname("Surname Test");
		registerForm.setPhone("+34 (95) 758400");
		registerForm.setUsername("Username Test");
		registerForm.setPassword("Password Test");
		registerForm.setConfirmPassword("Different Password Test");
		Investor investor = investorService.reconstruct(registerForm);
		investorService.save(investor);
	}

	// Listing requirement 1

	@Test
	public void testFindInvestor() {
		Collection<Investor> investors = investorService.findAll();
		Assert.isTrue(investors.size() == 3);
	}

	// 5.1 An actor who is authenticated as an investor must be able to:
	// Associate one of his or her investments with the bank that sponsors it,
	// if any.

	@Test
	public void testAssociateInvestmentBank() {
		authenticate("investor3");
		Bank bank;
		bank = bankService.findOne(20);
		Investment investment = investmentService.findOne(76);
		investment.setBank(bank);
		investmentService.save(investment);

		unauthenticate();

	}

	// ----------------------------------------------------
	// NEGATIVE TEST CASES Associate
	// ----------------------------------------------------
	// Autor no autorizado, no es propietario del investment.

	@Test(expected = IllegalArgumentException.class)
	public void testNegativeAssociateInvestmentBank1() {
		authenticate("investor1");
		Bank bank;
		bank = bankService.findOne(20);
		Investment investment = investmentService.findOne(76);
		investment.setBank(bank);
		investmentService.save(investment);

		unauthenticate();

	}

	// ----------------------------------------------------
	// NEGATIVE TEST CASES Associate
	// ----------------------------------------------------
	// No es un investment

	@Test(expected = IllegalArgumentException.class)
	public void testNegativeAssociateInvestmentBan2() {
		authenticate("investor3");
		Bank bank;
		bank = bankService.findOne(20);
		Investment investment = investmentService.findOne(2500);
		investment.setBank(bank);
		investmentService.save(investment);

		unauthenticate();

	}

	// 10.1 Every actor, including not authenticated ones, must be able to: List
	// the investors and navigate to their white papers.

	@Test
	public void investorsList() {
		Collection<Investor> encontrados = investorService.findAll();
		for (Investor d : encontrados) {
			System.out.println(d.getCompany());
			System.out.println(d.getWhitePapers());
		}
	}
}
