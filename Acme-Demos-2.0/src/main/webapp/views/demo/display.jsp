<%--
 *
 * Copyright (C) 2013 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>


<acme:jstlOut code="demo.title" value="${demo.title }"/>
<acme:jstlOut code="demo.momentReleased" value="${demo.momentReleased }"/>



<security:authorize access="hasRole('DEVELOPER')">
  	<jstl:if test="${mydemo == true}">
	
	<input type="button" value="<spring:message code="demo.edit" />" 
			onclick="javascript: window.location.assign('demo/developer/edit.do?demoId=${demo.id}')" />

	</jstl:if>
</security:authorize>
<br>
<h2><spring:message code="demo.descriptions"/></h2>

<display:table name="descriptions" id="row" pagesize="5" requestURI="${requestUri}" class="displaytag">
	
	<spring:message code="description.text" var="textHeader" />
	<display:column property="text" title="${textHeader}" />

	<spring:message code="description.isoCode" var="isoCodeHeader" />
	<display:column property="isoCode" title="${isoCodeHeader}" />
	
	
	<spring:message code="description.display" var="display" />
	<display:column title="${display}">
			<input type="button" value="<spring:message code="description.display" />" 
					onclick="javascript: window.location.assign('description/display.do?descriptionId=${row.id}')" />
	</display:column>
	
</display:table>
<br>
<security:authorize access="hasRole('DEVELOPER')">
	<input type="button" value="<spring:message code="description.create" />" 
			onclick="javascript: window.location.assign('description/developer/edit.do?demoId=${demo.id}')" />
</security:authorize>


<br>
<h2><spring:message code="demo.resources"/></h2>

<display:table name="resources" id="row" pagesize="5" requestURI="${requestUri}" class="displaytag">
	
	<spring:message code="resource.title" var="titleHeader" />
	<display:column property="title" title="${titleHeader}" />

	<spring:message code="resource.link" var="linkHeader" />
	<display:column property="link" title="${linkHeader}" />
	
	
	<spring:message code="resource.display" var="display" />
	<display:column title="${display}">
			<input type="button" value="<spring:message code="resource.display" />" 
					onclick="javascript: window.location.assign('resource/display.do?resourceId=${row.id}')" />
	</display:column>
	
</display:table>
<br>
<security:authorize access="hasRole('DEVELOPER')">
	<input type="button" value="<spring:message code="resource.create" />" 
			onclick="javascript: window.location.assign('resource/developer/edit.do?demoId=${demo.id}')" />
</security:authorize>


<input type="button" name="cancel" value="<spring:message code="demo.cancel"/>" onclick="javascript: window.history.back()" />
