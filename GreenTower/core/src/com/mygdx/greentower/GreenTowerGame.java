package com.mygdx.greentower;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GreenTowerGame extends Game {

	GamePlayScreen gameplayScreen;
	
	@Override
	public void create() {
		
		gameplayScreen = new GamePlayScreen();
		
		setScreen(gameplayScreen);
	}
}
