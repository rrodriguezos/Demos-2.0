package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.DemoRepository;
import domain.Comment;
import domain.Demo;
import domain.Description;
import domain.Developer;
import domain.Resource;

@Service
@Transactional
public class DemoService {

	// Managed repository -------------------
	@Autowired
	private DemoRepository demoRepository;

	// Supporting Services ------------------
	@Autowired
	private DeveloperService developerService;

	@Autowired
	private ResourceService resourceService;

	@Autowired
	private AdministratorService administratorService;

	// COnstructors -------------------------------------------------------
	public DemoService() {
		super();
	}

	// Simple CRUD methods--------------------------------------------------

	public Demo create() {
		Demo result;
		Collection<Resource> resources;
		Collection<Description> descriptions;
		Developer developer;

		Collection<Comment> comments;

		result = new Demo();

		resources = new ArrayList<Resource>();
		result.setResources(resources);

		descriptions = new ArrayList<Description>();
		result.setDescriptions(descriptions);

		developer = developerService.findByPrincipal();
		result.setDeveloper(developer);

		comments = new ArrayList<Comment>();
		result.setComments(comments);

		return result;
	}

	public void save(Demo demo) {
		Assert.notNull(demo);

		demoRepository.saveAndFlush(demo);
	}

	public void delete(Demo demo) {
		Assert.notNull(demo);
		checkPrincipal(demo.getDeveloper());
		demoRepository.delete(demo);
	}

	public Collection<Demo> findAll() {
		Collection<Demo> result;

		result = demoRepository.findAll();

		return result;
	}

	public Demo findOne(int demoId) {
		Demo result;

		result = demoRepository.findOne(demoId);

		return result;
	}

	// Other Methods--------------------
	private void checkPrincipal(Developer u) {
		Developer developer;

		developer = developerService.findByPrincipal();
		Assert.isTrue(developer != null);

		Assert.isTrue(developer.equals(u));
	}
	
	public Collection<Demo> search(String keyword){
		//TODO Alguien sabe hacerlo con query??
		String keyMin = keyword.toLowerCase();
		Collection<Demo> res = new ArrayList<Demo>();
		Collection<Demo> all = findAll();
		String min;
		for(Demo d : all){
			min = d.getTitle().toLowerCase();
			if(min.contains(keyMin)){
				res.add(d);
			}
			for(Description des : d.getDescriptions()){
				min = des.getText().toLowerCase();
				if(min.contains(keyMin)&&(!res.contains(d))){
					res.add(d);
				}
			}
			for(Resource r : d.getResources()){
				min = r.getTitle().toLowerCase();
				if((min.contains(keyMin))&&(!res.contains(d))){
					res.add(d);
				}
			}
		}
		Assert.notNull(res);
		return res;
	}

	// Repository Methods--------------------

	public Collection<Demo> findDemosByDeveloper(int developerId) {
		Collection<Demo> result;

		result = demoRepository.findDemosByDeveloper(developerId);

		return result;
	}

	public Collection<Demo> demoByDeveloperLogged() {
		Collection<Demo> result;
		Developer developer;

		developer = developerService.findByPrincipal();

		result = demoRepository.findDemosByDeveloper(developer.getId());

		return result;
	}
	

	// Dashboard Developer
	public Double averageNumberOfCommentsByDemo() {
		return demoRepository.averageNumberOfCommentsByDemo();
	}

	public Collection<Demo> demos25PercentageMoreCommentsThanAvg() {
		Collection<Demo> result;
		
		result = demoRepository.demos25PercentageMoreCommentsThanAvg();
		return result;
	}

	// public Double averageNumberOfStarsByDemo() {
	// return demoRepository.averageNumberOfStarsByDemo();
	// }

	// public Collection<Demo> demosSortedByStars() {
	// return demoRepository.demosSortedByStars();
	// }
	
	/***************** Principio *****************/
	public Double  ratioDemosWithInvestment() {
		Double result;
		
		result = demoRepository.ratioDemosWithInvestment();
		
		return result;
	}
	/***************** Fin *****************/

}
