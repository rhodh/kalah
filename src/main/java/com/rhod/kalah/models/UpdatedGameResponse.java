package com.rhod.kalah.models;

public class UpdatedGameResponse {

	private int id;
	private String url;
	private BoardModel status;

	public UpdatedGameResponse(GameInstance instance) {
		this.id = instance.getId();
		this.url = instance.getUrl();
		this.status = instance.getModel();
		this.status.prepareForUser();
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
	 * @return the uri
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param uri the uri to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the status
	 */
	public BoardModel getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(BoardModel status) {
		this.status = status;
	}
}
