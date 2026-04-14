<%--
  Created by IntelliJ IDEA.
  User: zijian
  Date: 2026年4月14日
  Time: 下午10:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    String username = (String) request.getAttribute("username");
//    String username = (String) session.getAttribute("username");
%>
<h1><%=username%></h1>
<%-- EL表达式 --%>
<h1>${username}</h1>
<br />
<a href="logout">退出登录</a>
<%-- JSP 页面有一个隐含对象 session --%>
</body>
</body>
</html>
