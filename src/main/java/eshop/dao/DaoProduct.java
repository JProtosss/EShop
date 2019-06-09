package eshop.dao;

import eshop.entity.Manufacturer;
import eshop.entity.Product;
import eshop.entity.Type;

import java.sql.*;
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
    final static String WHERE_PRODUCT_ID=resourceBundle.getString("WHERE_PRODUCT_ID");


    public Product findById(int id) throws SQLException {
        PreparedStatement preparedStatement=DaoFactory.getConnection().prepareStatement(FIND_ALL_PRODUCTS+" "+WHERE_PRODUCT_ID);
        preparedStatement.setInt(1,id);
        ResultSet resultSet=preparedStatement.executeQuery();
        if (resultSet.next()) {
            Product product = setProps(resultSet);
            return product;
        }
        return null;
    }

    public List<Product> findProducts() throws SQLException {
        List<Product> productsList = new ArrayList<>();
        Statement statement = DaoFactory.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(FIND_ALL_PRODUCTS);

        while (resultSet.next()) {
            Product product=setProps(resultSet);
            productsList.add(product);
        }
        return productsList;
    }



    private Product setProps(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        String price = resultSet.getString("price");
        int amount = resultSet.getInt("amount");
        String description = resultSet.getString("description");
        Blob image=resultSet.getBlob("image");
        int manufacturer_id=resultSet.getInt("manufacturer_id");
        String manufacturerName=resultSet.getString("manufacturerName");
        String manufacturerCountry=resultSet.getString("country");
        int type_id=resultSet.getInt("type_id");
        String type=resultSet.getString("type");
        return new Product(id,name,price,amount,description,image,new Manufacturer(manufacturer_id,manufacturerName,manufacturerCountry),new Type(type_id,type));
    }

}
