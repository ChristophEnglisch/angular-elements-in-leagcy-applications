package tech.englisch.person.service;

public class PersonNotFoundException extends RuntimeException {
	public PersonNotFoundException(Long id) {
		super("person with id %s not found".formatted(id));
	}
}
