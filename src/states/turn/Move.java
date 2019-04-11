package states.turn;

import entities.creatures.Creature;
import entities.EntityManager;
import entities.creatures.Player;
import test.Handler;
import tiles.Tile;
import states.GameState;
import maps.Map;

public class Move {
	private EntityManager entityManager;
	public Object t;

	private int[][] tiles;

	public Move() {

	}

	public boolean MovePressed() {

		if (GameState.ButtonPressed()) {
			// calculation();
			return true;

		} else {
			return false;

		}

	}



}
