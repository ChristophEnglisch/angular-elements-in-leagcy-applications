package tech.englisch.person.rest.person;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import tech.englisch.person.rest.ApiError;
import tech.englisch.person.service.PersonNotFoundException;

@ControllerAdvice
public class PersonControllerAdvice {
	@ExceptionHandler(PersonNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ApiError handlePersonNotFoundException(PersonNotFoundException ex) {
		return new ApiError(HttpStatus.NOT_FOUND, ex.getMessage());
	}
}
