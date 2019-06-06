package eshop.dao;

import eshop.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ResourceBundle;

/**
 * @author Евгений
 */
public class DaoUser {
    private static Connection connection;
    private static final Logger logger = LogManager.getLogger();
    final static ResourceBundle resourceBundle = ResourceBundle.getBundle("sqlstatements");

    public DaoUser() {
    }

    final static String INSERT_USER = resourceBundle.getString("INSERT_USER");
    final static String FIND_USER_BY_USERNAME = resourceBundle.getString("FIND_USER_BY_USERNAME");
    final static String FIND_USER_BY_ID = resourceBundle.getString("FIND_USER_BY_ID");
    final static String FIND_USER_WHERE_USERNAME_AND_PASSWORD = resourceBundle.getString("FIND_USER_WHERE_USERNAME_AND_PASSWORD");

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public void add(User user) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER);
        preparedStatement.setString(1, user.getUsername());
        preparedStatement.setString(2, user.getPassword());
        preparedStatement.setString(3, user.getEmail());
        preparedStatement.setString(4, user.getFirstname());
        preparedStatement.setString(5, user.getLastname());
        preparedStatement.setString(6, user.getAddress());
        preparedStatement.executeUpdate();
    }


    public boolean findByUsername(User user) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(FIND_USER_BY_USERNAME);
        preparedStatement.setString(1, user.getEmail());

        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            setProps(user, resultSet);
            return true;
        }

        return false;
    }

    public User findById(int userId) throws SQLException {
        User user = new User();
        PreparedStatement preparedStatement = connection.prepareStatement(FIND_USER_BY_ID);
        preparedStatement.setInt(1, userId);

        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            setProps(user, resultSet);
        }

        return user;
    }

    public boolean findByUsernameAndPassword(User user) throws SQLException {

        String query = "SELECT * FROM user WHERE username = ? AND password = ?;";

        PreparedStatement statement = DaoFactory.getConnection().prepareStatement(query);
        statement.setString(1, user.getUsername());
        statement.setString(2, user.getPassword());
        ResultSet resultSet = statement.executeQuery();

        while(resultSet.next())
        {
            setProps(user,resultSet);
            return true;
        }
        return false;
    }

    private void setProps(User user, ResultSet resultSet) throws SQLException {
        user.setFirstname(resultSet.getString("firstname"));
        user.setLastname(resultSet.getString("lastname"));
        user.setAddress(resultSet.getString("address"));
        user.setPassword(resultSet.getString("password"));
        user.setEmail(resultSet.getString("email"));
        user.setEmail(resultSet.getString("username"));
        user.setId(resultSet.getInt("id"));
        user.setId(resultSet.getInt("chosenProducts"));
    }

}
