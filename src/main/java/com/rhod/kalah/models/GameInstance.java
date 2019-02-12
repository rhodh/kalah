package com.rhod.kalah.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class GameInstance {
	private int id;
	private String url;
	private BoardModel status;
	
	@JsonInclude(Include.NON_NULL)
	private Integer version;
	
	/**
	 * Create an initial game instance 
	 * @param id the id of the game
	 * @param url the url of the game
	 */
	public GameInstance(int id, String url) {
		this.id = id;
		this.url = url;
		version = 0;
		status = new BoardModel();
	}
	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the model
	 */
	public BoardModel getStatus() {
		return status;
	}

	/**
	 * @param model the model to set
	 */
	public void setStatus(BoardModel model) {
		this.status = model;
	}

	/**
	 * @return the version
	 */
	public Integer getVersion() {
		return version;
	}

	/**
	 * @param version the version to set
	 */
	public void setVersion(Integer version) {
		this.version = version;
	}

	/**
	 * Clean up any data we don't wish to return to the user
	 */
	public void prepareForUser() {
		this.version = null;
		this.status.prepareForUser();
	}

}
