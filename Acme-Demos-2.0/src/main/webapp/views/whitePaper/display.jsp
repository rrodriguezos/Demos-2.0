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


<acme:jstlOut code="whitePaper.title" value="${whitePaper.title }" />
<acme:jstlOut code="whitePaper.abstractReview"
	value="${whitePaper.abstractReview }" />
<b><spring:message code="whitePaper.publishedDate" />: </b> <fmt:formatDate value="${whitePaper.publishedDate }" pattern="dd/MM/yyyy HH:mm" />
<br />


<security:authorize access="hasRole('INVESTOR')">
	<jstl:if test="${mywhitePaper == true}">

		<input type="button" value="<spring:message code="whitePaper.edit" />"
			onclick="javascript: window.location.assign('whitePaper/investor/edit.do?whitePaperId=${whitePaper.id}')" />

	</jstl:if>
</security:authorize>


<input type="button" name="cancel"
	value="<spring:message code="whitePaper.cancel"/>"
	onclick="javascript: window.history.back()" />
