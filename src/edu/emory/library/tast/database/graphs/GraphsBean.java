package edu.emory.library.tast.database.graphs;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;

import org.jfree.chart.JFreeChart;

import edu.emory.library.tast.database.query.SearchBean;
import edu.emory.library.tast.dm.attributes.Attribute;
import edu.emory.library.tast.dm.attributes.specific.FunctionAttribute;
import edu.emory.library.tast.util.query.Conditions;
import edu.emory.library.tast.util.query.QueryValue;

public class GraphsBean
{

	private static final int XY_GRAPH_TYPE_INDEX = 0;
	private static final int BAR_GRAPH_TYPE_INDEX = 1;
	private static final int PIE_GRAPH_TYPE_INDEX = 2;
	
	public static final String GRAPHS_SERVLET = "../servlet/graph";
	public static final String SESSION_KEY_GRAPH = "graph";
	private static final String DEFAULT_CHART_HEIGHT = "480";
	private static final String DEFAULT_CHART_WIDTH = "640";
	
	private static final Pattern REGEX_DEP_VARIABLE = Pattern.compile("(.+)-(count|avg|sum|min|max)");
	
	private SearchBean searchBean = null;
	private Conditions conditions = null;
	
	private boolean needRefresh = false;

	private List toRemove;

	private String chartHeight = DEFAULT_CHART_HEIGHT;
	private String chartWidth = DEFAULT_CHART_WIDTH;

	private static final IndependentVariable[] independentVariablesXY = new IndependentVariable[] {
		IndependentVariable.createForInteger("yearam", "Year arrived with slaves*", "yearam"),
		IndependentVariable.createForInteger("voy2imp", "Middle passage (days)*", "voy2imp"),
		IndependentVariable.createForInteger("tonmod", "Standardized tonnage*", "tonmod"),
		IndependentVariable.createForInteger("guns", "Guns mounted*", "guns"),
		IndependentVariable.createForInteger("slaximp", "Total slaves embarked*", "slaximp"),
		IndependentVariable.createForInteger("slamimp", "Total slaves disembarked*", "slamimp"),
		IndependentVariable.createForInteger("crew1", "Crew at voyage outset", "crew1"),
		IndependentVariable.createForInteger("crew3", "Crew at first landing of slaves", "crew3"),
	};

	private static final DependentVariable[] dependentVariablesXY = new DependentVariable[] {
		DependentVariable.createCounter("iid", "Number of voyages", "iid"),
		DependentVariable.createStandard("crew1", "Crew at voyage outset", "crew1"),
		DependentVariable.createStandard("crew3", "Crew at first landing of slaves", "crew3"),
		DependentVariable.createStandard("crewdied", "Crew deaths", "crewdied"),
		DependentVariable.createStandard("slaximp", "Total slaves embarked*", "slaximp"),
		DependentVariable.createStandard("slamimp", "Total slaves disembarked*", "slamimp"),
		DependentVariable.createPercentage("menrat7", "Percentage men*", "menrat7"),
		DependentVariable.createPercentage("womrat7", "Percentage women*", "womrat7"),
		DependentVariable.createPercentage("boyrat7", "Percentage boys*", "boyrat7"),
		DependentVariable.createPercentage("girlrat7", "Percentage girls*", "girlrat7"),
		DependentVariable.createPercentage("chilrat7", "Percentage children*", "chilrat7"),
		DependentVariable.createPercentage("malrat7", "Percentage male*", "malrat7"),
		DependentVariable.createStandard("jamcaspr", "Sterling cash price in Jamaica*", "jamcaspr"),
		DependentVariable.createPercentage("vymrtrat", "Percentage of slaves embarked who died during voyage*", "vymrtrat"),
	};

