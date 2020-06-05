/**
 * 
 * @author Jeannie Chan, Michael Sangho Song, Matthew Spelman
 * @team team 73
 * @variables 
 * char suit: suit of a card; 
 * int rank: rank of a card;
 * 
 */

public class Card {
	char suit;
	int rank;

	public Card(int rank, char suit) {
		this.suit = suit;
		this.rank = rank;
	}

	/**
	 * Method to get a rank of a card
	 * @return rank of a card
	 */
	
	public int getRank() {
		return rank;
	}

	/**
	 * Method to get a suit of a card
	 * @return suit of a card
	 */
	
	public int getSuit() {
		return suit;
	}

	/**
	 * Method to show a card into String rank 1 is A, 11 is Jack, 12 is Queen,
	 * 13 is King Other ranks will show their ranks of a card
	 */
	
	public String cardToString() {
		String result = "";

		if (rank == 1) {
			result = "A" + suit;
		} else if (rank > 10) {
			switch (rank) {
			case 11:
				result = "J" + suit;
				break;
			case 12:
				result = "Q" + suit;
				break;
			case 13:
				result = "K" + suit;
				break;
			}
		} else {
			if (rank > 1 && rank < 11) {
				result = Integer.toString(rank) + suit;
			}
		}
		return result;
	}

	/**
	 * Method to get a card value
	 * @return value of rank of a card
	 */

	public int getCardValue() {
		if (rank > 10) {
			return 10;
		} else {
			return rank;
		}
	}
}