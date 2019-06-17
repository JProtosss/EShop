package eshop.service.user;

import com.google.protobuf.ServiceException;
import eshop.dao.DaoFactory;
import eshop.entity.User;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;

/**
 * @author Евгений
 */
public class CRUDUserTest {

    static Connection connection;
    static Statement statement;

    @BeforeClass
    public void setUp() throws SQLException {
        connection = DaoFactory.getConnection();
        statement = connection.createStatement();
    }

    @Test
    public void testAdd() throws SQLException, ServiceException {
        User user = new User(1, "bot123bot","ThisIsPassword", "j23@y.com", "firstname", "lastname", "address");
        CRUDUser crudUser = new CRUDUser();
        crudUser.add(user);
        ResultSet resultSet = statement.executeQuery("select * from user where username='bot123bot'");
        String username="";
        if (resultSet.next()) username = resultSet.getString("username");
        assertEquals(username, user.getUsername());
    }

    @Test
    public void testDelete() throws SQLException {
        statement.executeUpdate("delete from user where username='bot123bot'");
        ResultSet resultSet=statement.executeQuery("select * from user where username='bot123bot'");
        assertFalse(resultSet.next());
    }

    @AfterClass
    public static void closeConnection() throws SQLException {
        connection.close();
    }
}
