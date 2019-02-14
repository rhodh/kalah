package com.rhod.kalah.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *  Allow the controller to return a 400 if we've been sent an invalid pitNo.
 * 
 * @author Rhodri
 */
@ResponseStatus(code=HttpStatus.BAD_REQUEST, reason="Invalid pit number given")
public class InvalidPitNumber extends RuntimeException  {

	public InvalidPitNumber(String string) {
		super(string);
	}

	private static final long serialVersionUID = 1L;

}
