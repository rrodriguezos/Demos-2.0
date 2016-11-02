package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.InstalmentRepository;
import domain.Instalment;
import domain.Investment;
import domain.Investor;

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
}
