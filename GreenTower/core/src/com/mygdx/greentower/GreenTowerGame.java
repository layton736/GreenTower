package com.mygdx.greentower;

import com.badlogic.gdx.Game;

public class GreenTowerGame extends Game {
	
	public static final int WIDTH = 600;
	public static final int HEIGHT = 800;
	
	public static final String TITLE = "Green Tower";

	GamePlayScreen gameplayScreen;
	
	@Override
	public void create() {
		
		gameplayScreen = new GamePlayScreen();
		
		setScreen(gameplayScreen);
	}
}
