package executor.utils;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class PropertiesHelper {
    public static Properties readProperties(@Nonnull String path) {
        Properties properties = new Properties();
        try {
            properties.load(Files.newInputStream(Paths.get(path)));
            return properties;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static long getPropertyLongValue(@Nonnull Properties properties, @Nonnull String key, long defValue) {
        try {
            return Long.parseLong(properties.getProperty(key, String.valueOf(defValue)));
        } catch (NumberFormatException e) {
            return defValue;
        }
    }

    public static int getPropertyIntValue(@Nonnull Properties properties, @Nonnull String key, int defValue) {
        try {
            return Integer.parseInt(properties.getProperty(key, String.valueOf(defValue)));
        } catch (NumberFormatException e) {
            return defValue;
        }
    }
}
