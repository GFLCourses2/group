package executor.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class PropertiesHelper {
    public static Properties readProperties(String path) {
        Properties properties = new Properties();
        try {
            properties.load(Files.newInputStream(Paths.get(path)));
            return properties;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static long getPropertyLongValue(Properties properties, String key, long defValue) {
        try {
            return Long.parseLong(properties.getProperty(key));
        } catch (NumberFormatException e) {
            return defValue;
        }
    }

    public static int getPropertyIntValue(Properties properties, String key, int defValue) {
        try {
            return Integer.parseInt(properties.getProperty(key));
        } catch (NumberFormatException e) {
            return defValue;
        }
    }
}
