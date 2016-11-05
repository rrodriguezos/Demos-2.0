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

}
