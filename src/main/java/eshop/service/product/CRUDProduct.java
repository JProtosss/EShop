package eshop.service.product;

import eshop.dao.DaoFactory;
import eshop.entity.Product;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * @author Евгений
 */
public class CRUDProduct {

    final static ResourceBundle resourceBundle = ResourceBundle.getBundle("sqlstatements");
    final static String UPDATE_PRODUCT=resourceBundle.getString("UPDATE_PRODUCT");
    final static String ADD_PRODUCT=resourceBundle.getString("ADD_PRODUCT");
    final static String REMOVE_PRODUCT=resourceBundle.getString("REMOVE_PRODUCT");

    public static void update(Product product) throws SQLException {
        PreparedStatement preparedStatement= DaoFactory.getConnection().prepareStatement(UPDATE_PRODUCT);
        preparedStatement.setString(1,product.getName());
        preparedStatement.setString(2,product.getPrice());
        preparedStatement.setInt(3,product.getAmount());
        preparedStatement.setString(4,product.getDescription());
        preparedStatement.setString(5,product.getImage());
        preparedStatement.setInt(6,product.getManufacturer().getId());
        preparedStatement.setInt(7,product.getType().getId());
        preparedStatement.setInt(8,product.getId());
        preparedStatement.executeUpdate();
    }


    public static void add(Product product) throws SQLException {
        PreparedStatement preparedStatement=DaoFactory.getConnection().prepareStatement(ADD_PRODUCT);
        preparedStatement.setString(1,product.getName());
        preparedStatement.setString(2,product.getPrice());
        preparedStatement.setInt(3,product.getAmount());
        preparedStatement.setString(4,product.getDescription());
        preparedStatement.setString(5,product.getImage());
        preparedStatement.setInt(6,product.getManufacturer().getId());
        preparedStatement.setInt(7,product.getType().getId());
        preparedStatement.executeUpdate();
    }

    public static void delete(Product product) throws SQLException {
        PreparedStatement preparedStatement=DaoFactory.getConnection().prepareStatement(REMOVE_PRODUCT);
        preparedStatement.setInt(1,product.getId());
        preparedStatement.executeUpdate();
    }
}
