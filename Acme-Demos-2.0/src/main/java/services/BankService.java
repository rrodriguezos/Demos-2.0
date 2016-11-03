package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.BankRepository;
import domain.Bank;

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
	
	
	// CRUD methods -------------------------------------------------
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
		
	public Bank create(){
		Bank result;
		
		result = new Bank();
		
		return result;
	}
		
	public void save(Bank bank) {
		Assert.notNull(bank);
		bankRepository.saveAndFlush(bank);
	}
	
	// Other Business Methods ---------------------------------------

}
