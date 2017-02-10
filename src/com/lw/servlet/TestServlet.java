package com.lw.servlet;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TestServlet extends HttpServlet{

	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse arg1)
			throws ServletException, IOException {
		Enumeration<String>enu = request.getParameterNames();
		System.out.println("________"+request.getParameter("title"));
		while(enu.hasMoreElements()) {
			String name = enu.nextElement();
			String value = request.getParameter(name);
			System.out.println(name+":"+value);
		}
		
	}
}
