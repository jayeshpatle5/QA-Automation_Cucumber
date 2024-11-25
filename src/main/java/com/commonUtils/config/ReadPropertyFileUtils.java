package com.commonUtils.config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;

import com.commonUtils.FrameworkConstants;
import com.commonUtils.enums.ConfigProperties;
import com.exceptions.PropertyFileUsageException;


public class ReadPropertyFileUtils {

	private ReadPropertyFileUtils() {

	}
	private static final Properties property = new Properties();
	private static final Map<String, String> CONFIGMAP = new HashMap<>();
	static {
		try (FileInputStream fil = new FileInputStream(FrameworkConstants.getCONFIGFILEPATH())) {
			property.load(fil);
			/*
			 * for(Object key :property.entrySet()) { CONFIGMAP.put(String.valueOf(key),
			 * String.valueOf(property.get(key))); }//one way assign values to Hash map
			 *
			 */

			for (Map.Entry<Object, Object> entry : property.entrySet()) {
				CONFIGMAP.put(String.valueOf(entry.getKey()), String.valueOf(entry.getValue()).trim());
			} // one way assign values to Hash map code review point of view

		} catch (FileNotFoundException e) {
			// throw new InvalidPathPropertyFileException("Please check the path of config file"); 
			e.printStackTrace();
			System.exit(0);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	// Hash table -little slow but thread safe
	public static String get(ConfigProperties key) {
		if (Objects.isNull(key) || Objects.isNull(CONFIGMAP.get(key.name().toLowerCase()))) {

			throw new PropertyFileUsageException("property key :" + key + " value is null");
			// PropertyFileUsageException is custon exception created in exception package

		}
		return CONFIGMAP.get(key.toString().toLowerCase());
	}


	public static String getValue(String key) {
		try {
			if (Objects.isNull(property.getProperty(key)) || Objects.isNull(key)) {
				throw new Exception("property key :" + key + " value is null");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return property.getProperty(key);

	}
}
