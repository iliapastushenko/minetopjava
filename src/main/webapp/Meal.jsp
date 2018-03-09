<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Meal</title>

</head>
<body>

<h2><a href="index.html">Home</a></h2>

<form align="center" method="POST" action=meals >

    <input type="hidden" name="mealid"
                     value="<c:out value="${meal.id}" />"/> <br/>

    DateTime: <input type="datetime-local" name="dateTime"

                    value="<c:out value="${meal.dateTime}"/>"/><br/>

    Description: <input type="text" name="description"

                       value="<c:out value="${meal.description}"/>"/><br/>

    Calories: <input type="number" name="calories"

                    value="<c:out value="${meal.calories}"/>"/><br/>

    <input type="submit" value="submit"/>


</form>
</body>
</html>