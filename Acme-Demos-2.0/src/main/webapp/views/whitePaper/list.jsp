<%--
 * action-1.jsp
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

<form action="whitePaper/search.do" method="get">
	<table class="formTable">
		<tr>
			<td><label for="q"><spring:message	code="whitePaper.searchtext" />:</label></td>
			<td><input type="text" name="q"></td>
			<td><input type="text" name="date1"></td>
			<td><input type="text" name="date2"></td>
		</tr>
		
		<tr>
			<td><input type="submit" value="<spring:message	code="common.search" />"></td>
		</tr>
	</table>
</form>

<display:table name="whitePapers" id="row" pagesize="5" requestURI="${requestUri}" class="displaytag">
	
	<spring:message code="whitePaper.title" var="titleHeader" />
	<display:column property="title" title="${titleHeader}" />
	
	<spring:message code="whitePaper.publishedDate" var="publishedDateHeader" />
	<display:column property="publishedDate" title="${publishedDateHeader}"
		format="{0,date,dd/MM/yyyy HH:mm}" sortable="true" />

		
	<spring:message code="whitePaper.abstractReview" var="abstractReviewHeader" />
	<display:column property="abstractReview" title="${abstractReviewHeader}" />
	
	<spring:message code="whitePaper.display" var="display" />
	<display:column title="${display}">
			<input type="button" value="<spring:message code="whitePaper.display" />" 
					onclick="javascript: window.location.assign('whitePaper/display.do?whitePaperId=${row.id}')" />
	</display:column>
	
	<spring:message code="whitePaper.sections" var="sectionsHeader" />
	<display:column title="${sectionsHeader}">
			<input type="button" value="<spring:message code="whitePaper.sections" />" 
					onclick="javascript: window.location.assign('section/list.do?whitePaperId=${row.id}')" />
	</display:column>
	

</display:table>