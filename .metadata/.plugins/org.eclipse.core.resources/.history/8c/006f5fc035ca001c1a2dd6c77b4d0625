<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- c:out ; c:forEach etc. -->
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- Formatting (dates) -->
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- form:form -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- for rendering errors on PUT routes -->
<%@ page isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit Sale</title>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="/css/main.css">
<script src="/webjars/jquery/jquery.min.js"></script>
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="wrapper">
		<div class="header">
			<a href="/dashboard">Home</a> | <a href="/logout">Logout</a>
		</div>
			<div class="editSale">
				<h2>Edit sale:</h2>
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
							<form:form action="/sale/${sale.id}/update" method="put" modelAttribute="editSaleFile">
								<td><form:input path="name" value="${sale.name}"/><br /><form:errors path="name" /></td>
								<td><form:input path="type" value="${sale.type}" class="form-control form-control-sm"/><br /><form:errors path="type" class="text-danger"/></td>
								<td><form:input path="company" value="${sale.company}" class="form-control form-control-sm"/><br /><form:errors path="company" class="text-danger"/></td>
								<td><form:input path="premium" value="${sale.premium}" class="form-control form-control-sm"/><br /><form:errors path="premium" class="text-danger"/></td>
								<td><form:input path="saleDate" value="${saleDate}" class="form-control form-control-sm" /><br /><form:errors path="saleDate" class="text-danger"/></td>
								<td><form:input path="saleCount" value="${sale.saleCount}" class="form-control form-control-sm"/><br /><form:errors path="saleCount" class="text-danger"/></td>
								<td><form:select path="signedApp">
									<option value="true">Yes</option>
									<option value="false">No</option>
								</form:select></td>
								<td><form:select path="driverLicense">
									<option value="true">Yes</option>
									<option value="false">No</option>
								</form:select></td>
								<td><form:select path="lifeQuote">
									<option value="true">Yes</option>
									<option value="false">No</option>
								</form:select></td>
								<td><form:select path="scanned">
									<option value="true">Yes</option>
									<option value="false">No</option>
								</form:select></td>
								<td><form:input path="comments" value="${sale.comments}"/><form:errors path="user" class="text-danger"/></td>
								<form:input path="user" type="hidden" value="${sale.user.id}"/>
								<td><input type="submit" value="Save"><a href="/sale/${sale.id}/delete">Delete</a></td>
							</form:form>
						</tr>
					</tbody>
				</table>
			</div>
	</div>
</body>
</html>