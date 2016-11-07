<%--
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
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:jstlOut code="bank.name" value="${bank.name }" />
<acme:jstlOut code="bank.surname" value="${bank.surname }" />
<acme:jstlOut code="bank.emailAddress" value="${bank.emailAddress }" />
<acme:jstlOut code="bank.phone" value="${bank.phone }" />
<fieldset>
	<legend align="left">
		<spring:message code="bank.bankData" />
	</legend>

	<acme:jstlOut code="bank.commercialName"
		value="${bank.commercialName }" />
	<acme:jstlOut code="bank.SWIFTCode" value="${bank.SWIFTCode }" />



</fieldset>

<security:authorize access="hasRole('DEVELOPER')">
	<input type="button" value="<spring:message code="bank.request.loan" />" 
			onclick="javascript: window.location.assign('loan/create.do?bankId=${id}')" />
</security:authorize>

<input type="button" name="cancel"
	value="<spring:message code="bank.cancel"/>"
	onclick="javascript: window.location.assign('bank/list.do')" />
