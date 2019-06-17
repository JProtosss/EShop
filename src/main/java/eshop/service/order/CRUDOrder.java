package eshop.service.order;

import eshop.dao.DaoFactory;
import eshop.entity.Order;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * @author Евгений
 */
public class CRUDOrder {
    final static ResourceBundle resourceBundle = ResourceBundle.getBundle("sqlstatements");
    final static String ADD_ORDER=resourceBundle.getString("ADD_ORDER");

    public static void add(Order order) throws SQLException {
        PreparedStatement preparedStatement= DaoFactory.getConnection().prepareStatement(ADD_ORDER);
        preparedStatement.setInt(1,order.getProduct_id());
        preparedStatement.setInt(2,order.getUser_id());
        try {
           preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
