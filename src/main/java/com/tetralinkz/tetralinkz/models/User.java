package com.tetralinkz.tetralinkz.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

@Entity
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Size(min = 2, max = 20)
	private String name;
	@Column(unique = true)
	@Size(min=3, max=50)
	private String email;
	@Size(min = 8)
	private String password;
	@Transient
	private String passwordConfirm;
	private Integer rankedPoints = 0;
	private Integer credits = 0;
	private Integer gamesPlayed = 0;
	private Integer gamesWon = 0;
	private Integer boxesBought = 0;
	private Boolean admin = false;
	private Integer friendCode;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "avatar_id")
	private Avatar avatar;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "token_id")
	private Token token;
	@Column(updatable = false)
	private Date createdAt;
	private Date updatedAt;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "users_tokens", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "token_id"))
	private List<Token> tokens;
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "users_avatars", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "avatar_id"))
	private List<Avatar> avatars;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "friends", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "friend_id"))
	private List<User> friends;
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "friends", joinColumns = @JoinColumn(name = "friend_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
	private List<User> userFriends;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="match_id")
	private Match currentMatch;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "private_message", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "friend_id"))
	private List<PrivateMessage> messages;
	
	public Match getCurrentMatch() {
		return currentMatch;
	}


	public void setCurrentMatch(Match currentMatch) {
		this.currentMatch = currentMatch;
	}


	public User() {

	}


	public List<PrivateMessage> getMessages() {
		return messages;
	}


	public void setMessages(List<PrivateMessage> messages) {
		this.messages = messages;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getPasswordConfirm() {
		return passwordConfirm;
	}


	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}


	public Integer getRankedPoints() {
		return rankedPoints;
	}


	public void setRankedPoints(Integer rankedPoints) {
		this.rankedPoints = rankedPoints;
	}


	public Integer getCredits() {
		return credits;
	}


	public void setCredits(Integer credits) {
		this.credits = credits;
	}


	public Integer getGamesPlayed() {
		return gamesPlayed;
	}


	public void setGamesPlayed(Integer gamesPlayed) {
		this.gamesPlayed = gamesPlayed;
	}


	public Integer getGamesWon() {
		return gamesWon;
	}


	public void setGamesWon(Integer gamesWon) {
		this.gamesWon = gamesWon;
	}


	public Integer getBoxesBought() {
		return boxesBought;
	}


	public void setBoxesBought(Integer boxesBought) {
		this.boxesBought = boxesBought;
	}


	public Boolean getAdmin() {
		return admin;
	}


	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}


	public Date getCreatedAt() {
		return createdAt;
	}


	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}


	public Date getUpdatedAt() {
		return updatedAt;
	}


	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}


	public List<Token> getTokens() {
		return tokens;
	}


	public void setTokens(List<Token> tokens) {
		this.tokens = tokens;
	}


	public List<Avatar> getAvatars() {
		return avatars;
	}


	public void setAvatars(List<Avatar> avatars) {
		this.avatars = avatars;
	}


	public List<User> getFriends() {
		return friends;
	}


	public void setFriends(List<User> friends) {
		this.friends = friends;
	}


	public List<User> getUserFriends() {
		return userFriends;
	}


	public void setUserFriends(List<User> userFriends) {
		this.userFriends = userFriends;
	}


	@PrePersist
	protected void onCreate() {
		this.createdAt = new Date();
	}

	@PreUpdate
	protected void onUpdate() {
		this.updatedAt = new Date();
	}


	public Avatar getAvatar() {
		return avatar;
	}


	public void setAvatar(Avatar avatar) {
		this.avatar = avatar;
	}


	public Token getToken() {
		return token;
	}


	public void setToken(Token token) {
		this.token = token;
	}


	public Integer getFriendCode() {
		return friendCode;
	}


	public void setFriendCode(Integer friendCode) {
		this.friendCode = friendCode;
	}	
}
