<%--
  Created by IntelliJ IDEA.
  User: Yang Shuailing
  Date: 2023/4/7
  Time: 18:31
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>userList</title>
</head>
<body>
<table>
    <c:forEach items="${users}" var="user">
        <td>${user.username}</td>
    </c:forEach>
</table>
</body>
</html>
