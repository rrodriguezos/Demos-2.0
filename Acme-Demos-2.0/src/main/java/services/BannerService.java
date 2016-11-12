package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Banner;
import domain.Demo;

import domain.Investor;

import repositories.BannerRepository;

@Service
@Transactional
public class BannerService {
	
	// Managed repository -------------------
	@Autowired
	private BannerRepository bannerRepository;
	
	// Supporting Services ------------------
	@Autowired
	private InvestorService investorService;
	
	
	//CRUD Methods---------------------------
	public Banner create(){
		Banner res = new Banner();
		Investor investor = investorService.findByPrincipal();
		Assert.notNull(investor);
		res.setInvestor(investor);
		Collection<Demo>demos = new ArrayList<Demo>();
		res.setDemos(demos);
		return res;
	}
	
	public Collection<Banner>findAll(){
		Collection<Banner> res = bannerRepository.findAll();
		Assert.notNull(res);
		return res;
	}
	
	public void save(Banner b){
		Assert.notNull(b);
		bannerRepository.saveAndFlush(b);
	}
	
	//Other Bussiness Methods------------------------------
	public Collection<Banner> bannersByInvestor(int investorId){
		Collection<Banner> res = bannerRepository.bannersByInvestor(investorId);
		Assert.notNull(res);
		return res;
	}
	
	public Collection<Banner>bannersByPrincipal(){
		Investor investor = investorService.findByPrincipal();
		Assert.notNull(investor);
		Collection<Banner> res = bannersByInvestor(investor.getId());
		Assert.notNull(res);
		return res;
	}

}
