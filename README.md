## Team Project Idea: Blackjack Game
MCIT591 final-project-team73game created by GitHub Classroom

### Project Team 73 Members:
- Jeannie Chan: <chanjea@seas.upenn.edu>
- Michael Sangho Song: <ss7277@seas.upenn.edu>
- Matthew Spelman: <mspel@seas.upenn.edu>

### Team Project Timeline:
- [X] Game except GUI (Java Swing) completed Nov 10
- [X] TA meeting Nov 11
- [X] Submit Design Nov 12
- [X] Team status meeting Nov 13
- [X] Team in-person meeting Nov 17 (10am Jeannie’s place)
  * Objective GUI integration
- [X] TA meeting Nov 18
- [X] Team status meeting Nov 20
- [X] Team in-person meeting Nov 23 
  * Finalization and identify debugging / test cases
- [X] *Team member(s) unavailable Thanksgiving week*
- [X] Team status meeting Dec 4
  * Record 3-5 min video demo
  * Verify understanding of final submission requirement
  * Confirm all requirements satisfied
- [X] Deadline to submit Final Project Dec 8

### How is our game set up:
- Game is set up with default value of $100 in reserve. 
- Game is also set up by default to play 10 rounds (or when reserve runs out, whichever happens first).
- These default values can be updated in GameRunner.java file.
- Please note that we have tried to accomodate different computer screen resolutions with a current width of 1280 pixels by a length of 700 pixels (the most typical screen resolution is 1366 x 768).
- Please ensure that you have UTF-8 enabled. You can check by navigating in Eclipse the following way (also shown in screenshot): Window > Preferences > General > Workspace, set Text file encoding to Other : ![UTF-8_Support_Screenshot](https://user-images.githubusercontent.com/44761887/84121199-c87ec900-aa71-11ea-8dd7-bcc4d9dcc358.png)

- Game play is similar to casino games, with two exceptions:
  * No Blackjack insurance is offered
  * Maximum of five cards per hand

### How to run and play our game:

1. The first step is to download the code repository to an IDE of your choice (i.e. Eclipse is preferred).
2. After creating a new local project that stores the program codes, please right click on the "src" folder and select the "run" button in your IDE.
3. After clicking "run" you will be prompted with a pop-up message that prompts you to play the game. Below is a screenshot:
![](welcomeScreen.png)
4. After clicking the "OK" button a playing board will pop-up. Below is a screenshot:
![](InitialBoardScreen.png)
5. In order to start playing the game, the player needs to select a Betting option of $5, 10, 15 or $20, and plays 10 rounds (as default) of Blackjack against the computer Dealer. Following this selection the player then clicks the "Bet" button in order for the initial cards to be dealt. 
6. Following the initial dealt cards, the player will then decide whether to click the "Hit" button to draw another card, or "Stay" to stop drawing cards.
7. Upon conclusion of the round the player's score will be calculated to determine if they won, lost, or tied the Dealer. Depending on the round outcome, the player's reserve will be adjusted automatically as well. The game will end after 10 rounds (as default) or when the Player runs out of money, whichever comes first. Insurance, doubling, and splitting not allowed.
8. The objective of the game is to beat the Dealer. Face cards are worth 10 points. Ace cards can worth either 1 or 11 points, whichever flavors the hand. If a Player or Dealer have a card score that equals 21, then they have hit Blackjack.
