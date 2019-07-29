package com.poker.api.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Hand {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
		
	@ElementCollection(targetClass=Card.class)
	private List<Card> hand;
	
	/**
     * Add a card to the hand.  It is added at the end of the current hand.
     * @param c the non-null card to be added.
     * @throws NullPointerException if the parameter c is null.
     */
    public void addCard(Card c) {
        if (c == null)
            throw new NullPointerException("Can't add a null card to a hand.");
        hand.add(c);
    }

    /**
     * Returns the number of cards in the hand.
     */
    public int getCardCount() {
        return hand.size();
    }
    
    public List<Card> getHand(){
    	return this.hand;
    }
    
    public setHand(List<Card> hand){
    	this.hand = hand;
    }

}
