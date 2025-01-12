<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://tas.library.emory.edu" prefix="s"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Slave voyages - Administration panel</title>
	<link href="../styles/main.css" rel="stylesheet" type="text/css">
	<link href="../admin/main.css" rel="stylesheet" type="text/css">
	<link href="../admin/tabs.css" rel="stylesheet" type="text/css">
	<link href="../admin/edit.css" rel="stylesheet" type="text/css">
</head>
<body style="margin: 15px;">
<f:view>
<h:form id="main">

	<h1>Slave voyages - <h:outputText value="Administration panel" rendered = "#{AdminSubmissionBean.isAdmin}"/> 
	<h:outputText value="Reviewer panel" rendered = "#{!AdminSubmissionBean.isAdmin}"/> </h1>
	<br> 
	<h:commandButton value="Download published and approved" rendered = "#{AdminSubmissionBean.isAdmin}" action="#{AdminSubmissionBean.getFilePublishedandApprovedData}" />
	<t:outputText escape="false" value="&nbsp;"/>
	<h:commandButton value="Download full" rendered = "#{AdminSubmissionBean.isAdmin}" action="#{AdminSubmissionBean.getFileAllData}" />
	<t:outputText escape="false" value="&nbsp;"/>
	<h:commandButton value="Logout" action="#{AdminSubmissionBean.logout}"/>	
	<br>
	<br>
	
	<t:htmlTag rendered="#{!AdminSubmissionBean.isAdmin}" value="div">
		<s:tabBar id="bar" selectedTabId="#{AdminSubmissionBean.selectedTab}" onTabChanged="#{AdminSubmissionBean.onTabChanged}">

			<s:tab text="Requests list" tabId="requests" />			
		</s:tabBar>
	</t:htmlTag>
	
	<t:htmlTag rendered="#{AdminSubmissionBean.isAdmin}" value="div">
	<tr>
		<s:tabBar id="bar" selectedTabId="#{AdminSubmissionBean.selectedTab}" onTabChanged="#{AdminSubmissionBean.onTabChanged}">
			<s:tab text="Voyages list" tabId="voyages" />
			<s:tab text="Requests list" tabId="requests" />
			<s:tab text="Users list" tabId="users" />
			<s:tab text="Source Codes" tabId="source_codes" />
			<s:tab text="Publish new database revision" tabId="publish" />						
		</s:tabBar>	
		</tr>
	</t:htmlTag>	
	
	<h:panelGroup rendered="#{AdminSubmissionBean.voyagesListSelected}">
	
		
		<f:verbatim>

		<br>
		</f:verbatim>
		<s:grid id="voyges" 
			columns="#{AdminVoyagesListBean.columns}"
			rows="#{AdminVoyagesListBean.rows}"
			action="#{AdminVoyageBean.openVoyageAction}"
			onOpenRow="#{AdminVoyageBean.openVoyage}" />
		<f:verbatim>	
		<br>
		</f:verbatim>
		<s:pager id="voyagesPager"
			maxShownPages="15"
			currentPage="#{AdminVoyagesListBean.currentPage}"
			firstRecord="#{AdminVoyagesListBean.firstRecordIndex}"
			lastRecord="#{AdminVoyagesListBean.lastRecordIndex}"
			pageSize="#{AdminVoyagesListBean.pageSize}" />
	
	
	</h:panelGroup>
	<br>
	<br>
	<h:panelGroup rendered="#{AdminSubmissionBean.requestsListSelected}">
		<div value="div" style="margin-top: 10px;">
			<table border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td style="padding-right: 10px">					
					<h:selectOneMenu value="#{AdminSubmissionBean.requestType}">
						<f:selectItems value="#{AdminSubmissionBean.requestTypes}"/>
					</h:selectOneMenu>
				</td>				
				<td style="padding-right: 10px">
						<h:selectOneMenu value="#{AdminSubmissionBean.requestStatus}">
							<f:selectItem itemLabel="Status: all" itemValue="1"/>
							<f:selectItem itemLabel="Not yet assigned" itemValue="3"/>
							<f:selectItem itemLabel="Under review" itemValue="2"/>
							<f:selectItem itemLabel="Review completed" itemValue="5"/>
							<f:selectItem itemLabel="Final decision" itemValue="4"/>
						</h:selectOneMenu></td>
				<td style="padding-right: 5px"> <h:commandButton value="Show"/></td>			
			</tr>
			</table>
		</div>
		<br>
		<div style="height: 500px; overflow: auto;">	
		<s:grid id="voyges" 
			columns="#{AdminSubmissionBean.requestColumns}"
			rows="#{AdminSubmissionBean.requestRows}" 
			onOpenRow="#{AdminSubmissionBean.newRequestId}"
			action="#{AdminSubmissionBean.resolveRequest}" />
		</div>		
		<f:verbatim>

		</f:verbatim>
	
	</h:panelGroup>

	<h:panelGroup rendered="#{AdminSubmissionBean.usersListSelected}">
	
		<div value="div" style="margin-top: 10px; padding: 5px 10px 5px 10px;">
			
			<table border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td style="padding-right: 5px"> <h:selectOneMenu value="#{SubmissionUsersBean.accountType}"><f:selectItems value="#{SubmissionUsersBean.accountTypes}"/></h:selectOneMenu> </td>
				<td><h:commandButton value="Refresh"/></td>
			</tr>
			</table>
		</div>
		<div style="height: 500px; overflow: auto; padding-top: 10px;">
		
		
		<s:grid id="users"
			columns="#{SubmissionUsersBean.userColumns}"
			rows="#{SubmissionUsersBean.userRows}" 
			action="#{SubmissionUsersBean.enterEditUser}"
			onOpenRow="#{SubmissionUsersBean.editUser}"
			onColumnClick="#{SubmissionUsersBean.onGridColumnClick}" />
		</div>
		<f:verbatim>
		
		</f:verbatim>
	
	</h:panelGroup>
	
	<h:panelGroup rendered="#{AdminSubmissionBean.sourceCodesSelected}">
		
		<div value="div" style="margin-top: 10px; padding: 5px 10px 5px 10px;">
			<table border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td width="10">&nbsp;</td>
				<td><h:commandButton style="width:200px;" value="Create New Source" action="#{SubmissionSourceCodesBean.switchToCreateSource}"/></td>
			</tr>
			</table>
		</div>
		<t:htmlTag value="br"/>
		<t:htmlTag value="br"/>
		
		<div style="height: 500px; overflow: auto; padding-top: 10px;">
		<s:grid id="sources"
			columns="#{SubmissionSourceCodesBean.sourceColumns}"
			rows="#{SubmissionSourceCodesBean.sourceRows}" 
			action="#{SubmissionSourceCodesBean.enterEditSource}"
			onOpenRow="#{SubmissionSourceCodesBean.editSource}"
			onColumnClick="#{SubmissionSourceCodesBean.onGridColumnClick}" />
		</div>
		
		

	</h:panelGroup>
	
	
	<h:panelGroup rendered="#{AdminSubmissionBean.publishSelected}">
		<t:htmlTag value="br"/>
		<f:verbatim>
			<h2>Warning!</h2>
			After after applying changes, new database revision will immediately be published.<br>
			The process can take few minutes. Please do not press the button twice or do not
			refresh the page.<br><br>
		</f:verbatim>

		<h:outputText value="#{AdminSubmissionBean.message}" rendered="#{AdminSubmissionBean.message != null}"/>
		<t:htmlTag value="br"></t:htmlTag>
		<h:commandButton value="Publish new revision" onclick="if (confirm('Are you sure you want to publish new database revision?')) return true; return false;" action="#{AdminSubmissionBean.publish}"/>
	</h:panelGroup>
	
	
</h:form>
</f:view>
</body>
</html>