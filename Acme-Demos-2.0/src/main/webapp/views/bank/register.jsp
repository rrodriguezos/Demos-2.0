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

	<form:form action="bank/register.do" modelAttribute="bankRegisterForm">
		<fieldset>
			<legend align="left">
				<spring:message code="bank.userAccount" />
			</legend>
			<acme:textbox code="bank.username" path="username" />

			<acme:password code="bank.password" path="password" />
			<acme:password code="bank.repeatPassword" path="confirmPassword" />
		</fieldset>

		<fieldset>
			<legend align="left">
				<spring:message code="bank.personalInfo" />
			</legend>
			<acme:textbox code="bank.name" path="name" />

			<acme:textbox code="bank.surname" path="surname" />


			<acme:textbox code="bank.email" path="emailAddress" />

			<acme:textbox code="bank.phone" path="phone" />
		</fieldset>
		<fieldset>
			<legend align="left">
				<spring:message code="bank.bankData" />
			</legend>
			<acme:textbox code="bank.commercialName" path="commercialName" />
			<acme:textbox code="bank.SWIFTCode" path="SWIFTCode" />

		</fieldset>

		<acme:checkbox code="bank.accept" path="accept"
			url="welcome/conditions.do" />

		<acme:submit name="save" code="bank.save" />
		<acme:cancel url="welcome/index.do" code="bank.cancel" />

	</form:form>

</security:authorize>