package services;


import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.SectionRepository;
import domain.Section;

@Transactional
@Service
public class SectionService {
	
	// Constructor --------------------------------------------------
	public SectionService() {
		super();
	}
	
	
	// Managed Repository -------------------------------------------
	@Autowired
	private SectionRepository sectionRepository;
	
	
	// Supported Services -------------------------------------------
	
	
	// CRUD methods -------------------------------------------------
	public Collection<Section> findAll() {
		Collection<Section> result;

		result = sectionRepository.findAll();

		return result;
	}

	public Section findOne(int sectionId) {
		Section result;
		
		Assert.isTrue(sectionId != 0);

		result = sectionRepository.findOne(sectionId);
		Assert.notNull(result);

		return result;
	}
		
	public Section create(){
		Section result;
		
		result = new Section();
		
		return result;
	}
		
	public void save(Section section) {
		Assert.notNull(section);
		sectionRepository.saveAndFlush(section);
	}

	public Collection<Section> sectionsByWhitePaper(int whitePaperId) {
		Collection<Section> result;
		result = sectionRepository.sectionsByWhitePaper(whitePaperId);
		
		return result;
	}
	
	// Other Business Methods ---------------------------------------
	public Integer minSectionsPerWhitePaperLastQuarter(){
		Integer aux = sectionRepository.minSectionsPerWhitePaperLastQuarter();
		int res;
		if(aux == null)
			res = 0;
		else 
			res = aux;
		return res;
	}
	
	public Integer maxSectionsPerWhitePaperLastQuarter(){
		Integer aux = sectionRepository.maxSectionsPerWhitePaperLastQuarter();
		int res;
		if(aux == null)
			res = 0;
		else 
			res = aux;
		return res;
	}
	
	public Double avgSectionsPerWhitePaperLastQuarter(){
		Double aux = sectionRepository.avgSectionsPerWhitePaperLastQuarter();
		double res;
		if(aux == null)
			res = 0;
		else 
			res = aux;
		return res;
	}
}
