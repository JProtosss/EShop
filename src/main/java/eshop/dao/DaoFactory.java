package eshop.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.TimeZone;

/**
 * @author Евгений
 */
public class DaoFactory {

    public static Connection connection;
    private static final Logger logger = LogManager.getLogger();

    private DaoFactory()
    {}

    public static void setConnection() throws ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        if (connection==null)
        {
            String url="jdbc:mysql://localhost:3306/carrental";
            String name="root";
            String pass="root";
            try {
                connection=DriverManager.getConnection(url+"?serverTimezone=" + TimeZone.getDefault().getID(), name, pass);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        logger.info("connected to db");
    }


    public static DaoUser getDaoUser() throws SQLException {
        DaoUser daoUser = new DaoUser();
        daoUser.setConnection(connection);
        return daoUser;
    }
}
