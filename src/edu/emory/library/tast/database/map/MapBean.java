package edu.emory.library.tast.database.map;

import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;

import org.ajaxanywhere.AAUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;

import edu.emory.library.tast.TastResource;
import edu.emory.library.tast.database.map.mapimpl.GlobalMapDataTransformer;
import edu.emory.library.tast.database.map.mapimpl.GlobalMapQueryHolder;
import edu.emory.library.tast.database.query.SearchBean;
import edu.emory.library.tast.database.query.SearchParameters;
import edu.emory.library.tast.maps.LegendItemsGroup;
import edu.emory.library.tast.maps.MapData;
import edu.emory.library.tast.maps.component.PointOfInterest;
import edu.emory.library.tast.maps.component.StandardMaps;
import edu.emory.library.tast.maps.component.ZoomChangedEvent;
import edu.emory.library.tast.maps.component.ZoomLevel;
import edu.emory.library.tast.maps.component.StandardMaps.ChosenMap;
import edu.emory.library.tast.util.HibernateUtil;
import edu.emory.library.tast.util.query.Conditions;

/**
 * The bean provides support for map tab in the database part of the system. It is used
 * in the database/search-tab-map.jsp page. This bean is connected with SearchBean which provides
 * current query that should be used when retrieving data from database. Data retrieved from dababase
 * are mainly ports/regions with number of slaves embarked/disembarked. The data later is shown in
 * map.
 * The main functionalities of this bean include: 
 * 1) provides a path that can be used to obtain map or mini-map tail.  
 * 2) reacts to changes on map and refreshes map.
 * 
 * 
 */
public class MapBean {

	public static int PORT_DEPARTURE = 2;

	public static int PORT_ARRIVAL = 3;

	public static int PORT_BOTH = 5;

	/**
	 * Reference to Search bean.
	 */
	private SearchBean searchBean = null;

	/**
	 * Conditions used in query.
	 */
	private Conditions conditions = null;

	//indicates if requery is required
	private boolean neededQuery = false;

	//Information show on-mouse-over (when mouse is over given point)
	private List pointsOfInterest = new ArrayList();

	//Data that is in map
	private MapData mapData = new MapData();
	
	private int attributeId = 0;

	private int type = -1;

	private int zoomLevelId;

	private boolean zoomLevelLocked = false;

	/**
	 * Sets current map data - points on the map.
	 * The type of visible points depends on:
	 * 1. zoom level which has been chosen
	 * 2. selected ports (embarkation/disembarkation) 
	 */
	private void setMapData() {
		
		Session session = HibernateUtil.getSession();
		Transaction t = session.beginTransaction();		

		if (!this.searchBean.getSearchParameters().getConditions().equals(
				this.conditions)) {

			SearchParameters params = this.searchBean.getSearchParameters();
			this.conditions = (Conditions) params.getConditions().clone();
			neededQuery = true;
		}

		if (this.neededQuery) {

			Conditions conditions = (Conditions) this.conditions.clone();

			this.pointsOfInterest.clear();
			GlobalMapQueryHolder queryHolder = new GlobalMapQueryHolder(conditions);
			//queryHolder.executeQuery(session, this.chosenMap/* + this.chosenAttribute * ATTRS.length*/);
			
			queryHolder.executeQuery(session, attributeId, type);

			GlobalMapDataTransformer transformer = new GlobalMapDataTransformer(
					queryHolder.getAttributesMap());
			this.mapData.setMapData(queryHolder, transformer);
			
			this.neededQuery = false;
		}
		
		t.commit();
		session.close();

	}

	/**
	 * Checks whether embarkation.disembarkation or both port types are selected.
	 * @return
	 */
	private int determineType() {
		if (this.mapData.getLegend() == null || this.mapData.getLegend().length < 2) {
			return -1;
		}
		LegendItemsGroup legend = this.mapData.getLegend()[1];
		if (legend.getItems()[0].isEnabled() && legend.getItems()[1].isEnabled()) {
			return -1;
		} else if (legend.getItems()[0].isEnabled()) {
			return 0;
		} else {
			return 1;
		}
	}

