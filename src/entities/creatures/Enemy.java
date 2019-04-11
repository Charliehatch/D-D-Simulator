package entities.creatures;

import java.awt.Graphics;

import gfx.Assets;
import items.Item;
import test.Handler;

public class Enemy extends Creature {

	public static boolean isDead = false;

	public Enemy(Handler handler, float x, float y) {

		super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);

		bounds.x = 1;
		bounds.y = 1;
		bounds.width = 63;
		bounds.height = 63;
	}

	@Override
	public void tick() {

	}

	public void die() {
		System.out.println("Enemy died.");
		isDead = true;

		handler.getMap().getItemManager().addItem(Item.coinItem.createNew((int)x + 32, (int)y));

	}

	@Override
	public void render(Graphics g) {

		g.drawImage(Assets.enemy, (int) x, (int) y, width, height, null);

	}

	public static boolean IsDead() {
		if (isDead) {
			return true;
		} else {
			return false;
		}
	}

}
