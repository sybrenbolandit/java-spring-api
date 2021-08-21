package org.shboland;

import org.junit.jupiter.api.Test;
import org.shboland.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Testcontainers
public class PersonIT {

	@Autowired
	private PersonController personController;

	@Container
	public static MySQLContainer mysql = new MySQLContainer("mysql:8.0.26");

	@DynamicPropertySource
	public static void setProperties(DynamicPropertyRegistry registry) {
		registry.add("spring.datasource.url", mysql::getJdbcUrl);
		registry.add("spring.datasource.username", mysql::getUsername);
		registry.add("spring.datasource.password", mysql::getPassword);
	}

	@Test
	public void testCreatePerson() {
		Person person = Person.builder()
						.name("Syb")
						.email("syb@dev.com")
						.build();

		Person responsePerson = personController.createPerson(person);

		assertEquals("Syb", responsePerson.getName());
		assertEquals("syb@dev.com", responsePerson.getEmail());
	}

	@Test
	public void testGetPerson() {
		Person person = Person.builder()
						.name("Mart")
						.email("mart@dev.com")
						.build();

		Person createdPerson = personController.createPerson(person);
		Integer createdId = createdPerson.getId();

		Person responsePerson = personController.getPerson(createdId.toString());

		assertEquals(createdId, responsePerson.getId());
		assertEquals("Mart", responsePerson.getName());
		assertEquals("mart@dev.com", responsePerson.getEmail());
	}

	@Test
	public void testGetUnknownPerson() {
		RuntimeException exception = assertThrows(RuntimeException.class, () -> personController.getPerson("321"));
		assertEquals("No person found for id: 321", exception.getMessage());
	}
}
