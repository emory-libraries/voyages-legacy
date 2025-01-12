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
package edu.emory.library.tast.database.timeline;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.model.SelectItem;

import org.hibernate.Session;
import org.hibernate.Transaction;

import edu.emory.library.tast.util.CSVUtils;
import edu.emory.library.tast.AppConfig;
import edu.emory.library.tast.TastResource;
import edu.emory.library.tast.common.EventLineEvent;
import edu.emory.library.tast.common.EventLineGraph;
import edu.emory.library.tast.common.EventLineLabel;
import edu.emory.library.tast.common.EventLineZoomLevel;
import edu.emory.library.tast.database.query.SearchBean;
import edu.emory.library.tast.db.HibernateConn;
import edu.emory.library.tast.db.TastDbConditions;
import edu.emory.library.tast.db.TastDbQuery;
import edu.emory.library.tast.dm.Resistance;
import edu.emory.library.tast.dm.Voyage;
import edu.emory.library.tast.dm.attributes.Attribute;
import edu.emory.library.tast.dm.attributes.specific.FunctionAttribute;
import edu.emory.library.tast.dm.attributes.specific.SequenceAttribute;

/**
 * Bean for time line.
 * The bean has a reference to search bean which provides current conditions that should be
 * used while database is queried. It is used in database/search-tab-graph.jsp.
 * Basically, this bean runs different queries on database (depending on statistic type
 * that is chosen on web iunterface) and generates yearly distribution of this data.
 * 
 * The implementation of time-line does not use JFreeChart package. It provides
 * data that is directly used by TimeLineComponent. For more details, please refer to
 * documentation of TimeLine component.
 *
 */
public class TimelineBean {

	/**
	 * List of voyage attributes.
	 */
	private List voyageAttributes;

	/**
	 * Chosen attribute name.
	 */
	private TimelineVariable chosenOption = null;

	/**
	 * Current search bean reference.
	 */
	private SearchBean searchBean;
	
	/**
	 * Conditions used in query last time.
	 */
	private TastDbConditions conditions = null;
	
	/**
	 * Need of query indication.
	 */
	private boolean needQuery = false;

	/**
	 * Attributes changed indication.
	 */
	private boolean attributesChanged = false;

	//Event line series
	private EventLineGraph graph;
	
	//viewport height
	private double viewportHeight;

	/**
	 * Avaialable voyage attributes.
	 */
	
	//Labels for certical axis 
	private EventLineLabel[] verticalLabels;

	//list of available statistics - filled in in constructor
	private ArrayList availableStats;

	/**
	 * Class that represents available statistics.
	 * Each statistic has a user label which represents string visible to user,
	 * sqlValue (sql formula that should be executed) and sqlWhere which defines rows for which 
	 * statistic can be computed. I also provide information about format that should be used by output values.
	 *
	 */
	private class TimelineVariable
	{

		public String userLabel;
		public Object sqlWhere = null;
		public String formatString;
		public Attribute attributeValue;
		public boolean isPercentage = false;
		
		public TimelineVariable(Attribute valueAttribute, String userLabel, String formatString)
		{
			this.attributeValue = valueAttribute;
			this.userLabel = userLabel;
			this.formatString = formatString;
		}

		public TimelineVariable(Attribute valueAttribute, String userLabel, String formatString, boolean isPercentage)
		{
			this.attributeValue = valueAttribute;
			this.userLabel = userLabel;
			this.formatString = formatString;
			this.isPercentage = isPercentage;
		}

	}
	
