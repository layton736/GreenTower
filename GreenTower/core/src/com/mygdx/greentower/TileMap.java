package com.mygdx.greentower;

import java.util.LinkedList;
import java.util.List;

public class TileMap {

	private int width,
				height;
	
	private List<MapTile[]> rows;
	
	
	

	public TileMap(int width, int height)
	{
		this.width = width;
		this.height = height;
		
		rows = new LinkedList<MapTile[]>();
	}
	
	public MapTile[] getRow(int y)
	{
		return rows.get(y);
	}
	
	public List<MapTile[]> getRows() {
		return rows;
	}
	
	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

}
