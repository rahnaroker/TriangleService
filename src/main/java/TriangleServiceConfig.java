import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class TriangleServiceConfig {

    private static final Properties PROPERTIES;

    static {
        PROPERTIES = new Properties();
        FileInputStream fio;
        try {
            fio = new FileInputStream("resources/config.properties");
            PROPERTIES.load(fio);
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getProps(String key) {
        return PROPERTIES.getProperty(key);
    }
}
