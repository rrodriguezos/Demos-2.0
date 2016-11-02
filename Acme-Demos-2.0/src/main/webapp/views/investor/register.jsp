<%--

 * register.jsp

 *

 * Copyright (C) 2016 Universidad de Sevilla

 * 

 * The use of this project is hereby constrained to the conditions of the 

 * TDG Licence, a copy of which you may download from 

 * http://www.tdg-seville.info/License.html

 --%>



<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<security:authorize access="isAnonymous()">

	<form:form action="investor/register.do"
		modelAttribute="investorRegisterForm">

		<fieldset>
			<legend align="left">
				<spring:message code="investor.userAccount" />
			</legend>
			<acme:textbox code="investor.username" path="username" />

			<acme:password code="investor.password" path="password" />
			<acme:password code="investor.confirmPassword" path="confirmPassword" />
		</fieldset>
		<fieldset>
			<legend align="left">
				<spring:message code="investor.personalInfo" />
			</legend>
			<acme:textbox code="investor.name" path="name" />

			<acme:textbox code="investor.surname" path="surname" />
			<acme:textbox code="investor.company" path="company" />

			<acme:textbox code="investor.emailAddress" path="emailAddress" />

			<acme:textbox code="investor.phone" path="phone" />
		</fieldset>
		<p>
			<acme:checkbox code="investor.accept" path="accept"
				url="welcome/conditions.do" />

			<acme:submit name="save" code="investor.save" />
			<acme:cancel url="welcome/index.do" code="investor.cancel" />
	</form:form>

</security:authorize>