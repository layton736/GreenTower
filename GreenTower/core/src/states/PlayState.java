package states;

import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.greentower.GreenTowerGame;
import com.mygdx.greentower.Int32Point2D;
import com.mygdx.greentower.MapTile;
import sprites.Player;
import sprites.Player.playerState;

import com.mygdx.greentower.TileMap;
import com.mygdx.greentower.TileMapCamera;
import com.mygdx.greentower.TileMapGenerator;

public class PlayState extends State{
	
	private static final int TILE_WIDTH = 20;
	private static final int TILE_HEIGHT = 20;
	private static final int TILES_HORIZONTAL = 20;
	
	private Player player;
	private Texture bg;
	

	private TileMap tilemap;
	private Texture imgTile;
	
	private TileMapGenerator tilemapGenerator;
	private TileMapCamera tileCamera;

	protected PlayState(GameStateManager gsm) {
		super(gsm);	
		//create player
		player = new Player(50,300);
		//set background texture
		bg = new Texture("bg.png");
		//only need one camera -> derive from state
		cam.setToOrtho(false, GreenTowerGame.WIDTH / 2, GreenTowerGame.HEIGHT / 2);
		
		
		
		//TODO - create the map?!?
		imgTile = new Texture("platform.png");
		tilemap = new TileMap(
				TILES_HORIZONTAL,
				(Gdx.graphics.getBackBufferHeight() / TILE_HEIGHT) + 1);
		tilemapGenerator = new TileMapGenerator(tilemap);
		tileCamera = new TileMapCamera(tilemap.getWidth(), tilemap.getHeight(), TILE_HEIGHT);
	}

	/**
	 * Handling all the Player inputs here
	 * 
	 */
	@Override
	protected void handleInput() {
		if(Gdx.input.isKeyPressed(Keys.SPACE))
		{
			player.jump();
		}
		if(Gdx.input.isKeyPressed(Keys.LEFT) || Gdx.input.isKeyPressed(Keys.RIGHT)){
			if(Gdx.input.isKeyPressed(Keys.LEFT))
			{
				player.setMoveDirection(new Vector3(-1,0,0));
			}
			if(Gdx.input.isKeyPressed(Keys.RIGHT))
			{
				player.setMoveDirection(new Vector3(1,0,0));
			}
		} else {
			player.setMoveDirection(new Vector3(0,0,0));
		}
		
	}
	
	/**
	 * Updates the game logic.
	 * 
	 * @param delta the time since the last update call.
	 */
	@Override
	public void update(float dt) {
		//always handle the input first
		handleInput();
		player.update(dt);
		//update the camera position relative to the player
		cam.position.y = (player.getPosition().y - Gdx.graphics.getBackBufferHeight() / 2);
		
		//TODO - create new platforms here
		
		
		//update the camera
		cam.update();
	}

	
	/**
	 * Packages everything into a Spritebatch and renders it to the screen
	 * 
	 * @param sb Spritebatch
	 */
	@Override
	public void render(SpriteBatch sb) {
		
		//start packaging
		sb.begin();
		//draw background in the middle of the screen
		sb.draw(bg, cam.position.x - cam.viewportWidth / 2, 0);
		//draw the player
		sb.draw(player.getTexture(), player.getPosition().x, player.getPosition().y);
		
		
		//TODO - create Map
		
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
				
					sb.draw(
					imgTile,
					screenPos.x,
					screenPos.y,
					TILE_WIDTH,
					TILE_HEIGHT);
				}
			}
		}
		
		sb.end();
	}
	

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
	}

}
