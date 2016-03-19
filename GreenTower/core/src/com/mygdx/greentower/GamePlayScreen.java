package com.mygdx.greentower;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.sun.javafx.font.LogicalFont;

import java.util.List;

/**
 * The {@link ScreenAdapter} which contains the actual gameplay.
 */
public class GamePlayScreen extends ScreenAdapter {

	private static final int TILE_WIDTH = 20;
	private static final int TILE_HEIGHT = 20;
	private static final int TILES_HORIZONTAL = 20;
	private static final float PLAYER_MOVESPEED = 200;
	private static final float PLAYER_JUMPPOWER = 4;
	private static final float PLAYER_MAX_SPEED_X = 5;
	private static final float PLAYER_MAX_SPEED_Y = 5;
	private static final float GRAVITY = -1f;
	
	private SpriteBatch spritebatch;
	
	private Texture imgTile,
					imgPlayer;
	
	private Camera camera;
	
	private Player player;
	private TileMap tilemap;
	private TileMapGenerator tilemapGenerator;
	private TileMapCamera tileCamera;
	
	
	/**
	 * Initializes a new instance of the {@link GamePlayScreen} class.
	 */
	public GamePlayScreen() {
		spritebatch = new SpriteBatch();
		
		camera = new OrthographicCamera();
		
		imgTile = new Texture("platform.png");
		imgPlayer = new Texture("player.png");
		
		player = new Player();
		player.setPosRect(new Rectangle(20, 200, 64, 64));
		player.setMoveVector(new Vector2());
		
		tilemap = new TileMap(
				TILES_HORIZONTAL,
				(Gdx.graphics.getBackBufferHeight() / TILE_HEIGHT) + 1);
		
		tilemapGenerator = new TileMapGenerator(tilemap);
		
		tileCamera = new TileMapCamera(tilemap.getWidth(), tilemap.getHeight(), TILE_HEIGHT);
		
	}
	
	@Override
	public void render(float delta) {
		
		update(delta);
		
		draw(delta);
		
		super.render(delta);
	}
	
	/**
	 * Tells the current {@link GamePlayScreen} to draw itself.
	 * 
	 * @param delta the time since the last draw call.
	 */
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
		
		List<MapTile[]> maprows = tilemap.getRows();
		
		for(int y = 0; y < maprows.size(); y++)
		{
			MapTile[] row = maprows.get(y);
			
			for(int x = 0; x < tilemap.getWidth(); x++)
			{
				MapTile tile = row[x];
				if(tile != null)
				{
					Int32Point2D worldPos = tileCamera.tileToWorld(x, y);
					Int32Point2D screenPos = tileCamera.screenToWorld(worldPos.x, worldPos.y);
				
					spritebatch.draw(
						imgTile,
						screenPos.x,
						screenPos.y,
						TILE_WIDTH,
						TILE_HEIGHT);
				}
			}
		}
		
		drawPlayer();
		
		spritebatch.end();
	}
	
	/**
	 * Draws the figur of the player.
	 */
	private void drawPlayer()
	{
		spritebatch.draw(
				imgPlayer,
				player.getPosRect().x,
				player.getPosRect().y,
				player.getPosRect().width,
				player.getPosRect().height);
	}
	
	
	/**
	 * Updates the game logic.
	 * 
	 * @param delta the time since the last update call.
	 */
	private void update(float delta)
	{
		float deltaY = 0;
		float deltaX = 0;
		
		float forceX = 0;
		float forceY = 0;
		
		if(Gdx.input.isKeyPressed(Keys.LEFT))
		{
			forceX = -1;
		}
		else if(Gdx.input.isKeyPressed(Keys.RIGHT))
		{
			forceX = 1;
		}
		else if(Gdx.input.isKeyPressed(Keys.SPACE))
		{
			//if(player.getMoveVector().y == 0)
				forceY = 1;
		}
		
		deltaX += forceX * (PLAYER_MOVESPEED * Gdx.graphics.getDeltaTime());
		deltaY += forceY * (PLAYER_JUMPPOWER * Gdx.graphics.getDeltaTime());
		
		deltaY += GRAVITY * Gdx.graphics.getDeltaTime();
		
		player.setMoveVector(new Vector2(
				MathUtils.span(player.getMoveVector().x + deltaX, -PLAYER_MAX_SPEED_X, PLAYER_MAX_SPEED_X),
				MathUtils.span(player.getMoveVector().y + deltaY, -PLAYER_MAX_SPEED_Y, PLAYER_MAX_SPEED_Y)));
		
		player.getPosRect().x += player.getMoveVector().x;
		player.getPosRect().y += player.getMoveVector().y;
		
		tileCamera.getPosition().y = (int)(player.getPosRect().y - Gdx.graphics.getBackBufferHeight() / 2);
	}

	
	@Override
	public void dispose() {	
		
		imgTile.dispose();
		imgPlayer.dispose();
		spritebatch.dispose();
		
		super.dispose();
	}
	
	
}
