<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>



<!-- Average number of comments per demo -->
<p>
	<h4><spring:message code="administrator.dashboard.averageCommentsPerDemo" /></h4>
</p>
<jstl:out value="${avgNumberCommentsPerDemo}" />
<br>

<!-- Demos that have got at least 25% more comments than the average -->
<p>
	<h4><spring:message code="administrator.dashboard.demos25PercentageMoreCommentsThanAvg" /></h4>
</p>
<display:table name="demos25PercentageMoreCommentsThanAvg" id="row" requestURI="${requestURI}" pagesize="5" class="displaytag" >
	
	<display:column property="title" titleKey="demo.title" />
	
	<display:column property="momentReleased" titleKey="demo.momentReleased" format="{0,date,dd/MM/yyyy HH:mm}" sortable="true" />
	
	<display:column titleKey="demo.display" >
			<input type="button" value="<spring:message code="demo.display" />" 
					onclick="javascript: window.location.assign('demo/display.do?demoId=${row.id}')" />
	</display:column>
	
	<display:column titleKey="demo.descriptions" >
			<input type="button" value="<spring:message code="demo.descriptions" />" 
					onclick="javascript: window.location.assign('description/list.do?demoId=${row.id}')" />
	</display:column>
	
	<display:column titleKey="demo.resources" >
			<input type="button" value="<spring:message code="demo.resources" />" 
					onclick="javascript: window.location.assign('resource/list.do?demoId=${row.id}')" />
	</display:column>
	
	<display:column titleKey="demo.comments" >
			<input type="button" value="<spring:message code="demo.comments" />" 
					onclick="javascript: window.location.assign('comment/list.do?demoId=${row.id}')" />
	</display:column>
</display:table>
<br>


<!-- Developers whose demos have got more comments than the average -->
<p>
	<h4><spring:message code="administrator.dashboard.developersMoreCommentsThanAvg" /></h4>
</p>
<display:table name="developersMoreCommentsThanAvg" id="row" requestURI="${requestURI}" pagesize="5" class="displaytag" >
	
	<display:column property="name" titleKey="developer.name" />
	
	<display:column property="surname" titleKey="developer.surname" />
	
	<display:column property="phone" titleKey="developer.phone" />
	
	<display:column property="emailAddress" titleKey="developer.email" />
	
</display:table>


<!--  The ratio of demos that have got an investment -->
<p>
	<h4><spring:message code="administrator.dashboard.ratioDemosWithInvestment" /></h4>
</p>
<jstl:out value="${ratioDemosWithInvestment}" />
<br>

<!--  The average and the standard deviation of the number of investments per investor -->
<p>
	<h4><spring:message code="administrator.dashboard.avgInvestmentsPerInvestor" /></h4>
</p>
<jstl:out value="${avgStddevInvestmentsPerInvestor[0][0]}" />
<br>
<p>
	<h4><spring:message code="administrator.dashboard.stddevInvestmentsPerInvestor" /></h4>
</p>
<jstl:out value="${avgStddevInvestmentsPerInvestor[0][1]}" />
<br>

<!--  The average amount of money that investors invest in demos -->
<p>
	<h4><spring:message code="administrator.dashboard.avgMoneyInvestInDemos" /></h4>
</p>
<jstl:out value="${avgMoneyInvestInDemos}" />
<br>

<!--  The average number of instalments per investment -->
<p>
	<h4><spring:message code="administrator.dashboard.avgNumberInstalmentsPerInvestment" /></h4>
</p>
<jstl:out value="${avgNumberInstalmentsPerInvestment}" />
<br>

<!-- ACME DEMOS 2.0 -->


<!--  The bank/s with more loan requests -->
<p>
	<h4><spring:message code="administrator.dashboard.banksWithMoreLoansRequest" /></h4>
</p>

<display:table name="banksMoreLoansReq" id="row" requestURI="${requestURI}" pagesize="5" class="displaytag" >
	
	<display:column property="commercialName" titleKey="bank.commercialName" />
	
	<display:column property="SWIFTCode" titleKey="bank.SWIFTCode" />
	
</display:table>

<!--  The average number of pending loans per bank -->
<p>
	<h4><spring:message code="administrator.dashboard.avgNumberPendingLoansPerBank" /></h4>
</p>
<jstl:out value="${avgNumberPendingLoansPerBank}" />
<br>

<!--  The average number of white papers per investor. -->
<p>
	<h4><spring:message code="administrator.dashboard.avgNumberWhitePapersPerInvestor" /></h4>
</p>
<jstl:out value="${avgNumberWhitePaperPerInvestor}" />
<br>

<!--  The listing of investors who have published more than the average number of white papers during the last quarter. -->
<p>
	<h4><spring:message code="administrator.dashboard.investorWithMoreAvNumberWPLastQuarter" /></h4>
</p>

<display:table name="investorWithMoreAvNumberWPLastQuarter" id="row" requestURI="${requestURI}" pagesize="5" class="displaytag" >
	
	<display:column property="name" titleKey="investor.name" />
	<display:column property="surname" titleKey="investor.surname" />
	<display:column property="company" titleKey="investor.company" />
</display:table>

<!--  The minimum, the average, and the maximum number of sections per white paper that has been published during the last quarter. -->
<p>
	<h4><spring:message code="administrator.dashboard.minAvgMaxSectionsPerWhitePaper" /></h4>
</p>
	<spring:message code="administrator.dashboard.min" />:
		<jstl:out value="${minSectionsPerWhitePaper}" /> <br>
	<spring:message code="administrator.dashboard.max" />:
		<jstl:out value="${avgSectionsPerWhitePaper}" /> <br>
	<spring:message code="administrator.dashboard.avg" />:
		<jstl:out value="${maxSectionsPerWhitePaper}" /> <br>		
<br>
