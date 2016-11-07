
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>



<div>
	<form:form action="searchWhitePaper/buscar.do"
		modelAttribute="searchFormWhitePaper" keepStatus="false">
		<acme:textbox code="search.introduceWhitePapers" path="text" />	
		<acme:date code="search.dateFirst" path="dateFirst" />
		

		<acme:date code="search.dateSecond" path="dateSecond" />
		<input type="submit" name="search"
			value="<spring:message code="search.search" />" />
	</form:form>
	<jstl:if test="${whitePapers!=null}">
		<h2>
			<spring:message code="search.explanation" />
			<jstl:out value="'${searchFormWhitePaper.text}'"></jstl:out>
			<spring:message code="search.explanation.dosWhitePapers" />
		</h2>
		<display:table name="whitePapers" id="paco" requestURI="${requestUri}"
			pagesize="5" class="displaytag" keepStatus="false">


			<spring:message code="search.title" var="title" />
			<display:column property="title" title="${title}" sortable="true" />

			<spring:message code="search.abstractReview" var="abstractReview" />
			<display:column property="abstractReview" title="${abstractReview}"
				sortable="true" />

			<spring:message code="search.publishedDate" var="publishedDate" />
			<display:column property="publishedDate" title="${publishedDate}"
				sortable="true" format="{0, date, dd/MM/yyyy HH:mm}" />

			<spring:message code="search.sections" var="sectionsHeader" />
			<display:column title="${sectionsHeader}">
				<input type="button"
					value="<spring:message code="search.sections" />"
					onclick="javascript: window.location.assign('section/list.do?whitePaperId=${paco.id}')" />
			</display:column>



		</display:table>
	</jstl:if>

</div>

