<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/update" method="post">
    <table>
        <tr>
            <td>ID：</td>
            <td>
                <input type="text" name="id" value="${news.id}" readonly/>
            </td>
        </tr>
        <tr>
            <td>title：</td>
            <td>
                <input type="text" name="title" value="${news.title}"/>
            </td>
        </tr>
        <tr>
            <td>content：</td>
            <td>
                <input type="text" name="content" value="${news.content}"/>
            </td>
        </tr>
        <tr>
            <td>createtime：</td>
            <td>
                <input type="text" name="createtime" value="${news.createtime}" readonly/>
            </td>
        </tr>
        <tr>
            <td>opername：</td>
            <td>
                <input type="text" name="opername" value="${news.opername}"/>
            </td>
        </tr>
        <tr>
            <td>
                <input type="submit" value="提交"/>
            </td>
            <td>
                <input type="reset" value="重置"/>
            </td>
        </tr>
    </table>
</form>
</body>
</html>