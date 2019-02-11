package com.rhod.kalah.logic;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rhod.kalah.exceptions.InvalidPitData;
import com.rhod.kalah.exceptions.InvalidPitNumber;
import com.rhod.kalah.models.BoardModel;

@RestController
public class BoardController {

	@RequestMapping(value = "move/{pitID}", method = RequestMethod.POST)
	public BoardModel update(@RequestBody BoardModel board, @PathVariable("pitID") int pitID) throws InvalidPitNumber, InvalidPitData {
		return MoveCalculator.makeMove(board, pitID);
	}
}
