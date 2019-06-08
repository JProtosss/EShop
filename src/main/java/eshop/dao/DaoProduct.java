package eshop.dao;

import eshop.entity.Product;

import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @author Евгений
 */
public class DaoProduct {

    final static ResourceBundle resourceBundle = ResourceBundle.getBundle("sqlstatements");

    public DaoProduct() {
    }

    final static String FIND_ALL_PRODUCTS = resourceBundle.getString("FIND_ALL_PRODUCTS");

    public List<Product> findProducts() throws SQLException {
        List<Product> productsList = new ArrayList<>();
        Statement statement = DaoFactory.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(FIND_ALL_PRODUCTS);

        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String price = resultSet.getString("price");
            int amount = resultSet.getInt("amount");
            String description = resultSet.getString("description");
            Blob image=resultSet.getBlob("image");
            String manufacturerName=resultSet.getString("manufacturerName");
            String manufacturerCountry=resultSet.getString("country");
            String type=resultSet.getString("type");
            productsList.add(new Product(id,name,price,amount,description,image,manufacturerName,manufacturerCountry,type));
        }
        return productsList;
    }

}
