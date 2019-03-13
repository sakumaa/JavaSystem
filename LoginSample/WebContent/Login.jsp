<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ログイン画面</title>
</head>
<body>
    <form method="POST" action="LoginServlet">
        ユーザーID：<input type="text" name="user_id" />
        <br/>
        パスワード：<input type="password" name="password">
        <br/>
        <input type="submit" value="ログイン">
    </form>
</body>
</html>