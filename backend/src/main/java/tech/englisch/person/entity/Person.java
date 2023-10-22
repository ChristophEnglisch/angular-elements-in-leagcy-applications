package tech.englisch.person.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import tech.englisch.person.rest.person.PersonPayload;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Person {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String firstname;
	private String lastname;
	private int age;

	public Person(String firstname, String lastname, int age) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.age = age;
	}

	public static Person fromPayload(PersonPayload personPayload) {
		return new Person(personPayload.firstname(), personPayload.lastname(), personPayload.age());
	}

	public void toExisting(PersonPayload personPayload) {
		this.firstname = personPayload.firstname();
		this.lastname = personPayload.lastname();
		this.age = personPayload.age();
	}
}
