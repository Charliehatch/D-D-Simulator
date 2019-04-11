package test;

import display.Display;

public class Launcher {

	public static void main(String[] args) {
		Game game = new Game("D&D Game", 1280, 720);

		game.start();
	}

}
