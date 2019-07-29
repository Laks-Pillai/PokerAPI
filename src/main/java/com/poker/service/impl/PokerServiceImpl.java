package com.poker.api.service.impl;

import java.util.List;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.poker.api.util.PokerServiceUtils;
import com.poker.dao.GameDao;
import com.poker.dao.PlayerDao;
import com.poker.dao.HandDao;
import com.poker.api.service.PokerService;

/**
 * Implemetation class for PokerService
 * @author 
 *
 */
public class PokerServiceImpl implements PokerService{

	@Autowired
	private GameDao gameDao;
	@Autowired
	private PlayerDao playerDao;
	@Autowired
	private HandDao handDao;
	@Autowired
	private PokerServiceUtils pokerServiceUtils;
	

	/**
	 * Game by Id
	 */ 
	public Game getGameById(Long id) {
		Game game = gameDao.findById(id);
			for(Player p : game.getPlayers()){
				p.getId();
			}
		return game;
	}
	
	/**
	 * Player by Id
	 */
	public Player getPlayerById(Long playerId){
		return playerDao.findById(playerId);
	}
	/**
	 * Save Game
	 */
	public Game saveGame(Long id){
		Game game = getGameById(id);
		return gameDao.save(game);
	}
	/**
	 * Save Player
	 */
	public Player savePlayer(Player player){
		return playerDao.save(player);
	}
	
	/**
	 * Start a new Game
	 */
	public Game startGame(Long id){
		Game game = getGameById(id);
		if(game.getPlayers().size() < 2){
			throw new Exception("Not Enough Players");
		}
		if(game.getPlayers().size() > 10){
			throw new Exception("Too Many Players");
		}
		if(game.isStarted()){
			throw new Exception("Game already started");
		}
		
		//Set started flag
		game.setStarted(true);
		
		//Assign random position to players.  Save the player.
		List<Player> players = new ArrayList<Player>();
		players.addAll(game.getPlayers());
		Collections.shuffle(players);
		for(int i = 0; i < players.size(); i++){
			Player p = players.get(i);
			p.setGamePosition(i+1);
			savePlayer(p);
		}
		//Save and return the updated game
		return gameDao.save(game);
	}
	
	/**
	 * method to add player to game. If player id is empty new player will be added
	 */
	public viod addPlayer(Long gameId,Long playerId){
		if(null != Long.valueOf(gameId)) { 
			Player player ;
			Game game = getGameById(gameId);
			if(Long.valueOf(playerId) == null ) {
				player = new Player();
				player = savePlayer(player);
			}else {
				player = getPlayerById(playerId);
			}
			if(game.isStarted()){
				throw new Exception("Game in progress, no new players");
			}
			if(game.getPlayers().size() >= 10){
				throw new Exception("Cannot have more than 10 players in one game");
			}
			game.getPlayers().add(player);
			game = gameDao.save(game);
		}else {
			throw new Exception("Game id is null");
		}
	}
	
