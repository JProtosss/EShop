package eshop.dao;

import eshop.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * @author Евгений
 */
public class DaoUser {
    Connection connection;
    final static ResourceBundle resourceBundle = ResourceBundle.getBundle("sqlstatements");

    public DaoUser() {
    }

    final static String INSERT_USER = resourceBundle.getString("INSERT_USER");
    final static String FIND_USER_BY_USERNAME = resourceBundle.getString("FIND_USER_BY_USERNAME");
    final static String FIND_USER_BY_ID = resourceBundle.getString("FIND_USER_BY_ID");
    final static  String FIND_USER_WHERE_USERNAME_AND_PASSWORD = resourceBundle.getString("FIND_USER_WHERE_USERNAME_AND_PASSWORD");

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
        if (resultSet.next() ){
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
        PreparedStatement preparedStatement = connection.prepareStatement(FIND_USER_WHERE_USERNAME_AND_PASSWORD);
        preparedStatement.setString(1, user.getUsername());
        preparedStatement.setString(2, user.getPassword());

        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next() ){
            setProps(user, resultSet);
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
        user.setId(resultSet.getInt("user_id"));
    }

}
