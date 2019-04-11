package maps;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Arrays;

import entities.Entity;
import entities.EntityManager;
import entities.creatures.Creature;
import entities.creatures.Enemy;
import entities.creatures.Player;
import entities.statics.Tree;
import items.ItemManager;
import test.Handler;
import tiles.Tile;
import utils.Utils;
import states.GameState;
import states.turn.GameLoop;

public class Map {

	private Handler handler;
	private int width, height;
	private int spawnX, spawnY;
	private int[][] tiles;
	float xPosition;
	float yPosition;
	boolean tileCheck;
	public boolean tooFar;
	public static double distanceCalculation;
	int tileX;
	int tileY;

	boolean running = true;

	// entities
	private EntityManager entityManager;

	// item
	private ItemManager itemManager;

	public boolean loopActive = true;

	public Map(Handler handler, String path) {
		this.handler = handler;

		entityManager = new EntityManager(handler, new Player(handler, 64, 64));
		itemManager = new ItemManager(handler);
		//
		entityManager.addEntity(new Tree(handler, 320, 192));
		entityManager.addEntity(new Tree(handler, 384, 192));
		entityManager.addEntity(new Tree(handler, 512, 192));
		entityManager.addEntity(new Enemy(handler, 512, 256));
		loadWorld(path);

	}

	public void tick() {
		itemManager.tick();
		entityManager.tick();

		if (GameState.ButtonPressed() && !tileCheck) {
			tileChecker(distanceCalculation);
		}
	}

	public void render(Graphics g) {
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				getTile(x, y).render(g, x * Tile.TILEWIDTH, y * Tile.TILEHEIGHT);

			}
		}
		itemManager.render(g);
		// Entities Render
		entityManager.render(g);
	}

	public void tileChecker(double distanceCalculation) {
		xPosition = entityManager.getPlayer().getX();
		yPosition = entityManager.getPlayer().getY();
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				if (!getTile(x, y).isSolid()) { // only check tiles that aren't solid
					getTile(x, y);
					tileX = x;
					tileY = y;
					calculation(tileX, tileY, distanceCalculation);
				}

			}
		}
		tileCheck = true;

	}

	public void calculation(int x, int y, double distanceCalculation) {
		tileX = x * 64;
		tileY = y * 64;
		distanceCalculation = distance(tileX, tileY, xPosition, yPosition);
		System.out.println(distanceCalculation);
		if (distanceCalculation > 384) {
			System.out.println("That's too far");
			//Creature.makeSolid();
		}

	}

	public static double distance(double x1, double y1, double x2, double y2) {
		double x = Math.pow(x2 - x1, 2);
		double y = Math.pow(y2 - y1, 2);
		double dist = Math.sqrt(x + y);
		return dist;

	}

	public Tile getTile(int x, int y) {
		if (x < 0 || y < 0 || x >= width || y >= height)
			return Tile.grassTile;

		Tile t = Tile.tiles[tiles[x][y]];

		if (t == null)
			return Tile.dirtTile;

		return t;

	}

	private void loadWorld(String path) {
		String file = Utils.loadFileAsString(path);

		String[] tokens = file.split("\\s+");
		width = Utils.parseInt(tokens[0]);
		height = Utils.parseInt(tokens[1]);
		spawnX = Utils.parseInt(tokens[2]);
		spawnY = Utils.parseInt(tokens[3]); // reading in the numbers of our file

		tiles = new int[width][height];

		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				tiles[x][y] = Utils.parseInt(tokens[(x + y * width) + 4]);

			}
		}
	}

	public static double getDistance() {
		return distanceCalculation;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {

		return height;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	public ItemManager getItemManager() {
		return itemManager;
	}

	public void setItemManager(ItemManager itemManager) {
		this.itemManager = itemManager;
	}

}
