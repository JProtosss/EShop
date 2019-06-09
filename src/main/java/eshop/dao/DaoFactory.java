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

    private DaoFactory()
    {}

    public static void setConnection() throws ClassNotFoundException, IOException {
       lockForSingleTone.lock();
        if (connection==null)
        {
            InputStream inputStream = DaoFactory.class.getResourceAsStream("/db.properties");
            Properties properties = new Properties();
            properties.load(inputStream);
            String url = properties.getProperty("url");
            String driver = properties.getProperty("driver");
            String user = properties.getProperty("user");
            String password = properties.getProperty("password");
            Class.forName(driver);
            try {
                connection=DriverManager.getConnection(url+"?serverTimezone=" + TimeZone.getDefault().getID()+"&autoReconnect=true&useSSL=false", user, password);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        lockForSingleTone.unlock();
        logger.info("connected to db");
    }

public static Connection getConnection()
{
    return connection;
}

    public static DaoUser getDaoUser() throws SQLException {
        DaoUser daoUser = new DaoUser();
        return daoUser;
    }
}
