<jsp:useBean id="currentMeals" scope="request" type="java.util.List"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="dateTime" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Meals</title>

    <style>
        th {
            background: #A8C7FF; /*поля таблицы*/
            color: white;
            padding: 15px 30px;
        }

        tr.exceeded {
            background-color: #FF987E
        }

        tr.notexceeded {
            background-color: #93E656
        }
    </style>

</head>
<body>


<h3><a href="index.html">Home</a></h3>

<h2>Meals</h2>

<c:if test="${!empty currentMeals}">
    <table align="center" class="tg">
        <tr>
            <th width="80">dateTime</th>
            <th width="120">description</th>
            <th width="120">calories</th>
            <th width="120">exceed</th>
        </tr>
        <c:forEach items="${currentMeals}" var="meal">
            <tr class="${meal.exceed  == true ? "exceeded" : "notexceeded"}">
                <td>${fn:replace(meal.dateTime, 'T', ' ')}</td>
                <td>${meal.description}</td>
                <td>${meal.calories}</td>
                <td>${meal.exceed}</td>
            </tr>
        </c:forEach>
    </table>
</c:if>


</body>
</html>



