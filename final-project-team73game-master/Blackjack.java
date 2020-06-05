import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 * 
 * @author Jeannie Chan, Michael Sangho Song, Matthew Spelman
 * @team team 73
 * 
 */

public class Blackjack extends JFrame {

	private static final long serialVersionUID = 1L;

	ArrayList<Card> playerHand = new ArrayList<Card>();
	ArrayList<Card> dealerHand = new ArrayList<Card>();
	Score score = new Score();
	Deck deck = new Deck();

	//Set for screen resolution of 1280 x 700 or higher
	int width = 1280;
	int height = 700;

	int moneyReserve;
	int bet;
	int round;
	int maxRound;

	double pay = 1.0;

	JPanel dealerFloatPanel;
	JTextField dealerFloatBox;
	JPanel dealerCardsPanel;
	JPanel playerCardsPanel;
	JPanel hitStayPanel;
	JLabel cardText;
	JButton hitButton;
	JButton stayButton;
	JPanel betDetailPanel;
	JTextField betDetailNotice;
	JPanel betActionPanel;
	JButton betButton;
	JTextField currentRound;
	JTextField currentReserveBox;
	JPanel infoPanel;
	ButtonGroup bg;

	public Blackjack(int reserve, int round, int maxRound) {
		this.moneyReserve = reserve;
		this.round = round;
		this.maxRound = maxRound;
	
	}
	
