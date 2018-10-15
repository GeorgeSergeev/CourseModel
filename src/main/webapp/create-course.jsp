<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<style><%@include file='css/goodlook.css'%></style>
<html>
<head>
    <title>Create new course</title>
</head>
<body>
    <h1 align="center">Create New Course</h1>
    <center>
        <form action="http://localhost:8080/manager/courseController/createCourse" method="post">
            <table border = "0">
                <tr>
                    <td><b>Name</b></td>
                    <td><input type = "text" name = "name" size = "50"/></td>
                </tr>
                <tr>
                    <td><b>Price</b></td>
                    <td><input type = "text" name = "price" size = "50"/></td>
                </tr>
                <tr>
                    <td><b>Professor</b></td>
                    <td><input type = "text" name = "professor" size = "50"/></td>
                </tr>
                <tr>
                    <td colspan = "2"><input type = "submit" value = "Create"/></td>
                </tr>
            </table>
        </form>
        <a href="http://localhost:8080/manager/courseController/getAllCourses">Show all courses</a>
        <a href="http://localhost:8080/manager/controller/preCreateStudent">Create student</a>
    </center>
</body>
</html>
