<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://tas.library.emory.edu" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

	<title>Methodology</title>

	<link href="../styles/main.css" rel="stylesheet" type="text/css">
	<link href="../styles/main-menu.css" rel="stylesheet" type="text/css">
	<link href="../styles/database-info.css" rel="stylesheet" type="text/css">
	
	<script src="../scripts/main-menu.js" language="javascript" type="text/javascript"></script>
	<script src="../scripts/tooltip.js" type="text/javascript" language="javascript"></script>

</head>
<body>
<f:view>
<h:form id="main">

	<s:siteHeader activeSectionId="database">
		<h:outputLink value="../index.faces"><h:outputText value="Home"/></h:outputLink>
		<h:outputLink value="search.faces"><h:outputText value="Voyages Database" /></h:outputLink>
		<h:outputText value="Understanding the Database" />
		<h:outputText value="Methodology" />
	</s:siteHeader>
	
	<div id="content">
		<table border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td valign="top" id="left-column">
				<f:param value="methodology-13" binding="#{EssaysBean.paramActiveMenuId}" />
				<%@ include file="guide-menu.jsp" %>
			</td>			
			<td valign="top" id="main-content">
				<s:simpleBox>
				
					<h1>Construction of the Trans-Atlantic Slave Trade Database: Sources and Methods</h1>
					
                    <div class="method-info">
						<span class="method-author"><b>David Eltis</b></span>
						<span class="method-location">(Emory University)</span>,
						<span class="method-date">2010</span>
					</div>
					
					<h2>Inferring Places of Trade</h2>
					
					<p>Establishing the outcome of the voyage is an important prerequisite to inferring information about both places of trade
					and numbers of people purchased. We have a good basis for imputing locations of slave trading as well as estimating the
					numbers of slaves embarked and disembarked. We turn first to the geography of the traffic. For some voyages we know the
					intended ports of trade on the African coast and in the Americas. Private correspondence, newspaper reports, and official
					records of clearances from ports in Europe and the Americas frequently provide such information. Of the 34,948 voyages in
					the data set, at least 1,262 did not embark slaves, usually on account of capture or natural hazard. Of those that did,
					several hundred failed to complete the Middle Passage. The data set provides some information on African place of trade
					for 21,291 voyages or about half of those that are likely to have obtained slaves. While this information surpasses
					current knowledge of the geography of the slave trade, it is possible to glean yet more. For 4,722 voyages that left
					Africa with slaves, or could have done so in the sense that the ship was not wrecked or captured prior to trade beginning,
					we may not know the African place of embarkation, but we do know where the captain intended to buy slaves. If we assume
					that he did in fact do what was intended, then after eliminating those locations that are not easy to group into regions
					(for example the French designation Côte d’Or, which ranged from the Windward Coast to the Bight of Biafra), we are left
					with 25,010 voyages that contain useful information on place of African trade—or about 60 percent of those vessels in
					our sample that actually did or could have left Africa with slaves. Switching to the other side of the Atlantic, the
					data set yields some information on ports of arrival for 24,916 voyages. Once more we have additional information on
					where 5,444 voyages intended to trade their slaves even though we cannot be certain that they actually did so. If we
					assume that captains completed the voyage according to plan, then the sample for places of disembarkation increases
					from 24,916 to 30,295 voyages, or close to 75 percent of all those ventures disembarking captives. </p>
					
					<p>How valid are these assumptions on imputing places of trade from information on intended place of trade? Most slave
					ships traded in the regions where owners declared they would trade. After eliminating captured ships that rarely
					completed their voyages as intended, as well as those ships with very broadly defined destinations ("Americas" or
					"British North America"), a Pearson product moment correlation run on ports of arrival in the Americas generated a
					coefficient of 0.83 (n=9,541). A similar procedure for region of trade in Africa and intended region of trade produced
					a Pearson product moment correlation of 0.714 (n=13,951). Ii should also be kept in mind that merchandise always had to
					be loaded in Europe and the Americas for a specific African region and was often impossible to sell in another region.
					It was unusual to find a specific manufactured good selling in more than one region.<span class="superscript">(10)</span>

					Taken together, this evidence
					appears sufficiently strong to allow some modest inferences for those voyages that we know purchased slaves in Africa,
					or subsequently disembarked slaves in other parts of the Atlantic world, and for which the intended but not the actual
					region of trade is known. </p>

					<p>In addition to these inferential issues, there are also known biases in the geographic data. The British signed
					three treaties with the Portuguese between 1811 and 1817 that contained clauses limiting Portuguese slave traders to
					regions of Africa south of the equator, and the last two of the treaties allowed British cruisers to capture Portuguese
					ships that did not adhere to these provisions. Brazil assumed these treaties when the country became independent in 1822.
					From 1815, slave ships arriving in Bahia, which had strong trading relations with the Bight of Benin or Slave Coast (north
					of the equator), usually reported their African port of departure as Cabinda or Malemba, ports just north of the Congo.
					British officials in Bahia, as well as naval officers patrolling the African coast, were convinced that all Bahian ships
					nevertheless continued to trade on the Slave Coast.<span class="superscript">(11)</span></p>
					
					<table border="0" cellspacing="0" cellpadding="0" class="method-prev-next">
					<tr>
						<td class="method-prev">
							<a href="methodology-12.faces">Voyage Outcomes</a>
						</td>
						<td class="method-next">
							<a href="methodology-14.faces">Imputing Numbers of Slaves</a>
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