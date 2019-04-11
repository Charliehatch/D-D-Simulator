package entities.creatures;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

import entities.Entity;
import gfx.Assets;
import inventory.Inventory;
import items.Item;
import states.turn.Move;
import test.Handler;

public class Player extends Creature {
	Move m = new Move();

	private Handler handler;

	// Attack timer
	private long lastAttackTimer, attackCooldown = 800, attackTimer = attackCooldown;

	// inventory

	private Inventory inventory;

	public Player(Handler handler, float x, float y) {
		super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);
		this.handler = handler;

		bounds.x = 1;
		bounds.y = 1;
		bounds.width = 63;
		bounds.height = 63;

		inventory = new Inventory(handler);
	}

	@Override
	public void tick() {

		if (m.MovePressed()) {
			// movement
			getInput();
			move();
			// attack
			checkAttacks();
		} else {

			// System.out.println("No movement allowed.");

		}

		inventory.tick();

	}

	private void checkAttacks() {
		attackTimer += System.currentTimeMillis() - lastAttackTimer;
		lastAttackTimer = System.currentTimeMillis();
		if (attackTimer < attackCooldown)
			return;

		Rectangle cb = getCollisionBounds(0, 0);
		Rectangle ar = new Rectangle();
		int arSize = 20;
		ar.width = arSize;
		ar.height = arSize;

		if (handler.getKeyManager().attack) {
			ar.x = cb.x + cb.width / 2 - arSize / 2;
			ar.y = cb.y - arSize;
		} else if (handler.getKeyManager().attack) {
			ar.x = cb.x + cb.width / 2 - arSize / 2;
			ar.y = cb.y + cb.height;
		} else if (handler.getKeyManager().attack) {
			ar.x = cb.x - arSize;
			ar.y = cb.y + cb.height / 2 - arSize / 2;
		} else if (handler.getKeyManager().attack) {
			ar.x = cb.x + cb.width;
			ar.y = cb.y + cb.height / 2 - arSize / 2;
		} else {
			return;
		}

		attackTimer = 0;

		for (Entity e : handler.getMap().getEntityManager().getEntities()) {
			if (e.equals(this))
				continue;
			if (e.getCollisionBounds(0, 0).intersects(ar)) {
				e.hurt(1);
				return;
			}
		}

	}

	public void die() {
		System.out.println("KYS");

	}

	private void getInput() {
		xMove = 0;
		yMove = 0;

		// if (t.TurnCheck(true)) {
		if (handler.getKeyManager().up)
			yMove = -speed;
		if (handler.getKeyManager().down)
			yMove = speed;
		if (handler.getKeyManager().left)
			xMove = -speed;
		if (handler.getKeyManager().right)
			xMove = speed;

	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.player, (int) x, (int) y, width, height, null);
		inventory.render(g);

	}

	public Inventory getInventory() {
		return inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}

}