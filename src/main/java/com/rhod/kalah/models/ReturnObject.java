package com.rhod.kalah.models;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * Return object storing the http code and the responseBody
 * @author Rhodri
 *
 * @param <T>
 */
@JsonPropertyOrder({ "HTTP code", "Response Body" })
public class ReturnObject<T> {
	
	private Integer httpCode;
	private T responseBody;

	
	public ReturnObject() {
	}
	
	public ReturnObject(final T responseBody, final HttpStatus httpCode) {
		this.responseBody = responseBody;
		this.httpCode = httpCode.value();
	}

	/**
	 * @return the responseBody
	 */
	@JsonProperty("Response Body")
	public T getResponseBody() {
		return responseBody;
	}

	/**
	 * @param responseBody the responseBody to set
	 */
	public void setResponseBody(T responseBody) {
		this.responseBody = responseBody;
	}

	/**
	 * @return the httpCode
	 */
	@JsonProperty("HTTP code")
	public Integer getHTTPCode() {
		return httpCode;
	}

	/**
	 * @param httpCode the httpCode to set
	 */
	public void setHTTPCode(Integer httpCode) {
		this.httpCode = httpCode;
	}

}
