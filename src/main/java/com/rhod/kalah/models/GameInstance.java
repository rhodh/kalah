package com.rhod.kalah.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class GameInstance {
	private int id;
	private String url;
	private BoardModel boardModel;
	
	@JsonInclude(Include.NON_NULL)
	private Integer version;
	
	/**
	 * Create an initial game instance 
	 * @param id the id of the game
	 * @param url the url of the game
	 */
	public GameInstance(int id, String url) {
		this.id = id;
		this.url = buildURL(id, url);
		version = 0;
		boardModel = BoardModel.initalBoard();
	}
	
	public GameInstance() {
	}

	private String buildURL(int id, String url) {
		String ret = url;
		
		if(!url.endsWith("/")) {
			ret += "/";
		}
		
		return ret + Integer.toString(id);
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
	public BoardModel getModel() {
		return boardModel;
	}

	/**
	 * @param model the model to set
	 */
	public void setModel(BoardModel model) {
		this.boardModel = model;
	}

	/**
	 * @return the version
	 */
	public Integer getVersion() {
		return version;
	}
	
	/**
	 * @return updates the version
	 */
	public void incrementVersion() {
		version++;
	}

	/**
	 * @param version the version to set
	 */
	public void setVersion(Integer version) {
		this.version = version;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "GameInstance [id=" + id + ", url=" + url + ", status=" + boardModel.toString() + ", version=" + version + "]";
	}


}
