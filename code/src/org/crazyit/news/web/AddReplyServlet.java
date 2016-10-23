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
 * <br/>��վ: <a href="http://www.crazyit.org">���Java����</a> 
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
		//���ý��뷽ʽ
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		//��ȡ�ظ��ı��⡢���ߡ�����
		String title = request.getParameter("title");
		String user = (String) request.getSession().getAttribute("user");
		String content = request.getParameter("content");
		//��ȡReferer����ͷ��ֵ��ͨ��������ͷ����ȡ��������������б�XML�ĵ���
		String path = request.getHeader("Referer");
		String file = "";
		//��ȡ����������Ϣ��XML�ļ����ļ�����
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
			//���file����Ϊ���ַ������ض���list.xml
			response.sendRedirect("list.xml");
		}
		else
		{
			//�ض�file����ǰ��Ŀո�
			file = file.trim();
			//��֤�ظ����⡢���ݲ���Ϊ�ա�
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
					//��ȡ�ظ�XML�ĵ��ľ���·��
					String filePath = getServletContext()
						.getRealPath("/WEB-INF/xml/" + file);
					//����Dom4jWrite��������ʵ��
					Dom4jWrite dw = new Dom4jWrite();
					dw.addReply(filePath, title, user, content);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
				//�ٴν��ող鿴�������б�XML�ĵ���Ϊ��Ӧ���
				request.getRequestDispatcher("/replysList?file=" + file)
					.forward(request, response);
			}
		}
	}
}
