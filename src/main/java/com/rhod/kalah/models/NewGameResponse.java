package com.rhod.kalah.models;

/**
 * Filter the GameInstance and report back to the
 * user the new game response.
 * @author Rhodri
 *
 */
public class NewGameResponse {
	
	private int id;
	private String uri;

	public NewGameResponse(GameInstance instance) {
		this.id = instance.getId();
		this.uri = instance.getUrl();
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
	public String getUri() {
		return uri;
	}

	/**
	 * @param uri the uri to set
	 */
	public void setUri(String uri) {
		this.uri = uri;
	}
}
