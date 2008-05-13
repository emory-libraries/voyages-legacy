package edu.emory.library.tast.estimates.map;

import javax.faces.model.SelectItem;

import org.hibernate.Session;
import org.hibernate.Transaction;

import edu.emory.library.tast.TastResource;
import edu.emory.library.tast.estimates.map.mapimpl.EstimateMapDataTransformer;
import edu.emory.library.tast.estimates.map.mapimpl.EstimateMapQueryHolder;
import edu.emory.library.tast.estimates.selection.EstimatesSelectionBean;
import edu.emory.library.tast.maps.LegendItemsGroup;
import edu.emory.library.tast.maps.MapData;
import edu.emory.library.tast.maps.component.PointOfInterest;
import edu.emory.library.tast.maps.component.StandardMaps;
import edu.emory.library.tast.maps.component.ZoomLevel;
import edu.emory.library.tast.maps.component.StandardMaps.ChosenMap;
import edu.emory.library.tast.util.HibernateUtil;
import edu.emory.library.tast.util.query.TastDbConditions;

/**
 * Backing bean for map tab in estimates.
 * The bean provides paths for map/minimap.
 * It also provides legend / points of interests.
 *
 */
public class EstimatesMapBean {

	/**
	 * Reference to estimates bean
	 */
	private EstimatesSelectionBean estimatesBean;

	/**
	 * Map data.
	 */
	private MapData mapData = new MapData();

	/**
	 * Current conditions
	 */
	private TastDbConditions conditions;
	
	/**
	 * Type of point of interest (ports/regions/broad regions);
	 */
	private int poiType;
	
	private int zoomLevel = 0;

	/**
	 * Forces query when setData called
	 */
	private boolean needRefresh = false;
	
	/**
	 * Type of visible points (emb/disembarkation)
	 */
	private int type = -1;

	public EstimatesSelectionBean getEstimatesBean() {
		return estimatesBean;
	}

	public void setEstimatesBean(EstimatesSelectionBean estimatesBean) {
		this.estimatesBean = estimatesBean;
	}

	public PointOfInterest[] getPointsOfInterest() {
		this.setData();
		return this.mapData.getPointsOfInterest();
	}

	/**
	 * Queries the database and sets currently visible points on the map
	 */
	private void setData()
	{
		
		Session session = HibernateUtil.getSession();
		Transaction t = session.beginTransaction();
		
		//Check whether query is required
		if (!this.getEstimatesBean().getConditions().equals(this.conditions) || needRefresh)
		{
			this.conditions = this.getEstimatesBean().getConditions();
			needRefresh = false;
			EstimateMapQueryHolder queryHolder = new EstimateMapQueryHolder(conditions);
			queryHolder.executeQuery(session, poiType, type);
			EstimateMapDataTransformer transformer = new EstimateMapDataTransformer(queryHolder.getAttributesMap());
			this.mapData.setMapData(queryHolder, transformer);
		}
		
		t.commit();
		session.close();
		
	}

	public LegendItemsGroup[] getLegend()
	{
		return this.mapData.getLegend();
	}
	
	/**
	 * When Refresh button clicked.
	 * @return
	 */
	public String refresh()
	{
		
		ChosenMap map = StandardMaps.getSelectedMap(this);
		this.estimatesBean.setYearFrom(String.valueOf(map.ident.yearFrom));
		this.estimatesBean.setYearTo(String.valueOf(map.ident.yearTo));
		this.estimatesBean.changeSelection();
		
		type = determineType();
		needRefresh = true;
		this.setData();
		
		return null;
		
	}
	
	/**
	 * Finds type of visible ports
	 * @return
	 */
	private int determineType()
	{
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
	 * Returns information of zoom levels for map
	 * @return
	 */
	public ZoomLevel[] getZoomLevels()
	{
		return StandardMaps.getZoomLevels(this);
	}
	
	/**
	 * Returns information on zoom level for minimap
	 * @return
	 */
	public ZoomLevel getMiniMapZoomLevel()
	{
		return StandardMaps.getMiniMapZoomLevel(this);
	}
	
	/**
	 * Sets active map.
	 * @param value
	 */
	public void setChosenMap(String value)
	{
		if (!StandardMaps.getSelectedMap(this).encodeMapId().equals(value))
		{
			needRefresh = true;
			StandardMaps.setSelectedMapType(this, value);
			ChosenMap map = StandardMaps.getSelectedMap(this);
			poiType = map.mapId;
			// this.zoomLevel = map.mapId;
		}
	}

	/**
	 * Returns id of chosen map
	 * @return
	 */
	public String getChosenMap()
	{
		return StandardMaps.getSelectedMap(this).encodeMapId();
	}

	/**
	 * Gets list of available maps.
	 * @return
	 */
	public SelectItem[] getAvailableMaps()
	{
		return StandardMaps.getMapTypes(this);
	}

	public int getZoomLevel() {
		return zoomLevel;
	}

	public void setZoomLevel(int zoomLevel)
	{
//		if (this.zoomLevel != zoomLevel)
//		{
//			forceQuery = true;
//			StandardMaps.zoomChanged(this, zoomLevel);
//		}
		this.zoomLevel = zoomLevel;
	}
	
	/**
	 * Gets list of available place types
	 */
	public SelectItem[] getAvailableAttributes()
	{
		return new SelectItem[] {
				new SelectItem("0", TastResource.getText("estimates_components_map_broadregions")),
				new SelectItem("1", TastResource.getText("estimates_components_map_regions")),
		};
	}
	
//	public Integer getChosenAttribute()
//	{
//		return new Integer(poiType);
//	}
	
//	public void setChosenAttribute(Integer id)
//	{
//		if (this.poiType != id.intValue())
//		{
//			StandardMaps.zoomChanged(this, id.intValue());
//			this.needRefresh = true;
//		}
//		poiType = id.intValue();
//	}
	
}
	

