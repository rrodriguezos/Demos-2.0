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

	<form:form action="demo/investor/sponsor.do" modelAttribute="demo">
		<form:hidden path="id" />
		<form:hidden path="version" />
		<form:hidden path="developer" />
		<form:hidden path="descriptions" />
		<form:hidden path="comments" />
		<form:hidden path="resources" />
		<form:hidden path="title" />
		<form:hidden path="momentReleased" />

		<acme:select code="demo.banner" path="banner" items="${banners}" itemLabel="link" id="banners" />
		
			
		<input type="submit" name="save"
				value="<spring:message code="demo.save" />" />
		<acme:cancel url="demo/list.do" code="demo.cancel" />

	</form:form>

</security:authorize>