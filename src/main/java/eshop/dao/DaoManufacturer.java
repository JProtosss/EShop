package eshop.dao;

import eshop.entity.Manufacturer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @author Евгений
 */
public class DaoManufacturer {

    final static ResourceBundle resourceBundle = ResourceBundle.getBundle("sqlstatements");

    public DaoManufacturer(){}

    final static String FIND_ALL_MANUFACTURERS=resourceBundle.getString("FIND_ALL_MANUFACTURERS");
    public List<Manufacturer> findAll() throws SQLException {
        List<Manufacturer> manufacturers = new ArrayList<>();
        Statement statement=DaoFactory.getConnection().createStatement();
        ResultSet resultSet=statement.executeQuery(FIND_ALL_MANUFACTURERS);
        while (resultSet.next())
            manufacturers.add(new Manufacturer(resultSet.getInt("manufacturer_id"),resultSet.getString("manufacturerName"),resultSet.getString("country")));
        return manufacturers;
    }

}
