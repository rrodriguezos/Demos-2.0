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

<display:table name="banks" id="row" pagesize="5"
	requestURI="${requestUri}" class="displaytag">

	<spring:message code="bank.username" var="username" />
	<display:column title="${username}">
		<jstl:out value="${row.getUserAccount().getUsername() }" />
	</display:column>

	<spring:message code="bank.name" var="name" />
	<display:column title="${name}" property="name" />

	<spring:message code="bank.commercialName" var="commercialName" />
	<display:column title="${commercialName}" property="commercialName" />

	<spring:message code="bank.SWIFTCode" var="SWIFTCode" />
	<display:column title="${SWIFTCode}" property="SWIFTCode" />

	<spring:message code="bank.surname" var="surname" />
	<display:column title="${surname}" property="surname" />

	<spring:message code="bank.phone" var="phone" />
	<display:column title="${phone}" property="phone" />

	<spring:message code="bank.emailAddress" var="emailAddress" />
	<display:column title="${emailAddress}" property="emailAddress" />

	<spring:message code="bank.display" var="display" />
	<display:column title="${display}">
		<input type="button" value="<spring:message code="bank.display" />"
			onclick="javascript: window.location.assign('bank/display.do?bankId=${row.id}')" />
	</display:column>

</display:table>