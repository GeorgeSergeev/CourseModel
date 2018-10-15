<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<style><%@include file='css/goodlook.css'%></style>
<html>
<head>
    <title>Index</title>
</head>
<body>
    <a href="create-course.jsp">Create new course</a>
    <sec:authorize access="hasRole('ROLE_ADMIN')">
        <a href="<c:url value="/success.jsp" />" > Administration</a>
    </sec:authorize>
</body>
</html>
