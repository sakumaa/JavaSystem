<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="been.User" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
User loginUser = (User)session.getAttribute("login_user");
List<User> users = (List<User>)session.getAttribute("users");
String msg = (String)session.getAttribute("msg");
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
.msg{
	font-size: 6px;
	font-weight: bold;
	color: red;
}
</style>
<body>
    ようこそ、<%= loginUser.getName() %>さん！！
    <br/>
    <span class="msg">${msg}</span>
    <table class="user_list">
    <tr>
    	<th>ID</th>
    	<th>名前</th>
    	<th>パスワード</th>
    	<th>処理</th>
    </tr>
    <tr class="operate">
		<form method="POST" action="UserManagerServlet">
	    	<td><input name="user_id" type="text" value="" /></td>
	    	<td><input name="user_name" type="text" value="" /></td>
	    	<td><input name="password" type="password" value="" /></td>
	    	<td><input name="submit" type="submit" value="登録" /></td>
		</form>
    </tr>
	<c:forEach var="user" items="${users}" varStatus="status">
	<tr>
		<form method="POST" action="UserManagerServlet">
			<td><input name="user_id" type="text" value="<c:out value="${user.id}"/>">
			<input name="base_id" type="hidden" value="<c:out value="${user.id}"/>"></td>
			<td><input name="user_name" type="text" value="<c:out value="${user.name}"/>">
			<input name="base_name" type="hidden" value="<c:out value="${user.name}"/>"></td>
			<td><input name="password" type="password" placeholder="（変更時のみ入力）" value=""></td>
			<td class="operate"><input name="submit" type="submit" value="更新" /><input name="submit" type="submit" value="削除" /></td>
		</form>
	</tr>
	</c:forEach>
    </table>
    <form method="GET" action="LoginServlet">
        <input type="submit" value="ログイン画面へ">
    </form>
</body>
</html>