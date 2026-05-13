<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<a href="add.jsp">添加</a>
<table border="1">
    <tr>
        <td>ID</td>
        <td>标题</td>
        <td>内容</td>
        <td>创建时间</td>
        <td>操作员</td>
        <td>操作</td>
    </tr>
    <c:forEach items="${list}" var="news">
        <tr>
            <td>${news.id}</td>
            <td>${news.title}</td>
            <td>${news.content}</td>
            <td>${news.time}</td>
            <td>${news.opername}</td>
            <td>
                <a href="/get?id=${news.id}">编辑</a>
                <a href="/delete?id=${news.id}">删除</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>