	private static final IndependentVariable[] independentVariablesBar = new IndependentVariable[] {
		IndependentVariable.createForString("shipname", "Vessel name", "shipname"),
		IndependentVariable.createForPort("placcons", "Place constructed", "placcons"),
		IndependentVariable.createForPort("placreg", "Place registered", "placreg"),
		IndependentVariable.createForPort("natinimp", "Flag*", "natinimp"),
		IndependentVariable.createForVesselRig("rig", "Rig", "rig"),
		IndependentVariable.createForFate("fate", "Particular outcome of the voyage", "fate"),
		IndependentVariable.createForFateSlaves("fate2", "Outcome for slaves*", "fate2"),
		IndependentVariable.createForFateOwner("fate4", "Outcome for owner*", "fate4"),
		IndependentVariable.createForFateVessel("fate3", "Outcome if ship captured*", "fate3"),
		IndependentVariable.createForPort("ptdepimp", "Place where voyage began*", "ptdepimp"),
		IndependentVariable.createForRegion("ptdepimp-region", "Region where voyage began*", "ptdepimp"),
		IndependentVariable.createForPort("mjbyptimp", "Principal place of slave purchase*", "mjbyptimp"),
		IndependentVariable.createForRegion("mjbyptimp-region", "Principal region of slave purchase*", "mjbyptimp"),
		IndependentVariable.createForPort("mjslptimp", "Principal place of slave landing*", "mjslptimp"),
		IndependentVariable.createForRegion("mjslptimp-region", "Principal region of slave landing*", "mjslptimp"),
		IndependentVariable.createForArea("mjslptimp-area", "Broad region of slave landing", "mjslptimp"),
		IndependentVariable.createForPort("portret", "Place where voyage ended", "portret"),
		IndependentVariable.createForRegion("portret-region", "Region where voyage ended", "portret"),
		IndependentVariable.createForMonth("datedep-month", "Month voyage began in Africa", "datedep"),
		IndependentVariable.createForMonth("datebuy-month", "Month trade began in Africa", "datebuy"),
		IndependentVariable.createForMonth("dateleftafr-month", "Month vessel departed Africa", "dateleftafr"),
		IndependentVariable.createForMonth("dateland1-month", "Month vessel arrived with slaves", "dateland1"),
		IndependentVariable.createForMonth("datedepam-month", "Month vessel departed for home port", "datedepam"),
		IndependentVariable.createForMonth("dateend-month", "Month voyage completed", "dateend"),
		IndependentVariable.createForYearPeriod("yearam-5", "Year arrived with slaves (5 year periods)", "yearam", 5),
		IndependentVariable.createForYearPeriod("yearam-10", "Year arrived with slaves (10 year periods)", "yearam", 10),
		IndependentVariable.createForYearPeriod("yearam-25", "Year arrived with slaves (25 year periods)", "yearam", 25),
	};

	private static final DependentVariable[] dependentVariablesBar = new DependentVariable[] {
		DependentVariable.createCounter("iid", "Number of voyages", "iid"),
		DependentVariable.createStandard("crew1", "Crew at voyage outset", "crew1"),
		DependentVariable.createStandard("crew3", "Crew at first landing of slaves", "crew3"),
		DependentVariable.createStandard("crewdied", "Crew deaths", "crewdied"),
		DependentVariable.createStandard("slaximp", "Total slaves embarked*", "slaximp"),
		DependentVariable.createStandard("slamimp", "Total slaves disembarked*", "slamimp"),
		DependentVariable.createPercentage("menrat7", "Percentage men*", "menrat7"),
		DependentVariable.createPercentage("womrat7", "Percentage women*", "womrat7"),
		DependentVariable.createPercentage("boyrat7", "Percentage boys*", "boyrat7"),
		DependentVariable.createPercentage("girlrat7", "Percentage girls*", "girlrat7"),
		DependentVariable.createPercentage("chilrat7", "Percentage children*", "chilrat7"),
		DependentVariable.createPercentage("malrat7", "Percentage male*", "chilrat7"),
		DependentVariable.createStandard("jamcaspr", "Sterling cash price in Jamaica*", "jamcaspr"),
		DependentVariable.createPercentage("vymrtrat", "Percentage of slaves embarked who died during voyage*", "vymrtrat")
	};
	
