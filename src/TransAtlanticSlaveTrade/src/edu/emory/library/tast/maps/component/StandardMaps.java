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

import java.util.HashMap;
import java.util.Map;

import javax.faces.model.SelectItem;
import javax.faces.model.SelectItemGroup;

import edu.emory.library.tast.AppConfig;
import edu.emory.library.tast.estimates.map.EstimatesMapBean;
import edu.emory.library.tast.util.StringUtils;

public class StandardMaps
{
	
	public static class MapIdent
	{
		public String mapPath;
		public String mapLabel;
		public int yearFrom;
		public int yearTo;
		public ZoomLevel levels[];
		public String[] zoomLabels;
		
		private MapIdent(String path, String label, int yearFrom, int yearTo, ZoomLevel[] levels, String[] zoomLabels)
		{
			this.mapLabel = label;
			this.mapPath = path;
			this.yearFrom = yearFrom;
			this.yearTo = yearTo;
			this.levels = levels;
			this.zoomLabels = zoomLabels;
		}
	}
	
	public static class ChosenMap
	{
		public MapIdent ident;
		int mapIdentId;
		public int mapId;
		public String encodeMapId()
		{
			return "map-" + mapIdentId + "_" + mapId;
		}
	}
	
	private static String baseMapUrl = StringUtils.trimEnd(AppConfig.getConfiguration().getString(AppConfig.MAP_URL), '/');
	
	private static ZoomLevel levelMini = new ZoomLevel(400, 300, -110.83 - 122/0.95, -61.83 - 92/0.95, 1, 1, 1.0/0.95, baseMapUrl + "/tiles/minimap");
	
	private static ZoomLevel levelGeo20 = new ZoomLevel(160, 120, -110.79, -61.85, 5, 5, 1.0/4.69, baseMapUrl + "/tiles/20");

//	private static ZoomLevel levelGeoD3 = new ZoomLevel(160, 120, -110.83, -61.83, 32, 32, 1.0/30.0,	baseMapUrl + "/tiles/geo/regions/3"); 
//	private static ZoomLevel levelGeoD6 = new ZoomLevel(160, 120, -110.83, -61.83, 16, 16, 1.0/15.0,	baseMapUrl + "/tiles/geo/regions/6");
//	private static ZoomLevel levelGeoD1 = new ZoomLevel(160, 120, -110.85, -61.82, 64, 64, 1.0/60.0,	baseMapUrl + "/tiles/geo/ports/1");
	
	private static ZoomLevel level1650D3 = new ZoomLevel(160, 120, -110.83, -61.83, 32, 32, 1.0/30.0,	baseMapUrl + "/tiles/1650/regions/3"); 
	private static ZoomLevel level1650D6 = new ZoomLevel(160, 120, -110.83, -61.83, 16, 16, 1.0/15.0,	baseMapUrl + "/tiles/1650/regions/6");
	private static ZoomLevel level1650D1 = new ZoomLevel(160, 120, -110.85, -61.82, 64, 64, 1.0/60.0,	baseMapUrl + "/tiles/1650/ports/1"); 
	
	private static ZoomLevel level1750D3 = new ZoomLevel(160, 120, -110.83, -61.83, 32, 32, 1.0/30.0,	baseMapUrl + "/tiles/1750/regions/3");
	private static ZoomLevel level1750D6 = new ZoomLevel(160, 120, -110.83, -61.83, 16, 16, 1.0/15.0,	baseMapUrl + "/tiles/1750/regions/6");
	private static ZoomLevel level1750D1 = new ZoomLevel(160, 120, -110.85, -61.82, 64, 64, 1.0/60.0,	baseMapUrl + "/tiles/1750/ports/1");;
	
