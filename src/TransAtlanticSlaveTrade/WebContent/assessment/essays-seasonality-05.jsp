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
				<f:param value="essays-seasonality-05" binding="#{EssaysBean.paramActiveMenuId}" />
				<%@ include file="essays-toc.jsp" %>
			</td>
			<td id="essays-right-column">
				<s:simpleBox>
			
					<h1>Seasonality in the Trans-Atlantic Slave Trade</h1>
					
					<div class="essay-info">
						<span class="essay-author">Stephen D. Behrendt</span>
						<span class="essay-location">(Victoria University of Wellington)</span>,
						<span class="essay-date">2008</span>
					</div>
					
					<h2>Agricultural calendars and labor requirements</h2>

					<p>Agricultural production requires different numbers of
					farmer-hours, “labor inputs,” at various stages in plants’ growth
					cycles. Labor intensity differs by the type of crop and the
					ecosystem in which the plant lives. Crops planted annually in
					shifting agricultural communities required heavy labor inputs
					clearing land and sowing seed. In regions prone to unexpected
					drought, all available people hurried to sow during the season’s
					first rains. After the planting season, families weeded and
					controlled insect and bird pests—work less dependent upon physical
					strength. Some crops required long workweeks to transplant shoots
					from seedbeds to fields. On both sides of the Atlantic, farmers
					worked intensively during dry season cane, fruit, berry, leaf, or
					cereal harvests.</p>

					<p>African crops require varying numbers of farmer-hours during
					land clearing, planting (“crop establishment”), weeding, and
					harvesting/threshing. Sorghum and millet, often inter-cropped,
					demanded intense labor during the summer rains when the cereals
					were planted and weeded. Threshing the cereals demanded fewer
					worker-hours. In the coastal West African rice region, from July to
					early October villagers cut mangrove trees, built dikes, and
					transplanted rice to paddies. Labor demand intensity is highest
					during the October/early November harvest. Rice is the most
					labor-consuming African crop. Men and women plant maize each year;
					along the Gold Coast and in the Bight of Benin the spring and fall
					equinoxes marked the beginning of the planting weeks. Weeding was
					the most labor-intensive activity in maize cultivation, but, as
					with other crops, children helped weed plants and eradicate pests.
					Growing yams in the Biafran hinterland requires the greatest labor
					inputs during the clearing/planting (January-April) and harvesting
					(August-October) seasons, and the fewest hours of crop work during
					spring/summer weeding.</p>

					<p>New World merchant-planters’ demand for workers increased
					during dry seasons north and south of the equator, when crops
					ripened, dried, and needed to be harvested. Sugar was the most
					important slave-produced crop, the one with the longest crop cycle,
					and the one that placed the greatest short-term demands on workers.
					Hours worked in cane-holing, trenching, and cutting tripled those
					hours worked by modern factory hands. Intensive tobacco work
					occurred when men and women transplanted tobacco stalks to the
					fields and they cut and stripped tobacco leaves. In the
					rice-growing Carolina/Georgia Lowcountry, Surinam and Maranhão,
					labor intensity increased when workers sowed seed, hoed wet fields,
					and harvested and processed rice. Planters throughout the
					Plantation Americas hired seasonal workers (“hired slaves”) to help
					harvest and process cash crops.</p>

					<table border="0" cellspacing="0" cellpadding="0" class="essay-prev-next">
					<tr>
						<td class="essay-prev">
							<a href="essays-seasonality-04.faces">Rainfall, crop type and agricultural calendars</a>
						</td>
						<td class="essay-next">
							<a href="essays-seasonality-06.faces">Provisioning-slaving seasons</a>
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