	private static final IndependentVariable[] independentVariablesPie = new IndependentVariable[] {
		IndependentVariable.createForString("shipname", "Vessel name", "shipname"),
		IndependentVariable.createForPort("placcons", "Place constructed", "placcons"),
		IndependentVariable.createForPort("placreg", "Place registered", "placreg"),
		IndependentVariable.createForPort("natinimp", "Flag*", "natinimp"),
		IndependentVariable.createForVesselRig("rig", "Rig", "rig"),
		IndependentVariable.createForFate("fate", "Particular outcome of the voyage", "fate"),
		IndependentVariable.createForFateSlaves("fate2", "Outcome for slaves*", "fate2"),
		IndependentVariable.createForFateOwner("fate4", "Outcome for owner*", "fate4"),
		IndependentVariable.createForFateVessel("fate3", "Outcome if ship captured*", "fate3"),
		IndependentVariable.createForPort("ptdepimp", "Place where voyage began*", "ptdepimp"),
		IndependentVariable.createForRegion("ptdepimp-region", "Region where voyage began*", "ptdepimp"),
		IndependentVariable.createForPort("mjbyptimp", "Principal place of slave purchase*", "mjbyptimp"),
		IndependentVariable.createForRegion("mjbyptimp-region", "Principal region of slave purchase*", "mjbyptimp"),
		IndependentVariable.createForPort("mjslptimp", "Principal place of slave landing*", "mjslptimp"),
		IndependentVariable.createForRegion("mjslptimp-region", "Principal region of slave landing*", "mjslptimp"),
		IndependentVariable.createForArea("mjslptimp-area", "Broad region of slave landing", "mjslptimp"),
		IndependentVariable.createForPort("portret", "Place where voyage ended", "portret"),
		IndependentVariable.createForRegion("portret-region", "Region where voyage ended", "portret"),
	};
	
	private static final DependentVariable[] dependentVariablesPie = new DependentVariable[] {
		DependentVariable.createCounter("iid", "Number of voyages", "iid"),
		DependentVariable.createStandard("crew1", "Crew at voyage outset", "crew1"),
		DependentVariable.createStandard("crew3", "Crew at first landing of slaves", "crew3"),
		DependentVariable.createStandard("slaximp", "Total slaves embarked*", "slaximp"),
		DependentVariable.createStandard("slamimp", "Total slaves disembarked*", "slamimp"),
	};
	
	private GraphType[] graphs = new GraphType[] {
		new GraphTypeXY("xy", independentVariablesXY, dependentVariablesXY),
		new GraphTypeBar("bar", independentVariablesBar, dependentVariablesBar),
		new GraphPie("xy", independentVariablesPie, dependentVariablesPie),
	};
	
	private GraphType selectedGraph;

	public GraphsBean()
	{
		resetToDefault();
	}
	
	public void resetToDefault()
	{
		
		chartHeight = DEFAULT_CHART_HEIGHT;
		chartWidth = DEFAULT_CHART_WIDTH;
		
		selectedGraph = graphs[XY_GRAPH_TYPE_INDEX];
		selectedGraph.removeAllSeries();
		selectedGraph.setSelectedIndependentVariableId("voy2imp");
		selectedGraph.setSelectedDependentVariableId("vymrtrat");
		selectedGraph.setSelectedAggregate("avg");
		addSeries();
		
		selectedGraph = graphs[BAR_GRAPH_TYPE_INDEX];
		selectedGraph.removeAllSeries();
		selectedGraph.setSelectedIndependentVariableId("mjbyptimp-region");
		selectedGraph.setSelectedDependentVariableId("chilrat7");
		selectedGraph.setSelectedAggregate("avg");
		addSeries();
		
		selectedGraph = graphs[PIE_GRAPH_TYPE_INDEX];
		selectedGraph.removeAllSeries();
		selectedGraph.setSelectedIndependentVariableId("natinimp");
		selectedGraph.setSelectedDependentVariableId("iid");
		selectedGraph.setSelectedAggregate("count");
		addSeries();
		
		selectedGraph = graphs[XY_GRAPH_TYPE_INDEX];

	}

