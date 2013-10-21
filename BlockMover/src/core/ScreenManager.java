package core;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import blockmover.BlockMoverGameField;
import screen.GameOver;
import screen.Menu;
import screen.MenuMain;
import screen.MenuNewGame;

public class ScreenManager {
	
	private BlockMoverGameField parent;
	private Menu currentDisplayMode = null;
			
	public ScreenManager(BlockMoverGameField parent) {
		this.parent = parent;
	}

	public Menu getCurrentDisplayMode() {
		return currentDisplayMode;
	}
	
	public void changeDisplayMode(Gamestate displayMode) {
		if(currentDisplayMode != null) {
			currentDisplayMode.unload();
		}
		
		if(displayMode == Gamestate.MENU_MAIN) {
			currentDisplayMode = new MenuMain(parent);
			currentDisplayMode.load();
			return;
		}
		
		if(displayMode == Gamestate.MENU_NEW_GAME) {
			currentDisplayMode = new MenuNewGame(parent);
			currentDisplayMode.load();
			return;
		}
		
		if(displayMode == Gamestate.GAME_OVER) {
			currentDisplayMode = new GameOver(parent);
			currentDisplayMode.load();
			return;
		}
		
		//TODO: Implement rest here
	}
	
	public void draw(Graphics g) {
		currentDisplayMode.draw(g);
	}
	
	public void update() {
		currentDisplayMode.update();
	}
}
