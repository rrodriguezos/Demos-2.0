package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.WhitePaperRepository;
import domain.Investor;
import domain.Section;
import domain.WhitePaper;

@Transactional
@Service
public class WhitePaperService {

	// Constructor --------------------------------------------------
	public WhitePaperService() {
		super();
	}

	// Managed Repository -------------------------------------------
	@Autowired
	private WhitePaperRepository whitePaperRepository;

	// Supported Services -------------------------------------------
	@Autowired
	private InvestorService investorService;
	
	
	// CRUD methods -------------------------------------------------
	public Collection<WhitePaper> findAll() {
		Collection<WhitePaper> result;

		result = whitePaperRepository.findAll();

		return result;
	}

	public WhitePaper findOne(int whitePaperId) {
		WhitePaper result;

		Assert.isTrue(whitePaperId != 0);

		result = whitePaperRepository.findOne(whitePaperId);
		Assert.notNull(result);

		return result;
	}

	public WhitePaper create() {
		WhitePaper result;
		Investor investor;
		Collection<Section> sections;

		investor = investorService.checkPrincipal();
		sections = new ArrayList<Section>();
		result = new WhitePaper();
		
		result.setInvestor(investor);
		result.setSections(sections);

		return result;
	}

	public void save(WhitePaper whitePaper) {
		Assert.isTrue(investorService.checkPrincipal().equals(whitePaper.getInvestor()));
		Assert.notNull(whitePaper);
		whitePaperRepository.saveAndFlush(whitePaper);
	}
	
	public void delete(WhitePaper whitePaper) {
		Investor investor;
		
		Assert.notNull(whitePaper);
		
		investor = investorService.checkPrincipal();
		Assert.isTrue(whitePaper.getInvestor().equals(investor));
		
		whitePaperRepository.delete(whitePaper.getId());
	}

	// Other Business Methods ---------------------------------------

	public Collection<WhitePaper> whitePapersByInvestor(int investorId) {
		Collection<WhitePaper> result;

		result = whitePaperRepository.whitePapersByInvestor(investorId);
		return result;
	}

	public Collection<WhitePaper> searchByKeyword(String q, Date date1,
			Date date2) {
		Collection<WhitePaper> result = new LinkedList<WhitePaper>();

		if (date1 == null || date2 == null) {

			result = whitePaperRepository.searchByKeyword(q);

		} else {

			result = whitePaperRepository.searchByKeywordWithRange(q, date1,
					date2);

		}

		return result;
	}
	
	public Double avgWhitePaperPerInvestor(){
		return whitePaperRepository.avgWhitePapersPerInvestor();
	}

	public Collection<WhitePaper> findAllByPrincipal() {
		Collection<WhitePaper> result;
		Investor investorPrincipal;
		
		investorPrincipal = investorService.checkPrincipal();
		result = this.whitePapersByInvestor(investorPrincipal.getId());
		Assert.notNull(result);
		
		return result;
	}

}
