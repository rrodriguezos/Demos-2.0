package services;

import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import domain.Demo;



import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml", "classpath:spring/config/packages.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class DemoServiceTest extends AbstractTest {
	
	//BAsed services test---
	@Autowired
	private DemoService demoService;
	
	
	/*
	 An actor who is not authenticated must be able to:
		Search the catalogue of demos using a single key 
		word that must appear somewhere in its tile,its 
		description, or the title of the corresponding 
		resources, if any.
	 */
	@Test
	public void search(){
		Collection<Demo>encontrados = demoService.search("testo");
		for(Demo d : encontrados){
			System.out.println(d.getTitle());
		}
	}
	
	

}
