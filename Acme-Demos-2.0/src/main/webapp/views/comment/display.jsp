

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags" %>


<b><spring:message code="comment.author"/>:</b>
<jstl:out value="${author}"></jstl:out>
<br>

<b><spring:message code="comment.moment"/>:</b>
<jstl:out value="${moment}"></jstl:out>
<br>

<b><spring:message code="comment.text"/>:</b>
<jstl:out value="${text}"></jstl:out>
<br>

<b><spring:message code="comment.stars"/>:</b>
<img src="<jstl:out value="${estrellas}" />"  />
<br>


<security:authorize access="hasRole('ADMINISTRATOR')">
	<input type="button" value="<spring:message code="comment.delete" />" 
			onclick="javascript: window.location.assign('comment/administrator/delete.do?commentId=${id}')" />
</security:authorize>

<input type="button" name="back" value="<spring:message code="comment.cancel"/>" onclick="javascript: window.history.back()" />