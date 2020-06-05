/**
 * @author Jeannie Chan, Michael Sangho Song, Matthew Spelman
 * @team team 73
 *
 */

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.junit.jupiter.api.Test;

class Validation {


	Score score = new Score();
	Deck deck = new Deck();
	Blackjack game = new Blackjack(100, 1, 10);

	//Testing Score class
	
	/**
	 * 1) Tests the getHandValue method in the Score class
	 * Test case: This is a two-step test to validate that Ace card have flexible scoring 
	 */
	
	@Test
	void getHandValueTest() {
		ArrayList <Card> hand = new ArrayList <Card>();
		Card tmpCard;
		int tmpScore;
		tmpCard = new Card(1, 'D');
		hand.add(tmpCard);
		tmpCard = new Card(1, 'H');
		hand.add(tmpCard);
		//initial hand with two aces should have value of 12, 
		//since one of the aces would have been assigned a value of 10
		tmpScore = score.getHandValue(hand);		
		assertEquals(12, tmpScore, "getHandValue test failed");
		//mimics "hitting" and gets a third card, which is a King
		tmpCard = new Card(13, 'H');
		hand.add(tmpCard);
		//both of the initial two aces reverts back to value of 1
		//the King has a value of 10. Total value of hand is at 12.
		tmpScore = score.getHandValue(hand);		
		assertEquals(12, tmpScore, "getHandValue test failed");
		
	}
	
	/**
	 * 2) Tests the checkBust method in the Score class
	 * Test case: busted hand
	 */
	
	@Test
	void checkBustTestBusted() {
		ArrayList <Card> hand = new ArrayList <Card>();
		Card tmpCard;
		tmpCard = new Card(10, 'D');
		hand.add(tmpCard);
		tmpCard = new Card(10, 'H');
		hand.add(tmpCard);
		tmpCard = new Card(10, 'S');
		hand.add(tmpCard);
		boolean check = score.checkBust(hand);
		assertEquals(true, check, "checkBust test failed");
		
	}
	
	/**
	 * 3) Tests the checkBust method in the Score class
	 * Test case: playable hand (not busted) 
	 */
	
	@Test
	void checkBustTestSafe() {
		ArrayList <Card> hand = new ArrayList <Card>();
		Card tmpCard;
		tmpCard = new Card(1, 'D');
		hand.add(tmpCard);
		tmpCard = new Card(1, 'H');
		hand.add(tmpCard);
		tmpCard = new Card(1, 'S');
		hand.add(tmpCard);
		boolean check = score.checkBust(hand);
		assertEquals(false, check, "checkBust test failed");
		
	}

	
	/**
	 * 4) Tests the checkBlackjack method in the Score class
	 * Test case: Blackjack hand
	 */
	
	@Test
	void checkBlackjackTestTrue() {
		ArrayList <Card> hand = new ArrayList <Card>();
		Card tmpCard;
		tmpCard = new Card(1, 'D');
		hand.add(tmpCard);
		tmpCard = new Card(10, 'H');
		hand.add(tmpCard);
		boolean check = score.checkBlackjack(hand);
		assertEquals(true, check, "checkBlackjack test failed");
		
	}
	
	/**
	 * 5) Tests the checkBlackjack method in the Score class
	 * Test case: non-Blackjack hand 
	 */
	
	@Test
	void checkBlackjackTestFalse() {
		ArrayList <Card> hand = new ArrayList <Card>();
		Card tmpCard;
		tmpCard = new Card(10, 'D');
		hand.add(tmpCard);
		tmpCard = new Card(10, 'H');
		hand.add(tmpCard);
		boolean check = score.checkBlackjack(hand);
		assertEquals(false, check, "checkBlackjack test failed");
		
	}
	
	/**
	 * 6) Tests the winLose method in the Score class
	 * Test case: player has Blackjack and dealer has Blackjack 
	 */
	
	@Test
	void winLoseTestOne() {
		ArrayList <Card> dealerHand = new ArrayList <Card>();
		dealerHand.add(new Card(10, 'D'));
		dealerHand.add(new Card(1, 'D'));
		ArrayList <Card> playerHand = new ArrayList <Card>();
		playerHand.add(new Card(10, 'H'));
		playerHand.add(new Card(1, 'H'));
		assertEquals(1, score.winLose(playerHand, dealerHand), "winLose test failed");
		
	}
	
	/**
	 * 7) Tests the winLose method in the Score class
	 * Test case: player has Blackjack  
	 */
	
	@Test
	void winLoseTestTwo() {
		ArrayList <Card> dealerHand = new ArrayList <Card>();
		dealerHand.add(new Card(10, 'D'));
		dealerHand.add(new Card(7, 'D'));
		ArrayList <Card> playerHand = new ArrayList <Card>();
		playerHand.add(new Card(10, 'H'));
		playerHand.add(new Card(1, 'H'));
		assertEquals(2.5, score.winLose(playerHand, dealerHand), "winLose test failed");
		
	}
	
