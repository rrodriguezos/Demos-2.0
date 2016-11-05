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


<display:table name="sections" id="row" pagesize="5"
	requestURI="section/list.do" class="displaytag">
	<form:hidden path="whitePaperId" />

	<spring:message code="section.title" var="titleHeader" />
	<display:column property="title" title="${titleHeader}" />

	<spring:message code="section.text" var="textHeader" />
	<display:column property="text" title="${textHeader}" />


	<spring:message code="section.display" var="display" />
	<display:column title="${display}">
		<input type="button" value="<spring:message code="section.display" />"
			onclick="javascript: window.location.assign('section/display.do?sectionId=${row.id}')" />
	</display:column>



	<security:authorize access="hasRole('INVESTOR')">
		<jstl:if test="${mywhitePaper == true}">
			<spring:message code="section.delete" var="delete" />
			<display:column title="${delete}">
				<input type="button" name="delete"
					value="<spring:message code="section.delete" />"
					onclick="javascript: window.location.assign('section/investor/delete.do?sectionId=${row.id}')" />
			</display:column>
		</jstl:if>
	</security:authorize>

</display:table>

<security:authorize access="hasRole('INVESTOR')">
	<jstl:if test="${mywhitePaper == true}">


		<input type="button" name="create"
			value="<spring:message code="section.create" />"
			onclick="javascript: window.location.assign('section/investor/create.do?whitePaperId=${whitePaperId}')" />
	</jstl:if>
</security:authorize>