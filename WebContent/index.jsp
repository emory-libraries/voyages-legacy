<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ taglib uri="http://tas.library.emory.edu" prefix="d" %>

<html>
 <head>
    <title>Show Custom Component</title>
    <link href="common.css" rel="stylesheet" type="text/css">
  </head>
  <body>
    <f:view>
    <h:form>
   
      <d:tabletab results="#{TableResultTabBean.results}" populatedAttributes="#{TableResultTabBean.populatedAttributes}">
		<d:resultscroll resultFirst="#{TableResultTabBean.current}" resultSize="#{TableResultTabBean.resultSize}">
			<h:commandLink id="prev_pack" value="Previous result set" action="#{TableResultTabBean.prev}"/>
			<h:commandLink id="next_pack" value="Next result set" action="#{TableResultTabBean.next}"/>
		</d:resultscroll>
      </d:tabletab>

      <d:stattab>
        <f:verbatim>Hello JSF Component Statistic</f:verbatim>
      </d:stattab>

      <h:commandButton id="submit" action="success" value="Submit"/>
    </h:form>
    </f:view>
  </body>
</html>