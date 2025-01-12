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
				<f:param value="methodology-07" binding="#{EssaysBean.paramActiveMenuId}" />			
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
					
					<h2>Names</h2>
					
					<p>Orthography is also a major issue in any historical database. For most voyage entries in the new data set, we maintained
					the spelling or wording of the names of vessels, captains, and merchants. Exceptions include corrections of obvious mistakes
					arising from the fact that the recorder of the information was often less than fluent in the language of the nation to which
					the vessel belonged. And in the Portuguese and Brazilian cases we took the more drastic step of standardizing all entries according
					to modern Portuguese conventions. Even without these problems, variations of spelling were, of course, common before the nineteenth
					century and, as discussed below, we have standardized some spellings to facilitate sorting. We removed the definite article from
					vessel names in all languages. Occasionally sources reported different names for the same vessel. The Pretty Betty is also identified,
					for example, as the Pretty Peggy. In such cases, we separated the two names with "(a)" to indicate an alternate name/spelling, as in
					Pretty Betty (a) Pretty Peggy. We attempted to maintain the consistency of captains’ and owners’ names throughout their voyage histories
					to facilitate the user’s sorting of the file. Again, for some entries we placed alternate spellings after "(a)."</p>
					
					<p>We included three variables for captains in the data set. The ordering of these names indicates the order these men appeared,
					chronologically, to be associated with the voyage. For some British and French voyages, sources list different captains during
					the ship’s outfitting. A slave vessel may have cleared customs under the command of one captain but sailed to Africa under a
					subsequent captain. Evidence from the British trade suggests that for some voyages the first captain, rather than leaving the
					vessel, worked as a supercargo for the voyage. Therefore, we decided to keep the names and their ordering in the data set. The
					user will not be able to determine which captains were in charge of the vessels on the Middle Passage for all voyages. Some
					of the captains died before slaving on the coast; other captains’ listings include the man who commanded the vessels on the
					homeward passages from the Americas. We kept all abbreviations in captains’ names, consistent with the documentary evidence.
					From the Mettas-Daget catalog of French slave voyages, we attempted to maintain a consistent spelling of captains’ names as
					indicated in the index to the two-volume French set. Double surnames and indicators of rank (Sieur, Chevalier, de, de la) pose
					problems singular to the organization of the French subset. In short, the spelling of names is not fixed in the French language.
					We followed the spellings preferred in the index, though we transcribed first-name abbreviations as indicated in the documentary
					evidence. To facilitate sorting the Voyages Database’ file by captains’ names, we maintained the ordering of surnames as indicated
					in the published index. </p>
					
					<p>Similarly, we followed, as closely as possible, the spelling and ordering of ship owners’ names given in the documentary evidence.
					The user will note some voyages "owned" by the RAC, Compagnie du Sénégal, or other monopoly trading groups. For these voyages, companies
					hired the vessels from ship owners and a group of partners or shareholders invested in the trading cargoes. The names of these individuals
					are not known. For most of the slave voyages in the data set, however, merchants owned fractional shares of the vessel and trading cargo.
					The listing of merchants in the set probably reflects the size of each shareholder, though this fact can be confirmed only for a few
					voyages. For some voyages we only know the principal owner "and Company." This is true particularly for many Bristol (England) voyages.
					To indicate the fact that the voyage was owned and/or organized by additional owners, we placed an asterisk, *, at the end of the last
					recorded merchant’s name, as in "Jones, Thomas*" (read: "Thomas Jones and Company"). For some other British voyages, father–son partnerships
					are listed, as in "Richard Farr, Sons and Company." For such voyages, we included the second owner with surname "Farr" as "Farr (Son)" and
					indicated that subsequent partners may be present by adding an asterisk after the third owner, "Farr (Son)*." Similarly, for the Dutch firm
					Jan Swart & Zoon (son), we entered the second owner as "Swart (Zoon)."</p>
					
					<p>Ownership information contained in the French slave trade documents presents additional problems for the researcher. Unlike the
					British trade, in which many records of extended partnerships survive, French documents usually list single armateurs who organized
					slave voyages. According to Stein, an armateur was "the merchant who organized and usually financed a large part of the slaving
					expedition."<span class="superscript">(8)</span>
					Other merchant-investors, therefore, are not recorded in the documents. In cases in which additional owners are
					suggested by the words "company" (Compagnie or Cie.) or "associates" (consorts), we inserted an asterisk. Many French slave
					voyages were organized by family members. French documents include these familial relationships: brother(s) (frères), father (père),
					wife (épouse), widow (veuve or vve), eldest son (fils aîné), and son(s) (fils). These relationships are integral to the archival
					record and have been maintained in the Voyages Database. Because the French words frères and fils can imply multiple brothers and
					sons, we inserted an asterisk in the second ownership column, as in "Portier (Frères)*." In some cases, the document may record
					owners as "Brunaud Frères et Compagnie." For these few cases, we inserted a double asterisk as in "Brunaud (Frères)**." Some
					documents report the names of the propriéteurs who hired out their vessels to the armateurs, the affreteurs who freighted the
					slave ships, or the local agents who transacted business for absentee armateurs. We excluded these names from Voyages Database.
					French owners’ names often include complex double surnames and aristocratic titles. As in the case of French captains’ names,
					we attempted to preserve the spelling in the original documents while following the Mettas-Daget index to standardize the basic
					spelling and name ordering. We did this to allow the user to analyze ownership patterns easily through an A–Z owner–variable
					sort. The user should refer to the index of volume 2 of Mettas-Daget’s Répertoire for a complete listing of the variant spellings
					of French merchants’ names.</p>

					
					<p>The common multiple Iberian names-- whether  vessels, captains or owners—causes particular problems for researchers. Spanish
					and Portuguese names often incorporate the surnames of both the father and the mother. In the case of ship’s names, length
					stemmed from the habit of introducing multiple saints’ names and objects of religious veneration into the name of the vessel -
					at least before 1800. Length is a problem in this context because the official record of the vessel (and person), as well as
					the common usage of names by sailors and owners often recorded and employed only fragments of the full name, but unfortunately
					not always the same fragments. Users are consequently warned that it is often more difficult to track and eliminate double-counting
					of Iberian vessels and people than it is of their non-Iberian counterparts. Upward bias from double-counting is thus more likely in
					the records of the Iberian than of the non-Iberian slave trades. </p>
				
					<table border="0" cellspacing="0" cellpadding="0" class="method-prev-next">
					<tr>
						<td class="method-prev">
							<a href="methodology-06.faces">Dates</a>
						</td>
						<td class="method-next">
							<a href="methodology-08.faces">Imputed Variables</a>
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