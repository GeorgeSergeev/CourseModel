<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<style><%@include file='css/goodlook.css'%></style>
<html>
<head>
    <title>index</title>
</head>
<body>
    <center><h1>Create Student</h1></center>
    <center>
        <form action="http://localhost:8080/manager/controller/createStudent" method="post">
        <table border = "0">
            <tr>
                <td><b>Name</b></td>
                <td><input type = "text" name = "name" size = "50"/></td>
            </tr>
            <tr>
                <td><b>Phone Number</b></td>
                <td><input type = "text" name = "phoneNumber" size = "50"/></td>
            </tr>
            <tr>
                <td><b>Email</b></td>
                <td><input type = "text" name = "email" size = "50"/></td>
            </tr>
            <tr>
                <td><b>Courses available</b></td>
                <td>
                    <select name = "possible-result">
                        <c:forEach var="row" items="${allCourses}">
                            <option value = "${row.number}">${row.name}</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <td colspan = "2"><input type = "submit" value = "Create"/></td>
            </tr>
        </table>
    </form>
    <a href="http://localhost:8080/manager/controller/getAll">Show all students</a>
    <a href="http://localhost:8080/manager/courseController/preCreateCourse">Create course</a>
    </center>
</body>
</html>
