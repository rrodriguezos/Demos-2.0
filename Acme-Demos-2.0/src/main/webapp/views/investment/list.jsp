<%--
 * list.jsp
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

<display:table name="investments" id="row" pagesize="5" requestURI="${requestUri}" class="displaytag">
	
	<display:column property="approvalMoment" titleKey="investment.approvalMoment"
		format="{0,date,dd/MM/yyyy HH:mm}" sortable="true" />
	
	<display:column property="description" titleKey="investment.description" />
	
	<display:column titleKey="investment.demo">
			<input type="button" value="<spring:message code="investment.demo" />" 
					onclick="javascript: window.location.assign('demo/display.do?demoId=${row.demo.id}')" />
	</display:column>
	
	<spring:message code="investment.display" var="display" />
	<display:column title="${display}">
			<input type="button" value="<spring:message code="investment.display" />" 
					onclick="javascript: window.location.assign('investment/display.do?investmentId=${row.id}')" />
	</display:column>
	
	<spring:message code="investment.bank" var="bankHeader" />
	<display:column title="${bankHeader}">
			<input type="button" value="<spring:message code="investment.bank" />" 
					onclick="javascript: window.location.assign('bank/listByInvestment.do?investmentId=${row.id}')" />
	</display:column>
	
	<security:authorize access="hasRole('INVESTOR')">
	<display:column titleKey="investment.instalments">
			<input type="button" value="<spring:message code="investment.instalments" />" 
					onclick="javascript: window.location.assign('instalment/investor/list.do?investmentId=${row.id}')" />
	</display:column>
	</security:authorize>
	
	
	

</display:table>