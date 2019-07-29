package com.poker.api.model;



/**
 * A typical playing card that is found in a 52 card deck.
 * The card has a suit, which can be spades, hearts, diamonds or clubs.
 * A spade, heart, diamond, or club has one of the 13 values: 
 * ace, 2, 3, 4, 5, 6, 7, 8, 9, 10, jack, queen, or king. 
 * Note that "ace" is considered to be the smallest value.  
 */
public class Card {
   
   public final static int SPADES = 0;   
   public final static int HEARTS = 1;
   public final static int DIAMONDS = 2;
   public final static int CLUBS = 3;
   
   public final static int ACE = 1;      
   public final static int JACK = 11;   
   public final static int QUEEN = 12;   
   public final static int KING = 13;
   
   /**
    * This card's suit, 
    */
   private final int suit; 
   
   /**
    * The card's value.  
    *
    */
   private final int value;
   

   /**
    * Creates a card with a specified suit and value.
    */
   public Card(int theValue, int theSuit) {
      if (theSuit != SPADES && theSuit != HEARTS && theSuit != DIAMONDS && 
            theSuit != CLUBS)
         throw new Exception("Illegal playing card suit");
      if (theValue < 1 || theValue > 13)
         throw new Exception("Illegal playing card value");
      value = theValue;
      suit = theSuit;
   }

   /**
    * Returns the suit of this card.
    * @returns the suit, which is one of the constants Card.SPADES, 
    * Card.HEARTS, Card.DIAMONDS, Card.CLUBS, or Card.JOKER
    */
   public int getSuit() {
      return suit;
   }
   
   /**
    * Returns the value of this card.
    *
    */
   public int getValue() {
      return value;
   }
   
   /**
    * Returns a String representation of the card's suit.
    * @return 
    */
   public String getSuitAsString() {
      switch ( suit ) {
      case SPADES:   return "Spades";
      case HEARTS:   return "Hearts";
      case DIAMONDS: return "Diamonds";
      default:       return "Clubs";
      }
   }
   
   /**
    * Returns a String representation of the card's value.
    * @return 
    */
   public String getValueAsString() {

         switch ( value ) {
         case 1:   return "Ace";
         case 2:   return "2";
         case 3:   return "3";
         case 4:   return "4";
         case 5:   return "5";
         case 6:   return "6";
         case 7:   return "7";
         case 8:   return "8";
         case 9:   return "9";
         case 10:  return "10";
         case 11:  return "Jack";
         case 12:  return "Queen";
         default:  return "King";
         
      }
   }
   
   /**
    * Returns a string representation of this card, including both
    * its suit and its value 
    */
   public String toString() {
         return getValueAsString() + " of " + getSuitAsString();
   }
   

} 