package edu.emory.library.tast.maps.component;

import java.util.HashMap;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.faces.model.SelectItemGroup;

import edu.emory.library.tast.estimates.map.EstimatesMapBean;

public class StandardMaps
{
	
	public static class MapIdent {
		public String mapPath;
		public String mapLabel;
		public int yearFrom;
		public int yearTo;
		public ZoomLevel levels[];
		public String[] zoomLabels;
		
		private MapIdent(String path, String label, int yearFrom, int yearTo, ZoomLevel[] levels, String[] zoomLabels) {
			this.mapLabel = label;
			this.mapPath = path;
			this.yearFrom = yearFrom;
			this.yearTo = yearTo;
			this.levels = levels;
			this.zoomLabels = zoomLabels;
		}
	}
	
	public static class ChosenMap {
		public MapIdent ident;
		int mapIdentId;
		public int mapId;
		
		public String encodeMapId() {
			return "map-" + mapIdentId + "_" + mapId;
		}
	}
	
	private static String contextPath = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
	private static ZoomLevel levelMini = new ZoomLevel(160, 120, -110.83, -61.83, 1, 1, 1.0/0.95, contextPath + "/map-assets/tiles/minimap");
	private static ZoomLevel levelGeo20 = new ZoomLevel(160, 120, -110.79, -61.85, 5, 5, 1.0/4.69, contextPath + "/map-assets/tiles/20");
	private static ZoomLevel levelGeo3 = new ZoomLevel(160, 120, -110.83, -61.83, 32, 32, 1.0/30.0,	contextPath + "/map-assets/tiles/geo/regions/3"); 
	private static ZoomLevel levelGeo3_1 = new ZoomLevel(160, 120, -110.83, -61.83, 64, 64, 1.0/60.0,	contextPath + "/map-assets/tiles/geo/regions/1");
	private static ZoomLevel levelGeo1 = new ZoomLevel(160, 120, -110.85, -61.82, 64, 64, 1.0/60.0,	contextPath + "/map-assets/tiles/geo/ports/1");
	private static ZoomLevel level1650_3 = new ZoomLevel(160, 120, -110.83, -61.83, 32, 32, 1.0/30.0,	contextPath + "/map-assets/tiles/1650/regions/3"); 
	private static ZoomLevel level1650_3_1 = new ZoomLevel(160, 120, -110.83, -61.83, 64, 64, 1.0/60.0,	contextPath + "/map-assets/tiles/1650/regions/1");
	private static ZoomLevel level1650_1 = new ZoomLevel(160, 120, -110.85, -61.82, 64, 64, 1.0/60.0,	contextPath + "/map-assets/tiles/1650/ports/1"); 
	private static ZoomLevel level1750_3 = new ZoomLevel(160, 120, -110.83, -61.83, 32, 32, 1.0/30.0,	contextPath + "/map-assets/tiles/1750/regions/3");
	private static ZoomLevel level1750_3_1 = new ZoomLevel(160, 120, -110.83, -61.83, 64, 64, 1.0/60.0,	contextPath + "/map-assets/tiles/1750/regions/1");
	private static ZoomLevel level1750_1 = new ZoomLevel(160, 120, -110.85, -61.82, 64, 64, 1.0/60.0,	contextPath + "/map-assets/tiles/1750/ports/1");;
	private static ZoomLevel level1850_3 = new ZoomLevel(160, 120, -110.83, -61.83, 32, 32, 1.0/30.0,	contextPath + "/map-assets/tiles/1850/regions/3"); 
	private static ZoomLevel level1850_3_1 = new ZoomLevel(160, 120, -110.83, -61.83, 64, 64, 1.0/60.0,	contextPath + "/map-assets/tiles/1850/regions/1");
	private static ZoomLevel level1850_1 = new ZoomLevel(160, 120, -110.85, -61.82, 64, 64, 1.0/60.0,	contextPath + "/map-assets/tiles/1850/ports/1");;
	
	private static final MapIdent[] mapsEstimates = new MapIdent[] {
		new MapIdent("geophysical", "Geophysical maps", 1501, 1867, new ZoomLevel[] {levelGeo20, levelGeo3, levelGeo3_1}, new String[] {"Geophysical map, broad regions", "Geophysical map, regions"}),
		new MapIdent("h_1650", "Historical maps: 1650 (1501-1641)", 1501, 1641, new ZoomLevel[] {levelGeo20, level1650_3, level1650_3_1}, new String[] {"Historical map (1650), broad regions", "Historical map (1650), regions"}),
		new MapIdent("h_1750", "Historical maps: 1750 (1642-1807)", 1642, 1807, new ZoomLevel[] {levelGeo20, level1750_3, level1750_3_1}, new String[] {"Historical map (1750), broad regions", "Historical map (1750), regions"}),
		new MapIdent("h_1850", "Historical maps: 1850 (1808-1867)", 1808, 1867, new ZoomLevel[] {levelGeo20, level1850_3, level1850_3_1}, new String[] {"Historical map (1850), broad regions", "Historical map (1850), regions"})		
	};
	
	private static final MapIdent[] mapsDatabase = new MapIdent[] {
		new MapIdent("geophysical", "Geophysical maps", 1501, 1867, new ZoomLevel[] {levelGeo20, levelGeo3, levelGeo1}, new String[] {"Geophysical map, brad regions", "Geophysical map, regions", "Geophysical map, ports"}),
		new MapIdent("h_1650", "Historical maps: 1650 (1501-1641)", 1501, 1641, new ZoomLevel[] {levelGeo20, level1650_3, level1650_1}, new String[] {"Historical map (1650), broad regions", "Historical map (1650), regions", "Historical map (1650), ports"}),
		new MapIdent("h_1750", "Historical maps: 1750 (1642-1807)", 1642, 1807, new ZoomLevel[] {levelGeo20, level1750_3, level1750_1}, new String[] {"Historical map (1750), broad regions", "Historical map (1750), regions", "Historical map (1750), ports"}),
		new MapIdent("h_1850", "Historical maps: 1850 (1808-1867)", 1808, 1867, new ZoomLevel[] {levelGeo20, level1850_3, level1850_1}, new String[] {"Historical map (1850), broad regions", "Historical map (1850), regions", "Historical map (1850), ports"})
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
		if (key instanceof EstimatesMapBean) {
			maps = mapsEstimates;
		} else {
			maps = mapsDatabase;
		}
		
		SelectItem[] items = new SelectItem[maps.length];
		for (int i = 0; i < items.length; i++) {
			SelectItem[] subItems = new SelectItem[maps[i].zoomLabels.length];
			for (int j = 0; j < subItems.length; j++) {
				subItems[j] = new SelectItem("map-" + i + "_" + j, maps[i].zoomLabels[j]);
			}
			items[i] = new SelectItemGroup(maps[i].mapLabel, null, true, subItems);
		}
		return items;
	}
	
	public static void setSelectedMapType(Object key, String selectedMap) {
		MapIdent[] maps = null;
		if (key instanceof EstimatesMapBean) {
			maps = mapsEstimates;
		} else {
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
		if (key instanceof EstimatesMapBean) {
			maps = mapsEstimates;
		} else {
			maps = mapsDatabase;
		}
		
		if (!chosenMaps.containsKey(key)) {
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
		if (map.ident.zoomLabels.length > zoom) {
			map.mapId = zoom;
		} else {
			map.mapId = map.ident.zoomLabels.length - 1;
		}
	}

}
