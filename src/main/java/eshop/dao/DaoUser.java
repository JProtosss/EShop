package eshop.dao;

import com.google.protobuf.ServiceException;
import eshop.entity.User;
import eshop.service.PasswordService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ResourceBundle;

/**
 * @author Евгений
 */
public class DaoUser {

    private static final Logger logger = LogManager.getLogger();
    final static ResourceBundle resourceBundle = ResourceBundle.getBundle("sqlstatements");

    public DaoUser() {
    }

    final static String INSERT_USER = resourceBundle.getString("INSERT_USER");
    final static String FIND_USER_BY_USERNAME = resourceBundle.getString("FIND_USER_BY_USERNAME");
    final static String FIND_USER_BY_ID = resourceBundle.getString("FIND_USER_BY_ID");
    final static String FIND_USER_BY_EMAIL = resourceBundle.getString("FIND_USER_BY_EMAIL");
    final static String FIND_USER_WHERE_USERNAME_AND_PASSWORD = resourceBundle.getString("FIND_USER_WHERE_USERNAME_AND_PASSWORD");
    final static String FIND_USER_WHERE_USERNAME_AND_EMAIL = resourceBundle.getString("FIND_USER_WHERE_USERNAME_AND_EMAIL");

    public void add(User user) throws SQLException, ServiceException {
        PreparedStatement preparedStatement = DaoFactory.getConnection().prepareStatement(INSERT_USER);
        preparedStatement.setString(1, user.getUsername());
        preparedStatement.setString(2, PasswordService.passwordHash(user.getPassword()));
        preparedStatement.setString(3, user.getEmail());
        preparedStatement.setString(4, user.getFirstname());
        preparedStatement.setString(5, user.getLastname());
        preparedStatement.setString(6, user.getAddress());
        preparedStatement.executeUpdate();
    }


    public boolean findByUsername(User user) throws SQLException, ServiceException {
        PreparedStatement preparedStatement = DaoFactory.getConnection().prepareStatement(FIND_USER_BY_USERNAME);
        preparedStatement.setString(1, user.getUsername());

        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            setProps(user, resultSet);
            return true;
        }
        return false;
    }


    public boolean findByEmail(User user) throws SQLException, ServiceException {
        PreparedStatement preparedStatement = DaoFactory.getConnection().prepareStatement(FIND_USER_BY_EMAIL);
        preparedStatement.setString(1, user.getEmail());

        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            setProps(user, resultSet);
            return true;
        }

        return false;
    }

    public boolean findByUsernameAndEmail(User user) throws SQLException, ServiceException {
        PreparedStatement statement = DaoFactory.getConnection().prepareStatement(FIND_USER_WHERE_USERNAME_AND_EMAIL);
        statement.setString(1, user.getUsername());
        statement.setString(2, user.getEmail());
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next())
        {
            return true;
        }
        return false;
    }

    public User findById(int userId) throws SQLException, ServiceException {
        User user = new User();
        PreparedStatement preparedStatement = DaoFactory.getConnection().prepareStatement(FIND_USER_BY_ID);
        preparedStatement.setInt(1, user.getId());

        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            setProps(user, resultSet);
        }

        return user;
    }

    public boolean findByUsernameAndPassword(User user) throws SQLException, ServiceException {
        PreparedStatement statement = DaoFactory.getConnection().prepareStatement(FIND_USER_WHERE_USERNAME_AND_PASSWORD);
        statement.setString(1, user.getUsername());
        statement.setString(2, PasswordService.passwordHash(user.getPassword()));
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            setProps(user, resultSet);
            return true;
        }
        return false;
    }

    private void setProps(User user, ResultSet resultSet) throws SQLException, ServiceException {
        user.setFirstname(resultSet.getString("firstname"));
        user.setLastname(resultSet.getString("lastname"));
        user.setAddress(resultSet.getString("address"));
        user.setEmail(resultSet.getString("email"));
        user.setEmail(resultSet.getString("username"));
        user.setId(resultSet.getInt("id"));
        user.setId(resultSet.getInt("chosenProducts"));
    }


}