	public TimelineBean()
	{
		
		this.availableStats = new ArrayList();
		
		this.availableStats.add(new TimelineVariable(
				new FunctionAttribute("COUNT", new Attribute[] {Voyage.getAttribute("voyageid")}),
				TastResource.getText("components_timeline_stat_numofvoyages"),
				"{0,number,#,###,###}"));
		
		this.availableStats.add(new TimelineVariable(
				new FunctionAttribute("AVG", new Attribute[] {Voyage.getAttribute("tonnage")}),
				TastResource.getText("components_timeline_stat_averagetonnage"),
				"{0,number,#,###,##0.0}"));
		
		this.availableStats.add(new TimelineVariable(
				new FunctionAttribute("AVG", new Attribute[] {Voyage.getAttribute("tonmod")}),
				TastResource.getText("components_timeline_stat_averagetonnagestand"),
				"{0,number,#,###,##0.0}"));
		
		this.availableStats.add(new TimelineVariable(
				new FunctionAttribute("AVG", new Attribute[] {Voyage.getAttribute("guns")}),
				TastResource.getText("components_timeline_stat_averageguns"),
				"{0,number,#,###,###.0}"));
		
		this.availableStats.add(new TimelineVariable(
				new FunctionAttribute("AVG", new Attribute[] {new FunctionAttribute("coalesce_to_0_100", new SequenceAttribute(new Attribute[] {Voyage.getAttribute("resistance"), Resistance.getAttribute("id")}))}),
				TastResource.getText("components_timeline_stat_rateresistance"),
				"{0,number,#,###,##0.0}%",
				true));

		this.availableStats.add(new TimelineVariable(
				new FunctionAttribute("AVG", new Attribute[] {Voyage.getAttribute("voy1imp")}),
				TastResource.getText("components_timeline_stat_averagedurationfirst"),
				"{0,number,#,###,##0.0}"));
		
		this.availableStats.add(new TimelineVariable(
				new FunctionAttribute("AVG", new Attribute[] {Voyage.getAttribute("voy2imp")}),
				TastResource.getText("components_timeline_stat_averagedurationmiddle"),
				"{0,number,#,###,##0.0}"));
		
		this.availableStats.add(new TimelineVariable(
				new FunctionAttribute("AVG", new Attribute[] {Voyage.getAttribute("crew1")}),
				TastResource.getText("components_timeline_stat_averagecrew"),
				"{0,number,#,###,##0.0}"));
		
		this.availableStats.add(new TimelineVariable(
				new FunctionAttribute("AVG", new Attribute[] {Voyage.getAttribute("crew3")}),
				TastResource.getText("components_timeline_stat_averagecrewfirst"),
				"{0,number,#,###,##0.0}"));
		
		this.availableStats.add(new TimelineVariable(
				new FunctionAttribute("SUM", new Attribute[] {Voyage.getAttribute("crewdied")}),
				TastResource.getText("components_timeline_stat_crewdeaths"),
				"{0,number,#,###,##0.0}"));
		
		this.availableStats.add(new TimelineVariable(
				new FunctionAttribute("AVG", new Attribute[] {Voyage.getAttribute("crewdied")}),
				TastResource.getText("components_timeline_stat_averagecrewdeaths"),
				"{0,number,#,###,##0.0}"));
		
		this.availableStats.add(new TimelineVariable(
				new FunctionAttribute("SUM", new Attribute[] {Voyage.getAttribute("slintend")}),
				TastResource.getText("components_timeline_stat_intnumpurchases"),
				"{0,number,#,###,##0.0}"));
		
		this.availableStats.add(new TimelineVariable(
				new FunctionAttribute("AVG", new Attribute[] {Voyage.getAttribute("slintend")}),
				TastResource.getText("components_timeline_stat_averageintpurchases"),
				"{0,number,#,###,###.0}"));
		
		this.availableStats.add(new TimelineVariable(
				new FunctionAttribute("SUM", new Attribute[] {Voyage.getAttribute("slaximp")}),
				TastResource.getText("components_timeline_stat_totalnumcaptivesemb"),
				"{0,number,#,###,###}"));
		
		this.availableStats.add(new TimelineVariable(
				new FunctionAttribute("AVG", new Attribute[] {Voyage.getAttribute("slaximp")}),
				TastResource.getText("components_timeline_stat_averagenumcaptivesemb"),
				"{0,number,#,###,##0.0}"));
		
		this.availableStats.add(new TimelineVariable(
				new FunctionAttribute("SUM", new Attribute[] {Voyage.getAttribute("slamimp")}),
				TastResource.getText("components_timeline_stat_totalnumcaptivesdisemb"),
				"{0,number,#,###,###}"));
		
		this.availableStats.add(new TimelineVariable(
				new FunctionAttribute("AVG", new Attribute[] {Voyage.getAttribute("slamimp")}),
				TastResource.getText("components_timeline_stat_averagenumcaptivesdisemb"),
				"{0,number,#,###,##0.0}"));
		
		this.availableStats.add(new TimelineVariable(
				new FunctionAttribute("AVG", new Attribute[] {new FunctionAttribute("crop_to_0_100", new Attribute[] {Voyage.getAttribute("menrat7")})}),
				TastResource.getText("components_timeline_stat_percentmen"),
				"{0,number,#,###,##0.0}%",
				true));

		this.availableStats.add(new TimelineVariable(
				new FunctionAttribute("AVG", new Attribute[] {new FunctionAttribute("crop_to_0_100", new Attribute[] {Voyage.getAttribute("womrat7")})}),
				TastResource.getText("components_timeline_stat_percentwomen"),
				"{0,number,#,###,##0.0}%",
				true));

		this.availableStats.add(new TimelineVariable(
				new FunctionAttribute("AVG", new Attribute[] {new FunctionAttribute("crop_to_0_100", new Attribute[] {Voyage.getAttribute("boyrat7")})}),
				TastResource.getText("components_timeline_stat_percentboys"),
				"{0,number,#,###,##0.0}%",
				true));
		
		this.availableStats.add(new TimelineVariable(
				new FunctionAttribute("AVG", new Attribute[] {new FunctionAttribute("crop_to_0_100", new Attribute[] {Voyage.getAttribute("girlrat7")})}),
				TastResource.getText("components_timeline_stat_percentgirls"),
				"{0,number,#,###,##0.0}%",
				true));
		
		this.availableStats.add(new TimelineVariable(
				new FunctionAttribute("AVG", new Attribute[] {new FunctionAttribute("crop_to_0_100", new Attribute[] {Voyage.getAttribute("malrat7")})}),
				TastResource.getText("components_timeline_stat_percentmales"),
				"{0,number,#,###,##0.0}%",
				true));

		this.availableStats.add(new TimelineVariable(
				new FunctionAttribute("AVG", new Attribute[] {new FunctionAttribute("crop_to_0_100", new Attribute[] {Voyage.getAttribute("chilrat7")})}),
				TastResource.getText("components_timeline_stat_percentchildren"),
				"{0,number,#,###,##0.0}%",
				true));
		
		this.availableStats.add(new TimelineVariable(
				new FunctionAttribute("AVG", new Attribute[] {Voyage.getAttribute("jamcaspr")}),
				TastResource.getText("components_timeline_stat_averageprice"),
				"{0,number,#,###,##0.0}"));

		this.availableStats.add(new TimelineVariable(
				new FunctionAttribute("SUM", new Attribute[] {Voyage.getAttribute("vymrtimp")}),
				TastResource.getText("components_timeline_stat_numdeaths"),
				"{0,number,#,###,###}",
				true));
		
		this.availableStats.add(new TimelineVariable(
				new FunctionAttribute("AVG", new Attribute[] {Voyage.getAttribute("vymrtimp")}),
				TastResource.getText("components_timeline_stat_averagedeaths"),
				"{0,number,#,###,##0.0}",
				true));
		
		this.availableStats.add(new TimelineVariable(
				new FunctionAttribute("AVG", new Attribute[] {new FunctionAttribute("crop_to_0_100", new Attribute[] {Voyage.getAttribute("vymrtrat")})}),
				TastResource.getText("components_timeline_stat_mortrate"),
				"{0,number,#,###,##0.0}%",
				true));
		
		/*this.availableStats.add(new TimelineVariable(
				new FunctionAttribute("SUM", new Attribute[] {Voyage.getAttribute("resistance")}),
				TastResource.getText("components_timeline_stat_resistance"),
				"{0,number,#,###,##0.0}",
				true));*/
		
		resetToDefault();

	}
	
