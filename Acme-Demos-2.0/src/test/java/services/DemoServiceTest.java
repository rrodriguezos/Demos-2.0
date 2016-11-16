package services;

import java.util.Collection;
import java.util.Date;

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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class DemoServiceTest extends AbstractTest {

	// BAsed services test---
	@Autowired
	private DemoService demoService;

	@Autowired
	private HelpDatesConvertService helpService;

	// ----------------------------------------------------
	// POSITIVE TEST CASES CREATE
	// ----------------------------------------------------
	// A user who is authenticated as an developer
	// 8.1 Manage his or her demos, which involves listing, registering,
	// modifying,
	// and deleting them.
	// creado exitosamente
	@Test
	public void testCreateDemoAsDeveloper() {
		authenticate("developer1");
		int sizeBefore = demoService.findAll().size();
		Demo demo = demoService.create();
		demo.setTitle("titulo 1");

		String momentReleased = "2017/02/02 00:00";

		Date date = helpService.formatFromStringToDate(momentReleased);

		demo.setMomentReleased(date);

		demoService.save(demo);
		int sizeAfter = demoService.findAll().size();
		Assert.isTrue(sizeAfter == sizeBefore + 1);
		unauthenticate();
	}

	// ----------------------------------------------------
	// NEGATIVE TEST CASES CREATE
	// ----------------------------------------------------
	// demo campos en blanco
	@Test(expected = ConstraintViolationException.class)
	public void testCreateDemoAsDeveloperNegative1() {
		authenticate("developer1");

		Demo demo = demoService.create();
		demo.setTitle("");

		String momentReleased = "";

		Date date = helpService.formatFromStringToDate(momentReleased);

		demo.setMomentReleased(date);

		demoService.save(demo);
		unauthenticate();
	}

	// demo con actores no autorizados
	@Test(expected = IllegalArgumentException.class)
	public void testCreateDemoAsDeveloperNegative2() {
		authenticate("admin");

		Demo demo = demoService.create();
		demo.setTitle("");

		String momentReleased = "";

		Date date = helpService.formatFromStringToDate(momentReleased);

		demo.setMomentReleased(date);

		demoService.save(demo);
		unauthenticate();
	}

	// ----------------------------------------------------
	// POSITIVE TEST CASES DELETE
	// ----------------------------------------------------
	// Eliminado correctamente

	@Test
	public void deletDemo() {
		authenticate("developer1");
		int sizeBefore = demoService.findAll().size();
		demoService.delete(demoService.findOne(28));
		int sizeAfter = demoService.findAll().size();
		Assert.isTrue(sizeAfter == sizeBefore - 1);
		unauthenticate();
	}

	// ----------------------------------------------------
	// NEGATIVE TEST CASES DELETE
	// ----------------------------------------------------
	// Lo intenta un admin eliminar

	@Test(expected = IllegalArgumentException.class)
	public void deleteDemo2() {
		authenticate("admin");
		demoService.delete(demoService.findOne(28));
		unauthenticate();
	}

	// Eliminamos un demo que no es demoId

	@Test(expected = IllegalArgumentException.class)
	public void deleteDemo3() {
		authenticate("developer1");
		demoService.delete(demoService.findOne(2500));

		unauthenticate();
	}

	// ----------------------------------------------------
	// POSITIVE TEST CASES EDITION
	// ----------------------------------------------------

	// editado correctamente
	@Test
	public void editDemo1() {

		authenticate("developer1");
		String edit = "Acme-Music";
		String edit2 = "Acme-Edit";
		Demo demo = demoService.findOne(28);
		Assert.isTrue(demo.getTitle().equals(edit));
		demo.setTitle("Acme-Edit");
		demoService.save(demo);
		Assert.isTrue(demo.getTitle().equals(edit2));

		unauthenticate();
	}

	// ----------------------------------------------------
	// NEGATIVE TEST CASES EDITION
	// ----------------------------------------------------
	// Edicion campos en blanco
	@Test(expected = ConstraintViolationException.class)
	public void editDemo2() {

		authenticate("developer1");
		String edit = "Acme-Music";
		String edit2 = "Acme-Edit";
		Demo demo = demoService.findOne(28);
		Assert.isTrue(demo.getTitle().equals(edit));
		demo.setTitle("");
		demoService.save(demo);
		Assert.isTrue(demo.getTitle().equals(edit2));

		unauthenticate();
	}

	// editar con actores no autorizados
	@Test(expected = IllegalArgumentException.class)
	public void editDemo3() {

		authenticate("admin");
		String edit = "Acme-Music";
		String edit2 = "Acme-Edit";
		Demo demo = demoService.findOne(28);
		Assert.isTrue(demo.getTitle().equals(edit));
		demo.setTitle("Acme-Edit");
		demoService.save(demo);
		Assert.isTrue(demo.getTitle().equals(edit2));

		unauthenticate();

	}

	/*
	 * An actor who is not authenticated must be able to: Search the catalogue
	 * of demos using a single key word that must appear somewhere in its
	 * tile,its description, or the title of the corresponding resources, if
	 * any.
	 */
	@Test
	public void search() {
		Collection<Demo> encontrados = demoService.search("testo");
		for (Demo d : encontrados) {
			System.out.println(d.getTitle());
		}
	}
	
	/*
	 * An actor who is not authenticated must be able to: List the demos
	 * available.
	 */
	@Test
	public void catalogue() {
		Collection<Demo> encontrados = demoService.findAll();
		for (Demo d : encontrados) {
			System.out.println(d.getTitle());
		}
	}

	// Edition requirement 1

	@Test
	public void editionDemo1() {

		authenticate("developer1");
		String edit = "Acme-Canyoning ";
		String edit2 = "Acme-Edit";
		Demo demo = demoService.findOne(29);
		Assert.isTrue(demo.getTitle().equals(edit));
		demo.setTitle("Acme-Edit");
		demoService.save(demo);
		Assert.isTrue(demo.getTitle().equals(edit2));

		unauthenticate();
	}

	// Edition requirement 2

	@Test
	public void editionDemo2() {

		authenticate("developer1");

		String momentReleased = "2017/02/02 00:00";

		Date date = helpService.formatFromStringToDate(momentReleased);

		Demo demo = demoService.findOne(28);
		demo.setMomentReleased(date);
		demoService.save(demo);
		Assert.isTrue(demo.getMomentReleased().equals(date));

		unauthenticate();
	}

}
