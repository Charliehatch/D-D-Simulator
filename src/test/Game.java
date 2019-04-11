package test;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import display.Display;
import entities.creatures.Enemy;
import gfx.Assets;
import input.KeyManager;
import input.MouseManager;
import states.GameState;
import states.MenuState;
import states.State;

public class Game implements Runnable {

	private Display display;
	public int width, height;
	public String title;

	private boolean running = false;
	private Thread thread;

	boolean gameLoopActive = true;

	private BufferStrategy bs;
	private Graphics g;

	// States
	public State gameState;
	public State menuState;

	// input
	private KeyManager keyManager;
	private MouseManager mouseManager;

	// Handler
	private Handler handler;

	public Game(String title, int width, int height) {

		this.width = width;
		this.height = height;
		this.title = title;
		keyManager = new KeyManager();
		mouseManager = new MouseManager();

	}

	private void init() {
		display = new Display(title, width, height);
		display.getFrame().addKeyListener(keyManager);
		display.getFrame().addMouseListener(mouseManager);
		display.getFrame().addMouseMotionListener(mouseManager);
		display.getCanvas().addMouseListener(mouseManager);
		display.getCanvas().addMouseMotionListener(mouseManager);
		Assets.init();

		handler = new Handler(this);

		gameState = new GameState(handler);
		// menuState = new MenuState(handler);
		State.setState(gameState); // setting the first state

	}

	private void tick() {
		keyManager.tick();
		if (State.getState() != null) {
			State.getState().tick();
		}

	}

	private void render() { // draw to screen

		bs = display.getCanvas().getBufferStrategy();
		if (bs == null) {
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics(); // g is the graphics object
		// clear screen before we draw anything
		g.clearRect(0, 0, width, height);
		// Start Drawing Here
		if (State.getState() != null) {
			State.getState().render(g);
		}

	

		// End Drawing Here
		bs.show();
		g.dispose();
	}

	public void run() {
		init();
		int fps = 60;
		double timePerTick = 1000000000 / fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;
		int ticks = 0;

		while (running) {
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			timer += now - lastTime;
			lastTime = now;

			if (delta >= 1) {

				tick();
				render();
				ticks++;
				delta--;
			}
			if (timer >= 1000000000) {
				// System.out.println("Ticks and Frames: " + ticks);
				ticks = 0;
				timer = 0;
			}
		}

		stop();
	}

	public KeyManager getKeyManager() {
		return keyManager;
	}

	public MouseManager getMouseyManager() {
		return mouseManager;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public synchronized void start() {
		if (running) {
			return;
		}
		running = true;
		thread = new Thread(this);
		thread.start();

	}

	public synchronized void stop() {
		if (!running) {
			return;
		}
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
