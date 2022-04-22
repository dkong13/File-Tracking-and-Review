<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- c:out ; c:forEach etc. --> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- Formatting (dates) --> 
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- form:form -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- for rendering errors on PUT routes -->
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
    <title>Dashboard</title>
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" href="/css/main.css">
    <script src="/webjars/jquery/jquery.min.js"></script>
    <script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="wrapper">
		<div class="header">
			<a href="/logout">Logout</a>
			<h1>Hello ${loggedInUser.userName}! Here is your Dashboard</h1>
			<c:if test="${loggedInUser.userName=='Kinston'}">
				<h4><a href="/adminSalesFilter">Sales by Office</a></h4>
				<h5><a href="/fileReviews">Review Files Here</a></h5>
			</c:if>
		</div>
		<div class="body">
			<div class="newSale">
				<h2>Enter a new sale:</h2>
				<table class="table table-bordered">
					<thead>
						<tr>
							<td>Name</td>
							<td>Policy Type</td>
							<td>Company</td>
							<td>Premium</td>
							<td>Sale Date</td>
							<td>Sale Count</td>
							<td>App Signed?</td>
							<td>Drivers License/<br/>Flood Form</td>
							<td>Life Quote</td>
							<td>Scanned/<br/>Uploaded</td>
							<td>Comments</td>
							<td>Save</td>
						</tr>
					</thead>
					<tbody>
						<tr>
							<form:form action="/saveSale" method="post" modelAttribute="newSaleFile">
								<td><form:input path="name"/><br /><form:errors path="name" class="text-danger"/></td>
								<td><form:input path="type" class="form-control form-control-sm"/><br /><form:errors path="type" class="text-danger"/></td>
								<td><form:input path="company" class="form-control form-control-sm"/><br /><form:errors path="company" class="text-danger"/></td>
								<td><form:input path="premium" class="form-control form-control-sm"/><br /><form:errors path="premium" class="text-danger"/></td>
								<td><form:input path="saleDate" class="form-control form-control-sm" placeholder="MM/DD/YYYY"/><br /><form:errors path="saleDate" class="text-danger"/></td>
								<td><form:input path="saleCount" class="form-control form-control-sm"/><br /><form:errors path="saleCount" class="text-danger"/></td>
								<td><form:select path="signedApp">
									<option value="false">No</option>
									<option value="true">Yes</option>
								</form:select></td>
								<td><form:select path="driverLicense">
									<option value="false">No</option>
									<option value="true">Yes</option>
								</form:select></td>
								<td><form:select path="lifeQuote">
									<option value="false">No</option>
									<option value="true">Yes</option>
								</form:select></td>
								<td><form:select path="scanned">
									<option value="false">No</option>
									<option value="true">Yes</option>
								</form:select></td>
								<td><form:input path="comments"/></td>
								<form:input path="user" type="hidden" value="${loggedInUser.id}"/>
								<td><input type="submit" value="Save"></td>
							</form:form>
						</tr>
					</tbody>
				</table>
			</div>
			<div class="sales">
				<a href="/previousSales">Previous Sales</a>
				<h3>These are your recent sales:</h3>
				<table class="table table-bordered">
					<thead>
						<tr>
							<td>Name</td>
							<td>Policy Type</td>
							<td>Company</td>
							<td>Premium</td>
							<td>Sale Date</td>
							<td>Sale Count</td>
							<td>App Signed?</td>
							<td>Drivers License/<br/>Flood Form</td>
							<td>Life Quote</td>
							<td>Scanned/<br/>Uploaded</td>
							<td>Comments</td>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${saleFiles}" var="sale">
							<tr>
								<td><a href="/sale/${sale.id}">${sale.name}</a></td>
								<td>${sale.type}</td>
								<td>${sale.company}</td>
								<td>$${sale.premium}</td>
								<td><fmt:formatDate pattern = "MM/dd/yyyy" value = "${sale.saleDate}" /></td>
								<td>${sale.saleCount}</td>
								<td>${sale.signedApp}</td>
								<td>${sale.driverLicense}</td>
								<td>${sale.lifeQuote}</td>
								<td>${sale.scanned}</td>
								<td>${sale.comments}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</body>
</html>

