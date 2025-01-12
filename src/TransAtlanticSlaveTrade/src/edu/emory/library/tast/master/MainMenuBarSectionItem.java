/*
Copyright 2010 Emory University
	
	    This file is part of Trans-Atlantic Slave Voyages.
	
	    Trans-Atlantic Slave Voyages is free software: you can redistribute it and/or modify
	    it under the terms of the GNU General Public License as published by
	    the Free Software Foundation, either version 3 of the License, or
	    (at your option) any later version.
	
	    Trans-Atlantic Slave Voyages is distributed in the hope that it will be useful,
	    but WITHOUT ANY WARRANTY; without even the implied warranty of
	    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
	    GNU General Public License for more details.
	
	    You should have received a copy of the GNU General Public License
	    along with Trans-Atlantic Slave Voyages.  If not, see <http://www.gnu.org/licenses/>. 
*/
package edu.emory.library.tast.master;

public class MainMenuBarSectionItem
{
	
	private String id;
	private String userLabel;
	private String imageUrlNormal;
	private String imageUrlHighlighted;
	private String imageUrlActive;
	private int imageWidth;
	private int imageHeight;
	private String url;
	private MainMenuBarPageItem[] subItems;
	private String boxCssClass;
	
	public MainMenuBarSectionItem(String id, String userLabel, String url, String imageUrlNormal, String imageUrlHighlighted, String imageUrlActive, int imageWidth, int imageHeight, String boxCssClass, MainMenuBarPageItem[] subItems)
	{
		this.id = id;
		this.userLabel = userLabel;
		this.url = url;
		this.imageUrlNormal = imageUrlNormal;
		this.imageUrlHighlighted = imageUrlHighlighted;
		this.imageUrlActive = imageUrlActive;
		this.imageWidth = imageWidth;
		this.imageHeight = imageHeight;
		this.boxCssClass = boxCssClass;
		this.subItems = subItems;
	}

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getImageUrlActive()
	{
		return imageUrlActive;
	}

	public void setImageUrlActive(String imageActive)
	{
		this.imageUrlActive = imageActive;
	}
	
	public String getImageUrlNormal()
	{
		return imageUrlNormal;
	}
	
	public void setImageUrlNormal(String imageNormal)
	{
		this.imageUrlNormal = imageNormal;
	}
	
	public int getImageHeight()
	{
		return imageHeight;
	}

	public void setImageHeight(int imageHeight)
	{
		this.imageHeight = imageHeight;
	}

	public int getImageWidth()
	{
		return imageWidth;
	}

	public void setImageWidth(int imageWidth)
	{
		this.imageWidth = imageWidth;
	}

	public MainMenuBarPageItem[] getSubItems()
	{
		return subItems;
	}
	
	public int getSubItemsCount()
	{
		return subItems == null ? 0 : subItems.length;
	}

	public void setSubItems(MainMenuBarPageItem[] subItems)
	{
		this.subItems = subItems;
	}

	public String getUrl()
	{
		return url;
	}

	public void setUrl(String url)
	{
		this.url = url;
	}

	public String getBoxCssClass()
	{
		return boxCssClass;
	}

	public void setBoxCssClass(String boxCssClass)
	{
		this.boxCssClass = boxCssClass;
	}

	public String getImageUrlHighlighted()
	{
		return imageUrlHighlighted;
	}

	public void setImageUrlHighlighted(String imageUrlHighlighted)
	{
		this.imageUrlHighlighted = imageUrlHighlighted;
	}

	public String getLabel()
	{
		return userLabel;
	}

	public void setUserLabel(String userLabel)
	{
		this.userLabel = userLabel;
	}

}
