<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<div class=center-text>

	<!-- Average number of comments per demo. -->
	<p>
		<spring:message code="developer.dashboard.1" />
	</p>
	<jstl:out value="${averageCommentsPerDemo}"></jstl:out>

	<!-- Average number of stars per demo. -->
	<p>
		<spring:message code="developer.dashboard.2" />
	</p>
	<jstl:out value="${averageStarsPerDemo}"></jstl:out>

	<!-- List of demos sorted according the average number of stars that
		// theyve got -->
	<p>
		<spring:message code="developer.dashboard.3" />
	</p>
	<display:table name="demoSortedAverageNumberStars" id="row3"
		requestURI="${requestUri}" pagesize="5"
		class="displaytag" keepStatus="true">
		<spring:message code="administrator.dashboard.title" var="titleHeader" />
		<display:column property="title" title="${titleHeader}"
			sortable="true" />
		<spring:message code="administrator.dashboard.momentReleased"
			var="momentReleasedHeader" />
		<display:column property="momentReleased"
			title="${momentReleasedHeader}" format="{0,date,dd/MM/yyyy HH:mm}"
			sortable="true" />
	</display:table>

</div>

