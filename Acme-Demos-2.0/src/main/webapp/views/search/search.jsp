
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
	<form:form action="search/buscar.do"  modelAttribute="searchForm" keepStatus="false"> 
		<acme:textbox code="search.introduce" path="text" />
		<input type="submit" name="search"
			value="<spring:message code="search.search" />" />
	</form:form>
	<jstl:if test="${demos!=null}">
		<h2>
			<spring:message code="search.explanation" />
			<jstl:out value="'${searchForm.text}'"></jstl:out>
			<spring:message code="search.explanation.dos" />
		</h2>
		<display:table name="demos" id="paco" requestURI="${requestUri}"
			pagesize="5" class="displaytag" keepStatus="false">
			

			<spring:message code="search.title" var="title" />
			<display:column property="title" title="${title}" sortable="true" />

			<spring:message code="search.momentReleased" var="momentReleased" />
			<display:column property="momentReleased" title="${momentReleased}"
				sortable="true" format="{0, date, dd/MM/yyyy HH:mm}" /> />
		</display:table>
	</jstl:if>
</div>

