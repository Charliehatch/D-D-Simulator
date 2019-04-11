package states.turn;

import states.GameState;
import entities.creatures.*;
import entities.Entity;
import entities.EntityManager;

public class RestrictMove {
	private EntityManager entityManager;

	public RestrictMove() {

		if (GameState.ButtonPressed()) {
			calculation();
		} else {
			return;

		}

	}

	public boolean calculation() {

		float xPosition = entityManager.getPlayer().getX();
		float yPosition = entityManager.getPlayer().getY();

		return false;
		// if is within bounds of the player

		// if out of bounds of the player

	}

}
