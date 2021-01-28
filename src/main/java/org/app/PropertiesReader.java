package org.app;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;


public final class PropertiesReader {

    private static final Properties PROPERTIES;

    private static final String PROP_FILE = "apikey.properties";

    private PropertiesReader() {
    }

    static {
        PROPERTIES = new Properties();
        final URL props = ClassLoader.getSystemResource(PROP_FILE);
        try {
            System.out.println("Reading Data");
            PROPERTIES.load(props.openStream());
            System.out.println("After Reading Data");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static String getProperty(final String name) {
        System.out.println(PROPERTIES.getProperty(name));
        return PROPERTIES.getProperty(name);
    }
}