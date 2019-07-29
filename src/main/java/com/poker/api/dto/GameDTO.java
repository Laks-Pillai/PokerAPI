package com.gpc.api.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
/**
 * DTO Object for Game
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GameDTO {

	private Set<Player> players;
	private Hand gameDeck;
	private Long id;
	private HashMap<String, int> undealtCardCountMap;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Set<Player> getPlayers() {
		return this.players;
	}
	public void setPlayers(Set<Player> players) {
		this.players = players;
	}
	public Hand getGameDeck() {
		return this.gameDeck;
	}
	public void setGameDeck(Hand gameDeck) {
		this.gameDeck = gameDeck;
	}
	public HashMap<String, int> getUndealtCardCountMap() {
		return this.undealtCardCountMap;
	}
	public void setUndealtCardCountMap(HashMap<String, int> undealtCardCountMap) {
		this.undealtCardCountMap = undealtCardCountMap;
	}
}
