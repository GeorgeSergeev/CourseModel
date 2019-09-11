<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>FILMS</title>
</head>
<body>

<h2>Listen Courses</h2>
<table>
    <tr>
        <th>Student name</th>
        <th>Current Courses</th>
        <th>action</th>
    </tr>
        <tr>
            <td>${student.name}</td>
            <td>${student.listenedCourses}</td>
            <td>
                <a href="#">add (not implemented)</a>
                <a href="#">delete (not implemented)</a>
            </td>
        </tr>
</table>

</body>
</html>