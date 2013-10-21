package core;

import screen.*;

public enum Gamestate {
	
	MENU_MAIN(1),
	MENU_NEW_GAME(2),
	MENU_INTRODUCTION(3),
	MENU_EXIT(4),
	GAME_OVER(99);
	
	private int state = 0;
	private Menu screen = null;

	private Gamestate(int state) {
		this.state = state;
	}
}
