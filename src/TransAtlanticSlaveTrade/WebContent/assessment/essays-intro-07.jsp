<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://tas.library.emory.edu" prefix="s"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

	<title>Essays</title>
	
	<link href="../styles/main.css" rel="stylesheet" type="text/css">
	<link href="../styles/main-menu.css" rel="stylesheet" type="text/css">
	
	<link href="../styles/assessment.css" rel="stylesheet" type="text/css">
	<link href="../styles/assessment-info.css" rel="stylesheet" type="text/css">
	<link href="../styles/assessment-essays.css" rel="stylesheet" type="text/css">
	
	<script src="../scripts/main-menu.js" language="javascript" type="text/javascript"></script>

</head>
<body>
<f:view>
<h:form id="form">

	<f:loadBundle basename="resources" var="res"/>

	<s:siteHeader activeSectionId="assessment">
		<h:outputLink value="../index.faces"><h:outputText value="Home"/></h:outputLink>
		<h:outputLink value="index.faces"><h:outputText value="Assessing the Slave Trade" /></h:outputLink>
		<h:outputText value="Essays" />
	</s:siteHeader>
	
	<div id="content">
	
		<table border="0" cellspacing="0" cellpadding="0" class="essays-layout">
		<tr>
			<td id="essays-left-column">
				<f:param value="essays-intro-07" binding="#{EssaysBean.paramActiveMenuId}" />
				<%@ include file="essays-toc.jsp" %>
			</td>
			<td id="essays-right-column">
				<s:simpleBox>
			
					<h1>A Brief Overview of the Trans-Atlantic Slave Trade</h1>

					<div class="essay-info">
						<span class="essay-author">David Eltis</span>
						<span class="essay-location">(Emory University)</span>,
						<span class="essay-date">2007</span>
					</div>

					<h2>The Middle Passage</h2>
			
					<p>Whatever the route taken, conditions on board reflected the
					outsider status of those held below deck. No European, whether
					convict, indentured servant, or destitute free migrant, was ever
					subjected to the environment which greeted the typical African
					slave upon embarkation. The sexes were separated, kept naked,
					packed close together, and the men were chained for long periods. No
					less than 26 percent of those on board were classed as children, a
					ratio that no other pre-twentieth century migration could come close to
					matching. Except for the illegal period of the trade when
					conditions at times became even worse, slave traders typically
					packed two slaves per ton. While a few voyages sailing from Upper
					Guinea could make a passage to the Americas in three weeks, the
					average duration from all regions of Africa was just over two
					months. Most of the space on a slave ship was absorbed by casks of
					water. Crowded vessels sailing to the Caribbean from West Africa
					first had to sail south before turning north-west and passing
					through the doldrums. In the nineteenth century, improvements in
					sailing technology eventually cut the time in half, but mortality
					remained high in this period because of the illegal nature of the
					business. Throughout the slave trade era, filthy conditions ensured
					endemic gastro-intestinal diseases, and a range of epidemic
					pathogens that, together with periodic breakouts of violent
					resistance, meant that between 12 and 13 percent of those embarked
					did not survive the voyage. Modal mortality fell well below mean
					mortality as catastrophes on a relatively few voyages drove up
					average shipboard deaths. Crew mortality as a percentage of those
					going on board, matched slave mortality over the course of the
					voyage, but as slaves were there for a shorter period of time than
					the crew, mortality rates for slaves (over time) were the more
					severe. The eighteenth-century world was violent and
					life-expectancy was short everywhere given that the global
					mortality revolution was still over the horizon, but the human
					misery quotient generated by the forced movement of millions of
					people in slave ships cannot have been matched by any other human
					activity.</p>

					<table border="0" cellspacing="0" cellpadding="0" class="essay-prev-next">
					<tr>
						<td class="essay-prev">
							<a href="essays-intro-06.faces">The African Side of the Trade</a>
						</td>
						<td class="essay-next">
							<a href="essays-intro-08.faces">The Ending of the Slave Trade</a>
						</td>
					</tr>
					</table>

				</s:simpleBox>
			</td>
		</tr>
		</table>
	</div>

</h:form>
	
</f:view>

<%@ include file="../footer.jsp" %>

</body>
</html>