<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:template match="/">
	<html>
		<head>
			<title>评论列表</title>
			<link href="images/css.css" rel="stylesheet" type="text/css"/>
			<link rel="stylesheet" type="text/css" href="css/style.css" media="screen" />
		</head>
		<body>
		<div id="wrap">
			<table width="780" align="left">
			<tr>
				<td rowspan="2"><font size="5"><b><h2><a>评论列表</a></h2></b></font><br/>
				本应用是一个基于XML的电子公告系统，每个用户都可以添加新的电子公告，
				还可以对电子公告添加评论。<br/>
				本应用无需使用数据库，所有数据都采用XML文档进行保存，本应用综合运用了
				XML文档、XML Schema、XSLT转换、CSS样式单、dom4j解析等知识。<br/>
				如果学习、使用本应用过程遇到任何问题，都可登录系统发帖提问。
				</td>
			</tr>
			<tr></tr>
			<tr>
				<td height="5" colspan="3"><hr /></td>
			</tr>
			<!-- 处理评论列表元素 -->
			<xsl:apply-templates select="评论列表"/>
			<tr>
				<td height="5" colspan="3">
				<form action="addReply" method="post">
				标题：<input type="text" name="title"/><br/>
			          内容：<br/><textarea name="content" cols="50" rows="10"/><br/>
				<input type="submit" value="我想评论"/>
				</form>
				</td>
			</tr>				
			</table>
			</div>
		</body>
	</html>
</xsl:template>
<xsl:template match="评论列表">
	<!-- 处理评论公告元素 -->
	<xsl:apply-templates select="公告"/>
	<!-- 处理评论评论元素 -->
	<xsl:apply-templates select="评论"/>
</xsl:template>
<xsl:template match="公告">
	<tr>
		<td class="mytitle" colspan="3"><h2><a><xsl:value-of select="标题"/></a></h2></td>
	</tr>
	<tr>
		<td colspan="3"><span>日期：<xsl:value-of select="发布时间"/></span>
		<span>作者：<xsl:value-of select="作者"/></span>
		<span><a href="list.xml">返回首页</a></span>
		</td>
	</tr>
	<tr>
		<td colspan="3"><div class="content">
			<!-- 将内容元素下的文本节点、br元素复制到结果文档中 -->
			<xsl:copy-of select="内容/text()|内容/br"/></div></td>
	</tr>
	<tr>
		<td colspan="3">
		<span class="comment-sub">以下是用户对此公告的评论</span><hr/>
		</td>
	</tr>
</xsl:template>
<xsl:template match="评论">
	<tr><td colspan="3">
		<div class="comment">
			<div class="ctitle">
				<xsl:value-of select="标题"/>
				<span style="font-size:9pt;padding-left:20px">
					<xsl:value-of select="作者"/>
					于<xsl:value-of select="评论时间"/>评论</span>
			</div>
			<div>
				<!-- 将内容元素下的文本节点、br元素复制到结果文档中 -->
				<xsl:copy-of select="内容/text()|内容/br"/>
			</div>
		</div>
	</td></tr>
</xsl:template>
</xsl:stylesheet>