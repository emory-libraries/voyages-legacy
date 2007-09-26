<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://tas.library.emory.edu" prefix="s"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://ajaxanywhere.sourceforge.net/" prefix="aa" %>

<h:inputHidden value="#{SearchBean.fakeHiddenForPermlinkRestore}" />

<s:messageBar rendered="false" binding="#{SearchBean.messageBar}" />

<%/*<s:expandableBoxSet expandedId="date">*/%>
<s:expandableBox boxId="date" text="#{res.database_search_selecttimeframe}">

	<t:htmlTag value="table" style="border-collapse: collapse;">
	<t:htmlTag value="tr">
		<t:htmlTag value="td" style="padding: 0px 10px 0px 0px">
			<h:outputText value="#{res.database_search_from}" />
		</t:htmlTag>
		<t:htmlTag value="td" style="padding: 0px 10px 0px 0px;">
			<h:inputText
				value="#{SearchBean.yearFrom}"
				style="width: 60px"
				onkeypress="QueryBuilderGlobals.updateTotal('form:queryBuilder', 1000)" />
		</t:htmlTag>
		<t:htmlTag value="td" style="padding: 0px 10px 0px 0px;">
			<h:outputText value="#{res.database_search_to}" />
		</t:htmlTag>
		<t:htmlTag value="td" style="padding: 0px;">
			<h:inputText
				value="#{SearchBean.yearTo}"
				style="width: 60px"
				onkeypress="QueryBuilderGlobals.updateTotal('form:queryBuilder', 1000)" />
		</t:htmlTag>
	</t:htmlTag>
	</t:htmlTag>
	
</s:expandableBox>

<%/*<s:expandableBox boxId="revision" text="#{res.database_search_selectrevision}">
	<h:outputText value="Choose revision: " />
	<h:selectOneMenu value="#{SearchBean.selectedRevision}"><f:selectItems value="#{SearchBean.revisions}"/></h:selectOneMenu>
</s:expandableBox>
</s:expandableBoxSet>*/%>
<br>
<s:expandableBoxSet expandedId="#{SearchBean.expandedGroup}">
<s:expandableBox boxId="basic" text="#{res.database_search_basic}">

	<s:menuPopup
		customSubmitFunction="animateAttribute"
		id="popupMenuAttributesBeginner"
		items="#{SearchBean.menuAttributesBeginners}"
		onMenuSelected="#{SearchBean.addConditionFromMenu}" />

</s:expandableBox>

<s:expandableBox boxId="general" text="#{res.database_search_general}">

	<s:menuPopup
		customSubmitFunction="animateAttribute"
		id="popupMenuAttributesGeneral"
		items="#{SearchBean.menuAttributesGeneral}"
		onMenuSelected="#{SearchBean.addConditionFromMenu}" />

</s:expandableBox>
</s:expandableBoxSet>
<br>
<script type="text/javascript" language="javascript">

function animateAttribute(menuItem, submitFunction)
{

	var dest = document.getElementById("animateAttributeDest");
	
	var x1 = ElementUtils.getOffsetLeft(menuItem) + 1;
	var y1 = ElementUtils.getOffsetTop(menuItem) + 1;
	var w1 = ElementUtils.getOffsetWidth(menuItem) - 2;
	var h1 = ElementUtils.getOffsetHeight(menuItem) - 2;
	
	var x2 = ElementUtils.getOffsetLeft(dest);
	var y2 = ElementUtils.getOffsetTop(dest);
	var w2 = ElementUtils.getOffsetWidth(dest) - 2;
	var h2 = ElementUtils.getOffsetHeight(dest) - 2;

	var a = document.createElement("div");
	a.style.position = "absolute";
	a.style.left = x1 + "px";
	a.style.top = y1 + "px";
	a.style.width = w1 + "px";
	a.style.height = h1 + "px";
	a.style.border = "1px solid Black";
	a.style.zIndex = "1";
	
	document.body.appendChild(a);
	
	var anim = new Animation(a, 20, 300, submitFunction);
	anim.setPositions(x1, y1, x2, y2);
	anim.setSizes(w1, h1, w2, h2);
	anim.setOpacities(1, 0.1);
	anim.start();

}

</script>

<div id="animateAttributeDest">

<s:expandableBox text="#{res.database_search_currentquery}">

	<s:queryBuilder
		id="queryBuilder"
		query="#{SearchBean.workingQuery}"
		onUpdateTotal="#{SearchBean.updateTotal}" />

	<t:htmlTag value="table" style="border-collapse: collapse;">
	<t:htmlTag value="tr">
		<t:htmlTag value="td" style="padding: 5px 0px 5px 0px;">

			<h:commandButton
				id="buttonSearch"
				action="#{SearchBean.search}"
				value="#{res.database_search_searchbutton}" />

		</t:htmlTag>
		<t:htmlTag value="td" style="padding: 5px 0px 5px 5px;">

			<h:commandButton
				id="buttonAgain"
				action="#{SearchBean.startAgain}"
				value="#{res.database_search_newquery}" />
	
		</t:htmlTag>
		<t:htmlTag value="td" style="padding: 5px 0px 5px 10px;" id="totalContainer" forceId="true">

			<aa:zoneJSF id="total">
				<h:outputText value="#{SearchBean.numberOfResultsText}" />
			</aa:zoneJSF>

		</t:htmlTag>
		<t:htmlTag value="td" style="padding: 0px 0px 0px 5px; display: none;" id="totalUpdateIndicator" forceId="true">
			<h:graphicImage url="../images/ajax-loader.gif" width="16" height="16" alt="" />
		</t:htmlTag>
	</t:htmlTag>
	</t:htmlTag>

</s:expandableBox>

</div>
<br>
<script type="text/javascript" language="javascript">

AjaxAnywhere.prototype.showLoadingMessage = function()
{
	//document.getElementById("totalContainer").style.display = "none";
	document.getElementById("totalUpdateIndicator").style.display = "";
}

AjaxAnywhere.prototype.hideLoadingMessage = function()
{
	//document.getElementById("totalContainer").style.display = "";
	document.getElementById("totalUpdateIndicator").style.display = "none";
}

AjaxAnywhere.prototype.handlePrevousRequestAborted = function()
{
}

</script>

<s:expandableBox text="#{res.database_search_history}">
	
	<s:historyList
		id="history"
		onDelete="#{SearchBean.historyItemDelete}"
		onRestore="#{SearchBean.historyItemRestore}"
		onPermlink="#{SearchBean.historyItemPermlink}"
		history="#{SearchBean.history}" />

</s:expandableBox>