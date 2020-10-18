package Util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class TriangleServiceConfig {

    private static final Logger logger = LogManager.getLogger(TriangleServiceConfig.class);
    private final Properties PROPERTIES = new Properties();

    public String readProperties(String key) {
        FileInputStream fio;
        try {
            fio = new FileInputStream("src/main/resources/config.properties");
            PROPERTIES.load(fio);
        } catch (FileNotFoundException e1) {
            logger.error(e1);
        } catch (IOException e) {
            logger.error(e);
        }
        return PROPERTIES.getProperty(key);
    }

    public static String getProps(String key) {
        return new TriangleServiceConfig().readProperties(key);
    }
}
