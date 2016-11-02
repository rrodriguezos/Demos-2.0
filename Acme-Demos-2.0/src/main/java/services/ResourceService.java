package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ResourceRepository;
import domain.Developer;
import domain.Resource;

@Service
@Transactional
public class ResourceService {

	// Managed repository -------------------
	@Autowired
	private ResourceRepository resourceRepository;

	// Supporting Services ------------------
	@Autowired
	private DeveloperService developerService;

	// COnstructors -------------------------
	public ResourceService() {
		super();
	}

	//Simple CRUD methods--------------------

		public Resource create(){
			Resource result;	

			result = new Resource();			

			return result;
		}

		public Collection<Resource> findAll(){
			Collection<Resource> result;

			result = resourceRepository.findAll();

			return result;
		}

		public Resource findOne(int resourceId){
			Resource result;

			result = resourceRepository.findOne(resourceId);

			return result;
		}

		public void save(Resource resource){
			Assert.notNull(resource);
			checkPrincipal(resource.getDemo().getDeveloper());
			
			resourceRepository.saveAndFlush(resource);
		}

		public void delete(Resource resource){
			checkPrincipal(resource.getDemo().getDeveloper());
			
			
			resourceRepository.delete(resource);
		}


		//Other Methods--------------------
		
		private void checkPrincipal(Developer d){
			Developer developer;
		
			developer = developerService.findByPrincipal();
			Assert.isTrue(developer != null);
			
			Assert.isTrue(developer.equals(d));
		}
		
		public Collection<Resource> findResourcesByDemoId(int demoId){
			Collection<Resource> result;
			
			result = resourceRepository.resourcesByDemo(demoId);
			return result;
		}
		

}
