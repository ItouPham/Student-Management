package com.api.student_management.exception;

import org.springframework.security.authentication.BadCredentialsException;

public class InvalidUsernameOrPasswrodException extends BadCredentialsException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3589069165889584991L;

	public InvalidUsernameOrPasswrodException(String msg) {
		super(msg);
	}

}
