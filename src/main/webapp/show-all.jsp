<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<style><%@include file='css/goodlook.css'%></style>
<html>
<head>
    <title>Show All</title>
</head>
<body>
    <h1 align="center">All Students</h1>
    <table cellspacing="2" cellpadding="3" border="1" width="100%">
        <thead>
        <tr>
            <th>Scorebook Number</th>
            <th>Name</th>
            <th>Phone Number</th>
            <th>Email</th>
            <th>Average Score</th>
            <th>Delete</th>
            <th>Update</th>
        <tr>
        </thead>
        <tbody>
        <c:choose>
            <c:when test="${fn:length(allStudents)>0}">
                <c:forEach var="row" items="${allStudents}">
                    <tr>
                        <td>${row.scoreBookNumber}</td>
                        <td>${row.name}</td>
                        <td>${row.phoneNumber}</td>
                        <td>${row.email}</td>
                        <td>${row.averageScore}</td>
                        <td><a href="http://localhost:8080/manager/controller/delete/${row.scoreBookNumber}">Delete</a></td>
                        <td><a href="http://localhost:8080/manager/controller/preUpdate/${row.scoreBookNumber}">Update</a></td>
                    </tr>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <tr>
                    <td colspan="2">Нет данных для вывода</td>
                </tr>
            </c:otherwise>
        </c:choose>
        </tbody>
    </table>
    <br>
    <a href="http://localhost:8080/manager/json/toJson">Get all entities in json file</a>
    <br>
    <a href="http://localhost:8080/manager/json/fromJson">Get all objects from json file</a>
</body>
</html>
