package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.LoanRepository;
import domain.Bank;
import domain.Developer;
import domain.Instalment;
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
	@Autowired
	private DeveloperService developerService;
	
	@Autowired
	private BankService bankService;
	
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
		
		Developer developer = developerService.findByPrincipal();
		Assert.notNull(developer);
		
		result.setDeveloper(developer);
		
		Collection<Instalment>instalments = new ArrayList<Instalment>();
		result.setInstalments(instalments);
		
		result.setStatus("PENDING");
		result.setRequestMoment(new Date(System.currentTimeMillis() - 100));
		return result;
	}
		
	public void save(Loan loan) {
		Assert.notNull(loan);
		loanRepository.saveAndFlush(loan);
	}

	
	
	// Other Business Methods ---------------------------------------
	public Collection<Loan> findLoansByBank(int bankId) {
		Collection<Loan> result;
		
		result = loanRepository.findLoansByBank(bankId);
		Assert.notNull(result);
		return result;
	}
	
	public Collection<Loan> findLoansByDev(int devId) {
		Collection<Loan> result;
		
		result = loanRepository.findLoansByDev(devId);
		Assert.notNull(result);
		return result;
	}
	
	public Collection<Loan> findLoansByDevPrincipal() {
		Collection<Loan> result;
		
		Developer developer = developerService.findByPrincipal();
		Assert.notNull(developer);
		
		result = findLoansByDev(developer.getId());
		Assert.notNull(result);
		return result;
	}
	
	public Collection<Loan> findLoansByBankPrincipal() {
		Collection<Loan> result;
		Bank bank;
		
		bank = bankService.findByPrincipal();
		Assert.notNull(bank);
		
		result = findLoansByBank(bank.getId());
		Assert.notNull(result);
		
		return result;
	}
	
	public void cancel(Loan l){
		Assert.notNull(l);
		Assert.isTrue(l.getStatus().equals("PENDING"));
		loanRepository.delete(l);
	}

	public void approve(int loanId) {
		Loan loan;
		
		loan = findOne(loanId);
		Assert.isTrue(loan.getStatus().equals("PENDING"), "loan.status.error");
		
		loan.setStatus("APPROVED");
		loan.setProcessMoment(new Date(System.currentTimeMillis() - 10));
		
		save(loan);
	}
	
	public void deny(int loanId) {
		Loan loan;
		
		loan = findOne(loanId);
		Assert.isTrue(loan.getStatus().equals("PENDING"), "loan.status.error");
		
		loan.setStatus("DENIED");
		loan.setProcessMoment(new Date(System.currentTimeMillis() - 10));
		
		save(loan);
	}
	
	public Double avgPendingLoansPerBank(){
		return loanRepository.avgPendingLoansPerBank();
	}
	
	public Double ratioApprovedToDeniedLoansPerBank(){
		double res;
		try{
			res = loanRepository.ratioApprovedToDeniedLoansPerBank();
		}catch(NullPointerException nex){
			res = 0.0;
		}
		return res;
	}
	
	public Double ratioApprovedToDeniedLoansPerDeveloper(int devloperId){
		double res;
		try{
			res = loanRepository.ratioApprovedToDeniedLoansPerDeveloper(devloperId);
		}catch(NullPointerException nex){
			res = 0.0;
		}
		return res;
	}
	
	
	
}
