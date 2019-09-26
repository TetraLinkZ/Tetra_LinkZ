package com.tetralinkz.tetralinkz.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Message {
	@JsonProperty("content")
	private String content;
	private String username;
	private String avatar;


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
	
	@JsonProperty("avatar")
	public String getAvatar() {
		return avatar;
	}
	
	@JsonProperty("avatar")
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	
	@JsonProperty("message")
	public String messageOut() {
		return this.getUsername() + " : " + this.getContent();
	}
	
	@JsonProperty("avatarSrc")
	public String avatarSrc() {
		return "<div class=\"userAvatar\" style=\"width:150px; height:150px; background-image:url("+this.getAvatar()+")\"></div>";
	}
	

}
