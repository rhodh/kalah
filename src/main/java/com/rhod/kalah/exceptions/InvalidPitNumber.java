package com.rhod.kalah.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Allow the controller to return a 404 if we've been sent a invalid pitNO
 * 
 * @author Rhodri
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidPitNumber extends Exception {

	public InvalidPitNumber(String string) {
		super(string);
	}

	private static final long serialVersionUID = 1L;

}