	/**
	 * Creates a new Game with one deck and 1 player
	 */
	public GameDTO createGame() {
		Game game = new Game();
		Hand hand = new Hand();
		Deck deck = new Deck();
		List<Player> players = new ArrayList<Player>();
		//initialize hand with a single deck
		hand.setHand(deck.getCards());
		//added one player to the Game
		players.add(new Player());
		GameDTO gameDTO = new GameDTO();
		try {
			playerDao.save(players);
			handDao.save(hand);
			gameDTO.setId(game.getId);
			gameDTO.setPlayers(players);
			gameDTO.setGameDeck(hand);
			game.setPlayers(players);
			game.setGameDeck(hand);
			gameDao.save(game);
		} catch (Exception e) {
			throw new Exception("Error occured while saving Game details");
		}
		return gameDTO;
	}
	/**
	 * Method to delete Game
	 */
	public void deleteGame(Long id) {
		try {
			if(null != Long.valueOf(id)) { 
				Game game = gameDao.findById(id);
				return gameDao.remove(game);
			}else {
				throw new Exception("Game id is null");
			}
		} catch (Exception e) {
			throw new Exception("Error occured while deleteGame");
		}
	}
	/**
	 * Shuffle game Deck
	 */
	public void shuffleShoe(Long id) {
		try {
			if(null != Long.valueOf(id)) { 
				Game game = gameDao.findById(id);
				List<Card> cardList = pokerServiceUtils.shuffle(game.getGameDeck().getCards());
				game.setGameDeck(cardList);
				gameDao.save(game);
			}else {
				throw new Exception("Game id is null");
			}
		}catch (Exception e) {
			throw new Exception("Error occured shuffleShoe()");
		}
	}
	/**
	 * add Deck
	 */
	public void addDeck(Long id) {
		try {
			if(null != Long.valueOf(id)) { 
				Game game = gameDao.findById(id);
				game = pokerServiceUtils.addDeck(game);
				gameDao.save(game);
			}else {
				throw new Exception("Game id is null");
			}
		}catch (Exception e) {
			throw new Exception("Error occured while adding Deck");
		}
	}
	/**
	 * method for dealing a single card for the player
	 * first calls shuffle() then calls deal card.
	 */
	public void dealCard(Long gameId,Long playerId) {
		try {
			if(null != Long.valueOf(gameId) && null != Long.valueOf(playerId)) { 
				Game game = getGameById(gameId);
				Player player = getPlayerById(playerId);
				
				//Shuffle the game deck
				List<Card> cardList = pokerServiceUtils.shuffle(game.getGameDeck().getCards());
				game.setGameDeck(cardList);
				
				//deal 1 card - add that card to Player hand.
				List<Card> cardList = pokerServiceUtils.dealCards(game,1);
				player.getPlayerHand().addAll(cardList);
				
				savePlayer(playerId);
				saveGame(gameId);
			}else {
				throw new Exception("Game id or Player id is null");
			}
		}catch (Exception e) {
			throw new Exception("Error occured on dealCard()");
		}
	}
	/**
	 * Sort Player list on face value
	 */
	public GameDTO getSortedPlayerList(Long gameId) {
		
			Game game = getGameById(gameId);
			GameDTO gameDTO= new GameDTO();
			try {
				pokerServiceUtils.setFaceValueforPlayer(game);
				
				//Sort Players with face value			
				Collections.sort(players, new Comparator<Player>() {
                @Override
                public int compare(Player p1, Player p2) {
                   return new p1.getFaceValue().compareTo(p2.getFaceValue());
                }
            });
			//decenting order
			 Collections.reverse(players); 
			 game.setPlayers(players);
			 saveGame(gameId);
			 gameDTO.setId(game.getId);
			 gameDTO.setPlayers(players);
			 gameDTO.setId(game.getId);
		}catch (Exception e) {
			throw new Exception("Error occured getSortedPlayerList()");
		}
		return gameDTO;
	}
	/**
	 * returns player hand for the particuler game and player
	 */
	public PlayerDTO getPlayerHand(Long gameId,Long playerId) {
		
		Game game = getGameById(gameId);
		PlayerDTO dto = new PlayerDTO();
		Player pl;
		try {
			Set<Player> players = game.getPlayers();
			for(Player p: players) {
				if(p.getId() == playerId) {
					pl = p;
					break;
				}
			}
			dto.setId(pl.getId);
			dto.setPlayerHand(pl.getPlayerHand());
		
		}catch (Exception e) {
			throw new Exception("Error occured getSortedPlayerList()");
		}
		return dto;
	}
	/**
	 * getUndealtGameDeck
	 */
	public GameDTO getUndealtGameDeck(Long gameId) {
		if(null != Long.valueOf(id)) { 
			Game game = getGameById(gameId);
			GameDTO gameDTO= new GameDTO();
			List<Card> cardList = game.getGameDeck().getCards();
			Map<String, int> undealtCardCountMap = new HashMap<String, int>();
			int count1=0;
			int count2=0;
			int count3=0;
			int count4=0;
			for(Card card : cardList) {
				if(card.getSuit()==0) {
					count1++;
				}
				if(card.getSuit()==1) {
					count2++;
				}
				if(card.getSuit()==2) {
					count3++;
				}
				if(card.getSuit()==3) {
					count4++;
				}
			}
			undealtCardCountMap.put("SPADES",count1);
			undealtCardCountMap.put("HEARTS",count2);
			undealtCardCountMap.put("DIAMONDS",count3);
			undealtCardCountMap.put("SPADES",count4);
			gameDTO.setGameDeck(game.getHand());
			gameDTO.setPlayers(players);
			gameDTO.setId(game.getId);
			gameDTO.setUndealtCardCountMap(undealtCardCountMap);
		}else {
			throw new Exception("Game id is null");
		}
		return gameDTO;
	}
	
	/**
	 * get double sorted card list on suit and then on value
	 */
	public GameDTO sortGameDeck(Long gameId) {
		//null check for game id
		if(null != Long.valueOf(id)) { 
			Game game = getGameById(gameId);
			GameDTO gameDTO= new GameDTO();
			List<Card> cardList = game.getGameDeck().getCards();
			if(cardList.size > 2) {
				try {
					cardList= pokerServiceUtils.sort(cardList);
					Hand hand = new Hand(cardList);
					game.setGameDeck(hand);
					saveGame(gameId);
					gameDTO.setGameDeck(game.getHand());
					gameDTO.setPlayers(players);
					gameDTO.setId(game.getId);
				}catch (Exception e) {
					throw new Exception("Error occured getSortedPlayerList()");
				}
			}else {
				throw new Exception("Game deck should have min 2 values to sort");
			}
		}else {
			throw new Exception("Game id is null");
		}
		return gameDTO;	
	}
	public PokerServiceUtils getPokerServiceUtils() {
		return pokerServiceUtils;
	}

	public void setPokerServiceUtils(PokerServiceUtils pokerServiceUtils) {
		this.pokerServiceUtils = pokerServiceUtils;
	}
}
