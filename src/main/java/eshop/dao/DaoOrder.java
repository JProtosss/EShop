package eshop.dao;

import eshop.entity.Order;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @author Евгений
 */
public class DaoOrder {

    final static ResourceBundle resourceBundle = ResourceBundle.getBundle("sqlstatements");
    final static String FIND_ORDERS_BY_USER_ID = resourceBundle.getString("FIND_ORDERS_BY_USER_ID");
    final static String COUNT_ORDERS_BY_USER_ID = resourceBundle.getString("COUNT_ORDERS_BY_USER_ID");

    public List<Order> finByUserId(int user_id) throws SQLException {
        PreparedStatement preparedStatement = DaoFactory.getConnection().prepareStatement(FIND_ORDERS_BY_USER_ID);
        preparedStatement.setInt(1, user_id);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Order> ordersList = new ArrayList<>();
        while (resultSet.next()) {
            Order order = setProps(resultSet);
            ordersList.add(order);
        }
        return ordersList;
    }

    public int countOrdersByUserId(int user_id) throws SQLException {
        PreparedStatement preparedStatement = DaoFactory.getConnection().prepareStatement(COUNT_ORDERS_BY_USER_ID);
        preparedStatement.setInt(1, user_id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next())
            return resultSet.getInt("total");
        return 0;
    }

    private Order setProps(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        int product_id = resultSet.getInt("product_id");
        int user_id = resultSet.getInt("user_id");
        return new Order(id, product_id, user_id);
    }

}