	private static ZoomLevel level1850D3 = new ZoomLevel(160, 120, -110.83, -61.83, 32, 32, 1.0/30.0,	baseMapUrl + "/tiles/1850/regions/3"); 
	private static ZoomLevel level1850D6 = new ZoomLevel(160, 120, -110.83, -61.83, 16, 16, 1.0/15.0,	baseMapUrl + "/tiles/1850/regions/6");
	private static ZoomLevel level1850D1 = new ZoomLevel(160, 120, -110.85, -61.82, 64, 64, 1.0/60.0,	baseMapUrl + "/tiles/1850/ports/1");;
	
//	private static ZoomLevel levelGeoE6 = new ZoomLevel(160, 120, -110.83, -61.83, 16, 16, 1.0/15.0,	baseMapUrl + "/tiles/estimates/geo/regions/6");
//	private static ZoomLevel levelGeoE3 = new ZoomLevel(160, 120, -110.83, -61.83, 32, 32, 1.0/30.0,	baseMapUrl + "/tiles/estimates/geo/regions/3"); 
//	private static ZoomLevel levelGeoE1 = new ZoomLevel(160, 120, -110.85, -61.82, 64, 64, 1.0/60.0,	baseMapUrl + "/tiles/estimates/geo/ports/1");
	
	private static ZoomLevel level1650E6 = new ZoomLevel(160, 120, -110.83, -61.83, 16, 16, 1.0/15.0,	baseMapUrl + "/tiles/estimates/1650/regions/6");
	private static ZoomLevel level1650E3 = new ZoomLevel(160, 120, -110.83, -61.83, 32, 32, 1.0/30.0,	baseMapUrl + "/tiles/estimates/1650/regions/3"); 
//	private static ZoomLevel level1650E1 = new ZoomLevel(160, 120, -110.85, -61.82, 64, 64, 1.0/60.0,	baseMapUrl + "/tiles/estimates/1650/ports/1"); 
	
	private static ZoomLevel level1750E6 = new ZoomLevel(160, 120, -110.83, -61.83, 16, 16, 1.0/15.0,	baseMapUrl + "/tiles/estimates/1750/regions/6");
	private static ZoomLevel level1750E3 = new ZoomLevel(160, 120, -110.83, -61.83, 32, 32, 1.0/30.0,	baseMapUrl + "/tiles/estimates/1750/regions/3");
//	private static ZoomLevel level1750E1 = new ZoomLevel(160, 120, -110.85, -61.82, 64, 64, 1.0/60.0,	baseMapUrl + "/tiles/estimates/1750/ports/1");;
	
	private static ZoomLevel level1850E6 = new ZoomLevel(160, 120, -110.83, -61.83, 16, 16, 1.0/15.0,	baseMapUrl + "/tiles/estimates/1850/regions/6");
	private static ZoomLevel level1850E3 = new ZoomLevel(160, 120, -110.83, -61.83, 32, 32, 1.0/30.0,	baseMapUrl + "/tiles/estimates/1850/regions/3"); 
//	private static ZoomLevel level1850E1 = new ZoomLevel(160, 120, -110.85, -61.82, 64, 64, 1.0/60.0,	baseMapUrl + "/tiles/estimates/1850/ports/1");;
	
	private static final MapIdent[] mapsEstimates = new MapIdent[] {
		new MapIdent(
				"geophysical", "All periods (1514-1866)", 1501, 1867,
				new ZoomLevel[] {levelGeo20, level1750E6, level1750E3},
				new String[] {"Broad regions (all periods)", "Regions (all periods)"}),
		new MapIdent(
				"h_1650",
				"1650 (1514-1641)",
				1501, 1641,
				new ZoomLevel[] {levelGeo20, level1650E6, level1650E3},
				new String[] {"Broad regions (1650)", "Regions (1650)"}),
		new MapIdent(
				"h_1750", "1750 (1642-1807)", 1642, 1807,
				new ZoomLevel[] {levelGeo20, level1750E6, level1750E3},
				new String[] {"Broad regions (1750)", "Regions (1750)"}),
		new MapIdent(
				"h_1850", "1850 (1808-1866)", 1808, 1867,
				new ZoomLevel[] {levelGeo20, level1850E6, level1850E3},
				new String[] { "Broad regions (1850)", "Regions (1850)"})		
	};
	
