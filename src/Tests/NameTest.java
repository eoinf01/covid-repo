package Tests;

import org.junit.*;

import Controller.Database;
import Model.Name;
import Model.Person;

import static org.junit.Assert.*;


public class NameTest {
	Name name; // declare a Counter here
	
	@Before
	public void setUp() {
		// run before each test
		name = new Name("Eoin","Fehily");
		} 
	
	@Test
	public void testGetFirstName() {
		assertEquals("Eoin", name.getFirstName());
	}
	
	@Test
	public void testGetLastName() {
		assertEquals("Fehily", name.getLastName());
	}

}