	public void play() {
	
		setSize(width, height);
		setTitle("Welcome to Blackjack!");
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		int standard = 50;
		int difference = width - standard;
		
		/*
		 * Creates a jOption Pane for initial game instructions.
		 */

		JOptionPane.showMessageDialog(null, "Hello and welcome to Team 73's Blackjack card game! Please click on the 'OK' button to get started!");

		/*
		 * Created the first panel, dealerFloatPanel, with a text box
		 * indicating whether the dealer's first card is face down or up.
		 */
		dealerFloatPanel = new JPanel();
		dealerFloatBox = new JTextField("Dealer's first card: Face-Down", 30);
		dealerFloatBox.setSize(300, 50);
		dealerFloatPanel.add(dealerFloatBox);
		dealerFloatPanel.setBounds(standard, 0, difference, 30);
		dealerFloatPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		add(dealerFloatPanel);

		/*
		 * Created the second panel, dealerCardsPanel, with text fields
		 * where cards will be displayed for the dealer
		 */

		dealerCardsPanel = new JPanel();
		dealerCardsPanel.setBounds(standard, 30, width - standard, 150);
		cardDeal("dealer card", dealerCardsPanel);
		dealerCardsPanel.setLayout(new GridLayout(0, 5));
		add(dealerCardsPanel);

		/*
		 * Created the third panel, playerCardsPanel, with text fields
		 * where cards will be displayed for the player
		 */

		playerCardsPanel = new JPanel();
		playerCardsPanel.setBounds(standard, 180, difference, 150);
		cardDeal("player card", playerCardsPanel);
		playerCardsPanel.setLayout(new GridLayout(0, 5));
		add(playerCardsPanel);

		/*
		 * Created the fourth panel, hitStayPanel, where there are
		 * two buttons, hit & stay 
		 */

		hitStayPanel = new JPanel();
		hitStayPanel.setBounds(standard, 330, difference, 100);

		/*
		 *  1) Hit button 
		 *  Press hit button to hit a card for player
		 */

		hitButton = new JButton();
		hitButton.setText("Hit");
		hitButton.setToolTipText("Click to draw the card.");
		hitButton.setEnabled(false);

		// hitButton action listener
		hitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				hitButton.setEnabled(true);
				stayButton.setEnabled(true);
				betButton.setEnabled(false);

				hit(); //hit method at the bottom

				if(score.checkBust(playerHand)) {
					JOptionPane.showMessageDialog(null, "Player Hand - BUSTED -");
					drawCardBoard();
					dealerFloatBox.setText("Dealer's card is now face-up");
					changeButtons();
				}
			}
		});
		hitButton.setPreferredSize(new Dimension(200, 80));

		/*
		 * 2. Stay button
		 * Press stay button to play with current cards
		 */

		stayButton = new JButton();
		stayButton.setText("Stay");
		stayButton.setToolTipText("Click to stop drawing cards.");
		stayButton.setEnabled(false);

		//stay button action listener
		stayButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				stay(); //stay method at the bottom

				if(score.checkBust(dealerHand)) {
					JOptionPane.showMessageDialog(null,"Dealer Hand - BUSTED -");
					changeButtons(); //change button method at the bottom
				}
				changeButtons();
			}
		});
		stayButton.setPreferredSize(new Dimension(200, 80));

		hitStayPanel.add(hitButton);
		hitStayPanel.add(stayButton);
		hitStayPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		add(hitStayPanel);

		/*
		 * Created the fifth panel, betDetailPanel, to indicate
		 * whether player win/lose/draw & notice player to press bet button
		 * to start play.
		 */

		betDetailPanel = new JPanel();
		cardText = new JLabel("Player to click 'Bet' button to start play.");
		betDetailPanel.add(cardText);
		betDetailPanel.setBounds(standard, 430, difference, 85);

		betDetailNotice = new JTextField("", 30);
		betDetailNotice.setPreferredSize(new Dimension(300, 80));
		betDetailPanel.add(betDetailNotice);
		betDetailPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		add(betDetailPanel);

		/*
		 * Created the sixth panel, betActionPanel, where there are
		 * bet button and bet radio buttons
		 */

		betActionPanel = new JPanel();
		betActionPanel.setBounds(standard, 510, difference, 85);

		/*
		 * Bet radio buttons
		 * Player to select 4 options of betting $5/$10/$15/$20
		 */

		bg = new ButtonGroup();
		ArrayList<JRadioButton> radioButtons = new ArrayList<>();
		for(int value = 5; value <= 20; value += 5) {
			JRadioButton jrb = new JRadioButton(String.format("$%d", value));

			//bet radio buttons action listener
			jrb.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					JRadioButton select = (JRadioButton) e.getSource();
					for(int i = 0; i < radioButtons.size(); i++) {
						if(select == radioButtons.get(i)) {
							switch(i) {
							case 0:
								bet = 5;
								break;

							case 1:
								bet = 10;
								break;

							case 2:
								bet = 15;
								break;

							case 3:
								bet = 20;
								break;
							}
						}
					}
				}	
			});
			bg.add(jrb);
			radioButtons.add(jrb);
			betActionPanel.add(jrb);
		}

		/*
		 * 3. Bet button
		 * Press bet button to play a game 
		 */

		betButton = new JButton();
		betButton.setText("Bet");
		betButton.setToolTipText("Minimum bet $5. Maximum bet $20. Bet in $5 increments.");

		//bet button action listener
		betButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				betDetailNotice.setText("Bet placed: $" + bet);

				//in case player presses Bet button without selecting a bet
				if (bet < 5) {
					bet = 5;
					betDetailNotice.setText("Minimum bet placed: $5");
				}

				//in case player tries to place a larger bet than there is funds
				if (bet > moneyReserve) {
					bet = moneyReserve;
					betDetailNotice.setText("Insufficient funds. Maximum bet placed: $" + bet);
				} 
				hitButton.setEnabled(true);
				stayButton.setEnabled(true);	
				betButton.setEnabled(false);
				betActionPanel.setVisible(false);
				
				gameOver(); //game over method at the bottom
				drawCards(); //draw cards method at the bottom
			}
		});
		betButton.setPreferredSize(new Dimension(100, 50));
		betActionPanel.add(betButton);
		betActionPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		add(betActionPanel);

		/*
		 * Created the seventh(last) panel, info Panel, where
		 * indicates 1) current round and 2) current money reserve
		 */

		infoPanel = new JPanel();
		infoPanel.setBounds(standard, 590, difference, 34);

		/*
		 * currentRound will show the current round the player playing
		 */

		currentRound = new JTextField("Current Round: " + round, 30);
		currentRound.setPreferredSize(new Dimension(300, 30));
		infoPanel.add(currentRound);

		/*
		 * current reserve box will show current reserved money
		 */

		currentReserveBox = new JTextField("Current Reserve: $" + moneyReserve , 30);
		currentReserveBox.setPreferredSize(new Dimension(300, 30));
		infoPanel.add(currentReserveBox);
		infoPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		add(infoPanel);
		
		currentReserveBoxText();
		currentRound.setText("Current Round: " + round);
		setVisible(true);
		gameOver();
	}
	
	/*
	 * Method to update current reserve to JTextField box
	 */
	public void currentReserveBoxText() {
		currentReserveBox.setText("Current Reserve: $" + moneyReserve);
	}
	
	/*
	 * Method to deal cards 
	 * @param text for the JTextField in the panel
	 * @param panel 
	 */
	public void cardDeal(String text, JPanel panel) {
		for (int i = 0; i < 5; i++) {
			JTextField temp = new JTextField(text);
			temp.setSize(100, 150);
			panel.add(temp);
		}
	}

	/*
	 * Method of the betButton. Since betting is the beginning of the play,
	 * drawCards will include the game logic of starting.
	 * Also, check if player's bet is larger than reserve --> player can't play no more.
	 */

	public void drawCards() {
		betDetailNotice.setBackground(Color.WHITE);
		round++;
		deck.shuffleDeck();
		moneyReserve = moneyReserve - bet;
		currentReserveBoxText();
		currentRound.setText("Current Round: " + round);
		playerHand.clear();
		dealerHand.clear();

		twoCards(playerHand);
		twoCards(dealerHand);

		drawCardBoard();

		JTextField dealerFaceDown = (JTextField) dealerCardsPanel.getComponent(0);
		dealerFaceDown.setText("HIDDEN");

		if(score.checkBlackjack(playerHand)) {
			if(score.checkBlackjack(dealerHand)) {
				pay = 1.0;
				hitBlackjack("Both hit BLACKJACK!");
			}
			else {
				pay = 2.5;
				hitBlackjack("Player hits BLACKJACK!");
			}
		}

		if(score.checkBlackjack(dealerHand)) {
			pay = 0.0;
			hitBlackjack("Dealer hits BLACKJACK!");
		}
	}
	
	/*
	 * Method to progress game after dealer or player hits Blackjack
	 */
	public void hitBlackjack(String text) {
		drawCardBoard();
		changeButtons();
		JOptionPane.showMessageDialog(null, text);
		roundEnd();
	}

	
	/*
	 * Method to deal the first two cards
	 */
	
	public void twoCards(ArrayList <Card> hand) {
		//i = 2 because we're add the first 2 cards 
		for (int i = 0; i < 2; i++) {
			Card c = deck.dealCard();
			hand.add(c);
		}
	}
	/*
	 * Method to draw on the cards panel. 
	 * It will paint the text fields of both player and dealer to string.
	 */

	private void drawCardBoard() {
		JTextField playerlab = new JTextField();
		JTextField dealerlab = new JTextField();
		for (int i=0; i < 5; i++) {
			playerlab = (JTextField)playerCardsPanel.getComponent(i);
			playerlab.setText("player card");
			dealerlab = (JTextField)dealerCardsPanel.getComponent(i);
			dealerlab.setText("dealer card");
		}
		
		drawHand(playerHand, playerlab, playerCardsPanel);
		drawHand(dealerHand, dealerlab, dealerCardsPanel);

	}

	/*
	 * Method to draw the dealer and player hands 
	 */
	
	public void drawHand (ArrayList <Card> hand, JTextField field, JPanel panel) {
		for (int i=0; i < hand.size(); i++) {
			field = (JTextField)panel.getComponent(i);
			field.setText(String.format("%s", hand.get(i).cardToString()));
		}
	}
	
	/*
	 * Method of the hitButton.
	 * Once the hitButton is pressed, hit method will
	 * draw the cards to player.
	 * But, set the dealer's first card as hidden.
	 */

	public void hit() {

		if(playerHand.size() >= 5) {
			stay();
		} 
		else {
			addCard(playerHand);
			drawCardBoard();
		}
		((JTextField)dealerCardsPanel.getComponent(0)).setText("HIDDEN");
	}

	/*
	 * Method of the stayButton. 
	 * Once the stayButton is pressed, stay method will 
	 * draw the cards to dealer and decide who wins.
	 * Also, the dealer's cards, including face-down, will show.
	 */

	public void stay() {
		drawCardBoard();
		dealerFloatBox.setText("Dealer's card is now face-up");

		while (score.getHandValue(dealerHand) < 17 && dealerHand.size() < 5 ) {
			addCard(dealerHand);
		}
		roundEnd();
	}
	
	/*
	 * Method to add a card to a hand
	 */
	public void addCard(ArrayList<Card> hand) {
		Card c = deck.dealCard();
		hand.add(c);
		drawCardBoard();
	}

	/*
	 * Method to calculate the pay out at the
	 * end of the round
	 */

	public void roundEnd() {
		pay = score.winLose(playerHand, dealerHand);

		if ( pay >= 2.0 ) {
			betDetailNoticeInfo("Player Wins!", Color.GREEN);

		} else if ( pay == 1.0 ) {
			betDetailNoticeInfo("Push", Color.LIGHT_GRAY);

		} else {
			pay = 0.0;
			betDetailNoticeInfo("Player Loses", Color.GRAY);
		}

		moneyReserve = (int) (moneyReserve + (bet * pay));
		currentReserveBoxText();
		bet = 0;
		bg.clearSelection();
		gameOver();
		deck = new Deck();
	}
	
	/*
	 * Method to set betDetailNotice after end of round
	 */
	public void betDetailNoticeInfo (String text, Color color) {
		betDetailNotice.setText(text);
		betDetailNotice.setBackground(color);
	}

	/*
	 * Method to end the game
	 */

	public void gameOver() {
		if(round > maxRound || moneyReserve <= 0) {
			JOptionPane.showMessageDialog(null, "Game is now over. Thank you for playing! - Team 73.");
			System.exit(0);
		}
	}

	/*
	 * Method to switch visibility of buttons and panel after bet was placed
	 */

	public void changeButtons() {
		hitButton.setEnabled(false);
		stayButton.setEnabled(false);
		betButton.setEnabled(true);
		betActionPanel.setVisible(true);
	}
}	