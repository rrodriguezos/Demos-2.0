package services;

import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.InvestorRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Administrator;
import domain.Comment;
import domain.Investment;
import domain.Investor;
import forms.InvestorRegisterForm;

@Service
@Transactional
public class InvestorService {
	// Managed repository -------------------------

	@Autowired
	private InvestorRepository investorRepository;

	// Supporting Services -------------------------

	@Autowired
	private UserAccountService userAccountService;

	// Constructors -------------------------------
	public InvestorService() {
		super();
	}

	// Simple CRUD methods -----------------------------

	public Investor create() {
		UserAccount useraccount;
		Investor result;
		Collection<Investment> investments;

		Authority aut = new Authority();

		aut.setAuthority("INVESTOR");
		useraccount = userAccountService.create();

		result = new Investor();

		useraccount.addAuthority(aut);
		result.setUserAccount(useraccount);

		investments = new LinkedList<Investment>();
		result.setInvestments(investments);

		return result;
	}

	public Collection<Investor> findAll() {
		Collection<Investor> result;

		result = investorRepository.findAll();

		return result;
	}

	public Investor findOne(int investorId) {
		Investor result;

		result = investorRepository.findOne(investorId);

		return result;
	}

	public void save(Investor investor) {
		Boolean create;
		create = false;
		if (investor.getId() == 0) {
			Md5PasswordEncoder encoder;

			create = true;
			encoder = new Md5PasswordEncoder();

			investor.getUserAccount().setPassword(
					encoder.encodePassword(investor.getUserAccount()
							.getPassword(), null));
		}
		investor = investorRepository.saveAndFlush(investor);
		Assert.notNull(investor);
	}

	// other methods
	// ----------------------------------------------------------------------

	public Investor findByPrincipal() {
		UserAccount userAccount;
		Investor result;
		int id;

		userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);
		id = userAccount.getId();
		result = investorRepository.findByInvestorAccountId(id);
		Assert.notNull(result);

		return result;

	}

	public Investor reconstruct(InvestorRegisterForm investorForm) {
		Investor res;
		res = create();
		Assert.isTrue(investorForm.getPassword().equals(
				investorForm.getConfirmPassword()));
		Assert.isTrue(investorForm.getAccept());
		res.setName(investorForm.getName());
		res.setCompany(investorForm.getCompany());
		res.setPhone(investorForm.getPhone());
		res.setSurname(investorForm.getSurname());
		res.setEmailAddress(investorForm.getEmailAddress());
		res.getUserAccount().setUsername(investorForm.getUsername());
		res.getUserAccount().setPassword(investorForm.getPassword());

		return res;
	}

	public InvestorRegisterForm copyInvestor() {
		InvestorRegisterForm result;
		Investor investor;

		result = new InvestorRegisterForm();
		investor = findByPrincipal();

		result.setName(investor.getName());
		result.setSurname(investor.getSurname());
		result.setId(investor.getId());
		result.setEmailAddress(investor.getEmailAddress());
		result.setPhone(investor.getPhone());
		result.setCompany(investor.getCompany());
		result.setUsername(investor.getUserAccount().getUsername());
		result.setPassword(investor.getUserAccount().getPassword());
		result.setConfirmPassword(investor.getUserAccount().getPassword());
		result.setPasswordPast(investor.getUserAccount().getPassword());
		result.setAccept(true);

		return result;
	}

	

	public Investor findByInvestorAccount(UserAccount investorAccount) {
		Assert.notNull(investorAccount);
		Investor result;
		result = investorRepository.findByInvestorAccountId(investorAccount
				.getId());
		return result;
	}




}
