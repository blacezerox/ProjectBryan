<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Feedback Edit</title>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <link rel="stylesheet"
href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
crossorigin="anonymous">

</head>
<body>
<nav class="navbar navbar-expand-md navbar-light">
<div>
<a class="navbar-brand"> Feedback Management Application </a>
</div>
<ul class="navbar-nav">
<li><a href="<%=request.getContextPath()%>/FeedbackListServlet/dashboard"
class="nav-link">Back to Dashboard</a></li>
</ul>
</nav>
<div class="container col-md-6">
<div class="card">
<div class="card-body">
<c:if test="${feedback != null}">
<form action="update" method="post">
</c:if>
<c:if test="${feedback == null}">
<form action="insert" method="post">
</c:if>
<caption>
<h2>
<c:if test="${feedback != null}">
Edit Feedback
</c:if>
<c:if test="${feedback == null}">
Add New Feedback
</c:if>
</h2>
</caption>
<c:if test="${feedback != null}">
<input type="hidden" name="oriquestion1" value="<c:out
value='${feedback.question1}' />" />
</c:if>
<fieldset class="form-group">
<label>Name</label> <input type="text" value="<c:out
value='${feedback.question1}' />" class="form-control" name="question1" required="required">
</fieldset>
<fieldset class="form-group">
<label>Did You Find The Information Useful?</label> <input type="text" value="<c:out
value='${feedback.question2}' />" class="form-control" name="question2">
</fieldset>
<fieldset class="form-group">
<label>If Yes,Why? If no, Give Us Feedback on how we can improve?</label> <input type="text" value="<c:out
value='${feedback.question3}' />" class="form-control" name="question3">
</fieldset>
<fieldset class="form-group">
<label>Out of 3, Rate Your Experience With Us(1-good, 2-OK,i guess, 3- Not good)</label> <input type="text" value="<c:out
value='${feedback.question4}' />" class="form-control" name="question4">
</fieldset>
<button type="submit" class="btn btn-success">Save</button>
</form>
</div>
</div>
</div>
</body>
</html>