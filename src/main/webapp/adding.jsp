<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Meal</title>
</head>
<body>

<h2><a href="index.html">Home</a></h2>

<form method="POST">
    <input type="hidden"  name="id"
           value="<c:out value="${meals.id}"/>"/><br/>
    dateTime:<input type="datetime-local" name="dateTime"
                    value="<c:out value="${meals.dateTime}"/>"/><br/>
    description:<input type="text" name="description"
                       value="<c:out value="${meals.description}"/>"/><br/>
    calories:<input type="number" name="calories"
                    value="<c:out value="${meals.calories}"/>"/><br/>
    <input type="submit" value="submit"/>
</form>
</body>
</html>