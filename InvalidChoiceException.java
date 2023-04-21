package com.project.todo;

public class InvalidChoiceException extends RuntimeException {
	String message;
	
	public InvalidChoiceException(String message) {
		this.message=message;
	}
	
	public String getMessage() {
		return message;
	}


}

