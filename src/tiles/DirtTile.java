package tiles;

import gfx.Assets;
import maps.Map;
import states.turn.Move;
import entities.creatures.Creature;
import entities.EntityManager;

public class DirtTile extends Tile {
	Move m = new Move();
	private EntityManager entityManager;

	public DirtTile(int id) {
		super(Assets.dirt, id);
	}

	public boolean isSolid() {

		return false;

	}

	public boolean makeSolid() {
		double distance = Map.getDistance();
		if (distance > 10) {
			return true;
		}
		return false;
	}

}
