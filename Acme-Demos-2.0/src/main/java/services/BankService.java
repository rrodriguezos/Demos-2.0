package services;

import java.util.Collection;
import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.BankRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Bank;
import domain.Investment;
import domain.Loan;
import forms.BankRegisterForm;

@Transactional
@Service
public class BankService {

	// Constructor --------------------------------------------------
	public BankService() {
		super();
	}

	// Managed Repository -------------------------------------------
	@Autowired
	private BankRepository bankRepository;

	// Supported Services -------------------------------------------

	@Autowired
	private UserAccountService userAccountService;

	// CRUD methods -------------------------------------------------

	public Bank create() {
		UserAccount useraccount;
		Bank result;
		Collection<Loan> loans;
		Collection<Investment> investments;

		Authority aut = new Authority();

		aut.setAuthority("BANK");
		useraccount = userAccountService.create();

		result = new Bank();

		useraccount.addAuthority(aut);
		result.setUserAccount(useraccount);

		loans = new LinkedList<Loan>();
		result.setLoans(loans);

		investments = new LinkedList<Investment>();
		result.setInvestments(investments);

		return result;
	}

	public Collection<Bank> findAll() {
		Collection<Bank> result;

		result = bankRepository.findAll();

		return result;
	}

	public Bank findOne(int bankId) {
		Bank result;

		Assert.isTrue(bankId != 0);

		result = bankRepository.findOne(bankId);
		Assert.notNull(result);

		return result;
	}

	public void save(Bank bank) {

		if (bank.getId() == 0) {
			Md5PasswordEncoder encoder;

			encoder = new Md5PasswordEncoder();

			bank.getUserAccount().setPassword(
					encoder.encodePassword(bank.getUserAccount().getPassword(),
							null));
		}
		bank = bankRepository.saveAndFlush(bank);
		Assert.notNull(bank);
	}

	// Other Business Methods ---------------------------------------

	public Bank findByPrincipal() {
		UserAccount userAccount;
		Bank result;
		int id;

		userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);
		id = userAccount.getId();
		result = bankRepository.findByBankAccountId(id);
		Assert.notNull(result);

		return result;

	}

	public Bank checkPrincipal() {
		UserAccount principalUserAccount;
		Bank result;

		principalUserAccount = LoginService.getPrincipal();
		result = bankRepository.findByBankAccountId(principalUserAccount
				.getId());

		Assert.notNull(result);

		return result;
	}

	public Bank reconstruct(BankRegisterForm bankRegisterForm) {
		Bank res;
		res = create();
		Assert.isTrue(bankRegisterForm.getPassword().equals(
				bankRegisterForm.getConfirmPassword()));
		Assert.isTrue(bankRegisterForm.getAccept());
		res.setName(bankRegisterForm.getName());
		res.setPhone(bankRegisterForm.getPhone());
		res.setSurname(bankRegisterForm.getSurname());
		res.setCommercialName(bankRegisterForm.getCommercialName());
		res.setSWIFTCode(bankRegisterForm.getSWIFTCode());
		res.setEmailAddress(bankRegisterForm.getEmailAddress());
		res.getUserAccount().setUsername(bankRegisterForm.getUsername());
		res.getUserAccount().setPassword(bankRegisterForm.getPassword());

		return res;
	}

	public Collection<Bank> searchByKeyword(String keyword) {
		Assert.notNull(keyword);
		Collection<Bank> result;
		result = bankRepository.searchByKeyword(keyword);
		return result;
	}

	public Collection<Bank> banksWithMoreLoans() {
		Collection<Bank> result = bankRepository.banksWithMoreLoans();
		Assert.notNull(result);
		return result;
	}

	public Bank bankByInvestment(int investmentId) {
		Bank result;

		result = bankRepository.bankByInvestment(investmentId);
		return result;
	}

}
