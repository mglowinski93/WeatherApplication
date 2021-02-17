package org.app;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public final class PropertiesReader {

    public static final String PROP_FILE = "apikey.properties";
    private static final Properties PROPERTIES;

    static {
        PROPERTIES = new Properties();

        InputStream input = PropertiesReader.class.getClassLoader().getResourceAsStream(PROP_FILE);

        try {
            PROPERTIES.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private PropertiesReader() {
    }

    public static String getProperty(final String name) {
        return PROPERTIES.getProperty(name);
    }
}