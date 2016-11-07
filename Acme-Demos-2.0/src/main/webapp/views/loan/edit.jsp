<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<security:authorize access="hasRole('DEVELOPER')">

	<form:form action="loan/edit.do" modelAttribute="loan">
		<form:hidden path="id" />
		<form:hidden path="version" />
		<form:hidden path = "bank" />
		<form:hidden path = "status" />
		<form:hidden path = "instalments" />
		<form:hidden path = "developer" />
		<form:hidden path = "requestMoment" />
		<form:hidden path = "processMoment" />
		
			
		
		<acme:textbox code="loan.amount" path="amount" />	
		
		<input type="submit" name="save"
				value="<spring:message code="loan.save" />" />
		

		<acme:cancel url="bank/list.do" code="loan.cancel" />

	</form:form>

</security:authorize>