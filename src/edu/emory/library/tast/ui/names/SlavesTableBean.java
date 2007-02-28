package edu.emory.library.tast.ui.names;

import java.text.MessageFormat;

import edu.emory.library.tast.dm.Country;
import edu.emory.library.tast.dm.Estimate;
import edu.emory.library.tast.dm.Image;
import edu.emory.library.tast.dm.Slave;
import edu.emory.library.tast.dm.attributes.Attribute;
import edu.emory.library.tast.dm.attributes.specific.FunctionAttribute;
import edu.emory.library.tast.dm.attributes.specific.SequenceAttribute;
import edu.emory.library.tast.ui.search.table.SortChangeEvent;
import edu.emory.library.tast.ui.search.table.TableData;
import edu.emory.library.tast.ui.search.tabscommon.VisibleAttributeInterface;
import edu.emory.library.tast.ui.search.tabscommon.links.TableLinkManager;
import edu.emory.library.tast.util.StringUtils;
import edu.emory.library.tast.util.query.Conditions;
import edu.emory.library.tast.util.query.QueryValue;

public class SlavesTableBean {
	
	private static final String ATTRIBUTE = "Attribute_";
	
	private TableData tableData;
	private TableLinkManager linkManager = new TableLinkManager(20);
	MessageFormat valuesFormat = new MessageFormat("{0,number,#,###,###}");
	
	private Integer queryAgeFrom;
	private Integer queryAgeTo;
	private Integer queryHeightFrom;
	private Integer queryHeightTo;
	private String querySlaveName;
	private String queryShipName;
	private boolean queryBoy;
	private boolean queryMan;
	private boolean queryMale;
	private boolean queryGirl;
	private boolean queryWoman;
	private boolean queryFemail;
	private String queryCountry;
	private String queryExpPort;
	private boolean querySierraLeona;
	private boolean queryHavana;
	
	public SlavesTableBean() {
		VisibleAttributeInterface[] visibleAttrs = new VisibleAttributeInterface[12];
		visibleAttrs[0] = VisibleAttrSlave.getAttributeForTable("id");
		visibleAttrs[1] = VisibleAttrSlave.getAttributeForTable("voyageId");
		visibleAttrs[2] = VisibleAttrSlave.getAttributeForTable("name");
		visibleAttrs[3] = VisibleAttrSlave.getAttributeForTable("shipname");
		visibleAttrs[4] = VisibleAttrSlave.getAttributeForTable("age");
		visibleAttrs[5] = VisibleAttrSlave.getAttributeForTable("height");
		visibleAttrs[6] = VisibleAttrSlave.getAttributeForTable("datearr");
		visibleAttrs[7] = VisibleAttrSlave.getAttributeForTable("source");
		visibleAttrs[8] = VisibleAttrSlave.getAttributeForTable("sexage");
		visibleAttrs[9] = VisibleAttrSlave.getAttributeForTable("country");
		visibleAttrs[10] = VisibleAttrSlave.getAttributeForTable("majselpt");
		visibleAttrs[11] = VisibleAttrSlave.getAttributeForTable("majbuypt");
		
		tableData = new TableData();
		tableData.setKeyAttribute(Slave.getAttribute("id"));
		tableData.setVisibleColumns(visibleAttrs);
		tableData.setOrderByColumn(visibleAttrs[0]);
	}
	
	public TableData getTableData() {
		Conditions conditions = this.prepareConditions();
		
		QueryValue qValue = new QueryValue(new String[] {"Slave"}, new String[] {"s"}, conditions);
		Attribute[] attrs = this.tableData.getAttributesForQuery();
		for (int i = 0; i < attrs.length; i++) {
			qValue.addPopulatedAttribute(attrs[i]);
		}
		qValue.setOrder(this.tableData.getOrder());
		qValue.setOrderBy(this.tableData.getOrderByColumn().getAttributes());
		qValue.setFirstResult(this.linkManager.getCurrentFirstRecord());
		qValue.setLimit(this.linkManager.getStep());
		this.tableData.setData(qValue.executeQuery());
		this.setNumberOfResults();
		
		return tableData;
	}
	
