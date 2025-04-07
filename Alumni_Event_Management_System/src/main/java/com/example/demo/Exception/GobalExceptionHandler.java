package com.example.demo.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
@RestControllerAdvice
public class GobalExceptionHandler {
	@ExceptionHandler(value=AlumniNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public @ResponseBody ErrorMassage handlerAlumniException(AlumniNotFoundException exception) {
		return new ErrorMassage(HttpStatus.NOT_FOUND.value(),exception.getMessage());
	}


}
