package com.example.demo.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GobalExceptionHandler {
	@ExceptionHandler(value = AlumniNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public @ResponseBody ErrorMassage handlerAlumniException(AlumniNotFoundException exception) {
		return new ErrorMassage(HttpStatus.NOT_FOUND.value(), exception.getMessage());
	}

	@ExceptionHandler(value = DepartmentNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public @ResponseBody ErrorMassage handleDepartmentNotFound(DepartmentNotFoundException exception) {
		return new ErrorMassage(HttpStatus.NOT_FOUND.value(), exception.getMessage());
	}

	@ExceptionHandler(value = EventNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public @ResponseBody ErrorMassage handleEventNotFound(EventNotFoundException exception) {
		return new ErrorMassage(HttpStatus.NOT_FOUND.value(), exception.getMessage());
	}

	@ExceptionHandler(value = OrgnizationNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public @ResponseBody ErrorMassage handleOrganizationNotFoundException(OrgnizationNotFoundException exception) { // Updated
																													// method
																													// name
		return new ErrorMassage(HttpStatus.NOT_FOUND.value(), exception.getMessage());
	}

	@ExceptionHandler(value = EventAttendanceNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public @ResponseBody ErrorMassage handleEventAttendanceNotFoundException(
			EventAttendanceNotFoundException exception) {
		return new ErrorMassage(HttpStatus.NOT_FOUND.value(), exception.getMessage());
	}
	@ExceptionHandler(value = FeedbackNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public @ResponseBody ErrorMassage handlerFeedbackNotFoundException(
			FeedbackNotFoundException exception) {
		return new ErrorMassage(HttpStatus.NOT_FOUND.value(),exception.getMessage());
	}
}
