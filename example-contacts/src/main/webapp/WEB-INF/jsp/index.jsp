<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>通讯录</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js" ></script>
</head>
<body>
	<h1>欢迎使用通讯录</h1>
	<p>
		还没有账号？
		<a href="${pageContext.request.contextPath}/register">马上注册</a>
	</p>
	<p>
		老用户？立即
		<a href="${pageContext.request.contextPath}/login">登录</a>
	</p>
</body>
</html>