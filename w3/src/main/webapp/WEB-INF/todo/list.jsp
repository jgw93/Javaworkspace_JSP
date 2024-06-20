<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 24. 4. 11.
  Time: 오전 11:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Todo list</title>
</head>
<body>
<h1>Todo List</h1>

<ul>
    <h2>${loginInfo}</h2>
    <h3>${loginInfo.mname}</h3>
    <c:forEach items="${dtoList}" var="dto">
        <li>
            <span><a href = "/todo/read?tno=${dto.tno}">${dto.tno}</a></span>
            <span>${dto.title}</span>
            <span>${dto.dueDate}</span>
            <span>${dto.finished? "DONE": "NOT YET"}</span>
        </li>
    </c:forEach>
    <form action = "/logout" method = "post">
        <button>LOGOUT</button>
    </form>
</ul>
</body>
</html>
