package eshop.util;

import java.util.ResourceBundle;

/**
 * @author Евгений
 */
public class PathManager {
    private final static ResourceBundle resourceBundle = ResourceBundle.getBundle("routes");

    private PathManager() { }
    public static String getProperty(String key) {
        return resourceBundle.getString(key);
    }
}
