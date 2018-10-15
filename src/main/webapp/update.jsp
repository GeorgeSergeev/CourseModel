<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<style><%@include file='css/goodlook.css'%></style>
<html>
<head>
    <title>Update</title>
</head>
<body>
<h1 align="center">Update Student</h1>
<center>
  <form action="http://localhost:8080/manager/controller/update" method="post">
    <table border = "0">
      <tr>
        <td><input type = "text" name = "scoreBookNumber" size = "50" value="${student.scoreBookNumber}" hidden=""/></td>
      </tr>
      <tr>
        <td><b>Name</b></td>
        <td><input type = "text" name = "name" size = "50" value="${student.name}"/></td>
      </tr>
      <tr>
        <td><b>Phone Number</b></td>
        <td><input type = "text" name = "phoneNumber" size = "50" value="${student.phoneNumber}"/></td>
      </tr>
      <tr>
        <td><b>Email</b></td>
        <td><input type = "text" name = "email" size = "50" value="${student.email}"/></td>
      </tr>
      <tr>
        <td colspan = "2"><input type = "submit" value = "Update"/></td>
      </tr>
    </table>
  </form>
  <h1 align="center">Course Info</h1>
  <form action="http://localhost:8080/manager/controller/changeCourse/${student.getScoreBookNumber()}" method="post">
    <table border = "0">
      <tr>
        <td><b>Current course: ${course}</b></td>
      </tr>
      <tr>
        <td><b>Change course</b></td>
        <td>
          <select name = "possible-result">
            <c:forEach var="row" items="${courseList}">
              <option value = "${row.number}">${row.name}</option>
            </c:forEach>
          </select>
        </td>
      </tr>
        <td colspan = "2"><input type = "submit" value = "Update course"/></td>
      </tr>
    </table>
  </form>
  <h1 align="center">Course Progress</h1>
  <form action="http://localhost:8080/manager/controller/passExam/${student.getScoreBookNumber()}" method="post">
    <table border = "0">
      <tr>
        <td><b>Course score: ${student.courseProgress.scoreList}</b></td>
      </tr>
      <tr>
        <td><b>Current average score: ${student.courseProgress.courseAverageScore}</b></td>
      </tr>
      <tr>
        <td><b>Final score: ${student.courseProgress.finalScore}</b></td>
      <c:if test="${student.courseProgress.finalScore == 0.0}">
        </tr>
          <td colspan = "2"><input type = "submit" value = "Pass exam"/></td>
        </tr>
      </c:if>
    </table>
  </form>
  <h1 align="center">History</h1>
  <table border = "0">
    <tr>
      <td><b>Course name and score: ${student.history.historyLine}</b></td>
    </tr>
    <tr>
      <td><b>Total average score: ${student.averageScore}</b></td>
    </tr>
    <tr>
      <td><b>Number of finished courses: ${student.numFinishedCourses}</b></td>
    </tr>
    <tr>
      <td><b>Total score of all courses: ${student.totalScore}</b></td>
    </tr>
  </table>
</center>
</body>
</html>
