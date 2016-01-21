package com.onlinemarketing.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;

import com.lib.Debug;

public class Constan {
	
	 public static String getProperty(String key,Context context) throws IOException {
	        Properties properties = new Properties();;
	        AssetManager assetManager = context.getAssets();
	        InputStream inputStream = assetManager.open("constan.properties");
	        InputStreamReader inputread = new InputStreamReader(inputStream, "UTF-8");
	        properties.load(inputread);
	        return properties.getProperty(key);
	    }
	
}
