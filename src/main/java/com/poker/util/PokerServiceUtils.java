package com.poker.api.util;

import java.util.ArrayList;
import com.poker.Card;
import com.poker.Player;
import com.poker.Hand;
import com.poker.Game;
import com.poker.Deck;
import java.util.Random;
import java.util.List;

/**
 * 
 *Utils Class for PokerService
 *Has Methods which can be reused
 */

public class PokerServiceUtils {
   
	private Random randomNumberGenerator;
    private PokerServiceImpl pokerServiceImpl;
	
	public PokerServiceUtils() {}

    /**
     * Shuffle elements in a List. This modifies the given list.
     *
     * @param cardList  The List to shuffle. This object wil be modified
     */
    public List<Card> shuffle(List<Card> cardList) {
    	this.randomNumberGenerator = new Random();

        // start at back and work forward
        for (int i = cardList.size() - 1; i > 1; i--) {
        	
            // generate a random index from current to the front
            int randomIndex = randomNumberGenerator.nextInt(i + 1);
            // swap current element with the element at random index.
            Card temp = cardList.get(i);
            cardList.set(i, cardList.get(randomIndex));
            cardList.set(randomIndex, temp);
        }
    }
    /**
     * Add one deck to gameDeck
     * @param game
     */
    public Game addDeck(Game game) {
    	//Constructor will initialize with deck with 52 crds
    	Deck deck = new Deck();
    	// add new deck to the existing gamedeck
    	game.getGameDeck().addAll(deck.getCards());
    	return game;
    }
    
    /**
     * Deals n number of cards from Game Deck.
     * Call shuffle() prior to Deal
     */
    public List<Card> dealCards(Game game, int numb){
    	List<Card> cards = new ArrayList<Card>(numb);
    	for(int i=0 ;i< numb;i++) {
    		//Game deck has atleast 1 card left then deal 
    		if(game.getGameDeck().size > 0) {
		    	cards.add(game.getGameDeck().get(0));
		    	game.getGameDeck().remove(0);
	    	}else {
	    		throw new Exception("Game deck is empty");
	    	}
    	}
    	pokerServiceImpl.saveGame(game);
    	return cards;
     }
    
    
    /**
     * set FaceValueforPlayer
     */
    public void setFaceValueforPlayer(Game game){
		Set<Player> players = game.getPlayers();
		for(Player p: players) {
			List<Card> cardList = p.getPlayerHand().getHand();
			int value=0;
			for(Card card : cardList) {
				value++ = card.getValue();
			}
			p.setFaceValue(value);
		}
		pokerServiceImpl.saveGame(game);
    }
    /**
     * sort List based on card.suit and then on card.value
     */
    public List<Card> sort(List<Card> cardList){
    	//perform sort if min 2 values in list
    	if(cardList.size > 2) {
	    	Collections.sort(cardList, new Comparator<Card>() {
	            @Override
	            public int compare(Card c1, Card c2) {
	            	int value1 = c1.suit.compareTo(c2.suit);
	                if (value1 == 0) {
	                		return c1.value.compareTo(c2.value);
	                }
	                else {
	                        return value1;
	                    }
	                return value1;
	    	}
	      });
    	}
    	return cardList;
    }

}