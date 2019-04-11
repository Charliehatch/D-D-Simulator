package states.turn;

import entities.creatures.Enemy;
import states.GameState;

public class Turn {

	private int i = 0;
	boolean playerTurn = true;

	public void TurnCheck() {
		int enemySpeed = 0;
		int playerSpeed = 0;
		boolean enemyAlive = true;
		boolean playerAlive = true;
		int turnCounter = 0;
		boolean loopRunning = true;

		if (playerTurn) {
			System.out.println("It is the Player's Turn to move.");
		}

		do {
			if (turnCounter == 0) {
				playerSpeed = 6;
				System.out.println("It is the Player's Turn to move. His speed is " + playerSpeed);
				turnCounter = 1;

			} else if (turnCounter == 1) {
				enemySpeed = 6;
				System.out.println("It is not your turn");
				turnCounter = 0;

			}
			enemyAlive = false;
			if (!enemyAlive || !playerAlive) {
				loopRunning = false;
			}

		} while (loopRunning);

	}
}

/*
 * if (playerTurn) { playerTurn(); return true; } else if (!playerTurn) {
 * enemyTurn(); // let enemy move return false; } return playerTurn;
 * 
 * }
 * 
 * private void playerTurn() { System.out.println("IT IS THE PLAYER TURN");
 * 
 * }
 * 
 * private void enemyTurn() { System.out.println("IT IS THE ENEMY TURN");
 * 
 * }
 * 
 * }
 */