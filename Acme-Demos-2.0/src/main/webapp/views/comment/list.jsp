<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<display:table name="comments" id="row" pagesize="5" requestURI="${requestUri}" class="displaytag">
	<form:hidden path="demoId" />
	
	
	<spring:message code="comment.author" var="author" />
	<display:column title="${author}">
		<jstl:out value="${row.getAuthor()}"/>
	</display:column>

	<spring:message code="comment.display" var="display" />
		<display:column title="${display}">
		<input type="button" value="<spring:message code="comment.display" />" 
			onclick="javascript: window.location.assign('comment/display.do?commentId=${row.id}')" />			
	</display:column>
	
</display:table>

<input type="button" name="create"
			value="<spring:message code="comment.create" />"
			onclick="javascript: window.location.assign('comment/create.do?demoId=${demoId}')" />


	