	/**
	 * 8) Tests the winLose method in the Score class
	 * Test case: player busted
	 */
	
	@Test
	void winLoseTestThree() {
		ArrayList <Card> dealerHand = new ArrayList <Card>();
		dealerHand.add(new Card(10, 'D'));
		dealerHand.add(new Card(7, 'D'));
		ArrayList <Card> playerHand = new ArrayList <Card>();
		playerHand.add(new Card(10, 'H'));
		playerHand.add(new Card(11, 'H'));
		playerHand.add(new Card(12, 'H'));
		assertEquals(0, score.winLose(playerHand, dealerHand), "winLose test failed");
		
	}
	
	/**
	 * 9) Tests the winLose method in the Score class
	 * Test case: player push (i.e. same value as dealer)
	 */
	
	@Test
	void winLoseTestFour() {
		ArrayList <Card> dealerHand = new ArrayList <Card>();
		dealerHand.add(new Card(10, 'D'));
		dealerHand.add(new Card(7, 'D'));
		ArrayList <Card> playerHand = new ArrayList <Card>();
		playerHand.add(new Card(10, 'H'));
		playerHand.add(new Card(7, 'H'));
		assertEquals(1.0, score.winLose(playerHand, dealerHand), "winLose test failed");
		
	}
	
	/**
	 * 10) Tests the winLose method in the Score class
	 * Test case: player win (i.e. larger value than dealer, but not bust)
	 */
	
	@Test
	void winLoseTestFive() {
		ArrayList <Card> dealerHand = new ArrayList <Card>();
		dealerHand.add(new Card(10, 'D'));
		dealerHand.add(new Card(7, 'D'));
		ArrayList <Card> playerHand = new ArrayList <Card>();
		playerHand.add(new Card(10, 'H'));
		playerHand.add(new Card(9, 'H'));
		assertEquals(2.0, score.winLose(playerHand, dealerHand), "winLose test failed");
		
	}
	
	//Testing Card Class
	
	/**
	 * 11) Tests the getCardValue method in the Card class
	 * Test case: face card
	 */
	
	@Test
	void getCardValueTestOne() {
		Card test = new Card (12, 'D');
		assertEquals(10, test.getCardValue(), "getCardValue test failed");
		
	}
	
	/**
	 * 12) Tests the getCardValue method in the Card class
	 * Test case: number card
	 */
	
	@Test
	void getCardValueTestTwo() {
		Card test = new Card (7, 'D');
		assertEquals(7, test.getCardValue(), "getCardValue test failed");
		
	}
	
	/**
	 * 13) Tests the cardToString method in the Card class
	 * Test case: number card
	 */
	
	@Test
	void cardToStringTestOne() {
		Card test = new Card (7, 'D');
		assertEquals("7D", test.cardToString(), "cardToString test failed");
		
	}
	
	/**
	 * 14) Tests the cardToString method in the Card class
	 * Test case: Ace card
	 */
	
	@Test
	void cardToStringTestTwo() {
		Card test = new Card (1, 'D');
		assertEquals("AD", test.cardToString(), "cardToString test failed");
		
	}
	
	/**
	 * 15) Tests the cardToString method in the Card class
	 * Test case: face cards
	 */
	
	@Test
	void cardToStringTestThree() {
		Card test = new Card (11, 'D');
		assertEquals("JD", test.cardToString(), "cardToString test failed");
		
	}
	
	/**
	 * 16) Tests the cardToString method in the Card class
	 * Test case: face cards
	 */
	
	@Test
	void cardToStringTestFour() {
		Card test = new Card (12, 'D');
		assertEquals("QD", test.cardToString(), "cardToString test failed");
		
	}
	
	/**
	 * 17) Tests the cardToString method in the Card class
	 * Test case: face cards
	 */
	
	@Test
	void cardToStringTestFive() {
		Card test = new Card (13, 'D');
		assertEquals("KD", test.cardToString(), "cardToString test failed");
		
	}
	
	//Testing Blackjack class
	/**
	 * 18) Tests the drawHand method in Blackjack class
	 */
	
	@Test
	void drawHandTest() {

		JFrame frame = new JFrame();
		JTextField field = new JTextField();
		JPanel panel = new JPanel();
		frame.add(panel);
		panel.add(field);
		
		ArrayList<Card> hand = new ArrayList<Card>();
		Card one = new Card(7, 'D');
		hand.add(one);
		
		game.drawHand (hand, field, panel);
		assertEquals("7D", field.getText(), "drawHand test failed");

		
	}
	

	/**
	 * 19) Tests the twoCards method in the Blackjack class
	 */
	
	@Test
	void twoCardsTest() {
		deck.shuffleDeck();
		ArrayList<Card> hand = new ArrayList<Card>();
		game.twoCards(hand);
		assertEquals(2, hand.size(), "twoCards test failed");

		
	}
		
	

	
	


	
}
