<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<form action="save" method="post"> <!-- 상대 경로 사용 -->
    name: <input type="text" name="name" />
    age:  <input type="text" name="age" />
    <button type="submit">전송</button>
</form>
</body>
</html>