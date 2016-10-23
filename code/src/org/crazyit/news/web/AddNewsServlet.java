package org.crazyit.news.web;

import javax.servlet.http.*;
import javax.servlet.*;

import java.util.*;
import java.io.*;

import org.dom4j.*;
import org.dom4j.io.*;
import org.crazyit.news.io.*;
/**
 * Description:
 * <br/>网站: <a href="http://www.crazyit.org">疯狂Java联盟</a> 
 * <br/>Copyright (C), 2001-2010, Yeeku.H.Lee
 * <br/>This program is protected by copyright laws.
 * <br/>Program Name:
 * <br/>Date:
 * @author  Yeeku.H.Lee kongyeeku@163.com
 * @version  1.0
 */
public class AddNewsServlet extends HttpServlet 
{	
	public void service(HttpServletRequest request,
		HttpServletResponse response)
		throws ServletException,IOException
	{
		//设置解码方式
		request.setCharacterEncoding("UTF-8");
		//获取新发公告的标题、作者、内容
		String title = request.getParameter("title");
		String user = (String) request.getSession().getAttribute("user");
		String content = request.getParameter("content");
		//验证公告标题、公告内容不能为空。
		if (title == null || title.trim().equals("")
			|| content == null || content.trim().equals(""))
		{
			//重定向的list.xml文档
			response.sendRedirect("list.xml");
		}
		else
		{
			//获取list.xml的绝对路径
			String path = getServletContext()
				.getRealPath("/list.xml");
			try
			{
				SAXReader reader = new SAXReader();
				//忽略元素的开始标签、结束标签之间的空白
				reader.setStripWhitespaceText(true);
				//将元素之间相邻的文本内容合并处理
				reader.setMergeAdjacentText(true);
				//创建Dom4jWrite解析器的实例
				Dom4jWrite dw = new Dom4jWrite();
				//向list.xml文档中添加一个<公告.../>节点
				dw.addElement(path, title, user, content);
				//建一个XML文档来保存指定公告的用户评论
				dw.addNews(title, user, content, path);
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			//重定向的list.xml文档
			response.sendRedirect("list.xml");
		}
	}
}
