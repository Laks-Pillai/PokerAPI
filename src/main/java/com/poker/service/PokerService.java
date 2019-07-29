package com.poker.service;

import org.springframework.stereotype.Service;

import com.poker.api.dto.GameDTO;
import com.poker.api.dto.PlayerDTO;

@Service
public interface PokerService {
	public GameDTO createGame() ;
	public void deleteGame(Long id);
	public void shuffleShoe(Long id);
	public void addPlayer(Long id);
	public void deletePlayer(Long gameId,Long playerId);
	public void addDeck(Long gameId);
	public void dealCard(Long gameId,Long playerId);
	public GameDTO getSortedPlayerList(Long gameId);
	public PlayerDTO getPlayerHand(Long gameId,Long playerId);
	public GameDTO getUndealtGameDeck(Long gameId);
	public GameDTO sortGameDeck(Long gameId);
}
