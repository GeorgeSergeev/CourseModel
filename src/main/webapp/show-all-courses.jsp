<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<style><%@include file='css/goodlook.css'%></style>
<html>
<head>
    <title>All Courses</title>
</head>
<body>
  <h1 align="center">All Courses</h1>
  <table cellspacing="2" cellpadding="3" border="1" width="100%">
    <thead>
  <tr>
    <th>Number</th>
    <th>Name</th>
    <th>Price</th>
    <th>Professor</th>
    <th>Delete</th>
    <th>Update</th>
  <tr>
  </thead>
  <tbody>
  <c:choose>
    <c:when test="${fn:length(allCourses)>0}">
      <c:forEach var="row" items="${allCourses}">
        <tr>
          <td>${row.number}</td>
          <td>${row.name}</td>
          <td>${row.price}</td>
          <td>${row.professor.name}</td>
          <td><a href="http://localhost:8080/manager/courseController/delete/${row.number}">Delete</a></td>
          <td><a href="http://localhost:8080/manager/courseController/preUpdate/${row.number}">Update</a></td>
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
</body>
</html>
