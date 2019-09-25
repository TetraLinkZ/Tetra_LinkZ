package com.tetralinkz.tetralinkz.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

@Entity
@Table(name="matches")
public class Match {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(updatable = false)
	private Date createdAt;
	private Date updatedAt;
	
	private String board;
	
	private User winner;
	
	// map to one user - player1
	@OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="player_one")
    private User playerOne;
	
	// map to another user - player2
	@OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="player_two")
    private User playerTwo;
	
	// CONSTRUCTOR
	
	public Match() {
		this.board = "0000000000000000000000000000000000000000000000000";
	}
	
	// GETTERS AND SETTERS
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBoard() {
		return board;
	}

	public void setBoard(String board) {
		this.board = board;
	}

	public User getWinner() {
		return winner;
	}

	public void setWinner(User winner) {
		this.winner = winner;
	}

	public User getPlayerOne() {
		return playerOne;
	}

	public void setPlayerOne(User playerOne) {
		this.playerOne = playerOne;
	}

	public User getPlayerTwo() {
		return playerTwo;
	}

	public void setPlayerTwo(User playerTwo) {
		this.playerTwo = playerTwo;
	}
    
	// // runs the method right before the object is created
    @PrePersist
    protected void onCreate(){
        this.createdAt = new Date();
    }
    // // runs a method when the object is modified
    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = new Date();
    }

	
	
}
