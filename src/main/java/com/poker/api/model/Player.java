package com.poker.api.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Player {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(name="game_position")
	private int gamePosition;
	
	@ManyToOne
	@JoinColumn(name="hand_id")
    private Hand playerHand;
	
	private int faceValue;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public int getGamePosition() {
		return gamePosition;
	}
	public void setGamePosition(int gamePosition) {
		this.gamePosition = gamePosition;
	}

	public Hand getPlayerHand() {
		return playerHand;
	}
	public void setPlayerHand(Hand playerHand) {
		this.playerHand = playerHand;
	}
	
	public setFaceValue(int value) {
		this.faceValue=value;
	}
	
	public int getFaceValue() {
		returnn this.faceValue;
	}
}
