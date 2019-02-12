package com.rhod.kalah.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Allow the controller to return a 404 if we can't find game
 * 
 * @author Rhodri
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class GameNotFound extends Exception {

	public GameNotFound(int gameID) {
		super("Game ID " + Integer.toString(gameID) + " not found in repository");
	}

	private static final long serialVersionUID = 1L;

}
