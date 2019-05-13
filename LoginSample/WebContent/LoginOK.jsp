<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="been.User" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
User loginUser = (User)session.getAttribute("login_user");
List<User> users = (List<User>)session.getAttribute("users");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ログイン成功</title>
</head>
<style>
.user_list{
	border: 3px #888 solid;
	border-collapse: collapse;
}
.user_list th,
.user_list td{
	border: 1px #888 solid;
	padding: 2px 10px;
}
.user_list th{
	background-color: #25f;
	color: #fff;
}
.operate{
	background-color: #25f;
	text-align: center;
}
</style>
<body>
    ようこそ、<%= loginUser.getName() %>さん！！
    <br/>
    <table class="user_list">
    <tr>
    	<th>ID</th>
    	<th>名前</th>
    	<th>パスワード</th>
    	<th>処理</th>
    </tr>
    <tr class="operate">
    	<td><input type="text" value="" /></td>
    	<td><input type="text" value="" /></td>
    	<td><input type="password" value="" /></td>
    	<td><input type="button" value="登録" /></td>
    </tr>
	<c:forEach var="user" items="${users}" varStatus="status">
	<tr>
		<td><c:out value="${user.id}"/></td>
		<td><c:out value="${user.name}"/></td>
		<td><c:out value="****"/></td>
		<td class="operate"><input type="button" value="更新" /><input type="button" value="削除" /></td>
	</tr>
	</c:forEach>
    </table>
    <form method="GET" action="LoginServlet">
        <input type="submit" value="ログイン画面へ">
    </form>
</body>
</html>