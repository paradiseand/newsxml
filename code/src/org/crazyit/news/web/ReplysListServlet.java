package org.crazyit.news.web;

import javax.servlet.http.*;
import javax.servlet.*;

import java.util.*;
import java.io.*;

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
public class ReplysListServlet extends HttpServlet 
{
	public void service(HttpServletRequest request,
		HttpServletResponse response)
		throws ServletException,IOException
	{
		//设置解码方式
		request.setCharacterEncoding("UTF-8");
		ServletContext application = getServletContext();
		//获取文件名
		String file = request.getParameter("file");
		//如果file参数不存在
		if (file == null || file.trim().equals(""))
		{
			//重定向到list.xml
			response.sendRedirect("list.xml");
		}
		else
		{
			//以file参数指定的XML文档作为响应
			request.getRequestDispatcher("/WEB-INF/xml/" + file.trim())
				.forward(request, response);
		}
	}
}
