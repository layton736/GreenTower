package sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;

public class Player {

	//create Gravity on Player only
	private static final int GRAVITY = -15;
	private static final int MOVEMENT = 100;
	
	private Vector3 moveDirection;
	private Vector3 position;
	private Vector3 velocity;
	
	private Texture player;
	
	public Player(int x, int y){
		position = new Vector3(x, y, 0);
		velocity = new Vector3(0, 0, 0);
		player = new Texture("player.png");
	}
	
	public void update(float dt){
		if(position.y > 0){
			//add gravity by deltatime
			velocity.add(0, GRAVITY, 0);
		}
		
		velocity.scl(dt);
		
		
		
		//add velocity to the player position
		position.add(moveDirection.x * MOVEMENT * dt, velocity.y, 0);
		//don't fall out of the map
		if (position.y < 0){
			position.y = 0;
		}
		//reverse scl
		velocity.scl(1/dt);
	}

	public Vector3 getPosition() {
		return position;
	}

	public Texture getTexture() {
		return player;
	}
	
	public void jump(){
		velocity.y = 250;
	}
	
	public Vector3 getMoveDirection(){
		return this.moveDirection;
	}
	
	public void setMoveDirection(Vector3 dir){
		this.moveDirection = dir;
	}
	
}

