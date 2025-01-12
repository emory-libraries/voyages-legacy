<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://tas.library.emory.edu" prefix="s"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>

<t:htmlTag value="h1" styleClass="with-subtitle"><h:outputText value="Table" /></t:htmlTag>

<t:div styleClass="database-table-options">
	<t:htmlTag value="table" style="border-collapse: collapse;">
	<t:htmlTag value="tr">
		<t:htmlTag value="td" style="padding: 5px 10px 5px 0px;">
			<h:outputText value="#{res.estimates_table_rows}"/>
		</t:htmlTag>
		<t:htmlTag value="td" style="padding: 0px 10px 0px 0px;">
			<h:selectOneMenu value="#{TableBean.rowGrouping}">
				<f:selectItem itemLabel="Flag*" itemValue="flagStar"/>
				<f:selectItem itemLabel="#{res.estimates_table_departureBroad}" itemValue="departureBroad" />
				<f:selectItem itemLabel="#{res.estimates_table_departureRegion}" itemValue="departureRegion" />
				<f:selectItem itemLabel="#{res.estimates_table_departure}" itemValue="departure" />
				<f:selectItem itemLabel="#{res.estimates_table_expregions}" itemValue="expRegion" />
				<f:selectItem itemLabel="#{res.database_tableview_expports}" itemValue="expPorts" />
				<f:selectItem itemLabel="#{res.estimates_table_impregionsbreakdown}" itemValue="impRegionBreakdowns" />
				<f:selectItem itemLabel="#{res.estimates_table_impregions}" itemValue="impRegion" />
				<f:selectItem itemLabel="#{res.database_tableview_impports}" itemValue="impPorts" />
				<f:selectItem itemLabel="#{res.estimates_table_individualyears}" itemValue="years1" />
				<f:selectItem itemLabel="#{res.estimates_table_5years}" itemValue="years5" />
				<f:selectItem itemLabel="#{res.estimates_table_10years}" itemValue="years10" />
				<f:selectItem itemLabel="#{res.estimates_table_25years}" itemValue="years25" />
				<f:selectItem itemLabel="#{res.estimates_table_50years}" itemValue="years50" />
				<f:selectItem itemLabel="#{res.estimates_table_100years}" itemValue="years100" />
			</h:selectOneMenu>
		</t:htmlTag>
	</t:htmlTag>
	<t:htmlTag value="tr">
		<t:htmlTag value="td" style="padding: 5px 10px 5px 0px;">
			<h:outputText value="#{res.estimates_table_columns}"/>
		</t:htmlTag>
		<t:htmlTag value="td" style="padding: 0px 10px 0px 0px;">
			<h:selectOneMenu value="#{TableBean.colGrouping}">
				<f:selectItem itemLabel="Flag*" itemValue="flagStar"/>
				<f:selectItem itemLabel="#{res.estimates_table_departureBroad}" itemValue="departureBroad" />
				<f:selectItem itemLabel="#{res.estimates_table_departureRegion}" itemValue="departureRegion" />
				<f:selectItem itemLabel="#{res.estimates_table_departure}" itemValue="departure" />
				<f:selectItem itemLabel="#{res.estimates_table_expregions}" itemValue="expRegion" />
				<f:selectItem itemLabel="#{res.database_tableview_expports}" itemValue="expPorts" />
				<f:selectItem itemLabel="#{res.estimates_table_impregionsbreakdown}" itemValue="impRegionBreakdowns" />
				<f:selectItem itemLabel="#{res.estimates_table_impregions}" itemValue="impRegion" />
				<f:selectItem itemLabel="#{res.database_tableview_impports}" itemValue="impPorts" />
			</h:selectOneMenu>
		</t:htmlTag>
	</t:htmlTag>
	<t:htmlTag value="tr">
		<t:htmlTag value="td" style="padding: 5px 10px 5px 0px;">
			<h:outputText value="#{res.estimates_table_cell}"/>
		</t:htmlTag>
		<t:htmlTag value="td" style="padding: 0px 10px 0px 0px;">
			<h:selectOneMenu value="#{TableBean.showMode}">
				<f:selectItems value="#{TableBean.availableAttributes}" />
			</h:selectOneMenu>
		</t:htmlTag>
		<t:htmlTag value="td" style="padding: 5px 0px 5px 0px;">
			<h:selectBooleanCheckbox value="#{TableBean.omitEmptyRowsAndColumns}" />
		</t:htmlTag>
		<t:htmlTag value="td" style="padding-right: 10px;">
			<h:outputText value="#{res.estimates_table_omitempty}"/>
		</t:htmlTag>
	</t:htmlTag>
	<t:htmlTag value="tr">
		<t:htmlTag value="td">
		</t:htmlTag>
		<t:htmlTag value="td">
			<h:commandButton action="#{TableBean.refreshTable}" value="#{res.estimates_table_show}" />
		</t:htmlTag>
	</t:htmlTag>
	</t:htmlTag>
</t:div>

<t:htmlTag value="div" styleClass="estimates-table">
	<s:simpleTable rows="#{TableBean.table}" />
</t:htmlTag>

<t:htmlTag value="div" style="margin-top: 5px;">
	<t:commandButton value="Download table data" action="#{TableBean.getFileAllData}"
		styleClass="button-save"/>
</t:htmlTag>
