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

	<form:form action="description/developer/create.do" modelAttribute="description">
		<form:hidden path="id" />
		<form:hidden path="version" />
		<form:hidden path="demo"/>


		<acme:textarea code="description.text" path="text" />

		<br>
	
	<tr>
	<spring:message code="description.isoCode" />
		<td><select NAME="isoCode">
				<OPTION VALUE="CHI">
					<spring:message code="description.CHI" />
				</OPTION>
				<OPTION VALUE="SPA">
					<spring:message code="description.SPA" />
				</OPTION>
				<OPTION VALUE="ENG">
					<spring:message code="description.ENG" />
				</OPTION>
				<OPTION VALUE="HIN">
					<spring:message code="description.HIN" />
				</OPTION>
				<OPTION VALUE="ARA">
					<spring:message code="description.ARA" />
				</OPTION>
				<OPTION VALUE="POR">
					<spring:message code="description.POR" />
				</OPTION>
				<OPTION VALUE="RUS">
					<spring:message code="description.RUS" />
				</OPTION>
				<OPTION VALUE="JPN">
					<spring:message code="description.JPN" />
				</OPTION>
				<OPTION VALUE="GER">
					<spring:message code="description.GER" />
				</OPTION>
				<OPTION VALUE="FRE">
					<spring:message code="description.FRE" />
				</OPTION>
				<OPTION VALUE="KOR">
					<spring:message code="description.KOR" />
				</OPTION>
				<OPTION VALUE="ITA">
					<spring:message code="description.ITA" />
				</OPTION>
		</select></td>
	</tr>
	<br>

		<jstl:if test="${description.id == 0}">
			<input type="submit" name="save"
				value="<spring:message code="description.save" />" />
				<acme:cancel url="demo/developer/mylist.do" code="description.cancel" />
		</jstl:if>
		<jstl:if test="${description.id != 0}">
			<input type="submit" name="save"
				value="<spring:message code="description.save" />"/>
				<acme:cancel url="demo/developer/mylist.do" code="description.cancel" />
		</jstl:if>


	</form:form>

</security:authorize>