package uy.com.group05.baascore.common.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyHandler {
	public String getProperty(String key) {
		try{
		     Properties p = new Properties();
		     InputStream in = getClass()
		           .getClassLoader()
		           .getResourceAsStream("uy/com/group05/baascore/common/utils/baas.properties");
		     
		     p.load(in);
		     
		     return p.getProperty(key); 
		}
		catch(IOException e){
		     e.printStackTrace(System.err);
		     return "";
		}
	}
}
