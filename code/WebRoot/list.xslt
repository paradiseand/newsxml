<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:template match="/">
	<html>
		<head>
			<title>公告列表</title>
		<link href="images/css.css" rel="stylesheet" type="text/css"/>
		<link rel="stylesheet" type="text/css" href="css/style.css" media="screen" />
	</head>
	<body>
	
<div id="wrap">
	
	<div id="header">
	<h1><a href="#">电子公告系统</a></h1>
	<h2>基于xml技术的电子公告系统  V1.1.0，每个用户都可以添加新的电子公告，还可以对电子公告添加评论。</h2>
	</div>
	
	<div id="menu">
	<ul>
	<li><a href="#">主页</a></li>
	<li><a href="#">关于</a></li>
	<li><a href="#">友情链接</a></li>
	<li><a href="#">框架</a></li>
	<li><a href="register.jsp">注册</a></li>
	<li><a href="login.jsp">登录</a></li>
	<li><a href="#">产品说明</a></li>
	</ul>
	</div>
	
	<div id="content">
		<div class="left"> 
		<h2><a href="#">电子公告列表</a></h2>
		
		<!-- 处理公告列表元素 -->
		<xsl:apply-templates select="公告列表"/> 
		<br /><br />
		<img src="images/pic.png" alt="Example pic" style="border: 3px solid #ccc;" />
		<br /><br />
		</div>
		
		<form action="addNews" method="post">
			<table align="center">
			<tr><td>标题：</td> <td><input type="text" name="title" size="60" /></td></tr>
			<tr><td>内容：</td> <td><textarea name="content" cols="100" rows="10"/></td></tr>
			<tr><td><input type="submit" value="发布"/></td></tr>
			</table>
		</form>
		
		<div id="footer">
				Copyright 2015
		</div>
	
	</div>
</div>
				
	</body>
</html>
</xsl:template>
<!-- 为公告列表定义转换模板 -->
<xsl:template match="公告">
<table>
	<tr>
		<td class="mytitle" colspan="3"><h2><a href="#"><xsl:value-of select="标题"/></a></h2></td>
	</tr>
	<tr>
		<td colspan="3"><span>日期：<xsl:value-of select="发布时间"/></span>
		<span>作者：<xsl:value-of select="作者"/></span>
		<span><a href="replysList?file={文件名}.xml">查看/发表评论</a></span>
		</td>
	</tr>
	<tr>
		<td colspan="3"><div class="content">
			<!-- 将内容元素下的文本节点、br元素复制到结果文档中 -->
			<xsl:copy-of select="内容/text()|内容/br"/></div></td>
	</tr>
</table>
</xsl:template>
</xsl:stylesheet>
