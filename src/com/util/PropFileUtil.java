package com.util;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.StringTokenizer;
import org.apache.log4j.Logger;
import com.action.BugTrackerInitServlet;

public class PropFileUtil {
    private static Properties prop = null;
    private static Logger log = Logger.getLogger(BugTrackerInitServlet.class);

    public static void load() throws Exception {
        try {
            prop = new Properties();

            ClassLoader classLoader = PropFileUtil.class.getClassLoader();
            InputStream inFile = classLoader.getResourceAsStream("bugtracker.properties");
            prop.load(inFile);
        } catch (Exception exp) {
            throw new Exception("Error while loading Property file", exp);
        }
    }

    /**
     * Returns a property value from the property File.
     * @param propertyKey the property key.
     * @return the property value.
     */
    public static String getValue(String propertyKey) {
        String propertyValue = null;
        if ((propertyKey == null) || (propertyKey.trim().length() <= 0)) {
            throw new IllegalArgumentException("Property Key is empty or null");
        }
        propertyValue = prop.getProperty(propertyKey);
        return propertyValue.trim();
    }
    
    /**
     * Returns a property value list from the property File based on the separator used.
     * (eg. contactField=contactId, contactName)
     * 
     * @param propertyKey the property key.
     * @param separator the property separator.
     * @return the property value.
     */
    public static List<String> getValueList(String propertyKey, String separator) {
		String listOfProperties = PropFileUtil.getValue(propertyKey);
		List<String> valueList = new ArrayList<String>();
		StringTokenizer parser = new StringTokenizer(listOfProperties, separator, false);

		while (parser.hasMoreTokens()) {
			valueList.add(parser.nextToken());
		}
		return valueList;
    }
}