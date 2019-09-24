package com.tetralinkz.tetralinkz.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Message {
	@JsonProperty("content")
	private String content;

	public Message() {}
	

	@JsonProperty("content")
	public String getContent() {
		return content;
	}

	@JsonProperty("content")
	public void setContent(String content) {
		this.content = content;
	}
	
	

}
