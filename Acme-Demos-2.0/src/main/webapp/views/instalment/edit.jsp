<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<security:authorize access="hasRole('INVESTOR')">

	<form:form action="instalment/investor/edit.do" modelAttribute="instalment">
		<form:hidden path="id" />
		<form:hidden path="version" />
		<form:hidden path = "investment" />
		

		<acme:date code="instalment.instalmentDate" path="instalmentDate"
			readonly="false" />
		
		<acme:textbox code="instalment.amount" path="amount" />	
		
		<input type="submit" name="save"
				value="<spring:message code="demo.save" />" />
		

		<acme:cancel url="investment/investor/list.do" code="demo.cancel" />

	</form:form>

</security:authorize>


<security:authorize access="hasRole('BANK')">

	<form:form action="instalment/bank/create.do" modelAttribute="createInstalmentsForm">
		<form:hidden path="loanId" />
		
		<acme:textbox code="instalment.numberInstalments" path="number" />	
		<acme:date code="instalment.dateFirstInstalment" path="date" />

		<br>
		
		<input type="submit" name="save"
				value="<spring:message code="instalment.save" />" />
		
	</form:form>

</security:authorize>