	private void setNumberOfResults() {
		//this.conditions = this.getEstimatesBean().getConditions();
		QueryValue qValue = new QueryValue(new String[] {"Slave"}, new String[] {"e"}, this.prepareConditions());
		qValue.addPopulatedAttribute(new  FunctionAttribute("count", new Attribute[] {Estimate.getAttribute("id")}));
		Object[] ret = qValue.executeQuery();
		this.linkManager.setResultsNumber(((Number) ret[0]).intValue());
	}

	private Conditions prepareConditions() {
		Conditions c = new Conditions();
		if (this.queryAgeFrom != null) {
			c.addCondition(Slave.getAttribute("age"), this.queryAgeFrom, Conditions.OP_GREATER_OR_EQUAL);
		}
		if (this.queryAgeTo != null) {
			c.addCondition(Slave.getAttribute("age"), this.queryAgeTo, Conditions.OP_SMALLER_OR_EQUAL);
		}
		if (this.queryHeightFrom != null) {
			c.addCondition(Slave.getAttribute("height"), new Double(this.queryHeightFrom.intValue()), Conditions.OP_GREATER_OR_EQUAL);
		}
		if (this.queryHeightTo != null) {
			c.addCondition(Slave.getAttribute("height"), new Double(this.queryHeightTo.intValue()), Conditions.OP_SMALLER_OR_EQUAL);
		}
		if (!StringUtils.isNullOrEmpty(this.querySlaveName)) {
			String[] s = StringUtils.extractQueryKeywords(this.querySlaveName, true);
			for (int i = 0; i < s.length; i++) {
				c.addCondition(new FunctionAttribute("upper", new Attribute[] {Slave.getAttribute("name")}), "%" + s[i] + "%", Conditions.OP_LIKE);
			}
		}
		if (!StringUtils.isNullOrEmpty(this.queryShipName)) {
			String[] s = StringUtils.extractQueryKeywords(this.queryShipName, true);
			for (int i = 0; i < s.length; i++) {
				c.addCondition(new FunctionAttribute("upper", new Attribute[] {Slave.getAttribute("shipname")}), "%" + s[i] + "%", Conditions.OP_LIKE);
			}
		}
		if (!StringUtils.isNullOrEmpty(this.queryCountry)) {
			String[] s = StringUtils.extractQueryKeywords(this.queryCountry, true);
			for (int i = 0; i < s.length; i++) {
				c.addCondition(new SequenceAttribute(new Attribute[] {Slave.getAttribute("country"), Country.getAttribute("name")}), "%" + s[i] + "%", Conditions.OP_LIKE);
			}
		}
		if (!StringUtils.isNullOrEmpty(this.queryExpPort)) {
			String[] s = StringUtils.extractQueryKeywords(this.queryCountry, true);
			for (int i = 0; i < s.length; i++) {
				c.addCondition(new SequenceAttribute(new Attribute[] {Slave.getAttribute("majbuypt"), Country.getAttribute("name")}), "%" + s[i] + "%", Conditions.OP_LIKE);
			}
		}
		return c;
	}

	public void setTableData(TableData tableData) {
		this.tableData = tableData;
	}
	
	public void sortChanged(SortChangeEvent event) {
		//Get column that will be sorted
		VisibleAttributeInterface attr = this.getVisibleAttribute(event.getAttributeSort());

		// Set appropriate order
		if (this.getTableData().getOrderByColumn().getName().equals(attr.getName())) {
			switch (this.getTableData().getOrder()) {
			case QueryValue.ORDER_ASC:
				this.getTableData().setOrder(QueryValue.ORDER_DESC);
				break;
			case QueryValue.ORDER_DESC:
				this.getTableData().setOrder(QueryValue.ORDER_DEFAULT);
				break;
			case QueryValue.ORDER_DEFAULT:
				this.getTableData().setOrder(QueryValue.ORDER_ASC);
				break;
			}
		} else {
			this.getTableData().setOrderByColumn(attr);
			this.getTableData().setOrder(QueryValue.ORDER_ASC);
		}

		// Indicate need of query
		this.linkManager.reset();
		//this.requery = true;
	}
	
