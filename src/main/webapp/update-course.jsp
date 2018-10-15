<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style><%@include file='css/goodlook.css'%></style>
<html>
<head>
    <title>Update Course</title>
</head>
<body>
<h1 align="center">Update Course</h1>
<center>
  <form action="http://localhost:8080/manager/courseController/update" method="post">
    <table border = "0">
      <tr>
        <td><input type = "text" name = "number" size = "50" value="${course.number}" hidden=""/></td>
      </tr>
      <tr>
        <td><b>Name</b></td>
        <td><input type = "text" name = "name" size = "50" value="${course.name}"/></td>
      </tr>
      <tr>
        <td><b>Price</b></td>
        <td><input type = "text" name = "price" size = "50" value="${course.price}"/></td>
      </tr>
      <tr>
        <td><b>Professor</b></td>
        <td><input type = "text" name = "professor" size = "50" value="${course.professor.name}"/></td>
      </tr>
      <tr>
        <td colspan = "2"><input type = "submit" value = "Update"/></td>
      </tr>
    </table>
  </form>
</center>
</body>
</html>
