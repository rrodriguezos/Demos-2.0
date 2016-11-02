package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.DeveloperRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Actor;
import domain.Demo;
import domain.Developer;
import forms.DeveloperRegisterForm;

@Service
@Transactional
public class DeveloperService {

	// Managed repository -------------------------

	@Autowired
	private DeveloperRepository developerRepository;

	// Supporting Services -------------------------

	@Autowired
	private UserAccountService userAccountService;

	@Autowired
	private AdministratorService administratorService;

	// Constructors -------------------------------
	public DeveloperService() {
		super();
	}

	// Simple CRUD methods -----------------------------
	public Developer create(){
		Collection<Demo>cd = new ArrayList<Demo>();
		Developer res = new Developer();
		res.setDemos(cd);
		UserAccount ua = new UserAccount();
		List<Authority> authorities = new ArrayList<Authority>();
		Authority a = new Authority();
		a.setAuthority(Authority.DEVELOPER);
		authorities.add(a);
		ua.setAuthorities(authorities);
		res.setUserAccount(ua);
		return res;
	}
	
	
	public Collection<Developer> findAll() {
		Collection<Developer> result;

		result = developerRepository.findAll();

		return result;
	}

	public Developer findOne(int userId) {
		Developer result;

		result = developerRepository.findOne(userId);

		return result;
	}
	
	public void save(Developer d) {
		Assert.notNull(d);
		developerRepository.saveAndFlush(d);
	}
	
	

	// other methods
	// ----------------------------------------------------------------------

	public Developer findByPrincipal() {
		UserAccount userAccount;
		Developer result;
		int id;

		userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);
		id = userAccount.getId();
		result = developerRepository.findByDeveloperAccountId(id);
		Assert.notNull(result);

		return result;

	}

	public Actor findByUserAccount(UserAccount userAccount) {
		Assert.notNull(userAccount);
		Developer result;
		result = developerRepository.findByUserAccountId(userAccount.getId());
		return result;
	}
	
	public Developer reconstruct(DeveloperRegisterForm df){
		Assert.isTrue(df.getPassword().equals(df.getConfirmPassword()));
		Assert.isTrue(df.getAccept());
		Developer res = create();
		res.getUserAccount().setUsername(df.getUsername());
		
		Md5PasswordEncoder enc = new Md5PasswordEncoder();
		String password = enc.encodePassword(df.getPassword(), null);
		res.getUserAccount().setPassword(password);
		
		res.setName(df.getName());
		res.setSurname(df.getSurname());
		res.setEmailAddress(df.getEmailAddress());
		res.setPhone(df.getPhone());
		
		return res;
	}

	public Collection<Developer> developersMoreCommentsThanAvg() {
		Collection<Developer> result;
		
		result = developerRepository.developersMoreCommentsThanAvg();
		
		return result;
	}

	

}
