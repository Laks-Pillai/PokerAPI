package com.poker.api.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class Game {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private String name;
	
	private boolean isStarted;
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY,mappedBy="game")
	private Set<Player> players;
	
	@OneToOne
	private Hand gameDeck;
	
   	public String getId() {
   		return id;
   	}
   	public void setId(String id) {
   		this.id = id;
   	}

	public Set<Player> getPlayers() {
		return players;
	}
	public void setPlayers(Set<Player> players) {
		this.players = players;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public boolean isStarted() {
		return isStarted;
	}
	public void setStarted(boolean isStarted) {
		this.isStarted = isStarted;
	}

	public Hand getGameDeck() {
		return gameDeck;
	}
	public void setGameDeck(Hand gameDeck) {
		this.gameDeck = gameDeck;
	}

}
