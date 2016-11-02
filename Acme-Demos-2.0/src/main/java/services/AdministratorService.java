package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.AdministratorRepository;
import security.LoginService;
import security.UserAccount;
import domain.Administrator;

@Service
@Transactional
public class AdministratorService {

	// Managed repository -----------------------
	@Autowired
	private AdministratorRepository administratorRepository;

	// Supporting services ----------------------

	// Constructors -----------------------------
	public AdministratorService() {
		super();
	}

	// Simple CRUD methods ----------------------

	public Collection<Administrator> findAll() {
		return administratorRepository.findAll();
	}

	public Administrator findOne(int profileId) {
		Administrator result = administratorRepository.findOne(profileId);
		Assert.notNull(result);
		return result;
	}

	// Other business methods -------------------
	public Administrator findByPrincipal() {
		Administrator result;
		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);
		result = findByUserAccount(userAccount);
		Assert.notNull(result);
		return result;
	}

	public Administrator findByUserAccount(UserAccount userAccount) {
		Assert.notNull(userAccount);
		Administrator result;
		result = administratorRepository.findByUserAccountId(userAccount
				.getId());
		return result;
	}

}
