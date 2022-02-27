<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Feedback Platform</title>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet"
href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
crossorigin="anonymous">
</head>
<body>
<div class="row">
<div class="container">
<h3 class="text-center">List of Users</h3>
<hr>
<div class="container text-left">
<!-- Add new user button redirects to the register.jsp page -->
<a href="<%=request.getContextPath()%>/feedback.jsp" class="btn btnsuccess">Add New Feedback</a>
</div>
<br>
<!-- Create a table to list out all current users information -->
<table class="table">
<thead>
<tr>
<th>Name</th>
<th>Did You Find The Information Useful?</th>
<th>If Yes,Why? If no, Give Us Feedback on how we can improve?</th>
<th>Out of 3, Rate Your Experience With Us</th>
</tr>
</thead>
<tbody>
<c:forEach var="feedback" items="${listFeedback}">
<!-- For each user in the database, display their
information accordingly -->
<tr>
<td>
<c:out value="${feedback.question1}" />
</td>
<td>
<c:out value="${feedback.question2}" />
</td>
<td>
<c:out value="${feedback.question3}" />
</td>
<td>
<c:out value="${feedback.question4}" />
</td>
<!-- For each user in the database, Edit/Delete
buttons which invokes the edit/delete functions -->
<td>
<a href="edit?question1=<c:out value='${feedback.question1}'
/>">Edit</a> &nbsp;&nbsp;&nbsp;&nbsp;
<a href="delete?question1=<c:out
value='${feedback.question1}' />">Delete</a>
</td>
</tr>
</c:forEach>
</tbody>
</table>
</div>
</div>
</body>
</html>