<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ taglib uri="http://tas.library.emory.edu" prefix="s" %>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Images</title>
<script type="text/javascript" src="utils.js"></script>
<script type="text/javascript" src="select-and-order.js"></script>
<script type="text/javascript" src="lookup-select.js"></script>
	
<style type="text/css">

body, input, select, textarea {
	font-family: Verdana, Arial, Helvetica, Bitstream Vera Sans, Sans, sans-serif;
	font-size: 8pt; }
	
div.section {
	font-weight: bold;
	background-color: #DDDDDD;
	padding: 5px;
	margin-bottom: 5px; }
	
div.error {
	margin-bottom: 5px;
	padding: 5px;
	font-weight: bold;
	color: White;
	background-color: #FF5500; }

</style>
	
</head>
<body>
<body>
<f:view>
	<h:form id="form" enctype="multipart/form-data">
	
		<div style="font-size: 12pt; font-weight: bold; padding: 0px 5px 5px 5px; font-family: Arial, sans-serif;">
		Trans-Atlantic Slave Trade / Image database
		</div>
		
		<t:htmlTag value="div" styleClass="error" rendered="#{ImagesBean.errorText != null}">
			<h:outputText value="#{ImagesBean.errorText}" />
		</t:htmlTag>
		
		<div class="section">Image</div>
	
		<h:graphicImage 
			url="#{ImagesBean.imageUrl}"
			width="#{ImagesBean.image.width}"
			height="#{ImagesBean.image.height}" />
			
		<br>
		
		<h:outputText value="#{ImagesBean.imageInfo}" />
		
		<br>
			
		<h:commandButton
			value="Upload new image"
			action="#{ImagesBean.showUploadBox}"
			rendered="#{!ImagesBean.uploadBoxShown}" />
		
		<h:panelGroup rendered="#{ImagesBean.uploadBoxShown}">
			
			<t:inputFileUpload
				storage="file"
				value="#{ImagesBean.uploadedImage}" />
				
			<h:commandButton
				action="#{ImagesBean.uploadNewImage}"
				value="Upload" />
			
			<h:commandButton
				action="#{ImagesBean.hideUploadBox}"
				value="Cancel" />
				
		</h:panelGroup>
		
		<br>
		<br>

		<div class="section">External metadata</div>
			
		<table border="0" cellspacing="0" cellpadding="0"><tr><td valign="top">

			<table border="0" cellspacing="5" cellpadding="0">
			<tr>
				<td style="width: 80px;">Title</td>
				<td><h:inputText style="width: 300px;" value="#{ImagesBean.image.title}" /></td>
			</tr>
			<tr>
				<td>Description</td>
				<td><h:inputTextarea rows="5" style="width: 300px;" value="#{ImagesBean.image.description}" /></td>
			</tr>
			</table>
		
		</td><td style="width: 20px;"></td><td valign="top">
		
			<table border="0" cellspacing="5" cellpadding="0">
			<tr>
				<td style="width: 100px;">Source</td>
				<td><h:inputText style="width: 300px;" value="#{ImagesBean.image.source}" /></td>
			</tr>
			<tr>
				<td>Date</td>
				<td><h:inputText style="width: 300px;" value="#{ImagesBean.image.date}" /></td>
			</tr>
			<tr>
				<td>Creator</td>
				<td><h:inputText style="width: 300px;" value="#{ImagesBean.image.creator}" /></td>
			</tr>
			<tr>
				<td>Language</td>
				<td><h:selectOneMenu value="#{ImagesBean.image.language}">
					<f:selectItems value="#{ImagesBean.languages}" />
				</h:selectOneMenu></td>
			</tr>
			</table>

		</td></tr></table>
			
		<br>

		<div class="section">Internal metadata</div>

		<table border="0" cellspacing="0" cellpadding="0"><tr><td valign="top">

			<table border="0" cellspacing="5" cellpadding="0">
			<tr>
				<td style="width: 80px;">Comments</td>
				<td><h:inputText style="width: 300px;" value="#{ImagesBean.image.comments}" /></td>
			</tr>
			<tr>
				<td>References</td>
				<td><h:inputTextarea rows="5" style="width: 300px;" value="#{ImagesBean.image.references}" /></td>
			</tr>
			</table>

		</td><td style="width: 20px;"></td><td valign="top">

			<table border="0" cellspacing="5" cellpadding="0">
			<tr>
				<td style="width: 100px;">Is at Emory</td>
				<td><h:selectBooleanCheckbox value="#{ImagesBean.image.emory}" /></td>
			</tr>
			<tr>
				<td>Emory location</td>
				<td><h:inputText style="width: 300px;" value="#{ImagesBean.image.emoryLocation}" /></td>
			</tr>
			<tr>
				<td>Status</td>
				<td><h:selectOneMenu value="#{ImagesBean.image.workflowStatus}">
					<f:selectItem itemLabel="-" itemValue="0" />
					<f:selectItem itemLabel="Applied for authorization" itemValue="1" />
					<f:selectItem itemLabel="Processing" itemValue="2" />
					<f:selectItem itemLabel="Ready to deploy" itemValue="3" />
				</h:selectOneMenu></td>
			</tr>
			</table>

		</td></tr></table>

		<br>

		<div class="section">Database connections</div>
		
		<table border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td valign="top" style="padding-left: 5px; padding-right: 10px;">
			
				<div style="margin-bottom: 5px; font-weight: bold;">Regions</div>

				<s:lookupSelect
					id="regions"
					sourceId="#{ImagesBean.regionsLookupSourceId}"
					selectedValues="#{ImagesBean.selectedRegionsIds}" />
			
			</td>
			<td valign="top" style="padding-right: 10px;">
			
				<div style="margin-bottom: 5px; font-weight: bold;">Ports</div>
			
				<s:lookupSelect
					id="ports"
					sourceId="#{ImagesBean.portsLookupSourceId}"
					selectedValues="#{ImagesBean.selectedPortsIds}" />
			
			</td>
			<td>
			
				<div style="margin-bottom: 5px; font-weight: bold;">People</div>
				
				<s:lookupSelect
					id="people"
					sourceId="#{ImagesBean.peopleLookupSourceId}"
					selectedValues="#{ImagesBean.selectedPeopleIds}" />

			</td>
		</tr>
		</table>
		
		<div style="margin-top: 10px; border-bottom: 2px solid #CCCCCC; margin-bottom: 10px;"></div>
		
		<h:commandButton value="Save" action="#{ImagesBean.saveImage}" />
		<h:commandButton value="Delete" onclick="if (!confirm('Are you sure?')) return false;" action="#{ImagesBean.deleteImage}" rendered="#{ImagesBean.image.id != 0}" />
		<h:commandButton value="Cancel" action="#{ImagesBean.cancelEdit}" />

	</h:form>
</f:view>
</body>
</html>