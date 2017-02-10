package com.lw.init;

import java.lang.Thread.UncaughtExceptionHandler;

import javax.servlet.http.HttpServlet;

import org.apache.log4j.PropertyConfigurator;

import com.lw.util.Util;

public class Log4jInit extends HttpServlet {

	public void init() {

		String prefix = getServletContext().getRealPath("/");

		String file = getInitParameter("log4j");

		System.out.println("................log4j start");

		if (file != null) {

			PropertyConfigurator.configure(prefix + file);

		}
		
		final UncaughtExceptionHandler handler = Thread.getDefaultUncaughtExceptionHandler();
		Thread.setDefaultUncaughtExceptionHandler(new UncaughtExceptionHandler() {
			
			@Override
			public void uncaughtException(Thread t, Throwable e) {
				Util.LogW(this.getClass().getName(), e.getMessage(), e);
				handler.uncaughtException(t, e);
			}
		});

	}

}