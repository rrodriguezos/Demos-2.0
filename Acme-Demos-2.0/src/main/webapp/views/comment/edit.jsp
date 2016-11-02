<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
 
 <%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
 <%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
 <%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
 <%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
 <%@taglib prefix="display" uri="http://displaytag.sf.net"%>
 <%@taglib prefix="acme" tagdir="/WEB-INF/tags" %>
 
 <form:form action="${actionUri}" modelAttribute="commentForm">
 	
 	
 	<acme:textbox code="comment.author" path="author"/>
 	
 	
 	<acme:textarea code="comment.text" path="text"/>
 	<acme:textbox code="comment.stars" path="stars"/>
 	
 	<input type="submit" name="save" value="<spring:message code="comment.save" />" />
 	
 	<input type="button" name="cancel" value="<spring:message code="comment.cancel" />" onclick="javascript: window.history.back()" />
 	
 </form:form>