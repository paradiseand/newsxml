<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:template match="/">
	<html>
		<head>
			<title>用户注册</title>
			<link href="images/css.css" rel="stylesheet" type="text/css"/>
		</head>
		<body>
			<table width="780" align="center"
				background="images/bodybg.jpg">
		
			<tr>
				<td height="5" colspan="3"><hr /></td>
			</tr>
			<!-- 处理公告列表元素 -->
			<xsl:apply-templates select="用户注册"/>
			<tr>
				<td height="5" colspan="3">
				<form action="AddUserServlet" method="post">
			          账号 ：<input type="text" name="id"/><br/>
				昵称：<input type="text" name="name"/><br/>
				密码：<input type="password" name="password" /><br/>
				<input type="submit" value="注册"/>
				</form>
				</td>
			</tr>
			</table>
		</body>
	</html>
</xsl:template>

</xsl:stylesheet>
