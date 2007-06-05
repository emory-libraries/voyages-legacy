<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://tas.library.emory.edu" prefix="s"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Submission</title>
	<link href="../styles/main.css" rel="stylesheet" type="text/css">
	<link href="../styles/submission-grid.css" rel="stylesheet" type="text/css">
	<script src="../scripts/utils.js" language="javascript" type="text/javascript"></script>
	<script src="../scripts/grid-editor.js" language="javascript" type="text/javascript"></script>
	<link href="../admin/main.css" rel="stylesheet" type="text/css">
</head>
<body>
<f:view>
<h:form id="main">

	<h1>Request details</h1>

	<s:gridEditor
		rows="#{AdminSubmissionBean.rows}"
		columns="#{AdminSubmissionBean.columns}"
		values="#{AdminSubmissionBean.values}"
		fieldTypes="#{AdminSubmissionBean.fieldTypes}"
		rowGroups="#{AdminSubmissionBean.rowGroups}"/>
	<br>
	
	<h2>Submitted sources</h2>
	
	<s:grid id="voyges" 
			columns="#{AdminSubmissionBean.sourcesColumns}"
			rows="#{AdminSubmissionBean.sourcesRows}" 
			onOpenRow="#{AdminSubmissionBean.openSourcesRow}" />
	<br>
	<br>

	<h:commandButton value="Add editor" 
	                 rendered="#{AdminSubmissionBean.isChiefEditor && !AdminSubmissionBean.addingEditor}"
					 action = "#{AdminSubmissionBean.addEditor}"/>
	<t:htmlTag value="div" 
	           rendered="#{AdminSubmissionBean.addingEditor}" 
	           style="border: 2px solid #CCCCCC; width: 400px; padding: 5px;" >
		<h:selectOneMenu value="#{AdminSubmissionBean.newEditorUser}">
			<f:selectItems value="#{SubmissionUsersBean.editorUsers}"/>
		</h:selectOneMenu>
		<h:commandButton value="Add editor" action="#{AdminSubmissionBean.applyAddEditor}"/>
		<h:commandButton value="Cancel" action="#{AdminSubmissionBean.cancelAddEditor}"/>
	</t:htmlTag>
	
	<br>
	<br>
	
	<h:commandButton value="< Back" action="back"/>
	<h:commandButton value="Save" action="#{AdminSubmissionBean.save}" rendered="#{AdminSubmissionBean.rejectAvailable}"/>
	<h:commandButton value="Apply changes" action="#{AdminSubmissionBean.submit}" rendered="#{AdminSubmissionBean.isAdmin}"/>
	<h:commandButton value="Reject request" action="#{AdminSubmissionBean.rejectSubmission}" rendered="#{AdminSubmissionBean.isAdmin}"/>
	<br>
</h:form>
</f:view>
</body>
</html>