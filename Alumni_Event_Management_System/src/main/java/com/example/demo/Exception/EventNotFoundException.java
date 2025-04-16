package com.example.demo.Exception;

public class EventNotFoundException extends RuntimeException{
	
	public EventNotFoundException(String message) {
        super(message);
	}
}
