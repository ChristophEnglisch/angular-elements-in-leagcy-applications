package tech.englisch.person.rest.person;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tech.englisch.person.entity.Person;
import tech.englisch.person.service.PersonService;

import java.util.Collection;

@RestController
@RequestMapping("person")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class PersonController {

	private final PersonService service;

	@GetMapping
	public Collection<Person> getAllPersons() {
		return service.findAll();
	}

	@GetMapping("/{id}")
	public Person getPersonById(@PathVariable Long id) {
		return service.findById(id);
	}

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public Person createPerson(@RequestBody PersonPayload person) {
		return service.save(person);
	}

	@PutMapping("/{id}")
	@ResponseStatus(code = HttpStatus.ACCEPTED)
	public Person updatePerson(@PathVariable Long id, @RequestBody PersonPayload person) {
		return service.updatePerson(id, person);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void deletePerson(@PathVariable Long id) {
		service.deleteById(id);
	}

}
