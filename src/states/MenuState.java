package states;

import java.awt.Graphics;
import ui.UIManager;

import gfx.Assets;
import test.Handler;
import ui.ClickListener;
import ui.UIImageButton;

public class MenuState extends State {

	private UIManager uiManager;

	public MenuState(Handler handler) {
		super(handler);
		uiManager = new UIManager(handler);
		handler.getMouseManager().setUIManager(uiManager);

		uiManager.addObject(new UIImageButton(550, 300, 128, 64, Assets.btn_start, new ClickListener() {
			@Override
			public void onClick() {
			//	handler.getMouseManager().setUIManager(null);
				//unsets the UI manager
				State.setState(handler.getGame().gameState);
				
			}
		}));
	}

	@Override
	public void tick() {
		uiManager.tick();

		// THIS CODE SKIPS THE MENU STATE, REACTIVATE LATER!!
		//handler.getMouseManager().setUIManager(null);
		//State.setState(handler.getGame().gameState);
		//// THIS CODE SKIPS THE MENU STATE, REACTIVATE LATER!!
	}

	@Override
	public void render(Graphics g) {
		uiManager.render(g);
	}

}
