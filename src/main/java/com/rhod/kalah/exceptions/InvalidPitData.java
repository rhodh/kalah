package com.rhod.kalah.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Allow the controller to return a 400 if we've been sent invalid pit data
 * 
 * @author Rhodri
 */
@ResponseStatus(code=HttpStatus.BAD_REQUEST, reason="Invalid pit data")
public class InvalidPitData extends RuntimeException  {
	public InvalidPitData(final int size) {
		super("Pit data sent is invalid. Only have " + Integer.toString(size) + " pits");
	}

	private static final long serialVersionUID = 1L;

}
