<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Group</title>
</head>
<body>
<f:view>
	<h:form>
		<h:inputText value="#{GroupsBean.selectedGroup.userLabel}" />
		<h:commandButton value="Save" action="#{GroupsBean.saveSelectedGroup}" />
		<h:commandButton value="Back" action="back" />
	</h:form>
</f:view>
</body>
</html>