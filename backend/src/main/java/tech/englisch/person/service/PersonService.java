package tech.englisch.person.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tech.englisch.person.entity.Person;
import tech.englisch.person.repository.PersonRepository;
import tech.englisch.person.rest.person.PersonPayload;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class PersonService {

	private final PersonRepository repository;

	public Collection<Person> findAll() {
		return repository.findAll();
	}

	public Person findById(Long id) {
		return repository.findById(id).orElseThrow(() -> new PersonNotFoundException(id));
	}

	public Person save(PersonPayload person) {
		return repository.save(Person.fromPayload(person));
	}

	public Person updatePerson(Long id, PersonPayload personPayload) {
		Person person = findById(id);
		person.toExisting(personPayload);
		return repository.save(person);
	}

	public void deleteById(Long id) {
		if(!repository.existsById(id)) {
			throw new PersonNotFoundException(id);
		}
		repository.deleteById(id);
	}
}
