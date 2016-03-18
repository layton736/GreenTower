package com.mygdx.greentower;

public class TileMap {

	private int width,
				height;
	
	private MapTile[][] tiles;
	
	
	

	public TileMap(int width, int height)
	{
		this.width = width;
		this.height = height;
		
		tiles = new MapTile[height][width];
	}
	
	public MapTile[] getRow(int y)
	{
		return tiles[y];
	}
	
	
	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

}
