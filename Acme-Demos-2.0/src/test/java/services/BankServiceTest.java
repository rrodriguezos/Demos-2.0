package services;

import java.util.Collection;
import java.util.List;

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
import forms.BankRegisterForm;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class BankServiceTest extends AbstractTest {
	// Service under test ------------------------
	@Autowired
	private BankService bankService;

	/* *****************************************************
	 * *An actor who is not authenticated must be able to:* Register to the
	 * system as a bank. ******************************************************
	 */

	/**
	 * Positive: A non authenticated registers as a bank
	 */
	@Test
	public void registerBankPositiveTest() {
		int actual = bankService.findAll().size();
		unauthenticate();
		BankRegisterForm registerForm = new BankRegisterForm();
		registerForm.setName("Name Test");
		registerForm.setSurname("Surname Test");
		registerForm.setPhone("956828271");
		registerForm.setUsername("Username Test");
		registerForm.setPassword("Password Test");
		registerForm.setConfirmPassword("Password Test");
		registerForm.setAccept(true);
		registerForm.setCommercialName("Cajasol");
		registerForm.setSWIFTCode("SPVGESMA");
		registerForm.setPasswordPast("Password Test");
		registerForm.setEmailAddress("test@gmail.com");
		Bank bank = bankService.reconstruct(registerForm);
		bankService.save(bank);
		int expected = bankService.findAll().size();
		Assert.isTrue(expected == actual + 1);
	}

	/**
	 * Negative: An authenticated none attempts to register as a bank
	 */
	@Test(expected = IllegalArgumentException.class)
	@Rollback(true)
	public void registerBankAsAdminNegativeTest() {
		authenticate("none");
		BankRegisterForm registerForm = new BankRegisterForm();
		registerForm.setName("Name Test");
		registerForm.setSurname("Surname Test");
		registerForm.setPhone("+34 (95) 758400");
		registerForm.setUsername("Username Test");
		registerForm.setPassword("Password Test");
		registerForm.setConfirmPassword("Password Test");
		registerForm.setAccept(true);
		registerForm.setCommercialName("Cajasol");
		registerForm.setSWIFTCode("SPVGESMM");
		registerForm.setEmailAddress("test@gmail.com");
		registerForm.setPasswordPast("Password Test");
		Bank bank = bankService.reconstruct(registerForm);
		bankService.save(bank);
	}

	/**
	 * Negative: A non authenticated attempts to register as a bank submitting
	 * wrong confirmation password
	 */
	@Test(expected = IllegalArgumentException.class)
	@Rollback(true)
	public void registerBankWrongFieldsNegativeTest() {
		unauthenticate();
		BankRegisterForm registerForm = new BankRegisterForm();
		registerForm.setName("Name Test");
		registerForm.setSurname("Surname Test");
		registerForm.setPhone("+34 (95) 758400");
		registerForm.setUsername("Username Test");
		registerForm.setPassword("Password Test");
		registerForm.setCommercialName("Cajasol");
		registerForm.setSWIFTCode("SPVGESMM");
		registerForm.setConfirmPassword("Different Password Test");
		Bank bank = bankService.reconstruct(registerForm);
		bankService.save(bank);
	}

	// Listing requirement 1

	@Test
	public void testFindBank() {
		Collection<Bank> banks = bankService.findAll();
		Assert.isTrue(banks.size() == 3);
	}

	// 4.1 A user who is not authenticated must be able to:
	// List the banks that have registered to the system and search for them
	// using a single keyword that must appear somewhere in its commercial name.
	// The listing must allow to navigate from a bank to the demos that have
	// got an investment that has been sponsored by the corresponding bank.
	@Test
	public void testSearchBank() {
		List<Bank> banks = (List<Bank>) bankService.searchByKeyword("Santander");
		Assert.isTrue(banks.size() == 1);
	}

}
