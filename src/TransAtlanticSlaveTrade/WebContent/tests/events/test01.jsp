<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://tas.library.emory.edu" prefix="s"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>EventLine test</title>

<script src="../../eventline.js" language="javascript" type="text/javascript"></script>
<script src="../../utils.js" language="javascript" type="text/javascript"></script>

<style type="text/css">

body {
	margin: 0px;
	font-size: 8pt;
	font-family: Verdana; }
	
table.event-line-left-label {
	-background-color: #AAFFFF; }

table.event-line-top-label {
	-background-color: #AAFFFF; }

div.event-line-graph {
	-background-color: #EEFFFF; }
	
div.event-line-indicator {
	-moz-opacity: 0.1;
	background-color: #333333; }
	
div.event-line-indicator-container {
	-background-color: #EEEEEE;}

div.event-line-indicator-label {
	background-color: White;
	border: 1px solid Black;
	padding: 5px; }
	
div.event-line-mark {
	cursor: pointer;
	background-image: url(../../event-line-mark.png);
	background-repeat: no-repeat; }

div.event-line-mark-pressed {
	cursor: pointer;
	background-image: url(../../event-line-mark-pressed.png);
	background-repeat: no-repeat; }

</style>

</head>
<body>
<f:view>
	<h:form id="form">
	
		<s:eventLine
			graphHeight="#{EventLineTestBean1.graphHeight}"
			barWidth="#{EventLineTestBean1.barWidth}"
			horizontalLabels="#{EventLineTestBean1.horizontalLabels}"
			verticalLabels="#{EventLineTestBean1.verticalLabels}"
			graphs="#{EventLineTestBean1.graphs}"
			events="#{EventLineTestBean1.events}" />
			
		<div style="margin-left: 70px; margin-top: 10px">
			<h:commandButton action="#{EventLineTestBean1.moveLeft}" value="&lt;" />
			<h:commandButton action="#{EventLineTestBean1.zoomPlus}" value="+" />
			<h:commandButton action="#{EventLineTestBean1.zoomMinus}" value="-" />
			<h:commandButton action="#{EventLineTestBean1.moveRight}" value="&gt;" />
		</div>
			
		<div id="debug"></div>

	</h:form>
</f:view>
</body>
</html>