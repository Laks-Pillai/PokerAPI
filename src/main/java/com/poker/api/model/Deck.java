package com.poker.api.model;

import java.util.ArrayList;
import java.util.List;
import com.poker.Card;
/**
 *  An object of type Deck represents a deck of playing cards. 
 */
public class Deck {
	/**
     * Deck Size 
     */
	 public final static int SIZE = 52;   

    
    private List<Card> cards;

    /**
     * Constructs a poker deck of playing cards, The deck contains
     * the usual 52 cards and can optionally contain two Jokers
     * in addition, for a total of 54 cards.   Initially the cards
     * are in a sorted order.  The shuffle() method can be called to
     * randomize the order.
     * @param includeJokers if true, two Jokers are included in the deck; if false,
     * there are no Jokers in the deck.
     */
    public Deck() {
    	cards = new ArrayList<Card>(SIZE);
    	// Numb of cards created 
        int size = 0;
        for ( int suit = 0; suit <= 3; suit++ ) {
            for ( int value = 1; value <= 13; value++ ) {
            	cards.add(new Card(value,suit));
            	size++;
            }
        }
    }

    public List<Card> getCards() {
    	return this.cards;
    	}

}