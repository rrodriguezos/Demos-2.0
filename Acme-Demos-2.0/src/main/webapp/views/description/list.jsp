<%--
 * action-1.jsp
 *
 * Copyright (C) 2013 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>



<display:table name="descriptions" id="row" pagesize="5"
	requestURI="description/list.do" class="displaytag">
	<form:hidden path="demoId" />

	<spring:message code="description.text" var="textHeader" />
	<display:column property="text" title="${textHeader}" />

	<spring:message code="description.isoCode" var="isoCodeHeader" />
	<display:column property="isoCode" title="${isoCodeHeader}" />
	
	
	<spring:message code="description.display" var="display" />
	<display:column title="${display}">
			<input type="button" value="<spring:message code="description.display" />" 
					onclick="javascript: window.location.assign('description/display.do?descriptionId=${row.id}')" />
	</display:column>
		
	
	
	<security:authorize access="hasRole('DEVELOPER')">
		<jstl:if test="${mydemo == true}">
		<spring:message code="description.delete" var="delete" />
		<display:column title="${delete}">
		<input type="button" name="delete"
			value="<spring:message code="description.delete" />"
			onclick="javascript: window.location.assign('description/developer/delete.do?descriptionId=${row.id}')" />
		</display:column>
		</jstl:if>
	</security:authorize>

</display:table>

<security:authorize access="hasRole('DEVELOPER')">
	<jstl:if test="${mydemo == true}">


		<input type="button" name="create"
			value="<spring:message code="description.create" />"
			onclick="javascript: window.location.assign('description/developer/create.do?demoId=${demoId}')" />
	</jstl:if>
</security:authorize>