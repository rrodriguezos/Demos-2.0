package services;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.InstalmentRepository;
import domain.Instalment;
import domain.Investment;
import domain.Investor;
import domain.Loan;
import forms.CreateInstalmentsForm;

@Service
@Transactional
public class InstalmentService {

	// Managed repository ---------------------------------
	@Autowired
	private InstalmentRepository instalmentRepository;
	
	// Supporting Services --------------------------------
	@Autowired
	private InvestorService investorService;
	
	@Autowired
	private InvestmentService investmentService;
	
	@Autowired
	private BankService bankService;
	
	@Autowired
	private LoanService loanService;
	
	
	//CRUD Methods ----------------------------------------
	public Instalment create(Integer investmentId) {
		Instalment result;
		Investor investor;
		Investment investment;
		
		investor = investorService.findByPrincipal();
		Assert.notNull(investor);
		
		investment = investmentService.findOne(investmentId);
		
		result = new Instalment();
		result.setInvestment(investment);
		
		return result;
	}
	
	public Instalment create(Integer loanId, Double amount, Date date) {
		Instalment result;
		Loan loan;
		
		bankService.findByPrincipal();
		
		loan = loanService.findOne(loanId);
		
		result = new Instalment();
		result.setLoan(loan);
		result.setAmount(amount);
		result.setInstalmentDate(date);
		result.setPaid(false);
		
		return result;
	}
	
	public Collection<Instalment> findAll(){
		Collection<Instalment> result;
		
		result = instalmentRepository.findAll();
		Assert.notNull(result);
		
		return result;
	}
	
	public Instalment findOne(int id){
		Instalment result;
		result = instalmentRepository.findOne(id);
		Assert.notNull(result);
		
		return result;
	}
	
	public void save (Instalment instalment){
		Assert.notNull(instalment);
		checkPrincipal(instalment.getInvestment().getInvestor());
		instalmentRepository.saveAndFlush(instalment);
	}
	
	public void saveBank (Instalment instalment){
		Assert.notNull(instalment);
		bankService.checkPrincipal();
		instalmentRepository.saveAndFlush(instalment);
	}
	
	public void delete(Instalment instalment){
		Assert.notNull(instalment);
		checkPrincipal(instalment.getInvestment().getInvestor());
		instalmentRepository.delete(instalment);
	}
	
	
	//Other Bussines Methods---------------------------
	private void checkPrincipal(Investor i) {
		Investor inv = investorService.findByPrincipal();
		Assert.isTrue(inv != null);

		Assert.isTrue(inv.equals(i));
	}
	
	public Collection<Instalment> findByInvestment(int investmentId){
		Collection<Instalment> result = instalmentRepository.findByInvestment(investmentId);
		Assert.notNull(result);
		return result;
	}

	/***************** Principio *****************/
	public Double  avgMoneyInvestInDemos() {
		Double result;
		
		result = instalmentRepository.avgMoneyInvestInDemos();
		
		return result;
	}
	
	public Double  avgNumberInstalmentsPerInvestment() {
		Double result;
		
		result = instalmentRepository.avgNumberInstalmentsPerInvestment();
		
		return result;
	}
	/***************** Fin *****************/

	public Collection<Instalment> findByLoan(int loanId) {
		Collection<Instalment> result;
		
		Assert.notNull(loanId);
		
		result = instalmentRepository.findByLoan(loanId);
		Assert.notNull(result);
		
		return result;
	}

	public void createAndSave(CreateInstalmentsForm form) {
		Instalment instalment;
		Loan loan;
		Double amount;
		Calendar cal;
		
		loan = loanService.findOne(form.getLoanId());
		amount = loan.getAmount() / form.getNumber();

		cal = Calendar.getInstance();
		cal.setTime(form.getDate());
		
		for (int i = 0; i < form.getNumber(); i++) {
			instalment = create(form.getLoanId(), amount, cal.getTime());
			
			saveBank(instalment);
			
			cal.add(Calendar.MONTH, 1);
		}
	}

	public Instalment pay(int instalmentId) {
		Instalment result;
		
		bankService.checkPrincipal();
		
		result = findOne(instalmentId);
		Assert.isTrue(result.getInstalmentDate().after(new Date()));
		
		result.setPaid(true);
		saveBank(result);
		
		return result;
	}
}
