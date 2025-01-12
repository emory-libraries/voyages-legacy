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
package edu.emory.library.tast.estimates.table;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import edu.emory.library.tast.common.table.Grouper;
import edu.emory.library.tast.common.table.Label;
import edu.emory.library.tast.dm.Estimate;
import edu.emory.library.tast.dm.EstimatesImportRegion;
import edu.emory.library.tast.dm.Region;
import edu.emory.library.tast.dm.attributes.Attribute;
import edu.emory.library.tast.dm.attributes.specific.SequenceAttribute;

public class GrouperImportRegionsWithBreakdowns extends Grouper
{
	
	private Label[] labels;
	private List regions;
	private Map lookupTable;
	
	public GrouperImportRegionsWithBreakdowns(int resultIndex, boolean omitEmpty, List regions)
	{
		super(resultIndex, omitEmpty);
		this.regions = regions;
	}
	
	public Attribute getGroupingAttribute()
	{
		 return new SequenceAttribute (new Attribute[] {
					Estimate.getAttribute("impRegion"),
					Region.getAttribute("id")});
	}

	public Attribute[] addExtraAttributes(int index)
	{
		return new Attribute[] {};
	}
	
	public void initSlots(Object[] dataTable)
	{

		Set regionsIdsInTable = new HashSet();
		if (omitEmpty)
		{
			for (int i = 0; i < dataTable.length; i++)
			{
				Object regionId = ((Object[]) dataTable[i])[resultIndex];
				regionsIdsInTable.add(regionId);
			}
		}
		
		lookupTable = new HashMap();
		
		List areas = new ArrayList();
		List regionsInArea = new ArrayList();
		
		int i = 0;
		long lastAreaId = 0;
		Label areaLabel = null;
		EstimatesImportRegion prevRegion = null;
		
		for (Iterator iter = regions.iterator(); iter.hasNext();)
		{
			
			EstimatesImportRegion region = (EstimatesImportRegion) iter.next();
			long areaId = region.getArea().getId().longValue();
			Long regionId = region.getId();
			
			if (!omitEmpty || regionsIdsInTable.contains(regionId))
			{
				
				if (areaId != lastAreaId)
				{
					if (areaLabel != null)
					{
						if (prevRegion.getArea().getRegions().size() > 1)
						{
							Label[] regionsArray = new Label[regionsInArea.size()];
							regionsInArea.toArray(regionsArray);
							areaLabel.setBreakdown(regionsArray);
						}
					}
					areaLabel = new Label(region.getArea().getName());
					areas.add(areaLabel);
					regionsInArea.clear();
					lastAreaId = areaId;
				}
				
				regionsInArea.add(new Label(region.getName()));
				lookupTable.put(regionId, new Integer(i));
				i++;
				
				prevRegion = region;
				
			}

		}

		if (areaLabel != null)
		{
			if (prevRegion.getArea().getRegions().size() > 1)
			{
				Label[] regionsArray = new Label[regionsInArea.size()];
				regionsInArea.toArray(regionsArray);
				areaLabel.setBreakdown(regionsArray);
			}
		}

		labels = new Label[areas.size()];
		areas.toArray(labels);

	}

	public int lookupIndex(Object[] dataRow)
	{
		Object regionId = dataRow[resultIndex];
		return ((Integer) lookupTable.get(regionId)).intValue();
	}

	public int getLeafLabelsCount()
	{
		return lookupTable.size();
	}

	public int getBreakdownDepth()
	{
		return 2;
	}

	public Label[] getLabels()
	{
		return labels;
	}

}