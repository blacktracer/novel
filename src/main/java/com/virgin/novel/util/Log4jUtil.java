package com.virgin.novel.util;

import org.apache.log4j.PropertyConfigurator;

public class Log4jUtil {
	public static void configure() {
		PropertyConfigurator.configure("D:\\workspace\\git\\novel\\src\\main\\resources\\log4j.properties");
	}
}
