package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.InvestmentRepository;
import domain.Demo;
import domain.Instalment;
import domain.Investment;
import domain.Investor;

@Service
@Transactional
public class InvestmentService {
	
	// Managed repository -------------------
	@Autowired
	private InvestmentRepository investmentRepository;
	
	// Supporting Services ------------------
	@Autowired
	private InvestorService investorService;
	
	@Autowired
	private DemoService demoService;
	
	
	//CRUD Methods-----------------------------------
	public Investment create(Integer demoId) {
		Investment result;
		Investor investor;
		Demo demo;
		Collection<Instalment> instalments;
		Date now;
		
		investor = investorService.findByPrincipal();
		Assert.notNull(investor);
		
		demo = demoService.findOne(demoId);
		Assert.notNull(demo);
		
		now = new Date(System.currentTimeMillis() - 10);
		
		result = new Investment();
		instalments = new ArrayList<Instalment>();
		result.setApprovalMoment(now);
		result.setInvestor(investor);
		result.setDemo(demo);
		result.setInstalments(instalments);
		
		return result;
	}
	
	public Collection<Investment> findAll(){
		Collection<Investment> res = investmentRepository.findAll();
		Assert.notNull(res);
		return res;
	}
	
	public Investment findOne(int id){
		Investment res = investmentRepository.findOne(id);
		Assert.notNull(res);
		return res;
	}
	
	public void save (Investment investment){
		Date now;
		
		Assert.notNull(investment);
		checkPrincipal(investment.getInvestor());
		
		now = new Date(System.currentTimeMillis() - 10);
		investment.setApprovalMoment(now);
		
		investmentRepository.saveAndFlush(investment);
	}
	
	public void delete(Investment i){
		Assert.notNull(i);
		checkPrincipal(i.getInvestor());
		investmentRepository.delete(i);
	}
	
	
	//Other Bussines Methods---------------------------
	public Collection<Investment>findInvestmentByInvestor(int investorId){
		Collection<Investment> res = investmentRepository.findInvestmentByInvestor(investorId);
		Assert.notNull(res);
		return res;
	}
	
	private void checkPrincipal(Investor i) {
		Investor inv = investorService.findByPrincipal();
		Assert.isTrue(inv != null);

		Assert.isTrue(inv.equals(i));
	}

	public Collection<Investment> investmentsByInvestorLogged() {
		Collection<Investment> result;
		Investor investor;
		
		investor = investorService.findByPrincipal();
				
		result = investmentRepository.investmentsByInvestorLogged(investor.getId());
		
		return result;
	}
	
	/***************** Principio *****************/
	public Collection<Object[]>  avgStddevInvestmentsPerInvestor() {
		Collection<Object[]> result;
		
		result = investmentRepository.avgStddevInvestmentsPerInvestor();
		
		return result;
	}
	/***************** Fin *****************/

}
