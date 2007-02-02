<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://tas.library.emory.edu" prefix="s"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Search</title>
	<link href="../styles/main.css" rel="stylesheet" type="text/css">
	<link href="../styles/main-menu.css" rel="stylesheet" type="text/css">
	<link href="../styles/search.css" rel="stylesheet" type="text/css">
	<link href="../styles/sections.css" rel="stylesheet" type="text/css">
	<link href="../styles/menu.css" rel="stylesheet" type="text/css">
	<link href="../styles/search-query.css" rel="stylesheet" type="text/css">
	<link href="../styles/search-table.css" rel="stylesheet" type="text/css">
	<link href="../styles/map.css" rel="stylesheet" type="text/css">
	<link href="../styles/timeline.css" rel="stylesheet" type="text/css">
	<script src="../scripts/lib/prototype.js" type="text/javascript" language="javascript"></script>
	<script src="../scripts/lib/scriptaculous.js" type="text/javascript" language="javascript"></script>
	<script src="../scripts/lib/aa.js" type="text/javascript" language="javascript"></script>
	<script src="../scripts/utils.js" language="javascript" type="text/javascript"></script>
	<script src="../scripts/main-menu.js" language="javascript" type="text/javascript"></script>
	<script src="../scripts/eventline.js" language="javascript" type="text/javascript"></script>
	<script src="../scripts/tooltip.js" type="text/javascript" language="javascript"></script>
	<script src="../scripts/map.js" language="javascript" type="text/javascript"></script>
	<script src="../scripts/query-builder.js" language="javascript" type="text/javascript"></script>
	<script src="../scripts/tools_map_adds.js" language="javascript" type="text/javascript"></script>
</head>
<body>
<f:view>

	<f:loadBundle basename="SlaveTradeResources" var="res"/>

	<div class="top-bar">
		<table border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td><a href="../index.faces"><img src="../images/logo.png" border="0" width="300" height="100"></a></td>
			<td class="main-menu-container"><s:mainMenuBar menuItems="#{MainMenuBean.mainMenu}" activeSectionId="database" activePageId="search" /></td>
		</tr>
		</table>
	</div>

	<div class="header-links">
		<table border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td><div class="header-link"><a href="estimates.faces">Estimates</a></div></td>
			<td><div class="header-link-active"><a href="search.faces">Database search</a></div></td>
			<td><div class="header-link"><a href="galleryp.faces?obj=1&id=1&set=1&pict=0">Images database</a></div></td>
		</tr>
		</table>
	</div>
	
	<h:form id="form">
	
		<table border="0" cellspacing="0" cellpadding="0" style="width: 100%">
		<tr>
			<td class="side-panel">
				<%@ include file="search-side-panel.jsp" %>
			</td>
			<td class="main-panel">
				<s:messageBar rendered="false" binding="#{SearchBean.messageBar}" />
				<%@ include file="search-main-panel.jsp" %>
			</td>
		</tr>
		</table>
		
	</h:form>

</f:view>
</body>
</html>