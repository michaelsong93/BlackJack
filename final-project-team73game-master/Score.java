import java.util.ArrayList;

/**
 * 
 * @author Jeannie Chan, Michael Sangho Song, Matthew Spelman
 * @team team 73
 *
 */

public class Score {

	Score(){
		
	}
	
	/**
	 * Compares values of two hands
	 * Return winning multiplier for player
	 * 0 = loss
	 * 1 = push
	 * 2 = win
	 * 2.5 = player has Blackjack
	 * @param <Card> playerHand
	 * @param <Card> dealerHand
	 * @return double
	 */
	
	public double winLose(ArrayList<Card> playerHand, ArrayList<Card> dealerHand) {
		int playerValue = getHandValue (playerHand);
		int dealerValue = getHandValue (dealerHand);
		double win = 0.0;
		
		if (checkBlackjack(playerHand) && !checkBlackjack (dealerHand)) {
			win = 2.5;
		}
		else if(checkBlackjack(playerHand) && checkBlackjack (dealerHand)) {
			win = 1.0;
		}
		else if(checkBust(playerHand)) {
			win = 0.0;
		}
		else if (checkBust(dealerHand)) {
			win = 2.0;
		}
		else if(playerValue == dealerValue) {
			win = 1.0;
		}
		else if(playerValue > dealerValue) {
			win = 2.0;
		}
		return win;
	}
	
	/**
	 * Calculate value of a hand
	 * @param <Card> hand
	 * @return int handValue
	 */
	
	public int getHandValue(ArrayList<Card> hand) {
		int handValue = 0;
		boolean ace = false;
		for (int i = 0; i < hand.size(); i++) {
			Card card = hand.get(i);
			handValue = card.getCardValue() + handValue;
			if ((hand.get(i)).getCardValue() == 1) {
				ace = true;
			}
		}
		
		// if there is an ace, check if the ace should be assigned a value of 11.
		if (ace == true) {
			if (handValue + 10 <= 21) {
				handValue = handValue + 10;
			}
		}
		return handValue;
	}

	/**
	 * Checks if a hand is a Blackjack hand
	 * @param <Card> hand
	 * @return boolean (TRUE = a Blackjack hand)
	 */
	
	public boolean checkBlackjack (ArrayList<Card> hand) {
		boolean checkBlackjack = false;		
		if (hand.size() == 2) {
			Card one = hand.get(0);
			Card two = hand.get(1);		
			
			// if there is an ace, check if the ace should be assigned a value of 11.
			if (one.rank == 1 || two.rank == 1) {
				
				// adding a value of 10 to the existing value of 1 for an ace card
				int aceHand = one.getCardValue() + two.getCardValue() + 10; 
				if (aceHand == 21) { // check if it's blackjack
					checkBlackjack = true;
				}
			}
		}
		return checkBlackjack;
	}
	
	/**
	 * Checks if a hand is busted (value > 21)
	 * Ace always assumed as 1
	 * @param <Card> hand
	 * @return boolean (TRUE = busted hand)
	 */
	
	public boolean checkBust (ArrayList<Card> hand) {
		boolean checkBust = false;
		int handValue = 0;
		for (int i = 0; i < hand.size(); i++) {
			Card card = hand.get(i);
			handValue = card.getCardValue() + handValue;
			if (handValue > 21) {
				checkBust = true;
			}
		}
		return checkBust;
	}
}