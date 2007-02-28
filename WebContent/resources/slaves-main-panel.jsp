<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://tas.library.emory.edu" prefix="s"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>


<t:htmlTag value="table" style="border-collapse: collapse; width: 100%;">
<t:htmlTag value="tr">
	<t:htmlTag value="td">
		<t:htmlTag value="h1"><h:outputText value="List of slaves" /></t:htmlTag>
	</t:htmlTag>
	<t:htmlTag value="td" style="text-align: right;">
	</t:htmlTag>
</t:htmlTag>
</t:htmlTag>

<s:tabletab id="listingEstimates"
	data="#{SlavesTableBean.tableData}"
	sortChanged="#{SlavesTableBean.sortChanged}"
	style="overflow: auto;" />

<t:htmlTag value="div" styleClass="pager">
	<t:htmlTag id="listingEstimates-bottom-bar" value="table" style="border-collapse: collapse; width: 100%;">
		<t:htmlTag value="tr">
			<t:htmlTag value="td" style="padding: 0px; text-align: left">
				<h:outputText value="#{res.estimates_list_showing} " />
				<h:outputText value="#{SlavesTableBean.firstDisplayed}" />
				<h:outputText value="-" />
				<h:outputText value="#{SlavesTableBean.lastDisplayed}" />
				<h:outputText value=" #{res.estimates_list_outof} " />
				<h:outputText value="#{SlavesTableBean.totalRows}" />
				<h:outputText value=" | #{res.estimates_list_show} " />
				<h:selectOneMenu onchange="submit()" value="#{SlavesTableBean.step}">
					<f:selectItem itemValue="10" itemLabel="10" />
					<f:selectItem itemValue="20" itemLabel="20" />
					<f:selectItem itemValue="50" itemLabel="50" />
					<f:selectItem itemValue="100" itemLabel="100" />
					<f:selectItem itemValue="200" itemLabel="200" />
				</h:selectOneMenu>
				<h:outputText value="  #{res.estimates_list_resperpage}" />
			</t:htmlTag>
			<t:htmlTag value="td" style="padding: 0px;">
				<s:tablelinks manager="#{SlavesTableBean.tableManager}"/>
			</t:htmlTag>
		</t:htmlTag>
	</t:htmlTag>
</t:htmlTag>
