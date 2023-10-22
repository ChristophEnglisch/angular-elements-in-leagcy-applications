package tech.englisch.person.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.englisch.person.entity.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {

}
