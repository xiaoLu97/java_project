<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/add" method="post">
    <table>
        <tr>
            <td>title：</td>
            <td>
                <input type="text" name="title"/>
            </td>
        </tr>
        <tr>
            <td>content：</td>
            <td>
                <input type="text" name="content"/>
            </td>
        </tr>
        <tr>
            <td>opername：</td>
            <td>
                <input type="text" name="opername"/>
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