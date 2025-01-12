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
				<f:param value="essays-intro-10" binding="#{EssaysBean.paramActiveMenuId}" />
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
					
					<h2>Eventual Abolition</h2>

					<p>Awareness of the insider-outsider divide within Europe
					coincided with the onset of the struggle to suppress first the
					slave trade, and then slavery itself. Early in the British campaign
					to suppress the slave trade, Charles James Fox, a British
					statesman, posed a question for the House of Commons that he
					described as “the foundation for the whole business.” How would
					members of Parliament react, he asked, if “a Bristol ship were to
					go to any part of France…and the democrats (there) were to sell the
					aristocrats, or vice versa, to be carried off to Jamaica….to be
					sold for slaves?” The very posing of this question – and this is
					the earliest documented example from someone close to power – meant
					that the issue was not whether the system was to be questioned, but
					rather, when it would end. In the same year, the Danes passed
					legislation ensuring their own slave trade would become illegal in
					1802. In 1807, the British and US governments made the trade
					illegal. Beginning in 1810, the British established a network of
					treaties that allowed their naval vessels to detain the slave ships
					of other nations. The decisive actions against the traffic
					nevertheless did not come until the mid 1840s and again in 1851,
					when the Cuban and Brazilian governments respectively took serious
					action against the slave trade. In effect, the traffic could be
					halted only by the intervention of the governments of regions that
					were either exporting or importing slaves; it could not be halted
					by naval action alone. Nevertheless, naval intervention did result
					in the capture of nearly 2,000 slave vessels after 1808. Only 544
					of these had slaves on board at the time of capture, but their
					125,000 captives (or strictly, re-captives) were diverted from the
					sugar and coffee plantations for which they were intended, and for
					the most part ended their lives with choices they did not have
					prior to their re-capture.</p>

					<p>Between the 1840s and 1850s, the traffic declined from an
					average of 50,000 a year to 16,000, and after 1860, to half this. It
					was carried on under the Spanish and Portuguese flags, and
					sometimes under no flag at all. By now all governments were
					cooperating to suppress the traffic. From one perspective, the slave
					trade dragged on for many decades after the first action was taken
					against it in 1792. From another, it disappeared in less than a
					century after millennia during which slavery and slave trading had
					been regarded as normal as growing food. Not surprisingly, a few
					decades beyond 1867 saw other (though much smaller) varieties of
					long-distance movement of coerced labor disappear as well. The flow
					of contract laborers from Asia to the Americas ended in 1917; the
					last convict dispatched to exile in the Americas returned from
					Devil’s Island to France in 1952. Notwithstanding the horrors of
					forced labor of the twentieth century and the ongoing smuggling of
					illegal laborers into developed countries, often under terms of debt
					slavery, it is inconceivable that a slave traffic could reappear as
					a central social institution.</p>

					<table border="0" cellspacing="0" cellpadding="0" class="essay-prev-next">
					<tr>
						<td class="essay-prev">
							<a href="essays-intro-09.faces">The Trade’s Influence on Ethnic and Racial Identity</a>
						</td>
						<td class="essay-next">
							<a href="essays-intro-11.faces">Notes</a>
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