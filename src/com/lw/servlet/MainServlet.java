package com.lw.servlet;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.SQLQuery;
import org.hibernate.Session;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lw.entry.DemoEntry;
import com.lw.entry.HibernateSessionFactory;

public class MainServlet extends HttpServlet{

	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Session session = HibernateSessionFactory.getSession();
		SQLQuery query = (SQLQuery) session.getNamedQuery("getAllInfo");
		query.addEntity(DemoEntry.class);
		List<DemoEntry> data = null;
		try{
			data = query.list();
		}catch(Exception e) {
			e.printStackTrace();
			HibernateSessionFactory.rebuildSessionFactory();
			query = (SQLQuery) session.getNamedQuery("getAllInfo");
			query.addEntity(DemoEntry.class);
			data = query.list();
		}
		Gson gson = new GsonBuilder() .setPrettyPrinting().disableHtmlEscaping() .create();
		String json = gson.toJson(data);
		System.out.println(json);
		OutputStream out = resp.getOutputStream();
		out.write(json.getBytes("utf-8"));
		out.flush();
		out.close();
	}
	
}
