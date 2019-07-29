package com.poker.api.controller.game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.poker.api.dto.GameDTO;
import com.poker.service.PokerService;
/**
 * Rest Controller for Poker Game API
 * @author 
 *
 */
@RestController
@RequestMapping("game")
public class GameController {
	
	//Instance for PokerService
	@Autowired
	private PokerService pokerService;
	
	/**
	 * A status checker method to see if the webservice up and running. 
	 *url - game
	 * @return
	 */
	@GetMapping("/")
    public ResponseEntity<String> welcome() {
	   return new ResponseEntity<String>("Running webservice!!", HttpStatus.OK);
    } 

	/**
	 * Method to create a new Game. 
	 * @param gameDTO
	 * @return
	 */
	@RequestMapping(method=RequestMethod.POST, consumes="application/json" , path="/createGame")
	public ResponseEntity<GameDTO> createGame() {
		GameDTO dto;
		try {
			dto = pokerService.createGame();
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}		
		return ResponseEntity.status(HttpStatus.OK).body(dto);
	}
	/**
	 * Method to delete a Game. 
	 * @param id
	 * @return
	 */
	@RequestMapping(method=RequestMethod.GET, consumes="application/json", path="/deleteGame")
	public ResponseEntity<String> deleteGame(@RequestParam("id") Long id) {
		try {
			pokerService.deleteGame(id);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}	
		
		return ResponseEntity.status(HttpStatus.OK).body("success");
		
	}
	/**
	 * Method to shuffle game deck. 
	 * @param id
	 * @return
	 */
	@RequestMapping(method=RequestMethod.POST, consumes="application/json" , path="/shuffleShoe")
    public Response shuffle(@RequestParam("gameId") Long id) {
            try { 
            	pokerService.shuffleShoe(id);
            } catch (Exception e) {
    			throw new Exception(e.getMessage());
    		}	
            return ResponseEntity.status(HttpStatus.OK).body("success");
    }
	
	/**
	 * Method to add a player to game 
	 * @param id
	 * @return
	 */
	@RequestMapping(method=RequestMethod.POST, consumes="application/json" , path="/addPlayer")
	public ResponseEntity<String> addPlayer(@RequestParam("gameId") Long id) {
		try {
			pokerService.addPlayer(id);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}		
		return ResponseEntity.status(HttpStatus.OK).body("success");
	}
	
	/**
	 * Method to delete a player from game 
	 * @param gameId
	 * @param playerId
	 * @return
	 */
	@RequestMapping(method=RequestMethod.POST, consumes="application/json" , path="/deletePlayer")
	public ResponseEntity<String> deletePlayer(@RequestParam("gameId") Long gameId,@RequestParam("playerId") Long playerId) {
		try {
			pokerService.deletePlayer(gameId,playerId);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}		
		return ResponseEntity.status(HttpStatus.OK).body("success");
	}
	
	/**
	 * Method to add a deck to game 
	 * @param gameId
	 * @return
	 */
	@RequestMapping(method=RequestMethod.POST, consumes="application/json" , path="/addDeck")
	public ResponseEntity<String> addDeck(@RequestParam("gameId") Long gameId) {
		try {
			pokerService.addDeck(gameId);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}		
		return ResponseEntity.status(HttpStatus.OK).body("success");
	}
	/**
	 * Method to deal card to player 
	 * @param gameId
	 * @param playerId
	 * @return
	 */
	@RequestMapping(method=RequestMethod.POST, consumes="application/json" , path="/dealCard")
	public ResponseEntity<String> dealCard(@RequestParam("gameId") Long gameId,@RequestParam("playerId") Long playerId) {
		try {
			pokerService.dealCard(gameId);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}		
		return ResponseEntity.status(HttpStatus.OK).body("success");
	}
	/**
	 * Method to deal card to player 
	 * @param gameId
	 * @param playerId
	 * @return
	 */
	@RequestMapping(method=RequestMethod.POST, consumes="application/json" , path="/dealCard")
	public ResponseEntity<String> dealCard(@RequestParam("gameId") Long gameId,@RequestParam("playerId") Long playerId) {
		try {
			pokerService.dealCard(gameId);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}		
		return ResponseEntity.status(HttpStatus.OK).body("success");
	}
	/**
	 * Method to sort Players List on the total face value of the cards in Player hand
	 * @param gameId
	 * @return
	 */
	@RequestMapping(method=RequestMethod.POST, consumes="application/json" , path="/playerList")
	public ResponseEntity<GameDTO> getPlayerList(@RequestParam("gameId") Long gameId) {
		GameDTO dto;
		try {
			dto=pokerService.getSortedPlayerList(gameId);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}		
		return ResponseEntity.status(HttpStatus.OK).body(dto);
	}
	/**
	 * Method to get Player's hand
	 * @param gameId
	 * @param playerId
	 * @return
	 */
	@RequestMapping(method=RequestMethod.POST, consumes="application/json" , path="/playerHand")
	public ResponseEntity<PlayerDTO> getPlayerHand(@RequestParam("gameId") Long gameId,@RequestParam("playerId") Long playerId) {
		PlayerDTO dto;
		try {
			dto=pokerService.getPlayerHand(gameId,playerId);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}		
		return ResponseEntity.status(HttpStatus.OK).body(dto);
	}
	/**
	 * Method to get count of how many cards per suit are left undealt in the game deck
	 * @param gameId
	 * @return
	 */
	@RequestMapping(method=RequestMethod.POST, consumes="application/json" , path="/getGameDeckSuit")
	public ResponseEntity<GameDTO> getGameDeckSuitCount(@RequestParam("gameId") Long gameId) {
		GameDTO dto;
		try {
			dto=pokerService.getUndealtGameDeck(gameId);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}		
		return ResponseEntity.status(HttpStatus.OK).body(dto);
	}
	/**
	 * Method to sort  GameDeck on Card's Suit and then on Value
	 * @param gameId
	 * @return
	 */
	@RequestMapping(method=RequestMethod.POST, consumes="application/json" , path="/getSortedGameDeck")
	public ResponseEntity<GameDTO> getSortedGameDeck(@RequestParam("gameId") Long gameId) {
		GameDTO dto;
		try {
			dto=pokerService.sortGameDeck(gameId);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}		
		return ResponseEntity.status(HttpStatus.OK).body(dto);
	}

	public PokerService getPokerService() {
		return pokerService;
	}

	public void setPokerService(PokerService pokerService) {
		this.pokerService = pokerService;
	}
}
