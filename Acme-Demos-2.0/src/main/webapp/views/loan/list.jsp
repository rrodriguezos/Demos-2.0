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

<display:table name="loans" id="row" pagesize="5" requestURI="${requestUri}" class="displaytag">
	
	<display:column property="status" titleKey="loan.status" sortable="true"  />
	
	<display:column property="requestMoment" titleKey="loan.requestMoment"
		format="{0,date,dd/MM/yyyy HH:mm}" sortable="true" />
	
	<display:column property="processMoment" titleKey="loan.processMoment"
		format="{0,date,dd/MM/yyyy HH:mm}" sortable="true" />		
	<display:column property="amount" titleKey="loan.amount" />
	
	<security:authorize access="hasRole('DEVELOPER')">
		<spring:message code="loan.cancelLoan" var="edit" />
		<display:column title="${cancel}">
				<input type="button" value="<spring:message code="loan.cancelLoan" />" 
						onclick="javascript: window.location.assign('loan/cancel.do?loanId=${row.id}')" />
		</display:column>
	</security:authorize>
	
	<security:authorize access="hasRole('BANK')">
		<display:column titleKey="loan.approve.deny">
			<jstl:choose>
				<jstl:when test="${row.status == 'PENDING'}">
					<input type="button" value="<spring:message code="loan.approve" />" 
							onclick="javascript: window.location.assign('loan/bank/approve.do?loanId=${row.id}')" />
					<input type="button" value="<spring:message code="loan.deny" />" 
							onclick="javascript: window.location.assign('loan/bank/deny.do?loanId=${row.id}')" />
				</jstl:when>
				<jstl:otherwise>
					<jstl:out value="${row.status}"></jstl:out>
				</jstl:otherwise>
			</jstl:choose>
		</display:column>
	
		<display:column titleKey="loan.instalment">
			<jstl:choose>
				<jstl:when test="${row.instalments.size() == 0}">
					<input type="button" value="<spring:message code="loan.instalment.create" />" 
							onclick="javascript: window.location.assign('instalment/bank/create.do?loanId=${row.id}')" />
				</jstl:when>
				<jstl:otherwise>
					<a href="instalment/bank/list.do?loanId=${row.id}">
						<spring:message code="loan.instalment" />
					</a>
				</jstl:otherwise>
			</jstl:choose>
		</display:column>
	</security:authorize>
	
</display:table>