	public String addSeries()
	{

		DependentVariable depVar = selectedGraph.getSelectedDependentVariable();
		String aggregate = selectedGraph.getSelectedAggregate();
		
		if (!selectedGraph.canHaveMoreSeries())
			selectedGraph.removeAllSeries();
		
		DataSeries newSeries = new DataSeries(depVar, aggregate);
		if (selectedGraph.getDataSeries().contains(newSeries))
			return null;
		
		selectedGraph.getDataSeries().add(newSeries);
		
		needRefresh = true;

		return null;
	}

	public String removeSeries()
	{
		if (this.toRemove != null)
		{
			List series = selectedGraph.getDataSeries();
			for (Iterator iter = toRemove.iterator(); iter.hasNext();)
			{
				Matcher matcher = REGEX_DEP_VARIABLE.matcher((String) iter.next());
				if (matcher.matches())
				{
					String varId = matcher.group(1); 
					String aggregate = matcher.group(2); 
					for (int i = 0; i < series.size(); i++)
					{
						DataSeries dataSeries = ((DataSeries)series.get(i));
						if (dataSeries.getVariable().getId().equals(varId) &&
								dataSeries.getAggregate().equals(aggregate))
						{
							series.remove(i);
							needRefresh = true;
							break;
						}
					}
				}
			}
		}
		return null;
	}
	
	public void refreshGraphIfNeeded()
	{
		
		Conditions searchBeanConditions = this.searchBean.getSearchParameters().getConditions();
		if (searchBeanConditions.equals(this.conditions) && !this.needRefresh)
			return;
		
		IndependentVariable indepVar = selectedGraph.getSelectedIndependentVariable();
		if (indepVar == null) return;

		this.conditions = (Conditions)searchBeanConditions.clone();
		this.needRefresh = false;

		Conditions localConditions = (Conditions)this.conditions.clone();
		
		localConditions.addCondition(indepVar.getSelectAttribute(), null, Conditions.OP_IS_NOT);
		
		QueryValue qValue = new QueryValue(new String[] {"Voyage"}, new String[] {"v"}, localConditions);
		
		qValue.addPopulatedAttribute(indepVar.getSelectAttribute());
		
		for (Iterator iter = selectedGraph.getDataSeries().iterator(); iter.hasNext();)
		{
			DataSeries element = (DataSeries) iter.next();
			qValue.addPopulatedAttribute(
					new FunctionAttribute(
							element.aggregate,
							element.getVariable().getSelectAttribute()));
		}
		
		qValue.setGroupBy(indepVar.getGroupByAttributes());
		
		qValue.setOrderBy(new Attribute[] {indepVar.getOrderAttribute()});
		qValue.setOrder(QueryValue.ORDER_ASC);
		
		Object[] data = qValue.executeQuery();
		
		JFreeChart chart = selectedGraph.createChart(data);

		ExternalContext servletContext = FacesContext.getCurrentInstance().getExternalContext();
		((HttpSession) servletContext.getSession(true)).setAttribute(SESSION_KEY_GRAPH, chart);

	}
	
	public String showGraph()
	{
		this.needRefresh = true;
		return null;
	}

	public String setNewView()
	{
		return null;
	}

	
	public List getSeries()
	{
		List list = new ArrayList();
		if (selectedGraph.getDataSeries() != null)
		{
			for (Iterator iter = selectedGraph.getDataSeries().iterator(); iter.hasNext();)
			{
				DataSeries element = (DataSeries) iter.next();
				list.add(new SelectItem(
						element.getVariable().getId() + "-" + element.getAggregate(), 
						element.getAggregate() + " of " + element.getVariable().getLabel()));
			}
		}
		return list;
	}

