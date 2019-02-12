package com.rhod.kalah.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rhod.kalah.exceptions.GameAlreadyExists;
import com.rhod.kalah.exceptions.GameNotFound;
import com.rhod.kalah.exceptions.UpdateVersionOutOfDate;
import com.rhod.kalah.models.GameInstance;

@RestController
public class RepositoryController {
	
	@Autowired
	private InMemoryRepository repository;

	@RequestMapping(value = "create/{url}", method = RequestMethod.POST)
	public GameInstance create(@PathVariable("url") String url) throws GameAlreadyExists  {
		final int newID = repository.getNextId();
		GameInstance instance = new GameInstance(newID, url);
		repository.add(instance);
		return instance;
	}
	
	@RequestMapping(value = "get/{gameID}" , method = RequestMethod.GET)
	public GameInstance get(@PathVariable("gameID") int gameID) throws GameNotFound {
		return repository.get(gameID);
	}
	
	@RequestMapping(value = "update", method = RequestMethod.PUT)
	public Boolean update(@RequestBody GameInstance game) throws UpdateVersionOutOfDate, GameNotFound{
		repository.update(game);
		return true;
	}
}
