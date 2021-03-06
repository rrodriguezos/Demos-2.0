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

<security:authorize access="hasRole('DEVELOPER')">

	<form:form action="demo/developer/edit.do" modelAttribute="demo">
		<form:hidden path="id" />
		<form:hidden path="version" />
		<form:hidden path="developer" />
		<form:hidden path="descriptions" />
		<form:hidden path="comments" />
		<form:hidden path="resources" />
		<form:hidden path="investments" />


		<acme:textbox code="demo.title" path="title" />

		<acme:date code="demo.momentReleased" path="momentReleased"
			readonly="false" />


		<jstl:if test="${demo.id == 0}">
			<input type="submit" name="save"
				value="<spring:message code="demo.save" />" />
		</jstl:if>

		<jstl:if test="${demo.id != 0}">
			<input type="submit" name="save"
				value="<spring:message code="demo.save" />"/>
			<input type="submit" name="delete"
				value="<spring:message code="demo.delete"/>"
				onclick="return confirm('<spring:message code="demo.confirm.delete" />')" />
		</jstl:if>

		<acme:cancel url="demo/developer/mylist.do" code="demo.cancel" />

	</form:form>

</security:authorize>