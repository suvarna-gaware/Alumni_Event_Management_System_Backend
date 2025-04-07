package com.example.demo.Exception;

public class AlumniNotFoundException extends RuntimeException{
	private String message;

	public AlumniNotFoundException(String message) {
		super(message);
		this.message = message;
	}

}
