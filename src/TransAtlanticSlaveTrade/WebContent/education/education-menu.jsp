<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://tas.library.emory.edu" prefix="s"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>

<t:div styleClass="left-menu-box">

	<s:simpleBox>
	
		<t:div styleClass="left-menu-title">
			<h:outputText value="EDUCATIONAL MATERIALS" />
		</t:div>
		
		<s:secondaryMenu>
			<s:secondaryMenuItem label="Lesson Plans" menuId="lesson-plans" href="lesson-plans.faces" />
			<s:secondaryMenuItem label="Web Resources" menuId="others" href="others.faces"/>							
		</s:secondaryMenu>
		
	</s:simpleBox>

</t:div>