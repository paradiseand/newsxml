<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
request.setCharacterEncoding("UTF-8");
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'register.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link href="images/css.css" rel="stylesheet" type="text/css"/>
	<link rel="stylesheet" type="text/css" href="css/style.css" media="screen" />
  </head>
  
  <body>
  <div id="wrap">
  	<br>
    <h2><a>用户注册</a></h2>
   	<table width="780" align="center">
		   <tr>
				<td height="5" colspan="3"></td>
			</tr>
			<!-- 处理公告列表元素 -->
			
			<tr>
				<td height="5" colspan="3">
				<form action="AddUserServlet" method="post">
			          账号 ：<input type="text" name="id"/> <span> <c:if test="${param.error=='error'}"> 该用户已存在  </c:if>  </span> <br/>
				昵称： <input type="text" name="name"/><br/>
				密码： <input type="password" name="password" /><br/>
			         <input type="submit" value="注册" onclick="{if(confirm('注册成功！')){return true;}return false;}"/>
			         <input type="button" value="返回" onclick="{window.history.back()}">
				</form>
				</td>
			</tr>
			</table>
		<br><br>
	</div>
  </body>
</html>
