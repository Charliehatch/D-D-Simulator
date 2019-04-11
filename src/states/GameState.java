package states;

import java.awt.Graphics;

import entities.creatures.Player;
import gfx.Assets;
import maps.Map;
import states.turn.Move;
import states.turn.Turn;
import test.Handler;

import ui.ClickListener;
import ui.UIImageButton;
import ui.UIManager;
import entities.creatures.Creature.*;

public class GameState extends State {

	Move m = new Move();

	private Player player;
	private Map map;
	private UIManager uiManager;
	public static boolean pressed = false;

	public GameState(Handler handler) {
		super(handler);
		map = new Map(handler, "res/maps/map1.txt");
		handler.setMap(map);

		// UI
		uiManager = new UIManager(handler);
		handler.getMouseManager().setUIManager(uiManager);
		uiManager.addObject(new UIImageButton(550, 600, 128, 64, Assets.btn_startMove, new ClickListener() {
			@Override
			public void onClick() {
				System.out.println("Move Pressed");
				pressed = true;

			}
		}));
		uiManager.addObject(new UIImageButton(690, 600, 128, 64, Assets.btn_endMove, new ClickListener() {
			@Override
			public void onClick() {
				pressed = false; // changing the move button to unpressed, reseting the movement
				System.out.println("End Turn Pressed");

			}
		}));

		System.out.println();
	}

	public static boolean ButtonPressed() {
		if (pressed)
			return true;
		else
			return false;
	}

	@Override
	public void tick() {
		map.tick();
		uiManager.tick();

	}

	@Override
	public void render(Graphics g) {
		map.render(g);
		uiManager.render(g);

	}

}