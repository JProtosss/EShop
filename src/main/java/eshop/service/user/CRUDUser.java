package eshop.service.user;

import eshop.dao.DaoFactory;
import eshop.entity.User;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * @author Евгений
 */
public class CRUDUser {

    final static ResourceBundle resourceBundle = ResourceBundle.getBundle("sqlstatements");
    final static String UPDATE_USER = resourceBundle.getString("UPDATE_USER");


    public void update(User user) throws SQLException {
        PreparedStatement preparedStatement = DaoFactory.getConnection().prepareStatement(UPDATE_USER);
        preparedStatement.setString(1, user.getUsername());
        preparedStatement.setString(2, user.getPassword());
        preparedStatement.setString(3, user.getEmail());
        preparedStatement.setString(4, user.getFirstname());
        preparedStatement.setString(5, user.getLastname());
        preparedStatement.setString(6, user.getAddress());
        preparedStatement.executeUpdate();

    }

}
