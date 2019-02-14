package com.rhod.kalah.game;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.rhod.kalah.exceptions.GameAlreadyExists;
import com.rhod.kalah.models.BoardModel;
import com.rhod.kalah.models.GameInstance;

/**
 * Orchestrates all the microservices
 * @author Rhodri
 *
 */
@Service
public class GameOrchestrater {

	protected Logger logger = Logger.getLogger(GameOrchestrater.class.getName());

	@Autowired
	@LoadBalanced
	protected RestTemplate restTemplate;
	final private String repositoryURL;
	final private String logicURL;
	
	public GameOrchestrater(final String repositoryURL, final String logicURL) {
		this.repositoryURL = repositoryURL;
		this.logicURL = logicURL;
	}
	
	/**
	 * Create a game instance
	 * @param url the entry point of the game 
	 * @return new game instance
	 */
	public GameInstance createGame(String url) {
		final String createURL = this.repositoryURL + "create/";
		
		logger.log(Level.INFO, "Posting to URL: " + createURL + " with body: " + url);
		
		GameInstance response = restTemplate.postForObject(createURL, url, GameInstance.class);

		logger.log(Level.INFO, "Received game with ID: " 
					+ Integer.toString(response.getId()) + " from url: " + createURL);
		
		return response;
	}

	/**
	 * Update given game with given move.
	 * @param gameId the game we wish to update.
	 * @param pitId the move we wish to make.
	 * @return the updated game post move.
	 */
	public GameInstance updateGame(final Integer gameId, final Integer pitId) {
		final GameInstance gameInstance = getGame(gameId);
		
		final BoardModel movedModel = doMove(gameInstance.getModel(), pitId);
		
		gameInstance.setModel(movedModel);
		gameInstance.incrementVersion();
		
		saveGame(gameInstance);
		
		return gameInstance;
	}

	/**
	 * Save the updated game instance in the repository
	 * @param movedInstance the instance we wish to save
	 */
	private void saveGame(final GameInstance movedInstance) {
		final String doSaveURL = this.repositoryURL + "update/";
		
		logger.log(Level.INFO, "Saving GameInstance with URL: " + doSaveURL + " and body " + movedInstance.toString());
		
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
		headers.add("HeaderName", "value");
		headers.add("Content-Type", "application/json");

		HttpEntity<GameInstance> request = new HttpEntity<>(movedInstance, headers);
		restTemplate.postForObject(doSaveURL, request, boolean.class);
		
		logger.log(Level.INFO, "Successfully saved GameInstance " + movedInstance.toString());
	}

	/**
	 * Update the BoardModel with the desired move
	 * @param originalStatus the game we wish to update
	 * @param pitId the move we wish to make
	 * @return the updated board.
	 */
	private BoardModel doMove(final BoardModel originalStatus, final Integer pitId) {
		final String doMoveURL = this.logicURL + "move/" + pitId.toString();
		
		logger.log(Level.INFO, "Updating GameInstance with URL: " + doMoveURL + " and body " + originalStatus.toString());
		
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
		headers.add("HeaderName", "value");
		headers.add("Content-Type", "application/json");

		HttpEntity<BoardModel> request = new HttpEntity<>(originalStatus, headers);
		BoardModel updatedStatus = restTemplate.postForObject(doMoveURL, request, BoardModel.class);
		
		logger.log(Level.INFO, "Updating GameInstance with URL: " + doMoveURL + " and body " + updatedStatus.toString());
		
		return updatedStatus;
	}

	/**
	 * Get the game from the repository microservice
	 * @param gameId the game we wish to get
	 * @return the game instance
	 */
	private GameInstance getGame(final Integer gameId) {
		final String getURL = this.repositoryURL + "get/" + gameId.toString();
		
		logger.log(Level.INFO, "Getting GameInstance with to URL: " + getURL);
		
		GameInstance response = restTemplate.getForObject(getURL, GameInstance.class);
		
		logger.log(Level.INFO, "Successfully received GameInstance: " + gameId.toString());
		return response;
	}

}
