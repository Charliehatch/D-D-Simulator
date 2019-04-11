package tiles;

import gfx.Assets;
import maps.Map;

public class GrassTile extends Tile {

	public GrassTile(int id) {
		super(Assets.grass, id);
	}

	public boolean isSolid() {

		return false;
	}


}
