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
<title>File Reviews</title>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="/css/main.css">
<script src="/webjars/jquery/jquery.min.js"></script>
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="wrapper">
		<div class="header">
			<a href="/dashboard">Home</a> | <a href="/logout">Logout</a>
			<h2>The following files need to be reviewed:</h2>
		</div>
		<div class="body">
			<div class="reviewList">
				<table class="table table-bordered">
					<thead>
						<tr>
							<td>Name</td>
							<td>Policy Type</td>
							<td>Company</td>
							<td>Office</td>
							<td>Sale Date</td>
							<!-- <td>Sale Count</td> -->
							<td>App Signed?</td>
							<td>Drivers License/<br />Flood Form
							</td>
							<td>Life Quote</td>
							<td>Scanned/<br />Uploaded
							</td>
							<td>Comments</td>
							<td>Review</td>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${saleFiles}" var="sale">
							<tr>
								<td><a href="/sale/${sale.id}">${sale.name}</a></td>
								<td>${sale.type}</td>
								<td>${sale.company}</td>
								<td>${sale.user.userName}</td>
								<td><fmt:formatDate pattern="MM/dd/yyyy"
										value="${sale.saleDate}" /></td>
								<%-- <td>${sale.saleCount}</td> --%>
								<td>${sale.signedApp}</td>
								<td>${sale.driverLicense}</td>
								<td>${sale.lifeQuote}</td>
								<td>${sale.scanned}</td>
								<td>${sale.comments}</td>
								<td><a href="/sale/${sale.id}/review">Review</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</body>
</html>