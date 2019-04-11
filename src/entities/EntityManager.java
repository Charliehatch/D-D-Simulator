package entities;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;

import entities.creatures.Player;
import test.Handler;

public class EntityManager {

	private Handler handler;
	private Player player;
	private ArrayList<Entity> entities;

	public EntityManager(Handler handler, Player player) {

		this.handler = handler;
		this.player = player;
		entities = new ArrayList<Entity>();
		addEntity(player);
	}

	public void tick() {
		Iterator<Entity> it = entities.iterator();
		while (it.hasNext()) {

			Entity e = it.next();
			e.tick();

			if (!e.isActive())
				it.remove();
		}

	}

	public void render(Graphics g) {
		for (Entity e : entities) { // creates entities
			e.render(g);
		}
		// render player
	}

	public void addEntity(Entity e) {
		entities.add(e);
	}

	// GETTERS SETTERS

	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public ArrayList<Entity> getEntities() {
		return entities;
	}

	public void setEntities(ArrayList<Entity> entities) {
		this.entities = entities;
	}

}
