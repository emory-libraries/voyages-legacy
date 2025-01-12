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
package edu.emory.library.tast.maps;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import edu.emory.library.tast.db.HibernateConn;
import edu.emory.library.tast.maps.component.PointOfInterest;

/**
 * Data of map.
 * The class keeps map items shown on the map.
 * It also keeps legend of map and tooltips.
 *  
 * @author Pawel Jurczyk
 *
 */
public class MapData {
	
	/**
	 * Items on map.
	 */
	private AbstractMapItem[] items = new AbstractMapItem[] {};
	
	/**
	 * Tooltips.
	 */
	private PointOfInterest[] points = new PointOfInterest[] {};
	
	/**
	 * Legend - keept as legend groups.
	 */
	private LegendItemsGroup[] legendItems = new LegendItemsGroup[] {};
	private LegendItemsGroup[] oldLegend;
	
	/**
	 * Constructs empty map data object.
	 *
	 */
	public MapData() {
	}
	
	/**
	 * Sets map data.
	 * This function performs translation from DB response to map items by calling appropriate data
	 * transformer.
	 * @param queryResult DB result
	 * @param min minimal value in data
	 * @param max max value in data
	 * @param transformer data  trasformer implementation that should be used
	 */
	public void setMapData(AbstractTransformerQueryHolder data, AbstractDataTransformer transformer)
	{
		
		Session session = HibernateConn.getSession();
		Transaction t = session.beginTransaction();
		
		//Transform data
		TransformerResponse response = transformer.transformData(session, data);
		
		//Set items
		this.items = response.getItems();
		
		//Set legend
		this.oldLegend = legendItems;
		this.legendItems = response.getLegendItems();
		if (this.oldLegend != null && this.legendItems != null && this.legendItems.length == this.oldLegend.length)
		for (int i = 0; i < oldLegend.length; i++)
		{
			LegendItem[] oldLegendItems = oldLegend[i].getItems();
			LegendItem[] newLegendItems = legendItems[i].getItems();
			if (oldLegendItems != null && newLegendItems != null && newLegendItems.length == oldLegendItems.length)
			{
				for (int j = 0; j < oldLegend[i].getItems().length; j++)
				{
					newLegendItems[j].setEnabled(oldLegendItems[j].isEnabled());
				}
			}
		}
		
		t.commit();
		session.close();
		
	}

	/**
	 * Gets items that will appear on map.
	 * @return
	 */
	public AbstractMapItem[] getItems() {
		return items;
	}
	
	/**
	 * Gets tooltips that will appear on map.
	 * @return
	 */
	public PointOfInterest[] getPointsOfInterest() {
		
		//Prepare tooltips
		List localPoints = new ArrayList();
		for (int i = 0; i < this.items.length; i++)
		{
			if (this.items[i].isPointEnabled())
			{
				PointOfInterest pt = this.items[i].getTooltipText(legendItems);
				if (pt != null) localPoints.add(pt);
			}
		}			
		this.points = (PointOfInterest[])localPoints.toArray(new PointOfInterest[] {});
		return points;
	}

	/**
	 * Gets legend of map.
	 * @return
	 */
	public LegendItemsGroup[] getLegend()
	{
		return this.legendItems;
	}
	
}
