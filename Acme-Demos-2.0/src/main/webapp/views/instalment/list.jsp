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

<display:table name="instalments" id="row" pagesize="5" requestURI="${requestUri}" class="displaytag">
	
	<display:column property="instalmentDate" titleKey="instalment.instalmentDate"
		format="{0,date,dd/MM/yyyy HH:mm}" sortable="true" />
	
	<display:column property="amount" titleKey="instalment.amount" />
	
	<security:authorize access="hasRole('INVESTOR')">
		<spring:message code="instalment.edit" var="edit" />
		<display:column title="${edit}">
				<input type="button" value="<spring:message code="instalment.edit" />" 
						onclick="javascript: window.location.assign('instalment/investor/edit.do?instalmentId=${row.id}')" />
		</display:column>
	</security:authorize>
	
	<security:authorize access="hasRole('BANK')">
		<display:column titleKey="instalment.payment">
			<jstl:choose>
				<jstl:when test="${row.paid == false}">
					<input type="button" value="<spring:message code="instalment.pay" />" 
						onclick="javascript: window.location.assign('instalment/bank/pay.do?instalmentId=${row.id}')" />
				</jstl:when>
				<jstl:otherwise>
					<spring:message code="instalment.paid"  />
				
				</jstl:otherwise>
			</jstl:choose>
			
		</display:column>
	</security:authorize>
	
	
	
	
	

</display:table>
<security:authorize access="hasRole('INVESTOR')">
<input type="button" value="<spring:message code="instalment.create" />" 
					onclick="javascript: window.location.assign('instalment/investor/create.do?investmentId=${investmentId}')" />
</security:authorize>



