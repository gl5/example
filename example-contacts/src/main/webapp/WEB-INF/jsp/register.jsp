<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>通讯录</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js" ></script>
</head>
<body>
	<h1>注册</h1>
	<form id="register-form" action="${pageContext.request.contextPath}/user/register" method="post">
		<table>
			<tr>
				<td>账号：</td>
				<td>
					<input id="username" name="username" type="text" />
				</td>
			</tr>
			<tr>
				<td>密码：</td>
				<td>
					<input id="password" name="password" type="password" />
				</td>
			</tr>
			<tr>
				<td>再输一次：</td>
				<td>
					<input id="password2" type="password" />
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" value="注册" />
					<input type="button" value="返回" onclick="window.location.href='${pageContext.request.contextPath}';" />
				</td>
			</tr>
		</table>
	</form>
	<script type="text/javascript">
	$(function(){
		$('#register-form').submit(function(){
			if($.trim($('#username').val())==''){
				alert('请输入账号!');
				return false;
			}
			if($.trim($('#password').val())==''){
				alert('请输入密码!');
				return false;
			}
			if(!$.trim($('#password2').val())==$.trim($('#password').val())){
				alert('两次密码必须一致!');
				return false;
			}
			return true;
		});
	});
	</script>
</body>
</html>