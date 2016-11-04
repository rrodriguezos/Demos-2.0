package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.LoanRepository;
import domain.Loan;

@Transactional
@Service
public class LoanService {
	
	// Constructor --------------------------------------------------
	public LoanService() {
		super();
	}
	
	
	// Managed Repository -------------------------------------------
	@Autowired
	private LoanRepository loanRepository;
	
	
	// Supported Services -------------------------------------------
	
	
	// CRUD methods -------------------------------------------------
	public Collection<Loan> findAll() {
		Collection<Loan> result;

		result = loanRepository.findAll();

		return result;
	}

	public Loan findOne(int loanId) {
		Loan result;
		
		Assert.isTrue(loanId != 0);

		result = loanRepository.findOne(loanId);
		Assert.notNull(result);

		return result;
	}
		
	public Loan create(){
		Loan result;
		
		result = new Loan();
		
		return result;
	}
		
	public void save(Loan loan) {
		Assert.notNull(loan);
		loanRepository.saveAndFlush(loan);
	}

	public Collection<Loan> findLoansByBank(int bankId) {
		Collection<Loan> result;
		
		result = loanRepository.findLoansByBank(bankId);
		
		return result;
	}
	
	// Other Business Methods ---------------------------------------

}
