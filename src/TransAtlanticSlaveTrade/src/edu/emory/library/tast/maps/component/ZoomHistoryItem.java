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
package edu.emory.library.tast.maps.component;

import java.io.Serializable;

public class ZoomHistoryItem implements Serializable
{
	
	private static final long serialVersionUID = -435520343082690652L;
	
	private double x1;
	private double y1;
	private double x2;
	private double y2;
	
	public ZoomHistoryItem(double x1, double y1, double x2, double y2)
	{
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	}

	public double getX1()
	{
		return x1;
	}
	
	public void setX1(double x1)
	{
		this.x1 = x1;
	}
	
	public double getY1()
	{
		return y1;
	}
	
	public void setY1(double y1)
	{
		this.y1 = y1;
	}
	
	public double getX2()
	{
		return x2;
	}
	
	public void setX2(double x2)
	{
		this.x2 = x2;
	}

	public double getY2()
	{
		return y2;
	}

	public void setY2(double y2)
	{
		this.y2 = y2;
	}

}
