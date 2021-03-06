package com.rhod.kalah.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Allow the controller to return a 400 if we've asked to update with an old game instance
 * 
 * @author Rhodri
 */
@ResponseStatus(code=HttpStatus.BAD_REQUEST, reason="Update version out of date")
public class UpdateVersionOutOfDate extends RuntimeException {
	public UpdateVersionOutOfDate(final Integer gameId, final Integer requestVersion, final Integer actualVersion) {
		super("Been asked to update game " + gameId.toString() 
					+ " to version " + requestVersion.toString() 
					+ " when already at version " + actualVersion.toString());
	}

	private static final long serialVersionUID = 1L;

}
