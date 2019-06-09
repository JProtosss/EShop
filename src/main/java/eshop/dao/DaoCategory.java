package eshop.dao;

import eshop.entity.Type;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @author Евгений
 */
public class DaoCategory {

    final static ResourceBundle resourceBundle = ResourceBundle.getBundle("sqlstatements");

    public DaoCategory(){}

    final static String FIND_ALL_TYPES=resourceBundle.getString("FIND_ALL_TYPES");

    public List<Type> findAll() throws SQLException {

        List<Type> types =new ArrayList<>();
        Statement statement=DaoFactory.getConnection().createStatement();
        ResultSet resultSet=statement.executeQuery(FIND_ALL_TYPES);

        while (resultSet.next())
        {
            types.add(new Type(resultSet.getInt("type_id"),resultSet.getString("type")));
        }

        return types;
    }
}
