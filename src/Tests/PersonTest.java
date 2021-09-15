package Tests;

import org.junit.*;

import Model.Name;
import Model.Person;

import static org.junit.Assert.*;

public class PersonTest {
	Person person; // declare a Counter here
	Name name;
	
	@Before
	public void setUp() {
		// run before each test
		name = new Name("Eoin","Fehily");
		person = new Person(12,name,021); // initialize the Counter here
		} 
	
	@Test
	public void testGetId() {
		assertEquals(12, person.getId());
	}
	
	@Test
	public void testGetPhone() {
		assertEquals(021, person.getPhone());
	}

}
