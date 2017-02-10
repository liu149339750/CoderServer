package com.lw.util;

import org.apache.log4j.Logger;

public class Util {

	
	public static void LogI(String tag,String message) {
		Logger.getLogger(tag).info(message);
	}
	
	public static void LogW(String tag,String message,Throwable t) {
		Logger.getLogger(tag).warn(message, t);
	}
}
