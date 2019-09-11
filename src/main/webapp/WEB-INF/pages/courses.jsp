<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>COURSES</title>
</head>
<body>

<h2>Students</h2>
<table>
    <tr>
        <th>Course Number</th>
        <th>Name</th>
        <th>Cost</th>
        <th>Lecturer</th>
        <th>Actions</th>

    </tr>
    <c:forEach var="course" items="${coursesList}">
        <tr>
            <td>${course.courseID}</td>
            <td>${course.courseName}</td>
            <td>${course.coursePrice}</td>
        </tr>
    </c:forEach>
</table>

<h2>Add new course</h2>
<%--<c:url value="/fill" var="fill"/>--%>
<%--<a href="${fill}">Fill</a>--%>
</body>
</html>