	/**
	 * Refreshes any data in map. It queries the database if needed.
	 * @return
	 */
	public String refresh() {
		type = determineType();
		neededQuery = true;
		this.setMapData();
		return null;
	}

	/**
	 * Returns list of points of interests. Point of interest shows some description on-mouse-over event.
	 * @return
	 */
	public PointOfInterest[] getPointsOfInterest() {
		setMapData();
		return this.mapData.getToolTip();
	}

	/**
	 * Gets legend of current map.
	 * @return
	 */
	public LegendItemsGroup[] getLegend() {
		return this.mapData.getLegend();
	}

	/**
	 * Gets search bean instance for current application.
	 * @return
	 */
	public SearchBean getSearchBean() {
		return searchBean;
	}

	/**
	 * Invoked by JSF - sets search bean instance for current application context
	 * @param searchBean
	 */
	public void setSearchBean(SearchBean searchBean) {
		this.searchBean = searchBean;
	}

	/**
	 * Sets chosen map (regions/ports)
	 * @param value
	 */
	public void setChosenMap(String value) {
		if (!value.equals(StandardMaps.getSelectedMap(this).encodeMapId())) {
			this.neededQuery = true;
			StandardMaps.setSelectedMapType(this, value);
			ChosenMap map = StandardMaps.getSelectedMap(this);
			this.zoomLevelId = map.mapId;
			this.searchBean.setYearFrom(map.ident.yearFrom);
			this.searchBean.setYearTo(map.ident.yearTo);
			this.searchBean.lockYears(true);
			zoomLevelLocked = true;
			StandardMaps.setSelectedMapType(this, value);
			this.searchBean.search();
		}
	}

	/**
	 * Chosen map
	 * @return
	 */
	public String getChosenMap() {
		this.searchBean.lockYears(false);
		return StandardMaps.getSelectedMap(this).encodeMapId();
	}

	/**
	 * Map options (geophysical maps, 1650, 1750...)
	 * @return
	 */
	public SelectItem[] getAvailableMaps() {
		return StandardMaps.getMapTypes(this);
	}
	
	/**
	 * Configuration for zoom levels on the map.
	 * @return
	 */
	public ZoomLevel[] getZoomLevels() {
		setMapData();
		return StandardMaps.getZoomLevels(this);
	}
	
	/**
	 * Mini map zoom level
	 * @return
	 */
	public ZoomLevel getMiniMapZoomLevel() {
		setMapData();
		return StandardMaps.getMiniMapZoomLevel(this);
	}

	/**
	 * Zoom level on the map
	 * @return
	 */
	public int getZoomLevel() {
		zoomLevelLocked  = false;
		return zoomLevelId;
	}

	/**
	 * Registers change of zoom level.
	 * Ugly hack to handle feedback between zoom level and visible places (broad regions/regions/ports)
	 * @param zoomLevelId
	 */
	public void setZoomLevel(int zoomLevelId) {
		if (zoomLevelLocked) {
			return;
		}
		if (this.zoomLevelId != zoomLevelId) {
			StandardMaps.zoomChanged(this, zoomLevelId);
			this.neededQuery = true;
		}
		this.zoomLevelId = zoomLevelId;
	}
	
	public SelectItem[] getAvailableAttributes() {
		return new SelectItem[] {
				new SelectItem("0", TastResource.getText("database_components_map_broadregions")),
				new SelectItem("1", TastResource.getText("database_components_map_regions")),
				new SelectItem("2", TastResource.getText("database_components_map_ports")),
		};
	}
	
	public Integer getChosenAttribute() {
		return new Integer(attributeId);
	}
	
	public void setChosenAttribute(Integer id) {
		if (this.attributeId != id.intValue()) {
			StandardMaps.zoomChanged(this, id.intValue());
			this.neededQuery = true;
		}
		attributeId = id.intValue();
	}
	
	public void onZoomChanged(ZoomChangedEvent e) {
		System.out.println("Zoom was changed!");
		HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest(); 
		if (AAUtils.isAjaxRequest(request)) {
			AAUtils.addZonesToRefresh(request, "map-legend");
		}
	}
}
