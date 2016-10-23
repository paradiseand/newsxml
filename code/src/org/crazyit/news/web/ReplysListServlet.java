package org.crazyit.news.web;

import javax.servlet.http.*;
import javax.servlet.*;

import java.util.*;
import java.io.*;

import org.crazyit.news.io.*;
/**
 * Description:
 * <br/>��վ: <a href="http://www.crazyit.org">���Java����</a> 
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
		//���ý��뷽ʽ
		request.setCharacterEncoding("UTF-8");
		ServletContext application = getServletContext();
		//��ȡ�ļ���
		String file = request.getParameter("file");
		//���file����������
		if (file == null || file.trim().equals(""))
		{
			//�ض���list.xml
			response.sendRedirect("list.xml");
		}
		else
		{
			//��file����ָ����XML�ĵ���Ϊ��Ӧ
			request.getRequestDispatcher("/WEB-INF/xml/" + file.trim())
				.forward(request, response);
		}
	}
}
