package com.tetralinkz.tetralinkz.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Message {
	@JsonProperty("content")
	private String content;
	private String username;

	public Message() {}
	

	@JsonProperty("content")
	public String getContent() {
		return content;
	}

	@JsonProperty("content")
	public void setContent(String content) {
		this.content = content;
	}
	
	@JsonProperty("username")
	public String getUsername() {
		return username;
	}

	@JsonProperty("username")
	public void setUsername(String username) {
		this.username = username;
	}
	
	@JsonProperty("message")
	public String messageOut() {
		return this.getUsername() + " : " + this.getContent();
	}
	

}