	private static final MapIdent[] mapsDatabase = new MapIdent[] {

		new MapIdent(
				"geophysical", "All periods (1514-1866)", 1501, 1867,
				new ZoomLevel[] {levelGeo20, level1750D6, level1750D3, level1750D1},
				new String[] {"Broad regions (all periods)", "Regions (all periods)", "Ports (all periods)"}),

		new MapIdent(
				"h_1650", "1650 (1514-1641)", 1501, 1641,
				new ZoomLevel[] {levelGeo20, level1650D6, level1650D3, level1650D1},
				new String[] {"Broad regions (1650)", "Regions (1650)", "Ports (1650)"}),
		
		new MapIdent(
				"h_1750", "1750 (1642-1807)", 1642, 1807,
				new ZoomLevel[] {levelGeo20, level1750D6, level1750D3, level1750D1},
				new String[] {"Broad regions (1750)", "Regions (1750)", "Ports (1750)"}),
		
		new MapIdent(
				"h_1850", "1850 (1808-1866)", 1808, 1867,
				new ZoomLevel[] {levelGeo20, level1850D6, level1850D3, level1850D1},
				new String[] {"Broad regions (1850)", "Regions (1850)", "Ports (1850)"})
	};
	
	private static Map chosenMaps = new HashMap();
	
	public static ZoomLevel[] getZoomLevels(Object key)
	{			
		MapIdent chosenMap = getSelectedMap(key).ident;
		return chosenMap.levels;
	}
	
	public static ZoomLevel getMiniMapZoomLevel(Object key)
	{
		return levelMini;
	}
	
	public static SelectItem[] getMapTypes(Object key) {
		MapIdent[] maps = null;
		if (key instanceof EstimatesMapBean)
		{
			maps = mapsEstimates;
		}
		else
		{
			maps = mapsDatabase;
		}

		SelectItem[] items = new SelectItem[maps.length];
		for (int i = 0; i < items.length; i++)
		{
			SelectItem[] subItems = new SelectItem[maps[i].zoomLabels.length];
			for (int j = 0; j < subItems.length; j++)
			{
				subItems[j] = new SelectItem("map-" + i + "_" + j, maps[i].zoomLabels[j]);
			}
			items[i] = new SelectItemGroup(maps[i].mapLabel, null, true, subItems);
		}
		return items;
	}
	
	public static void setSelectedMapType(Object key, String selectedMap) {
		MapIdent[] maps = null;
		if (key instanceof EstimatesMapBean)
		{
			maps = mapsEstimates;
		}
		else
		{
			maps = mapsDatabase;
		}

		String mapIdentIdStr = selectedMap.substring(selectedMap.indexOf('-') + 1, selectedMap.indexOf('_'));
		String mapIdStr = selectedMap.substring(selectedMap.indexOf('_') + 1);

		int mapIdentId = Integer.parseInt(mapIdentIdStr);
		int mapId = Integer.parseInt(mapIdStr);

		ChosenMap map = new ChosenMap();
		map.mapId = mapId;
		map.ident = maps[mapIdentId];
		map.mapIdentId = mapIdentId;
		chosenMaps.put(key, map);
	}
	
	public static ChosenMap getSelectedMap(Object key) {
		MapIdent[] maps = null;
		if (key instanceof EstimatesMapBean)
		{
			maps = mapsEstimates;
		}
		else
		{
			maps = mapsDatabase;
		}

		if (!chosenMaps.containsKey(key))
		{
			ChosenMap map = new ChosenMap();
			map.ident = maps[0];
			map.mapIdentId = 0;
			map.mapId = 0;
			chosenMaps.put(key, map);
		}
		return (ChosenMap) chosenMaps.get(key);
	}
	
	public static void zoomChanged(Object key, int zoom) {
		ChosenMap map = getSelectedMap(key);
		if (map.ident.zoomLabels.length > zoom)
		{
			map.mapId = zoom;
		}
		else
		{
			map.mapId = map.ident.zoomLabels.length - 1;
		}
	}

}
