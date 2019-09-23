package com.tetralinkz.tetralinkz.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Min;

import com.zhen.javaexam.models.User;

@Entity
@Table(name = "match_histories")
public class MatchHistory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Min(0)
	private Integer user_one_wins;
	@Min(0)
	private Integer user_two_wins;
	@Min(0)
	private Integer total_games;
	@Column(updatable=false)
    private Date createdAt;
    private Date updatedAt;
    @ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;
    @ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "opponent_id")
	private User opponent;
    
    public MatchHistory() {}
    
	@PrePersist
	public void onCreate() {
		this.createdAt = new Date();
	}
	
	@PreUpdate	
	public void onUpdate() {
		this.updatedAt = new Date();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getUser_one_wins() {
		return user_one_wins;
	}

	public void setUser_one_wins(Integer user_one_wins) {
		this.user_one_wins = user_one_wins;
	}

	public Integer getUser_two_wins() {
		return user_two_wins;
	}

	public void setUser_two_wins(Integer user_two_wins) {
		this.user_two_wins = user_two_wins;
	}

	public Integer getTotal_games() {
		return total_games;
	}

	public void setTotal_games(Integer total_games) {
		this.total_games = total_games;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public User getOpponent() {
		return opponent;
	}

	public void setOpponent(User opponent) {
		this.opponent = opponent;
	}
	
	
}
