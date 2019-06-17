package eshop.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.TimeZone;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Евгений
 */
public class DaoFactory {

    public static Connection connection;
    private static final Logger logger = LogManager.getLogger();
    private static Lock lockForSingleTone = new ReentrantLock();

    private DaoFactory() {
    }


    public static Connection getConnection() {
        lockForSingleTone.lock();
        if (connection == null) {
            try {
                InputStream inputStream = DaoFactory.class.getResourceAsStream("/db.properties");
                Properties properties = new Properties();
                properties.load(inputStream);
                String url = properties.getProperty("url");
                String driver = properties.getProperty("driver");
                String user = properties.getProperty("user");
                String password = properties.getProperty("password");
                Class.forName(driver);
                connection = DriverManager.getConnection(url + "?serverTimezone=" + TimeZone.getDefault().getID() + "&autoReconnect=true&useSSL=false", user, password);
                logger.info("Connection to db successfull");
            } catch (SQLException e) {
                logger.info("Cannot connect to db");
            } catch (ClassNotFoundException e) {
               logger.error("Error with db driver");
            } catch (IOException e) {
                logger.error("File with db properties not found");
            }
        }
        lockForSingleTone.unlock();
        return connection;
    }
}
