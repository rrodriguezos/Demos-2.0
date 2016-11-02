<%--
 * edit.jsp
 *
 * Copyright (C) 2013 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<security:authorize access="hasRole('INVESTOR')">

	<form:form action="investment/investor/create.do" modelAttribute="investment">
		<form:hidden path="id" />
		<form:hidden path="version" />
		<form:hidden path="approvalMoment" />
		<form:hidden path="investor" />
		<form:hidden path = "instalments" />
		<form:hidden path="instalments" />
		<form:hidden path="demo" />

		<acme:textarea code="investment.description" path="description" />
		
		<input type="submit" name="save"
				value="<spring:message code="demo.save" />" />
		

		<acme:cancel url="investment/investor/list.do" code="demo.cancel" />

	</form:form>

</security:authorize>