package utils;

import java.io.FileInputStream;
import java.util.Properties;

public class EnvProperties {

	private static final String PATH = System.getProperty("user.dir");
	String propPath = PATH + "/src/test/resources/config.properties";

	public String readProperty(String PropKey) {
		String PropValue = null;
		try {
			FileInputStream fi = new FileInputStream(propPath);
			Properties prop = new Properties();
			prop.load(fi);
			PropValue = prop.getProperty(PropKey);

		} catch (Exception e) {
		}
		return PropValue;
	}
}
