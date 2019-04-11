package states.turn;

import entities.EntityManager;
import entities.creatures.Enemy;
import entities.creatures.Player;
import test.Handler;

public class GameLoop {

	public boolean loopActive = true;

	public GameLoop() {

		while (loopActive) {
			System.out.println("Game loop start");

			if (Enemy.IsDead()) {
				System.out.println("Enemy is dead!");
				loopActive = false;
			}

		}
	}

}
