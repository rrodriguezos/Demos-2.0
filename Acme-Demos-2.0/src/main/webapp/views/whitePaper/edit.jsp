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
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<security:authorize access="hasRole('INVESTOR')">

	<form:form action="whitePaper/investor/edit.do"
		modelAttribute="whitePaper">
		<form:hidden path="id" />
		<form:hidden path="version" />
		<form:hidden path="investor" />
		<form:hidden path="sections" />


		<acme:textbox code="whitePaper.title" path="title" />

		<acme:date code="whitePaper.publishedDate" path="publishedDate"
			readonly="false" />

		<acme:textbox code="whitePaper.abstractReview" path="abstractReview" />


		<jstl:if test="${whitePaper.id == 0}">
			<input type="submit" name="save"
				value="<spring:message code="whitePaper.save" />" />
		</jstl:if>

		<jstl:if test="${whitePaper.id != 0}">
			<input type="submit" name="save"
				value="<spring:message code="whitePaper.save" />"/>
			<input type="submit" name="delete"
				value="<spring:message code="whitePaper.delete"/>"
				onclick="return confirm('<spring:message code="whitePaper.confirm.delete" />')" />
		</jstl:if>

		<acme:cancel url="whitePaper/investor/mylist.do"
			code="whitePaper.cancel" />

	</form:form>

</security:authorize>