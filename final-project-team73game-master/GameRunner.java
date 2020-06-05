/**
 * 
 * @author Jeannie Chan, Michael Sangho Song, Matthew Spelman
 * @team team 73
 * 
 */

public class GameRunner{
	
	
	public static void main(String[]args) {
		
		/**
		 * Input game parameters below
		 * @Variable moneyReserve is how much money player starts the game with (default = 100)
		 * @Variable lastRound sets the round number for the last round of game (default = 10)
		 * @Variable firstRound sets the round number for the first round of game (default = 1)
		 */
		int moneyReserve = 100;
		int lastRound = 10;
		int firstRound = 1;
		
		
		Blackjack game = new Blackjack(moneyReserve,firstRound - 1,lastRound -1);
		game.play();
	}

}