	private VisibleAttributeInterface getVisibleAttribute(String attributeSort) {
		VisibleAttributeInterface ret = null;
		if (attributeSort.startsWith(ATTRIBUTE)) {
			String attrId = attributeSort.substring(ATTRIBUTE.length(), attributeSort.length());
			ret = VisibleAttrSlave.getAttributeForTable(attrId);
		} 
		return ret;
	}
	
	public int getFirstDisplayed() {
		return this.linkManager.getCurrentFirstRecord() + 1;
	}
	
	public int getLastDisplayed() {
		if (this.linkManager.getResultsNumber() == 0)
			return 0;
		else
			return this.linkManager.getCurrentFirstRecord() + 1 + 
					(this.tableData.getData() != null ? this.tableData.getData().length - 1 : 0);
	}
	
	public String getStep() {
		return String.valueOf(this.linkManager.getStep());
	}
	
	public int getTotalRows() {
		return this.linkManager.getResultsNumber();
	}
	
	public void setStep(String step) {
		if ("all".equals(step)) {
			this.linkManager.setStep(Integer.MAX_VALUE);
		} else {
			this.linkManager.setStep(Integer.parseInt(step));
		}
		//this.requery = true;
	}
	
	public TableLinkManager getTableManager() {
		return this.linkManager;
	}

	public Integer getQueryAgeFrom()
	{
		return queryAgeFrom;
	}

	public void setQueryAgeFrom(Integer queryAgeFrom)
	{
		this.queryAgeFrom = queryAgeFrom;
	}

	public Integer getQueryAgeTo()
	{
		return queryAgeTo;
	}

	public void setQueryAgeTo(Integer queryAgeTo)
	{
		this.queryAgeTo = queryAgeTo;
	}

	public boolean isQueryBoy()
	{
		return queryBoy;
	}

	public void setQueryBoy(boolean queryBoy)
	{
		this.queryBoy = queryBoy;
	}

	public String getQueryCountry()
	{
		return queryCountry;
	}

	public void setQueryCountry(String queryCountry)
	{
		this.queryCountry = queryCountry;
	}

	public String getQueryExpPort()
	{
		return queryExpPort;
	}

	public void setQueryExpPort(String queryExpRegion)
	{
		this.queryExpPort = queryExpRegion;
	}

	public boolean isQueryFemail()
	{
		return queryFemail;
	}

	public void setQueryFemail(boolean queryFemail)
	{
		this.queryFemail = queryFemail;
	}

	public boolean isQueryGirl()
	{
		return queryGirl;
	}

	public void setQueryGirl(boolean queryGirl)
	{
		this.queryGirl = queryGirl;
	}

	public boolean isQueryHavana()
	{
		return queryHavana;
	}

	public void setQueryHavana(boolean queryHavana)
	{
		this.queryHavana = queryHavana;
	}

	public Integer getQueryHeightFrom()
	{
		return queryHeightFrom;
	}

	public void setQueryHeightFrom(Integer queryHeightFrom)
	{
		this.queryHeightFrom = queryHeightFrom;
	}

	public Integer getQueryHeightTo()
	{
		return queryHeightTo;
	}

	public void setQueryHeightTo(Integer queryHeightTo)
	{
		this.queryHeightTo = queryHeightTo;
	}

	public boolean isQueryMale()
	{
		return queryMale;
	}

	public void setQueryMale(boolean queryMale)
	{
		this.queryMale = queryMale;
	}

	public boolean isQueryMan()
	{
		return queryMan;
	}

	public void setQueryMan(boolean queryMan)
	{
		this.queryMan = queryMan;
	}

	public String getQueryShipName()
	{
		return queryShipName;
	}

	public void setQueryShipName(String queryShipName)
	{
		this.queryShipName = queryShipName;
	}

	public boolean isQuerySierraLeona()
	{
		return querySierraLeona;
	}

	public void setQuerySierraLeona(boolean querySierraLeona)
	{
		this.querySierraLeona = querySierraLeona;
	}

	public String getQuerySlaveName()
	{
		return querySlaveName;
	}

	public void setQuerySlaveName(String querySlaveName)
	{
		this.querySlaveName = querySlaveName;
	}

	public boolean isQueryWoman()
	{
		return queryWoman;
	}

	public void setQueryWoman(boolean queryWoman)
	{
		this.queryWoman = queryWoman;
	}
}