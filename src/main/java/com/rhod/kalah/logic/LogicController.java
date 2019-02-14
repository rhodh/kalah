package com.rhod.kalah.logic;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rhod.kalah.exceptions.InvalidPitData;
import com.rhod.kalah.exceptions.InvalidPitNumber;
import com.rhod.kalah.models.BoardModel;

@RestController
public class LogicController {

	protected Logger logger = Logger.getLogger(LogicController.class.getName());
	
	@RequestMapping(value = "move/{pitID}", method = RequestMethod.POST, produces = "application/json")
	public BoardModel update(@PathVariable("pitID") int pitID, @RequestBody BoardModel board) throws InvalidPitNumber, InvalidPitData {
		logger.log(Level.INFO, "Updating GameInstance: " + board.toString() + " with move " + Integer.toString(pitID));
		BoardModel result = MoveCalculator.makeMove(board, pitID);
		logger.log(Level.INFO, "Move complete with result: " + result.toString());
		return result;
	}
}
