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

<acme:jstlOut code="investor.name" value="${investor.name }"/>
<acme:jstlOut code="investor.surname" value="${investor.surname }"/>
<acme:jstlOut code="investor.emailAddress" value="${investor.emailAddress }"/>
<acme:jstlOut code="investor.phone" value="${investor.phone }"/>
<fieldset>
<acme:jstlOut code="investor.phone" value="${investor.company }"/>
</fieldset>
<h2><spring:message code="investor.investments"/></h2>
<display:table name="investments" id="row" pagesize="5" requestURI="investor/display.do" class="displaytag">
	
	<spring:message code="investment.title" var="title" />
	<display:column title="${title}" property="title" />
	
	<spring:message code="investment.display" var="display" />
	<display:column title="${display}">
			<input type="button" value="<spring:message code="investment.display" />" 
					onclick="javascript: window.location.assign('investment/display.do?investmentId=${row.id}')" />
	</display:column>
	
</display:table>

<input type="button" name="cancel" value="<spring:message code="investor.cancel"/>" onclick="javascript: window.location.assign('investor/list.do')" />
