<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="FeedbackServlet" method="post">
Q1:Name <input type= "text" name="question1">
Q2:Did You Find The Information Useful? <input type= "text" name= "question2">
Q3:If Yes,Why? If no, Give Us Feedback on how we can improve? <input type= "text" name="question3">
Q4: Out of 3, Rate Your Experience With Us(1-good, 2-OK,i guess, 3- Not good) <select name= "question4">
<option>1</option>
<option>2</option>
<option>3</option>
</select>
<input type="submit" value="Submit" />
</form>
</body>
</html>