package entities.statics;

import java.awt.Graphics;

import gfx.Assets;
import test.Handler;
import tiles.Tile;

public class Tree extends StaticEntity {

	public Tree(Handler handler, float x, float y) {
		super(handler, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT);

		bounds.x = 1;
		bounds.y = 1;
		bounds.width = 63;
		bounds.height = 63;

	}

	@Override
	public void tick() {

	}

	public void die() {

	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.tree, (int) (x), (int) (y), width, height, null);

	}

}
