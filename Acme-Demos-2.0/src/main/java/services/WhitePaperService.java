package services;

import java.util.Collection;
import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.WhitePaperRepository;
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

		result = new WhitePaper();

		return result;
	}

	public void save(WhitePaper whitePaper) {
		Assert.notNull(whitePaper);
		whitePaperRepository.saveAndFlush(whitePaper);
	}

	// Other Business Methods ---------------------------------------

	public Collection<WhitePaper> whitePapersByInvestor(int investorId) {
		Collection<WhitePaper> result;

		result = whitePaperRepository.whitePapersByInvestor(investorId);
		return result;
	}

	public Collection<WhitePaper> searchByKeyword(String q, String date1,String date2) {
		Collection<WhitePaper> result = new LinkedList<WhitePaper>();
		Collection<WhitePaper> cotaText = new LinkedList<WhitePaper>();

		if (date1.isEmpty()|| date2.isEmpty()) {

			result = whitePaperRepository.searchByKeyword(q);

		}else{
			
			cotaText = whitePaperRepository.searchByKeyword(q);
			//Falta acotar por las fechas de entrada a patir de date1 y date 2 y el texto
		}

		return result;
	}

}
