package gfx;

import java.awt.image.BufferedImage;

public class Assets {

	private static final int width = 64, height = 64;

	public static BufferedImage dirt, grass, water, player, tree, stone, enemy;
	public static BufferedImage coin;
	public static BufferedImage[] btn_start, btn_startMove, btn_endMove;

	public static void init() { // Initialise assets

		SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/sheet.png"));
		// buttons
		btn_start = new BufferedImage[2];
		btn_start[0] = sheet.crop(0, height * 2, width * 2, height);
		btn_start[1] = sheet.crop(width * 2, height * 2, width * 2, height);
		// startMove
		btn_startMove = new BufferedImage[2];
		btn_startMove[0] = sheet.crop(0, height * 3, width * 2, height);
		btn_startMove[1] = sheet.crop(width * 2, height * 3, width * 2, height);
		// endMove
		btn_endMove = new BufferedImage[2];
		btn_endMove[0] = sheet.crop(0, height * 4, width * 2, height);
		btn_endMove[1] = sheet.crop(width * 2, height * 4, width * 2, height);

		// tiles
		dirt = sheet.crop(0, 0, width, height);
		grass = sheet.crop(width, 0, width, height);
		water = sheet.crop(width * 2, 0, width, height);
		player = sheet.crop(0, height, width, height);
		tree = sheet.crop(width * 3, 0, width, height);
		enemy = sheet.crop(width, height, width, height);
		stone = sheet.crop(width * 2, height, width, height);

		// items
		coin = sheet.crop(width * 3, height, width, height);

	}
}
