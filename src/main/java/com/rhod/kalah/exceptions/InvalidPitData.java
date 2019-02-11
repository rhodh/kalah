package com.rhod.kalah.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Allow the controller to return a 404 if we've been sent a invalid pit data
 * 
 * @author Rhodri
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidPitData extends Exception {
	public InvalidPitData(final int size) {
		super("Pit data sent is invalid only have " + Integer.toString(size) + " pits");
	}

	private static final long serialVersionUID = 1L;

}