	public void resetToDefault()
	{
		
		attributesChanged = false;
		needQuery = false;

		this.chosenOption = (TimelineVariable)this.availableStats.get(15);
		
	}
	
	/**
	 * Gets available list of statistics for voyages.
	 * @return
	 */
	public List getVoyageNumericAttributes() {

		this.voyageAttributes = new ArrayList();
		Iterator iter = this.availableStats.iterator();
		while (iter.hasNext()) {
			TimelineVariable option = (TimelineVariable)iter.next();
			this.voyageAttributes.add(new SelectItem(String.valueOf(option.hashCode()), option.userLabel));
		}
		
		return this.voyageAttributes;
	}


	/**
	 * Shows time line chart.
	 * This function queries the database if necessary.
	 * @return
	 */
	public String showTimeLine() {
		
		if (!this.searchBean.getSearchParameters().getConditions().equals(this.conditions)) {
			this.conditions = (TastDbConditions)this.searchBean.getSearchParameters().getConditions().clone();
			needQuery = true;
		}
		
		//Check if we can construct chart
		if ((this.needQuery || this.attributesChanged) && this.searchBean.getSearchParameters().getConditions() != null) {

			Session sess = HibernateConn.getSession();
			Transaction tran = sess.beginTransaction();

			TastDbQuery qValue = new TastDbQuery(new String[] {"Voyage"}, new String[] {"v"}, this.searchBean.getSearchParameters().getConditions());
			qValue.setGroupBy(new Attribute[] {Voyage.getAttribute("yearam")});
			qValue.addPopulatedAttribute(Voyage.getAttribute("yearam"));
			qValue.addPopulatedAttribute(this.chosenOption.attributeValue);
			qValue.setOrderBy(new Attribute[] {Voyage.getAttribute("yearam")});
			qValue.setOrder(TastDbQuery.ORDER_ASC);
			
			boolean useSQL = AppConfig.getConfiguration().getBoolean(AppConfig.DATABASE_USE_SQL);
			List ret = qValue.executeQueryList(sess, useSQL);
			
			MessageFormat fmt = new MessageFormat(chosenOption.formatString);
			Object[] valueHolder = new Object[1];
			Double zeroDouble = new Double(0);

			double[] values = new double[ret.size()];
			String[] stringValues = new String[ret.size()];
			int[] years = new int[ret.size()];
			
			int i = 0;
			for (Iterator iter = ret.iterator(); iter.hasNext();)
			{
				Object[] row = (Object[]) iter.next();
				years[i] = ((Number)row[0]).intValue();
				if (row[1] != null)
				{
					valueHolder[0] = row[1];
					values[i] = ((Number)row[1]).doubleValue();
					if (this.chosenOption.isPercentage)
						values[i] = Math.min(100.0, values[i]);
				}
				else
				{
					valueHolder[0] = zeroDouble;
					values[i] = 0;
				}
				stringValues[i] = fmt.format(valueHolder);
				i++;
			}
			
			graph = new EventLineGraph();
			graph.setName(chosenOption.userLabel);
			graph.setX(years);
			graph.setY(values);
			graph.setLabels(stringValues);
			graph.setBaseCssClass("timeline-color");
			
			if (this.chosenOption.isPercentage)
				graph.setExplicitMax(100.0);
			
			tran.commit();
			sess.close();
			
			double maxValue = graph.getMaxValue();

			if (maxValue > 0)
			{
				verticalLabels = this.chosenOption.isPercentage ?
						EventLineLabel.createPercentualLabels(fmt) :
						EventLineLabel.createStandardLabels(maxValue, fmt);
				viewportHeight = verticalLabels[verticalLabels.length - 1].getValue();
			}
			else
			{
				verticalLabels = new EventLineLabel[] {new EventLineLabel(0.0, "0", true)};
				viewportHeight = 100;
			}

			this.needQuery = false;
			this.attributesChanged = false;

		}

		return null;
		
	}

