<%--
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
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<br>
<br>
<br>
<acme:jstlOut code="investment.description" value="${investment.description }"/>
<b><spring:message code="investment.approvalMoment" />: </b> <fmt:formatDate value="${investment.approvalMoment }" pattern="dd/MM/yyyy HH:mm" />
<br />

<acme:jstlOut code="investment.bank" value="${investment.bank.commercialName }"/>


<br>
<br>
<security:authorize access="hasRole('INVESTOR')">
  	<jstl:if test="${myinvestment == true}">
	
	<input type="button" value="<spring:message code="investment.edit" />" 
			onclick="javascript: window.location.assign('investment/investor/edit.do?investmentId=${investment.id}')" />

	</jstl:if>
</security:authorize>
<br>
<br>

<input type="button" name="cancel" value="<spring:message code="demo.cancel"/>" onclick="javascript: window.history.back()" />
