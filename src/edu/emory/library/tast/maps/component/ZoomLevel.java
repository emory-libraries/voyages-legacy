package edu.emory.library.tast.maps.component;

import java.io.Serializable;

public class ZoomLevel implements Serializable
{
	
	private static final long serialVersionUID = 5541108478267879512L;

	private double bottomLeftTileX;
	private double bottomLeftTileY;
	private int tilesNumX;
	private int tilesNumY;
	private double scale;
	private String tilesDir;
	
	public ZoomLevel(double bottomLeftTileX, double bottomLeftTileY, int tilesNumX, int tilesNumY, double scale, String tilesDir)
	{
		this.bottomLeftTileX = bottomLeftTileX;
		this.bottomLeftTileY = bottomLeftTileY;
		this.tilesNumX = tilesNumX;
		this.tilesNumY = tilesNumY;
		this.scale = scale;
		this.tilesDir = tilesDir;
	}

	public double getBottomLeftTileX()
	{
		return bottomLeftTileX;
	}
	
	public void setBottomLeftTile(double bottomLeftTileX, double bottomLeftTileY)
	{
		this.bottomLeftTileX = bottomLeftTileX;
		this.bottomLeftTileY = bottomLeftTileY;
	}

	public void setBottomLeftTileX(double bottomLeftTileX)
	{
		this.bottomLeftTileX = bottomLeftTileX;
	}
	
	public double getBottomLeftTileY()
	{
		return bottomLeftTileY;
	}
	
	public void setBottomLeftTileY(double bottomLeftTileY)
	{
		this.bottomLeftTileY = bottomLeftTileY;
	}
	
	public double getScale()
	{
		return scale;
	}
	
	public void setScale(double scale)
	{
		this.scale = scale;
	}
	
	public String getTilesDir()
	{
		return tilesDir;
	}
	
	public void setTilesDir(String tilesDir)
	{
		this.tilesDir = tilesDir;
	}
	
	public void setTilesNum(int tilesNumX, int tilesNumY)
	{
		this.tilesNumX = tilesNumX;
		this.tilesNumY = tilesNumY;
	}

	public int getTilesNumX()
	{
		return tilesNumX;
	}
	
	public void setTilesNumX(int tilesNumX)
	{
		this.tilesNumX = tilesNumX;
	}
	
	public int getTilesNumY()
	{
		return tilesNumY;
	}
	
	public void setTilesNumY(int tilesNumY)
	{
		this.tilesNumY = tilesNumY;
	}

}