package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.DescriptionRepository;
import domain.Description;
import domain.Developer;

@Service
@Transactional
public class DescriptionService {

	// Managed repository -------------------
	@Autowired
	private DescriptionRepository descriptionRepository;

	// Supporting Services ------------------
	@Autowired
	private DeveloperService developerService;

	// COnstructors -------------------------
	public DescriptionService() {
		super();
	}

	// Simple CRUD methods--------------------

	public Description create() {
		Description result;

		result = new Description();

		return result;
	}

	public Collection<Description> findAll() {
		Collection<Description> result;

		result = descriptionRepository.findAll();

		return result;
	}

	public Description findOne(int descriptionId) {
		Description result;

		result = descriptionRepository.findOne(descriptionId);

		return result;
	}

	public void save(Description description) {
		Assert.notNull(description);
		checkPrincipal(description.getDemo().getDeveloper());

		descriptionRepository.saveAndFlush(description);
	}

	public void delete(Description description) {
		checkPrincipal(description.getDemo().getDeveloper());

		descriptionRepository.delete(description);
	}

	// Other Methods--------------------

	private void checkPrincipal(Developer u) {
		Developer developer;

		developer = developerService.findByPrincipal();
		Assert.isTrue(developer != null);

		Assert.isTrue(developer.equals(u));
	}

	public Collection<Description> findDescriptionsByDemoId(int tripId) {
		Collection<Description> result;

		result = descriptionRepository.descriptionsByDemo(tripId);
		return result;
	}

}
