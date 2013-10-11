package uy.com.group05.baasclient.sdk.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

public class AssetsPropertyReader {

	public static String getProperties(Context context, String key) {
		String property = null;
		try {
			Properties properties = new Properties();
			AssetManager assetManager = context.getAssets();
			InputStream inputStream = assetManager.open("baassdk.properties");
			properties.load(inputStream);
			property = properties.getProperty(key);

		} catch (IOException e) {
			Log.e("AssetsPropertyReader",e.toString());
		}

		return property;
	}

}