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
public class AddNewsServlet extends HttpServlet 
{	
	public void service(HttpServletRequest request,
		HttpServletResponse response)
		throws ServletException,IOException
	{
		//���ý��뷽ʽ
		request.setCharacterEncoding("UTF-8");
		//��ȡ�·�����ı��⡢���ߡ�����
		String title = request.getParameter("title");
		String user = (String) request.getSession().getAttribute("user");
		String content = request.getParameter("content");
		//��֤������⡢�������ݲ���Ϊ�ա�
		if (title == null || title.trim().equals("")
			|| content == null || content.trim().equals(""))
		{
			//�ض����list.xml�ĵ�
			response.sendRedirect("list.xml");
		}
		else
		{
			//��ȡlist.xml�ľ���·��
			String path = getServletContext()
				.getRealPath("/list.xml");
			try
			{
				SAXReader reader = new SAXReader();
				//����Ԫ�صĿ�ʼ��ǩ��������ǩ֮��Ŀհ�
				reader.setStripWhitespaceText(true);
				//��Ԫ��֮�����ڵ��ı����ݺϲ�����
				reader.setMergeAdjacentText(true);
				//����Dom4jWrite��������ʵ��
				Dom4jWrite dw = new Dom4jWrite();
				//��list.xml�ĵ������һ��<����.../>�ڵ�
				dw.addElement(path, title, user, content);
				//��һ��XML�ĵ�������ָ��������û�����
				dw.addNews(title, user, content, path);
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			//�ض����list.xml�ĵ�
			response.sendRedirect("list.xml");
		}
	}
}
