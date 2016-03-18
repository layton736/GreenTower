package com.mygdx.greentower;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import java.util.*;

public class GamePlayScreen extends ScreenAdapter {

	private static final int TILE_WIDTH = 20;
	private static final int TILE_HEIGHT = 20;
	private static final int TILES_HORIZONTAL = 20;
	
	private SpriteBatch spritebatch;
	
	private Texture imgTile,
					imgPlayer;
	
	private Camera camera;
	
	private Player player;
	private TileMap tilemap;
	
	public GamePlayScreen() {
		spritebatch = new SpriteBatch();
		
		camera = new OrthographicCamera();
		
		imgTile = new Texture("platform.png");
		imgPlayer = new Texture("player.png");
		
		player = new Player();
		player.setPosRect(new Rectangle(20, 20, 64, 64));
		
		tilemap = new TileMap(
				TILES_HORIZONTAL,
				(Gdx.graphics.getBackBufferHeight() / TILE_HEIGHT) + 1);
		
		
	}
	
	@Override
	public void render(float delta) {
		
		update(delta);
		
		draw(delta);
		
		super.render(delta);
	}
	
	private void draw(float delta)
	{
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		camera.update();
		
		spritebatch.begin();
		
		/*for(Platform platform : platforms)
		{
			spritebatch.draw(
					imgPlatform,
					platform.getRect().x,
					platform.getRect().y,
					platform.getRect().width,
					platform.getRect().height);
			
		}*/
		
		for(int y = 0; y < tilemap.getHeight(); y++)
		{
			for(int x = 0; x < tilemap.getWidth; x++)
			{
				
			}
		}
		
		spritebatch.draw(
				imgPlayer,
				player.getPosRect().x,
				player.getPosRect().y,
				player.getPosRect().width,
				player.getPosRect().height);
		
		spritebatch.end();
	}
	
	private void update(float delta)
	{
		if(Gdx.input.isKeyPressed(Keys.LEFT))
		{
			
		}
		else if(Gdx.input.isKeyPressed(Keys.RIGHT))
		{
			
		}
		else if(Gdx.input.isKeyPressed(Keys.SPACE))
		{
			
		}
	}

	
	@Override
	public void dispose() {	
		
		imgTile.dispose();
		imgPlayer.dispose();
		spritebatch.dispose();
		
		super.dispose();
	}
}
