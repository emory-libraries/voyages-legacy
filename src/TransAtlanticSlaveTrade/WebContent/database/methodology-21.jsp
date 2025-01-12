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
				<f:param value="methodology-21" binding="#{EssaysBean.paramActiveMenuId}" />			
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
					
					<h2 style="text-align:center;">Notes</h2>
					
					<p><span class="superscript">1</span> David Eltis and David Richardson, eds., <font style="font-style: italic;">Extending the Frontiers:
					Essays on the New Transatlantic Slave trade Database</font> (New Haven: Yale University Press, 2008).</p>
					
					<p><span class="superscript">2</span> Joseph Inikori has given “a preferred global figure of 15.4 million for the European 
					slave trade.” Adjusting for those carried to the offshore islands and Europe, this implies
					14.9 million headed for the Americas. See <font style="font-style: italic;">Cahiers d’Etudes africaines</font>, 32 (1993):686.</p>

					
					<p><span class="superscript">3</span> H.C.V. Leibbrandt, <font style="font-style: italic;">Precis of the Archives of the Cape of Good Hope: 
					Journal 1662–1670</font> (Cape Town: Richards, Government Printers, 1901), 127-128.</p>
					
					<p><span class="superscript">4</span> British slave ships trading from Africa to Lisbon include the
					<font style="font-style: italic;">Kent</font> (1731), the <font style="font-style: italic;">Mary</font> (1737), and 
					the <font style="font-style: italic;">Betsey</font> and <font style="font-style: italic;">Hennie</font> (1755).
					For sources see the data set. For the removal of slaves from Ambriz to St. Helena and Sierra Leone, see Kelly Muspratt to Aberdeen, 31 July 1843,
					British National Archives (henceforth BNA), FO84/501.</p>

					
					<p><span class="superscript">5</span> A separate discussion of tonnage is to be found below.</p>
					
					<p><span class="superscript">6</span> One frequently cited shipping list reports that there were no
					children on board several British slave voyages in the 1790s (House of Lords Record Office, House of Lords,
					Main Papers, 28 July 1800). This document, however, omitted to report the children embarked (cf. BNA, T70/1574;
					House of Lords Record Office, House of Lords, Main Papers, 14, 25 June 1799). In Luanda and Benguela Portuguese
					customs reports of departures for Brazil report very low numbers of children embarked. But in this instance,
					“children” refers to infants only, and was above a tax category that indicated exemption from customs duties.</p>
					
					<p><span class="superscript">7</span> Mediterranean passes were issued by most European nations as a result of
					treaties with the Barbary powers. In theory, these documents allowed the vessels of the signing nation to pass
					freely through the “Mediterranean” waters frequented by Barbary corsairs. The passes record vessels’ and 
					captain’s names, tonnage, the date the pass was issued, and intended trading location, such as “Africa” 
					or “Africa and the Americas” or “Barbary” or “Madeira.” See David Richardson, <font style="font-style: italic;">
					The Mediterranean Passes in the Public Records Office </font> (East Ardsley, UK: EP Microform Ltd., 1981). On 
					different numbers reported in the Americas, in the date given for the arrival of slave vessels might be the 
					date the vessel cleared customs, but in fact this could easily occur 2-4 weeks after the actual arrival.</p>

					
					<p><span class="superscript">8</span> Robert Louis Stein, <font style="font-style: italic;">The French Slave Trade in the Eighteenth Century:
					An Old Regime Business</font> (Madison: University of Wisconsin Press, 1979), xv. Some armateurs also may have owned the vessel. French 
					dictionaries define armateurs firstly as those merchants who fit out the ship or expedition and secondly as (ship)owners.  See Emile 
					Littré, <font style="font-style: italic;">Dictionnaire de la Langue Française</font>, vol. I (Paris: Hachette, 1881), 194.</p>
					
					<p><span class="superscript">9</span> One major aid in identifying produce vessels is the Seaman’s Sixpence
					ledgers (BNA, ADM 68 series). One of our team, Jelmer Vos, went through this large series with great care. </p>
					
					<p><span class="superscript">10</span> One of the most widely used contemporary surveys of African regional
					preferences was Lt. Edward Bold, <font style="font-style: italic;">The Merchants and Mariners’ African Guide</font> 
					(J. W. Norie and Co.: London, 1822). For a very detailed private record, see the manuscript in the Sidney Jones Library, 
					University of Liverpool, “Memorandum of African Trade, 1830–1840,” for W.A. Maxwell and Co.</p>

					
					<p><span class="superscript">11</span> See Pierre Verger, <font style="font-style: italic;">Trade Relations Between Bahia de Todos os Santos and
					the Bight of Benin, 17th to the 19th Century</font> (Ibadan: Ibadan University Press, 1976), 358-361; and David Eltis, “The Export of Slaves 
					from Africa, 1820–43,” <font style="font-style: italic;">Journal of Economic History</font> 37 (1977): 417-420, for a fuller discussion.</p>
					
					<p><span class="superscript">12</span> In the downloadable version they would use the data variables
					“Percentage male embarked*” (MALRAT1), “Percentage male disembarked*” (MALRAT3), “Percentage children embarked*”
					(CHILRAT1) and “Percentage children disembarked*” (CHILRAT3) variables instead of the variables, “Percentage male*”
					(MALRAT7) and “Percentage children*” CHILRAT7.</p>
					
					<p><span class="superscript">13</span> For discussion of the general problem see Frederick C. Lane, “Tonnages, Medieval and Modern,” 
					<font style="font-style: italic;">Economic History Review</font>, 17 (1964–5): 213–33.</p>

					
					<p><span class="superscript">14</span> The 1773 legislation is 13 Geo III, c. 74. See W. Salisbury, “Early Tonnage Measurements in England: 
					I, H.M. Customs and Statutory Rules,” <font style="font-style: italic;">Mariner’s Mirror</font>, 52 (1966): 329–40. To convert registered 
					tons into measured tons, we used the formulae in Christopher J. French, “Eighteenth Century Shipping Tonnage Measurements,” 
					<font style="font-style: italic;">Journal of Economic History</font> 33 (1973): 434–43. The 1786 act is 26
					Geo III, c. 60, and its 1835 counterpart is 5 and 6 Will IV, c. 56, which introduced different rules for
					empty ships (s. 2) and those with cargo (s. 6). As the latter appears to have been used on slave ships,
					it is the one adopted here, and a further regression equation allows us to convert post-1836 tonnages
					into the measured ton of 1773–1835. It is: <br>
					Y = 52.86 + (1.22 x X)   N = 63, R² = 0.77 <br>
					where Y = measured tons, 1773–1835, and X = measured tons after 1835.</p>
					
					<p><span class="superscript">15</span> Palmerston to Kennedy, May 4, 1840 (circular dispatch), BNA, FO84/312.</p>

					
					<p><span class="superscript">16</span> H. Chamberlain to Canning, 18 Sept. 1824 (enc.), FO84/31; W. Cole and
					H. W. Macaulay to Palmerston, 1 Jan. 1835 (enc.), BNA, FO84/169; W. W. Lewis and R. Docherty to Palmerston,
					9 Sept. 1837 (enc.), BNA, FO84/214; J. Barrow to Aberdeen, 16 May 1842 (enc.), BNA, FO84/439; G. Jackson and
					F. Grigg to Aberdeen, 2 Jan. 1841 (enc.), BNA, FO84/350.</p>
					
					<p><span class="superscript">17</span> For Spanish into British tonnage, data are limited. The equation is:<br>
				    Y = 71 + (0.86 x X)   N = 32, R² = 0.66.<br>
				    Where Y = British measured tons, 1773–1835, and X = Spanish tons.<br>
					For US and British, see “An Act for Registering and Clearing Vessels, Regulating the Coasting Trade, and for other purposes,” 
					<font style="font-style: italic;">Statutes at Large of the United States of America, 1789-1873</font>, 1 (1789): 55. For a 
					discussion, see W. Salisbury, “Early Tonnage Measurements in England: IV, Rules Used by Shipwrights and Merchants,” 
					<font style="font-style: italic;">Mariner’s Mirror</font> 53 (1967): 260–64.</p>

					
					<p><span class="superscript">18</span> See David Eltis and David Richardson, “Productivity in the Transatlantic Slave Trade,” 
					<font style="font-style: italic;">Explorations in Economic History</font> 32 (1995): 481, for the formula and a discussion.</p>
					
					<p><span class="superscript">19</span> See Lane, “Tonnages, Medieval and Modern,” 217-233 for a discussion.</p>
					
					<p><span class="superscript">20</span>  Stein, French Slave Trade, 40–1; Patrick Villiers, “The Slave and Colonial Trade 
					in France just before the Revolution,” in <font style="font-style: italic;">Slavery and the Rise
					of the Atlantic System</font>, ed. Barbara L. Solow (Cambridge, UK: Cambridge University Press, 1991), 228.</p>

					
					<p><span class="superscript">21</span> See Herbert S. Klein, The <font style="font-style: italic;">Middle Passage: Comparative Studies in the Atlantic
					Slave Trade</font> (Princeton: Princeton University Press, 1978), 29-31.</p>
					
					<p><span class="superscript">22</span> See, for example, H. Chamberlain to Canning, 7 July 1824 (enc.), BNA, FO84/31.</p>
					
					<p><span class="superscript">23</span> David Eltis and David Richardson, “Slave Prices of Newly Arrived Africans in the Americas, 1673-1807: A 
					Quinquennial Series,” in <font style="font-style: italic;">Historical Statistics of the United States</font>, 
					Susan Carter et al., eds. (Cambridge, UK: Cambridge University Press, 2006), 5: 690-691; 
					<font style="font-style: italic;">idem</font>, “Markets for Newly Arrived Slaves in the Americas, 1673-1864,” in 
					<font style="font-style: italic;">Slavery in the Development of	the Americas</font>, David Eltis, Frank Lewis, and Kenneth Sokoloff, eds. 
					(Cambridge, UK: Cambridge University Press, 2004), 183-221.</p>

					
					<p><span class="superscript">24</span> More specifically, the price presented here is the average of the first ten
					males sold off the vessel.</p>		
					
					<table border="0" cellspacing="0" cellpadding="0" class="method-prev-next">
					<tr>
						<td class="method-prev">
							<a href="methodology-20.faces">Appendix</a>
						</td>
						<td class="method-next">

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