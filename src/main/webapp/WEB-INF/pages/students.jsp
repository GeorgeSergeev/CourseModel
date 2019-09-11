<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>STUDENTS</title>
</head>
<body>

<h2>Students</h2>
<table>
    <tr>
        <td>Student Number</td>
        <th>Name</th>
        <th>Address</th>
        <th>Phone</th>
        <th>E-mail</th>
        <th>Gradebook number</th>
        <th>Average Score</th>
        <th>Listen Courses</th>
        <th>Actions</th>

    </tr>
    <c:forEach var="student" items="${studentsList}">
        <tr>
            <td>${student.studentID}</td>
            <td>${student.name}</td>
            <td>${student.address}</td>
            <td>${student.phone}</td>
            <td></td>
            <td></td>
            <td></td>
            <td>
                <a href="/listen/${student.studentID}">Get Courses Info</a>
            </td>
        </tr>
    </c:forEach>
</table>

<h2>Courses</h2>
<c:url value="/courses" var="courses"/>
<a href="${courses}">View course list</a>

</body>
</html>