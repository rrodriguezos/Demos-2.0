<%--
 * header.jsp
 *
 * Copyright (C) 2014 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>

<div>
	<img src="images/logo.png" alt="Acme Demos Co., Inc." />
</div>

<div>
	<ul id="jMenu">
		<!-- Do not forget the "fNiv" class for the first level links !! -->

		<li><a class="fNiv" href="${pageContext.request.contextPath}">
				<spring:message code="master.page.home" />
		</a></li>

		<li><a href="demo/list.do"><spring:message
					code="master.page.public.catalogueDemo" /></a>
		<li><a class="fNiv" href="search/buscar.do"><spring:message
					code="master.page.search.demo" /></a></li>


		<security:authorize access="isAnonymous()">
			<li><a class="fNiv"> <spring:message
						code="master.page.register" /> 
			</a>
				<ul>
					<li class="arrow"></li>
						<li><a  href="developer/register.do"> <spring:message
						code="master.page.DeveloperRegister" /></a></li>
						<li><a  href="investor/register.do"> <spring:message
						code="master.page.InvestorRegister" /></a></li>
				</ul>
			</li>
			<li><a class="fNiv" href="security/login.do"> <spring:message
						code="master.page.login" /></a></li>
		</security:authorize>

		<security:authorize access="hasRole('ADMINISTRATOR')">
			<li><a class="fNiv" href="dashboard/administrator/dashboard.do">
					<spring:message code="master.page.administrator.dashboard" />
			</a></li>

		</security:authorize>
		
		<security:authorize access="hasRole('INVESTOR')">
			

		</security:authorize>

		<security:authorize access="isAuthenticated()">
			<li><a class="fNiv"> <spring:message
						code="master.page.profile" /> (<security:authentication
						property="principal.username" />)
			</a>
				<ul>
					<li class="arrow"></li>
					
					<security:authorize access="hasRole('DEVELOPER')">
						<li><a href="demo/developer/mylist.do"><spring:message
									code="master.page.developer.mydemos" /></a></li>
						<li><a href="demo/developer/create.do"><spring:message
									code="master.page.developer.createDemo" /></a></li>
									<li><a href="dashboard/developer/list.do"><spring:message
									code="master.page.developer.dashboard" /></a></li>
					</security:authorize>
					<security:authorize access="hasRole('INVESTOR')">
						<li><a href="investment/investor/list.do">
					<spring:message code="master.page.investor.investments" /></a></li>
					</security:authorize>
					<li><a href="j_spring_security_logout"> <spring:message
						code="master.page.logout" /></a></li>


				</ul></li>
			
		</security:authorize>
	</ul>
</div>

<div>
	<a href="?language=en">en</a> | <a href="?language=es">es</a>
</div>

