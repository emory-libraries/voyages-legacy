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
				<f:param value="essays-grandio" binding="#{EssaysBean.paramActiveMenuId}" />
				<%@ include file="essays-toc.jsp" %>
			</td>
			<td id="essays-right-column">
				<s:simpleBox>
			
					<h1>Dobo: A Liberated African in Nineteenth-Century Havana</h1>
					
					<div class="essay-info">
						<span class="essay-author">Oscar Grandio Moraguez</span>
						<span class="essay-location">(York University)</span>
						<span class="essay-date"></span>
					</div>

					<p>In the sixty years after 1807, many former slave trading nations, in particular the 
					British, launched a major effort to suppress the slave trade. In this era, the Voyages 
					Database shows that naval cruisers captured 1,985 slave ships and conferred liberated 
					African status on 177,000 of those slaves found on board some of these vessels. The fate 
					of these “re-captives,” as they were called, varied considerably. The majority spent their 
					lives as self-sufficient farmers or fishermen. About one quarter of the group migrated to 
					the British West Indies to work as contract laborers. For a few thousand others, they lived 
					as de-facto slaves. The story that follows is for one of the latter group. It is a story 
					that the search interfaces of the Voyages Database have helped reconstruct.</p>

					<p>In March 1826 HMS <font style="font-style:italic;">Speedwell</font> detained the 
					small Spanish schooner named<font style="font-style:italic;">Fingal</font> 
					off the Cuban coast with 58 slaves, the survivors of 61, en route from 
					Cape Mount (modern-day Liberia). After detention, the slaver, its crew, and 
					human cargo were taken into Havana, where the slaves disembarked. In the 
					Voyages Database the details of the voyage are found in VoyageID 558. British 
					and Spanish officials interviewed the captives and recorded their names, ages, 
					and places of habitation. The officials added data on height, sex, and a 
					description of the most obvious cicatrization. From the docks, the newly 
					disembarked Africans were taken to a nearby barracoon and held there by 
					Cuban colonial officials. They then transferred, as emancipated Africans, 
					to residents of the island from whom they were supposed to receive religious 
					and occupational training. After a five-year term, they were to be integrated 
					into Cuban society as free persons.</p>

					<p>The <font style="font-style:italic;">Fingal</font> was one of many slavers 
					adjudicated at the Havana Court of Mixed Commission which, under the 1817 
					Anglo-Spanish treaty, was one of the courts established to interrogate 
					suspected slave vessels detained under the Spanish or British flags and 
					to declare any slaves on board to be emancipados, or liberated Africans. 
					Under the provisions of the treaty, the two governments agreed that the 
					emancipados should become free people in the territory where the adjudication 
					took place. (1) It soon became clear, however, that the lives of the liberated 
					Africans on the island were little different from those of slaves. (2) Certainly, 
					the Cuban authorities did not want to distribute and monitor thousands of free Africans. </p>

					<p>The story of one of the Africans on board the <font style="font-style:italic;">Fingal</font>, 
					a ten-year old boy named Dobo, illustrates the difficulties emancipados faced living under 
					Spanish jurisdiction. Dobo’s story, or Gabino as he was renamed after his arrival in Cuba, 
					is one of the most revealing accounts of the trauma and re-identification experienced by 
					those enslaved in Africa and then “emancipated.” His personal story offers insight into 
					how the slave trade was conducted in Africa, and how emancipated Africans in Cuba lived 
					and worked effectively as slaves.</p>
					
					<p>After the African youth landed in Havana, court officials interviewed him.  Because Dobo’s 
					slaver embarked slaves at Cape Mount, he was described as a Ganga, a term given in Cuba to all 
					African peoples embarked at ports from the Sierra Leone and Windward Coasts. (3) But Dobo also 
					identified himself as a member of the Kongoba nation, and he entered the Court’s Liberated African 
					register as “Ganga-longová” (African Names Database, ID 70345). Dobo came from the interior of 
					Galinhas, an area occupied by Gola people, an ethnolinguistic subgroup of Mel speakers who migrated 
					south to the Galinhas hinterland from the Kongba region (and called themselves Kongoba). They 
					eventually occupied a large section of the region between Lofa and Cape Mount, displacing in 
					their expansion Dei groups. (4)</p>
					
					<p>In 1826, when Dobo was shipped to Cuba, Spanish slave agents were very active on the Galinhas 
					coast, and they traded extensively with local chiefs, especially those from the warring Dei and 
					Gola peoples. The Spanish obtained prisoners of war from both groups. (5) The Dei belonged to the 
					Kwa linguistic family, who occupied in the nineteenth century an area that extended from the coast 
					to the hinterland of Galinhas. Gola expansion in the early nineteenth century displaced the Dei, 
					causing friction and eventually war. (6) Constant skirmishes between the two parties produced 
					continual lots of captives who were marched to the coast. Spanish agents—including the notorious 
					slave trader Pedro Blanco—stockpiled imported goods in coastal entrepôts and maintained small sailing 
					craft to shuttle slaves along the Sierra Leone and Windward Coasts. Vai merchants, who belonged to the 
					Mande linguistic family and who inhabited the coastal area of Galinhas, acted as middlemen between the 
					Europeans and the Dei and Gola slave traders. (7) Blanco and other Spanish brokers had built a profitable 
					working relationship with local Vai chiefs, and rapidly expanded their operations by stationing agents at 
					Cape Mount, Shebar, Digby (near Monrovia), Young Sestos, and nearby outlets.</p>
					
					<p>There is no information on how young Dobo entered slavery in his Gola homeland. He might have been 
					a victim of a Dei raid on a Gola settlement, or sold by relatives, or born as a slave and traded from 
					his original community. It was common for Dei or Gola rulers who desired imported goods to raid 
					neighboring peoples and send their captives to the coast in exchange for items such as salt, 
					tobacco, and various metals, particularly brass objects. Not all slaves traded from the interior, 
					however, were captured in raids or prisoners of war. Some people in the Galinhas hinterland 
					were born into slavery; some entered slave status by being orphaned. The practice of bartering 
					children—and even sometimes adults—for food during famines was not unknown. Children were also 
					sold to compensate for homicides and other crimes committed by relatives. Adults expelled from 
					their original kin groups might also risk enslavement when forced to leave their own kin groups 
					because of quarrels, threats, hunger, or criminal activity. Dobo could have entered slavery in 
					any one of these ways. (8)</p>
					
					<p>Before reaching the Galinhas coast, Dobo had to travel many hundred miles from the 
					Gola hinterland, and almost certainly he was bought and sold more than once during his 
					journey. From his first capture, Dobo found himself surrounded by strangers. Dobo was 
					probably transferred from Gola or Dei traders to Vai middlemen who marched him to the 
					coast. As a newcomer in alien communities and separated from his own ethnic group, Dobo 
					must have had to redefine a new identity, social personality, and status. New surroundings 
					usually implied new customs, languages, or dialects. (9) </p>
					
					<p>When he arrived on the coast, Dobo was housed in a slave factory belonging to one of the 
					Spanish factors around Galinhas before he was traded for imported commodities. Cape Mount, 
					from whence the <font style="font-style:italic;">Fingal</font> had sailed, functioned as an 
					auxiliary shipping point of the main slave entrepôt in the area, the Galinhas River, between 
					the Mano and Moa Rivers. At Cape Mount, several slave depots operated in the 1820s and 1830s, 
					including one owned by Pedro Blanco. Once he was bartered for merchandise, Dobo and 60 others 
					were taken on board the slaver by canoes. Conditions must have been horrific on the schooner 
					<font style="font-style:italic;">Fingal</font>, and three slaves died during the Atlantic crossing.</p>
					
					<p>The treaty of 1817 clearly stipulated that emancipados, once disembarked, came under the 
					jurisdiction of the nation in whose territory the court was located. Dobo and his African 
					companions thus remained under the custody of Spain after registration. They were assigned 
					a new “Christian” name and a tin ticket for identification, and began a five-year labor 
					term under some responsible trustee “to ease their transition to civilization.” But these 
					terms could be extended legally to a maximum of three more years, and other mechanisms 
					existed to prolong servitude indefinitely. Spanish colonial law also decreed that trustees 
					needed to feed and clothe their emancipados, provide medical care, instruction in Catholicism 
					and train them in an occupation. (10) As Dobo’s story will show, many, if not most emancipados, 
					fell victim to corruption and fraud. Trustees often kept them in servitude and paid them only 
					nominal monthly wages after the expiration of their term.</p>
					
					<p>Dobo was consigned for five years to Luisa Aper de la Paz, a rich widow from Havana. She paid 
					612 pesos to bribe the authorities, and used Dobo, now named Gabino, as a water-carrier, and paid 
					him only one peso per day. (11) After the end of his five-year term, she paid further bribes to 
					extend Gabino’s services for two more five-year terms. (12) Extending Gabino’s involuntary servitude 
					gave Donna Aper de la Paz greater financial flexibility than purchasing a slave: slaves required 
					large outlays of cash, and owners bore the risk that slaves would die or escape. Hiring free 
					laborers in Havana was also very expensive, and wageworkers could not be controlled or exploited 
					like emancipados. The emancipado system enabled “trustees” to obtain labor at below-market rates 
					and avoid the long-term commitments, preoccupations, and maintenance costs associated with slavery. </p>
					
					<p>As an emancipado, Gabino was in limbo, neither free nor slave. His legal status and day-to-day 
					existence combined the worst of two worlds. He did not enjoy the freedoms and higher wages of free 
					persons of color, nor did he have the few legal and material protections that most slaves could 
					reasonably expect. Further, Gabino needed to pay taxes to the colonial government. After taxes, 
					he lost two-thirds of his one-dollar per day salary. Gabino also was isolated from the slave 
					community in Havana, which devised their own solidarity mechanisms to survive, resist and 
					pursue freedom. Emancipados were the object of contempt to both free blacks and slaves. In 
					fact, blacks in Cuba used “emancipado” as a derogatory term (13), and people like Gabino were 
					at the bottom of Cuba’s social ladder. They had no protection in the Cuban colonial legal 
					system, and their only hope for redress was to gain the attention of a foreign official, 
					such as the Superintendent of Liberated Africans in Havana.</p>
					
					<p>The post of Superintendent was created by an additional Anglo-Spanish anti-slave trade 
					treaty signed in 1835. The new treaty allowed the adjudication of vessels with slave trading 
					equipment on board—vessels thus could be seized before they embarked slaves. (14) The treaty 
					reflected the British government’s renewed interest in the welfare of emancipados. It stipulated 
					that emancipados would hereafter come under the authority of the capturing nation. Further, every 
					six months Spanish officials were required to provide the Havana Mixed Commission with updated 
					registers of emancipados. (15) The new measures had an immediate impact: British officials no 
					longer transferred emancipados to Spanish colonial authorities; they instead began relocating 
					them to several British Caribbean possessions, in which slavery had been abolished (1834). 
					A Superintendent was appointed to oversee these arrangements.</p>
					
					<p>From the mid-1830s until 1841 two well-known British abolitionists, Dr. Richard Robert Madden 
					and David Turnbull, occupied the post of Superintendent. The two were members of the British and 
					Foreign Anti-Slavery Society and came to Cuba ideologically committed to the interests of liberated 
					Africans. Turnbull replaced Madden in 1840 and also held the position of British Consul in Havana. 
					The British foreign secretary instructed Turnbull to persuade Cuban authorities to obey the treaties 
					of 1817 and 1835, and to investigate the conditions of the emancipados and protect their rights. 
					However, emancipados like Gabino, landed in Havana before 1835, remained under the jurisdiction of 
					Spanish law and Spanish officials. The Superintendent could do no more than investigate and denounce 
					cases that were in clear violation of the earlier treaty. Many emancipados learned of the Superintendent’s 
					position and sought his assistance. One of them was Gabino.</p>
					
					<p>When Mrs. Aper de la Paz died in 1840, Gabino’s livelihood deteriorated. Felix Piñero, who inherited 
					the widow’s property, cut off Dobo’s tiny remuneration as a water-carrier, in violation of all conventions 
					regarding liberated Africans. (16) Gabino’s desperation led him to appeal to Superintendent Turnbull. In his 
					declaration, Gabino narrated the abuse to which he had been subjected since his arrival in Havana, then as 
					Dobo. Turnbull immediately sent a strong note of protest to Cuban officials claiming for him “the immediate 
					and unconditional enjoyment of the freedom which was guaranteed to him by the treaties in force between 
					Spain and Great Britain.” By 1841, Gabino had been in Cuba for fifteen years, and understood that the Spanish 
					legal system was closed to him. He searched for an alternative method of redress and found one.</p>
					
					<p>In his note to the Captain General in Havana, Principe de Anglona, Turnbull also claimed that Gabino 
					was entitled to all monies held from him unlawfully. The Captain General responded by declaring Turnbull 
					to be a “persona non grata,” a danger to the island’s security because of his links with “the people of 
					color.” Turnbull was also told in this letter that his intervention in Gabino’s case “supposes that you 
					are qualified to listen to complaints and to offer protection to the people of color, and to support 
					their pretensions.” The Captain General feared that “such a state of things might loosen the ties of 
					subordination and obedience among emancipados.” (17) The Captain General thus opposed Turnbull’s defense 
					of emancipados’ rights, without addressing the specifics of Gabino’s case. (18) Turnbull had in fact 
					previously traveled through the island to check on the conditions of the emancipados working on plantations, 
					and had listened to the complaints of many other emancipados in Havana. He was also seen accompanied by 
					white Cubans who openly opposed slavery in the island. The Cuban colonial government wanted Turnbull to 
					leave the island, and his official complaint in Gabino’s case gave them the opportunity. The Superintendent 
					was forced to abandon his offices at the British Consulate in Havana and move to a British vessel anchored 
					in the harbor, as he was not allowed to remain on Spanish soil. Eventually, he had to sail to Jamaica. (19)  </p>
					
					<p>Turnbull, though forced to leave Cuba, achieved his immediate objective: Gabino, at the age of 25, won 
					his freedom. A letter from the Anglona’s successor as Captain General, Geronimo Valdes, to the British Consulate, 
					dated June 23, 1841, stated that a letter of emancipation had been issued for Gabino. The report also mentioned 
					that Gabino had married a slave, Candelaria. (20) Gabino had achieved the nearly impossible: he had overcome all 
					the barriers imposed on emancipados by the Spanish colonial government and gained his freedom. His decision to 
					bypass the Spanish legal system, importantly, produced a diplomatic crisis between the British and Spanish governments. </p>
					
					<p>In late June 1841 Gabino began a new life as a free black man with a wife, but the Spanish colonial government 
					did not forget him. A few months after his letter of emancipation, he was accused of conspiracy to foment a black 
					uprising and condemned to eight years in prison. He would need to serve his sentence in a prison in Ceuta, a Spanish 
					enclave in northern Morocco, far from his wife. Paradoxically, Gabino now returned to the African continent as a 
					captive. His freedom had lasted only a few months. In November 1841 he left Havana imprisoned on board the Spanish 
					naval warship <font style="font-style:italic;">Correo 4</font>. It was the second time that Gabino crossed the 
					Atlantic, in both cases as a captive. The conditions on board the <font style="font-style:italic;">Correo 4</font> 
					were undoubtedly as bad as those on the slaving vessel Fingal. Gabino was gravely ill when the naval ship arrived 
					at the Spanish port Cadiz. A few days after disembarkation, he died in the military prison. (21) Gabino, born Dobo, 
					never reached the African continent. His story in Africa and in the Americas illustrates the fate of thousands of 
					liberated Africans in Cuba. Dobo did not arrive in Cuba as a slave, but like other emancipados, he lost his African 
					name, had his rights ignored, and was not remunerated for his work. Dobo, a free youth from Gola, became Gabino, a 
					de-facto slave in Cuba.</p>
					
					<br>
					
					<div class="essay-note">						
						<p style="text-align:center">Notes</p>																		
					</div>
					
					<p class="note">1 Leslie Bethell, “The Mixed Commissions for the Suppression of the Transatlantic Slave Trade in the Nineteenth Century,”  
					<font style="font-style: italic;">Journal of African History 7 </font>(1966): 79-93; David R. Murray, 
					<font style="font-style: italic;">Odious Commerce: Britain, Spain, and the abolition of the Cuban slave trade </font> 
					(Cambridge, UK: Cambridge University Press, 1972).</p>
					
					<p class="note">2 Archivo Histórico Nacional, Madrid (hereafter AHNM), Estado, Esclavitud, Legajo 8034/21.</p>
					
					<p class="note">3 The term seems to come from the Gbangá River in Sierra Leone or a toponymic that refers to the zone of Gbangbama, 
					where that river comes into the sea. See Alessandra Basso Ortiz, “Los Gangá Longobá: el Nacimiento de los Dioses,”  
					<font style="font-style: italic;">Boletín Antropológico </font>52 (2001): 195-208. </p>
					
					<p class="note">4 On Gola, see Warren L. d’Azevedo, “A Tribal Reaction to Nationalism (Part I),” 
					<font style="font-style: italic;">Liberian Studies Journal </font>1 (1969): 1-21; and 
					Svend E. Holsoe, “A Study of Relations between Settlers and Indigenous People in Western Liberia, 1821-1847,”  
					<font style="font-style: italic;">African Historical Studies</font> 4 (1971): 331-62.</p>
					
					<p class="note">5 Lino Novás Calvo, <font style="font-style: italic;">Pedro Blanco, el Negrero </font> (Havana: Editorial Letras Cubanas, 1997); 
					William Renwick Riddell, “Observation on Slavery and Privateering,” <font style="font-style: italic;">Journal of Negro History</font> 15 (1930): 337-71.</p>
					
					<p class="note">6 See Svend E. Holsoe, “Chiefdoms and Clan Maps of Western Liberia,” 
					<font style="font-style: italic;">Liberian Studies Journal </font>1 (1969): 23-39.	</p>
					
					<p class="note">7 On Vai, see: Svend E. Holsoe, “The cassava-leaf people: An ethno-historical study of 
					the Vai people with particular emphasis on the Tewo chiefdom” (Ph.D. diss., Boston University, 1967).</p>
					
					<p class="note">8 For Sierra Leone and Liberia, see Svend E. Holsoe, “Slavery and Economic Response among the Vai 
					(Liberia and Sierra Leone)” in <font style="font-style: italic;">Slavery in Africa: Historical and Anthropological Perspectives, </font>
					eds. Suzanne Miers and Igor Kopytoff (Madison: University of Wisconsin Press, 1977), 287-303. </p>
					
					<p class="note">9 For slavery in Africa, see: Igor Kopytoff and Suzanne Miers, “African ‘Slavery’ as an Institution of Marginality,” 
					in <font style="font-style: italic;">Slavery in Africa, </font>eds. Miers and Kopytoff, 3-78. </p>
					
					<p class="note">10 See: José Gutierrez de la Concha Habana, <font style="font-style: italic;">Memoria sobre el ramo de 
					emancipados de la Isla de Cuba </font>(Madrid: Imprenta de la América, 1861).</p>
					
					<p class="note">11 Some reports have mentioned that Spanish officials were selling emancipados in Havana during the 1830s for 
					nine ounces of gold, or about one-third the cost of a slave.  Archivo Nacional de Cuba (hereafter ANC), Gobierno Superior Civil, Legajo 
					105/5363; ANC, Reales Órdenes y Cédulas, Legajo 100/14. </p>
					
					<p class="note">12 AHNM, Estado, Esclavitud, Legajo 8019/39.</p>
					
					<p class="note">13 Inés Roldán de Montaud, “Origen, evolución y supresión del grupo de negros ‘emancipados’ en Cuba, 1817-1870,” 
					<font style="font-style: italic;">Revista de Indias </font>42 (1982): 580. </p>
					
					<p class="note">14 Foreign Office, “Great Britain: Treaty between His Majesty and the Queen Regent of Spain, during the minority 
					of her daughter, Donna Isabella the Second, Queen of Spain, for the Abolition of the Slave Trade, signed at Madrid, June 28, 1835,” 
					<font style="font-style: italic;">British and Foreign State Papers, </font>1834-35, 23: 343-71; AHNM, Ultramar, Legajo 3547/6. </p>
					
					<p class="note">15 ANC, Reales Órdenes y Cédulas, Legajo 51/123.</p>
					
					<p class="note">16 This paragraph from AHNM, Estado, Esclavitud, Legajo 8019/39/4.</p>
					
					<p class="note">17 Ibid., 8019/39/1-7.</p>
					
					<p class="note">18 Before his appointment as British Consul in Havana, Turnbull spent almost two years traveling in Cuba and 
					writing his best-known book, <font style="font-style: italic;">Travels in the West: Cuba; with Notices 
					of Porto Rico and the Slave Trade </font>(London: Longman, Orme, Brown, Green, and Longmans, 1840). </p>
					
					<p class="note">19 Turnbull would return to Cuba in 1842 from the Bahamas.  Accompanied by several free blacks, he hoped to  
					free some emancipados who were held as slaves. For his arrest and deportation, see Hugh Thomas,  
					<font style="font-style: italic;">The Slave Trade: the story of the Atlantic slave trade, 1440-1870 </font>(New York: Simon and Schuster, 1997), 668.</p>
					
					<p class="note">20 AHNM, Estado, Esclavitud, Legajo 8019/39/10.</p>
					
					<p class="note">21 AHNM, Estado, Esclavitud, Legajo 8019/39/15-18.</p>				
				
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