	public SelectItem[] getIndependentVariables()
	{
		
		IndependentVariable[] vars = selectedGraph.getIndependentVariables();
		
		SelectItem[] items = new SelectItem[vars.length];
		for (int i = 0; i < vars.length; i++)
		{
			IndependentVariable var = vars[i];
			items[i] = new SelectItem(var.getId(), var.getLabel());
		}
		
		return items;
		
	}
	
	public SelectItem[] getDependentVariables()
	{
		
		DependentVariable[] vars = selectedGraph.getDependentVariables();
		
		List itemsList = new LinkedList();
		
		for (int i = 0; i < vars.length; i++)
		{
			DependentVariable var = vars[i];
			
			if (var.hasCount())
				itemsList.add(new SelectItem(
						var.getId() + "-count",
						var.getLabel()));
			
			if (var.hasSum())
				itemsList.add(new SelectItem(
						var.getId() + "-sum",
						"Total sum of " + var.getLabel()));

			if (var.hasAvg())
				itemsList.add(new SelectItem(
						var.getId() + "-avg",
						"Average of " + var.getLabel()));

			if (var.hasMin())
				itemsList.add(new SelectItem(
						var.getId() + "-min",
						"Minimum of " + var.getLabel()));

			if (var.hasMax())
				itemsList.add(new SelectItem(
						var.getId() + "-max",
						"Maximum of " + var.getLabel()));
			
		}
		
		SelectItem[] items = new SelectItem[itemsList.size()];
		itemsList.toArray(items);
		
		return items;
		
	}
	
	public String switchToXY()
	{
		selectedGraph = graphs[XY_GRAPH_TYPE_INDEX];
		needRefresh = true;
		return null;
	}

	public String switchToBar()
	{
		selectedGraph = graphs[BAR_GRAPH_TYPE_INDEX];
		needRefresh = true;
		return null;
	}

	public String switchToPie()
	{
		selectedGraph = graphs[PIE_GRAPH_TYPE_INDEX];
		needRefresh = true;
		return null;
	}

	public String getChartPath()
	{
		this.refreshGraphIfNeeded();
		return GRAPHS_SERVLET + "?" +
			"height=" + this.chartHeight + "&" +
			"width=" + this.chartWidth;
	}

	public List getToRemove()
	{
		return toRemove;
	}

	public void setToRemove(List toRemove)
	{
		this.toRemove = toRemove;
	}

	public String getChartHeight()
	{
		return chartHeight;
	}

	public void setChartHeight(String chartHeight)
	{
		this.chartHeight = chartHeight;
	}

	public String getChartWidth()
	{
		return chartWidth;
	}

	public void setChartWidth(String chartWidth)
	{
		this.chartWidth = chartWidth;
	}

	public String getSelectedIndependentVariableId()
	{
		return selectedGraph.getSelectedIndependentVariableId();
	}

	public String getSelectedDependentVariableId()
	{
		return
			selectedGraph.getSelectedDependentVariableId() + "-" +
			selectedGraph.getSelectedAggregate();
	}

	public void setSelectedDependentVariableId(String id)
	{
		Matcher matcher = REGEX_DEP_VARIABLE.matcher(id);
		if (matcher.matches())
		{
			selectedGraph.setSelectedAggregate(matcher.group(2));
			selectedGraph.setSelectedDependentVariableId(matcher.group(1));
			needRefresh = true;
		}
	}

	public void setSelectedIndependentVariableId(String id)
	{
		selectedGraph.setSelectedIndependentVariableId(id);
	}

	public boolean isGraphAvailable()
	{
		return selectedGraph.hasDataSeries();
	}
	
	public boolean isHaveSeries()
	{
		return selectedGraph.hasDataSeries();
	}

	public SearchBean getSearchBean()
	{
		return searchBean;
	}

	public void setSearchBean(SearchBean searchBean)
	{
		this.searchBean = searchBean;
	}

}
