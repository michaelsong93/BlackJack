
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * 
 * @author Jeannie Chan, Michael Sangho Song, Matthew Spelman
 * @team team 73
 * @variable ArrayList<Card> deck: a deck of cards
 * 
 */

public class Deck {
	ArrayList<Card>deck;

	public Deck() {
		deck = new ArrayList<Card>();

		String suitLetters = "♠♦♣♥";
		for (int rank = 1; rank <= 13; rank++) {
			for(int suit = 0; suit <= 3; suit++) {
				Card newCard = new Card(rank,suitLetters.charAt(suit));
				deck.add(newCard);
			}
		}
	}

	/**
	 * Method to shuffle the deck
	 */

	public void shuffleDeck() {
		Collections.shuffle(deck, new Random());
	}

	/**
	 * Method to deal a card from the deck
	 * @return Remove one card from the deck
	 */

	public Card dealCard() {
		return deck.remove(0);
	}
}