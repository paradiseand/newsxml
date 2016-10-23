package org.crazyit.news.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.crazyit.news.io.Dom4jWrite;


public class AddUserServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request,HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		//获取回复的标题、作者、内容
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		
		Dom4jWrite dom4jWrite = new Dom4jWrite();
		String path = getServletContext()
				.getRealPath("/users.xml");
		try {
			
			int n = dom4jWrite.addUser(id, name, password, path);
			if(n==1){
				response.sendRedirect("/news/login.jsp");
			}else{
				
				response.sendRedirect("/news/register.jsp?error=error");
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
	
}
