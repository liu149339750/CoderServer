package com.lw.test;


import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;


import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.lw.entry.DemoEntry;
import com.lw.entry.HibernateSessionFactory;

public class Main {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {

		
		test1();
		test2();
	}
	
	private static void test2() throws IOException {
		URL url = new URL("http://localhost:8080/CodeServer/query");
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		InputStream in = con.getInputStream();
		byte buffer[] = new byte[8*1024];
		int len = 0;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		while((len = in.read(buffer)) != -1) {
			bos.write(buffer, 0, len);
		}
		String json = new String(bos.toByteArray(),"UTF-8");
		System.out.println(json);
		in.close();
	}

	private static void test1() {
		DemoEntry entry = new DemoEntry();
		entry.setDetail("dd");
		entry.setApk("http://sunsonfly.synology.me:8001/demoApp/main-plugin-a.apk");
		entry.setIcon("http://b.hiphotos.baidu.com/baike/c0=baike180,5,5,180,60/sign=ca5abb5b7bf0f736ccf344536b3cd87c/29381f30e924b899c83ff41c6d061d950a7bf697.jpg");
		entry.setTitle("tt");
		entry.setMinversion("2.3");
		entry.setId(2);
		
		Session session = HibernateSessionFactory.getSession();
//		SessionFactory sf = new Configuration().configure().buildSessionFactory();
//		Session session = sf.openSession();
		session.beginTransaction();
		session.save(entry);
		session.getTransaction().commit();
//		Query query = session.createSQLQuery("SELECT * from DemoEntry").addEntity(DemoEntry.class);
//		Query query = session.createQuery("SELECT * from DemoEntry");
//		Query query = session.getNamedQuery("getAllInfo");
//		((SQLQuery)query).addEntity(DemoEntry.class);
//		query.setParameter("id", 2);
//		query.setProperties(entry);
//		List<DemoEntry> list = query.list();
//		System.out.println(list);
		
		HibernateSessionFactory.closeSession();
//		session.close();
	}

}
