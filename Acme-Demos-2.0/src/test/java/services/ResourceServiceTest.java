package services;

import java.util.Collection;

import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Demo;
import domain.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class ResourceServiceTest extends AbstractTest {

	@Autowired
	private ResourceService resourceService;
	@Autowired
	private DemoService demoService;

	// ----------------------------------------------------
	// POSITIVE TEST CASES CREATE
	// ----------------------------------------------------
	// creado exitosamente
	@Test
	public void testCreateResource1() {

		authenticate("developer1");
		int sizeBefore = resourceService.findAll().size();
		Resource desc = resourceService.create();
		Demo demo = demoService.findOne(28);
		desc.setDemo(demo);
		desc.setTitle("Title 1");
		desc.setLink("http://www.acme-demos.com/test");

		resourceService.save(desc);
		int sizeAfter = resourceService.findAll().size();
		Assert.isTrue(sizeAfter == sizeBefore + 1);

		unauthenticate();
	}

	// ----------------------------------------------------
	// NEGATIVE TEST CASES CREATE
	// ----------------------------------------------------
	// resource campos en blanco
	@Test(expected = ConstraintViolationException.class)
	public void testCreateResource2() {
		authenticate("developer1");

		Resource desc = resourceService.create();
		Demo demo = demoService.findOne(28);
		desc.setDemo(demo);
		desc.setTitle("");
		desc.setLink("");

		resourceService.save(desc);

		unauthenticate();
	}

	// crear un demo con actores no autorizados
	@Test(expected = IllegalArgumentException.class)
	public void testCreateResource3() {
		authenticate("admin");

		Resource desc = resourceService.create();
		Demo demo = demoService.findOne(28);
		desc.setDemo(demo);
		desc.setTitle("Title 1");
		desc.setLink("http://www.acme-demos.com/test");

		resourceService.save(desc);

		unauthenticate();
	}

	// ----------------------------------------------------
	// POSITIVE TEST CASES DELETE
	// ----------------------------------------------------
	// Eliminado correctamente

	@Test
	public void deletResource1() {
		authenticate("developer1");
		Resource resource;
		int sizeBefore = resourceService.findAll().size();

		resource = resourceService.findOne(37);

		resourceService.delete(resource);
		int sizeAfter = resourceService.findAll().size();
		Assert.isTrue(sizeAfter == sizeBefore - 1);

		unauthenticate();

	}

	// ----------------------------------------------------
	// NEGATIVE TEST CASES DELETE
	// ----------------------------------------------------
	// Lo intenta un admin eliminar

	@Test(expected = IllegalArgumentException.class)
	public void deletResource2() {
		authenticate("admin");
		Resource resource;

		resource = resourceService.findOne(37);

		resourceService.delete(resource);

		unauthenticate();
	}

	// Eliminamos un demo que no es demoId

	@Test(expected = NullPointerException.class)
	public void deletResource3() {
		authenticate("developer1");
		Resource resource;

		resource = resourceService.findOne(887954);

		resourceService.delete(resource);

		unauthenticate();
	}

	// Listing requirement 1

	@Test
	public void testFindResource() {
		Collection<Resource> resources = resourceService.findAll();
		Assert.isTrue(resources.size() == 3);
	}

	// ----------------------------------------------------
	// POSITIVE TEST CASES EDITION
	// ----------------------------------------------------

	// editado correctamente
	@Test
	public void editResource1() {

		authenticate("developer1");
		String edit = "UML Model";
		String edit2 = "Edit";
		Resource resource = resourceService.findOne(37);
		Assert.isTrue(resource.getTitle().equals(edit));
		resource.setTitle("Edit");
		resourceService.save(resource);
		Assert.isTrue(resource.getTitle().equals(edit2));

		unauthenticate();
	}

	// ----------------------------------------------------
	// NEGATIVE TEST CASES EDITION
	// ----------------------------------------------------
	// Edicion campos en blanco
	@Test(expected = ConstraintViolationException.class)
	public void editDemo2() {

		authenticate("developer1");

		Resource resource = resourceService.findOne(37);
		resource.setTitle("");
		resourceService.save(resource);

		unauthenticate();
	}

	// editar con actores no autorizados
	@Test(expected = IllegalArgumentException.class)
	public void editDemo3() {

		authenticate("admin");

		Resource resource = resourceService.findOne(37);

		resource.setTitle("Edit");
		resourceService.save(resource);

		unauthenticate();
	}

}
