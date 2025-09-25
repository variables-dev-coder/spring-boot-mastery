package com.munna.springboot.day5.exception;

public class NotFoundException extends RuntimeException {
	public NotFoundException(String message) { 
		super(message);
		}
}
