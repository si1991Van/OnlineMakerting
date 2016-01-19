package com.onlinemarketing.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Constan {
	public static String Error01 = "";
	public static String Error02 = "";
	public static String Error03 = "";
	public static String ErrorConnectInterNet = "";
	public static String ErrorConnectInterNetMessage = "";
	public static String Ok = "";
	public static String Cancel = "";
//	thời gian chờ chạy khi vào app.
	public static int sleep = 5000;
	
	// status login success	
	public static int loginSuccess = 0;
	//login failse
	public static int loginerror = 1;
	
	public static void loadProperties() throws IOException {
		PathConfig root = new PathConfig();
		String root_path = root.getRoot(); 
		String fileName = root_path+"/assets/constan.cfg";
		try
		{
			FileInputStream propsFile = new FileInputStream(fileName);
			properties.load(propsFile);
			propsFile.close();

			Error01 = properties.getProperty("Error01", Error01);
			Error02 = properties.getProperty("Error02", Error02);
			Error03 = properties.getProperty("Error03", Error03);
			ErrorConnectInterNet = properties.getProperty("ErrorConnectInterNet", ErrorConnectInterNet);
			ErrorConnectInterNetMessage = properties.getProperty("ErrorConnectInterNetMessage", ErrorConnectInterNetMessage);
			Ok = properties.getProperty("Ok", Ok);
			Cancel = properties.getProperty("Cancel", Cancel);
			
			sleep = getIntProperty("sleep",sleep);
			loginSuccess = getIntProperty("loginSuccess", loginSuccess);
			loginerror = getIntProperty("loginerror", loginerror);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	private static Properties properties = new Properties();
	static int getIntProperty(String propName, int defaultValue) {
		return Integer.parseInt(properties.getProperty(propName, Integer
				.toString(defaultValue)));
	}
	

	static boolean getBoolProperty(String propName, boolean defaultValue) {
		if (properties.getProperty(propName).equalsIgnoreCase("true")) return true;
		else if (properties.getProperty(propName).equalsIgnoreCase("false")) return false;
		else return defaultValue;
	}

	static {
		try {
			loadProperties();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
 