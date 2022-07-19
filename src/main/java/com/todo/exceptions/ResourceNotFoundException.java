package com.todo.exceptions;

import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.ResponseStatus;

//@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
      private static final long serialVersionUID = -8006818509264757117L;

	public ResourceNotFoundException(String message) {
		// TODO Auto-generated constructor stub
    	  super(message);
	}
}
