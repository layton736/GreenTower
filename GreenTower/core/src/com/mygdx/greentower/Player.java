package com.mygdx.greentower;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Player {

	private Rectangle posRect;
	private Vector2 moveVector;
	
	
	
	public Vector2 getMoveVector() {
		return moveVector;
	}

	public void setMoveVector(Vector2 moveVector) {
		this.moveVector = moveVector;
	}

	public Rectangle getPosRect() {
		return posRect;
	}

	public void setPosRect(Rectangle posRect) {
		this.posRect = posRect;
	}
	
	
}
