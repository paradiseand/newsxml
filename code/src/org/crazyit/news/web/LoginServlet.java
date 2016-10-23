package org.crazyit.news.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.crazyit.news.io.Dom4jWrite;

public class LoginServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request,HttpServletResponse response)
			throws ServletException, IOException {
		
		Dom4jWrite dom4jWrite = new Dom4jWrite();
		String id = request.getParameter("id");
	    String password = request.getParameter("password");
		
		
		String path = getServletContext()
				.getRealPath("/users.xml");
		
		try {
			String name = dom4jWrite.login(id, password, path);
			
			if(!name.equals("")){
				request.getSession().setAttribute("user", name);
				response.sendRedirect("/news/list.xml");
			}else{
				response.sendRedirect("/news/login.jsp?error=error");
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	
}