	/**
	 * Gets currently chosen attribute.
	 * @return
	 */
	public String getChosenAttribute() {
		return String.valueOf(chosenOption.hashCode());
	}

	/**
	 * Sets currently chosen attribute.
	 * @param chosenAttribute
	 */
	public void setChosenAttribute(String chosenAttribute) {
		this.attributesChanged = true;
		if (chosenAttribute != null) {
			Iterator iter = this.availableStats.iterator();
			while (iter.hasNext()) {
				TimelineVariable option = (TimelineVariable) iter.next();
				if (option.hashCode() == Integer.parseInt(chosenAttribute)) {
					this.chosenOption = option;
					break;
				}
			}
		}

	}


	public String setNewView() {
		return null;
	}

	public SearchBean getSearchBean() {
		return searchBean;
	}

	public void setSearchBean(SearchBean searchBean) {
		this.searchBean = searchBean;
	}

	public Double getViewportHeight() {
		showTimeLine();
		return new Double(this.viewportHeight);
	}
	
	/**
	 * Gets available series in timeline - by default only one here.
	 * @return
	 */
	public EventLineGraph[] getGraphs() {
		
		this.showTimeLine();
		return new EventLineGraph[] {graph};
	}
	
	/**
	 * Gets current events associated with dates.
	 * @return
	 */
	public EventLineEvent[] getEvents() {
		showTimeLine();
		return new EventLineEvent[] {};
	}

	/**
	 * Gets avaialble zoom levels.
	 * @return
	 */
	public EventLineZoomLevel[] getZoomLevels() {
		showTimeLine();
		return new EventLineZoomLevel[] {
				new EventLineZoomLevel(2, 50, 400, 100),
				new EventLineZoomLevel(4, 25, 200, 50),
				new EventLineZoomLevel(8, 10, 100, 25),
				new EventLineZoomLevel(16, 5, 50, 10),
				new EventLineZoomLevel(32, 5, 25, 5)};
	}
	public EventLineLabel[] getVerticalLabels() {
		showTimeLine();
		return verticalLabels;
	}
	
	/**
	 * Prepares ZIP file with current data.
	 * @return
	 */
	public String getFileAllData() {	
		Session session = HibernateConn.getSession();
		Transaction t = session.beginTransaction();
		
		String[][] data = new String[this.graph.getX().length + 1][2];
		data[0][0] = "year";
		data[0][1] = this.chosenOption.userLabel;
		for (int i = 0; i < data.length - 1; i++) {
			data[i + 1][0] = String.valueOf(this.graph.getX()[i]);
			data[i + 1][1] = String.valueOf(this.graph.getY()[i]);
		}
		CSVUtils.writeResponse(session, data);
		
		t.commit();
		session.close();
		return null;
	}

}
