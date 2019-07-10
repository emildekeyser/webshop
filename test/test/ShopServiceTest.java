package test;

import static org.junit.Assert.*;

import domain.model.Person;
import domain.model.ShopService;
import org.junit.Before;
import org.junit.Test;

public class ShopServiceTest {
	private static ShopService service = new ShopService();
	private Person person;
	private String userid;
	private String PASSWORD = "1234";

	@Before
	public void setUp() {
		userid = generateRandomUseridInOrderToRunTestMoreThanOnce("sinterklaas");
		person = new Person(userid, "klaas@klaas.be", PASSWORD, "Klaas", "Claesens");
	}

	@Test
	public void getPerson_should_return_the_person_if_registered() {
		service.addPerson(person);

		Person personRetrieved = service.getPerson(userid);

		assertNotNull(personRetrieved);
		assertTrue(personRetrieved.isCorrectPassword(PASSWORD));
		assertEquals(userid, personRetrieved.getUserid());
		assertEquals(person.getEmail(), personRetrieved.getEmail());
		assertEquals(person.getFirstName(), personRetrieved.getFirstName());
		assertEquals(person.getLastName(),personRetrieved.getLastName());
	}

	@Test(expected = domain.db.DbException.class)
	public void getPerson_should_return_null_if_person_not_registered() {
		service.addPerson(person);
		
		service.getPerson("Unknown");

//		assertNull(personRetrieved);
	}

	@Test
	public void getUserIfAuthenticated_should_return_the_person_if_registered_and_correct_password() {
		service.addPerson(person);

		Person personRetrieved = service.getUserIfAuthenticated(userid, PASSWORD);

		assertNotNull(personRetrieved);
		assertTrue(personRetrieved.isCorrectPassword(PASSWORD));
		assertEquals(userid, personRetrieved.getUserid());
	}

	@Test(expected = domain.db.DbException.class)
	public void getUserIfAuthenticated_should_throw_exception_if_person_not_registered() {
		service.addPerson(person);
		
		service.getUserIfAuthenticated("Unknown", PASSWORD);

//		assertNull(personRetrieved);
	}

	@Test(expected = domain.db.DbException.class)
	public void getUserIfAuthenticated_should_throw_exception_if_person_is_registered_but_incorrect_password() {
		service.addPerson(person);
		
		service.getUserIfAuthenticated(userid, "WrongPassword");

//		assertNull(personRetrieved);
	}

	private String generateRandomUseridInOrderToRunTestMoreThanOnce(String component) {
		int random = (int) (Math.random() * 1000 + 1);
		return random + component;
	}

}
