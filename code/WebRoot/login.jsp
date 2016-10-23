<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'login1.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link href="css/bootstrap.min.css" rel="stylesheet">
	<link href="css/signin.css" rel="stylesheet">

</head>

<body>

<div class="signin">
	<div class="signin-head"><img src="images/test/head_120.png" alt="" class="img-circle"></div>
	<form class="form-signin" role="form" action="LoginServlet">
		<input type="text" class="form-control" placeholder="用户名" name="id" required autofocus />
		<input type="password" class="form-control" name="password" placeholder="密码" required />
		<button class="btn btn-lg btn-warning btn-block" type="submit">登录</button>
		<label class="checkbox">
			<input type="checkbox" value="remember-me"> 记住我&nbsp;&nbsp;<a href="register.jsp">还没账号?注册</a>
		</label>
		<br>
		<p><c:if test="${param.error=='error'}"> 用户名或密码错误 </c:if> </p>
	</form>
</div>

<div style="text-align:center;">
	
</div>
  </body>
</html>
