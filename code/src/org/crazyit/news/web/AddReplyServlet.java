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

public class AddReplyServlet extends HttpServlet 
{
	String[] f;
	 public void service(HttpServletRequest request,
		HttpServletResponse response)
		throws ServletException,IOException
	{
		//设置解码方式
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		//获取回复的标题、作者、内容
		String title = request.getParameter("title");
		String user = (String) request.getSession().getAttribute("user");
		String content = request.getParameter("content");
		//获取Referer请求头的值，通过该请求头来获取正在浏览的评论列表XML文档。
		String path = request.getHeader("Referer");
		String file = "";
		//获取保存评论信息的XML文件的文件名。
		if (path != null && path.indexOf("=") >= 0)
		{
			f = path.split("=");
			file = f[1];
			session.setAttribute("file", file);
		}
		else
		{
			file = (String)session.getAttribute("file");
		}
		if (file.trim().equals(""))
		{
			//如果file参数为空字符串，重定向到list.xml
			response.sendRedirect("list.xml");
		}
		else
		{
			//截断file参数前后的空格
			file = file.trim();
			//验证回复标题、内容不能为空。
			if (title == null || title.trim().equals("")
				|| content == null || content.trim().equals(""))
			{
				request.getRequestDispatcher("/replysList?file=" + file)
					.forward(request, response);
			}
			else
			{
				try
				{
					//获取回复XML文档的绝对路径
					String filePath = getServletContext()
						.getRealPath("/WEB-INF/xml/" + file);
					//创建Dom4jWrite解析器的实例
					Dom4jWrite dw = new Dom4jWrite();
					dw.addReply(filePath, title, user, content);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
				//再次将刚刚查看的评论列表XML文档作为响应输出
				request.getRequestDispatcher("/replysList?file=" + file)
					.forward(request, response);
			}
		}
	}
}
