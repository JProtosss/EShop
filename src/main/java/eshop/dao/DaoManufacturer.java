package eshop.dao;

import eshop.entity.Manufacturer;

import java.sql.PreparedStatement;
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
    final static String FIND_MANUFACTURER_BY_NAME=resourceBundle.getString("FIND_MANUFACTURER_BY_NAME");

    public List<Manufacturer> findAll() throws SQLException {
        List<Manufacturer> manufacturers = new ArrayList<>();
        Statement statement=DaoFactory.getConnection().createStatement();
        ResultSet resultSet=statement.executeQuery(FIND_ALL_MANUFACTURERS);
        while (resultSet.next())
            manufacturers.add(new Manufacturer(resultSet.getInt("manufacturer_id"),resultSet.getString("manufacturerName"),resultSet.getString("country")));
        return manufacturers;
    }

    public Manufacturer findByName(String name) throws SQLException {
        Manufacturer manufacturer;
        PreparedStatement preparedStatement=DaoFactory.getConnection().prepareStatement(FIND_MANUFACTURER_BY_NAME);
        preparedStatement.setString(1,name);
        ResultSet resultSet=preparedStatement.executeQuery();

        if (resultSet.next())
        {
            return setProps(resultSet);
        }
        return null;
    }

    private Manufacturer setProps(ResultSet resultSet) throws SQLException {
        int manufacturer_id=resultSet.getInt("manufacturer_Id");
        String manufacturerName=resultSet.getString("manufacturerName");
        String country = resultSet.getString("country");
        return new Manufacturer(manufacturer_id,manufacturerName,country);
    }

}
