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


<acme:jstlOut code="resource.title" value="${resource.title }" />
<acme:jstlOut code="resource.link" value="${resource.link }" />
<br>
<security:authorize access="hasRole('DEVELOPER')">
	<jstl:if test="${logeado == true}">
		<jstl:if test="${mydemo == true}">

			<input type="button"
				value="<spring:message code="description.edit" />"
				onclick="javascript: window.location.assign('resource/developer/edit.do?resourceId=${resource.id}')" />

		</jstl:if>
	</jstl:if>
</security:authorize>



