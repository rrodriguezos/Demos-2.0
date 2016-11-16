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
import domain.Description;
import domain.ISOCode;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class DescriptionServiceTest extends AbstractTest {

	@Autowired
	private DescriptionService descriptionService;
	@Autowired
	private DemoService demoService;

	// ----------------------------------------------------
	// POSITIVE TEST CASES CREATE
	// ----------------------------------------------------
	// creado exitosamente
	@Test
	public void testCreateDescription1() {

		authenticate("developer1");
		int sizeBefore = descriptionService.findAll().size();
		Description desc = descriptionService.create();
		Demo demo = demoService.findOne(28);
		desc.setDemo(demo);
		desc.setIsoCode(ISOCode.ENG);
		desc.setText("Description test");

		descriptionService.save(desc);
		int sizeAfter = descriptionService.findAll().size();
		Assert.isTrue(sizeAfter == sizeBefore + 1);

		unauthenticate();
	}

	// ----------------------------------------------------
	// NEGATIVE TEST CASES CREATE
	// ----------------------------------------------------
	// description campos en blanco
	@Test(expected = ConstraintViolationException.class)
	public void testCreateDescription2() {
		authenticate("developer1");

		Description desc = descriptionService.create();
		Demo demo = demoService.findOne(28);
		desc.setDemo(demo);
		desc.setIsoCode(ISOCode.ENG);
		desc.setText("");

		descriptionService.save(desc);

		unauthenticate();
	}

	// crear un demo con actores no autorizados
	@Test(expected = IllegalArgumentException.class)
	public void testCreateDescription3() {
		authenticate("admin");
		Description desc = descriptionService.create();
		Demo demo = demoService.findOne(28);
		desc.setDemo(demo);
		desc.setIsoCode(ISOCode.ENG);
		desc.setText("Description test");

		descriptionService.save(desc);

		unauthenticate();
	}

	// ----------------------------------------------------
	// POSITIVE TEST CASES DELETE
	// ----------------------------------------------------
	// Eliminado correctamente

	@Test
	public void deletDescription1() {
		authenticate("developer1");
		Description description;
		int sizeBefore = descriptionService.findAll().size();

		description = descriptionService.findOne(52);

		descriptionService.delete(description);
		int sizeAfter = descriptionService.findAll().size();
		Assert.isTrue(sizeAfter == sizeBefore - 1);

		unauthenticate();

	}

	// ----------------------------------------------------
	// NEGATIVE TEST CASES DELETE
	// ----------------------------------------------------
	// Lo intenta un admin eliminar

	@Test(expected = IllegalArgumentException.class)
	public void deletDescription2() {
		authenticate("admin");
		Description description;

		description = descriptionService.findOne(52);

		descriptionService.delete(description);

		unauthenticate();
	}

	// Eliminamos un demo que no es demoId

	@Test(expected = NullPointerException.class)
	public void deletDescription3() {
		authenticate("developer1");
		Description description;

		description = descriptionService.findOne(887954);

		descriptionService.delete(description);

		unauthenticate();
	}

	// Listing requirement 1

	@Test
	public void testFindDescription() {
		Collection<Description> descriptions = descriptionService.findAll();
		Assert.isTrue(descriptions.size() == 13);
	}
	
	// ----------------------------------------------------
		// POSITIVE TEST CASES EDITION
		// ----------------------------------------------------

		// editado correctamente
		@Test
		public void editDescription1() {

			authenticate("developer1");
			String edit = "Text1";
			String edit2 = "Edit";
			Description description = descriptionService.findOne(52);
			Assert.isTrue(description.getText().equals(edit));
			description.setText("Edit");
			descriptionService.save(description);
			Assert.isTrue(description.getText().equals(edit2));

			unauthenticate();
		}

		// ----------------------------------------------------
		// NEGATIVE TEST CASES EDITION
		// ----------------------------------------------------
		// Edicion campos en blanco
		@Test(expected = ConstraintViolationException.class)
		public void editDemo2() {

			authenticate("developer1");

			Description description = descriptionService.findOne(52);
			description.setText("");
			descriptionService.save(description);


			unauthenticate();
		}

		// editar con actores no autorizados
		@Test(expected = IllegalArgumentException.class)
		public void editDemo3() {

			authenticate("admin");

			Description description = descriptionService.findOne(52);

			description.setText("Edit");
			descriptionService.save(description);


			unauthenticate();
		}

}
