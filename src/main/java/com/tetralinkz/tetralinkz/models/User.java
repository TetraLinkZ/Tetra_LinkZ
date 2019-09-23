package com.tetralinkz.tetralinkz.models;

import com.tetralinkz.tetralinkz.models.Token;
import com.tetralinkz.tetralinkz.models.Avatar;
import com.tetralinkz.tetralinkz.models.MatchHistory;
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
@Size(min = 2, max = 20)
private String name;
@Column(unique = true)
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
	
@Column(updatable = false)
private Date createdAt;
private Date updatedAt;

@ManyToMany(fetch = FetchType.LAZY)
@JoinTable(name = "users_tokens",
joinColumns = @JoinColumn(name = "user_id"),
inverseJoinColumns = @JoinColumn(name = "token_id")
)
private List<Token> tokens;
@ManyToMany(fetch = FetchType.LAZY)
@JoinTable(name = "users_avatars",
joinColumns = @JoinColumn(name = "user_id"),
inverseJoinColumns = @JoinColumn(name = "avatar_id")
)
private List<Avatar> avatars;

@ManyToMany(fetch = FetchType.LAZY)
@JoinTable(
		name = "friends",
		joinColumns = @JoinColumn(name = "user_id"),
		inverseJoinColumns = @JoinColumn(name = "friend_id")
		)
private List<User> friends;
@ManyToMany(fetch = FetchType.LAZY)
@JoinTable(
		name = "friends",
		joinColumns = @JoinColumn(name = "friend_id"),
		inverseJoinColumns = @JoinColumn(name = "user_id")
		)
private List<User> userFriends;
public User() {

}


public String getName() {
	return name;
}


public void setName(String name) {
	this.name = name;
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


@PrePersist
protected void onCreate(){
    this.createdAt = new Date();
}
@PreUpdate
protected void onUpdate(){
    this.updatedAt = new Date();
}
}
