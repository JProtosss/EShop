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
    final  static String REMOVE_USER=resourceBundle.getString("REMOVE_USER");


    public static void update(User user) throws SQLException {
        PreparedStatement preparedStatement = DaoFactory.getConnection().prepareStatement(UPDATE_USER);
        preparedStatement.setString(1, user.getUsername());
        preparedStatement.setString(2, user.getPassword());
        preparedStatement.setString(3, user.getEmail());
        preparedStatement.setString(4, user.getFirstname());
        preparedStatement.setString(5, user.getLastname());
        preparedStatement.setString(6, user.getAddress());
        preparedStatement.setString(7,user.getRole());
        preparedStatement.setInt(8, user.getId());
        System.out.println(preparedStatement.toString());
        preparedStatement.executeUpdate();
    }


    public static void delete(User user) throws SQLException {
        PreparedStatement preparedStatement=DaoFactory.getConnection().prepareStatement(REMOVE_USER);
        preparedStatement.setInt(1,user.getId());
        preparedStatement.executeUpdate();
    }

}
