package eshop.service.user;

import com.google.protobuf.ServiceException;
import eshop.dao.DaoFactory;
import eshop.dao.DaoUser;
import eshop.entity.User;

import java.sql.SQLException;

/**
 * @author Евгений
 */
public class VerifyUser {
    public static boolean verifyUserParams( User user) {
        boolean isAnyError = true;
        if (isCredentialsWellFormed(user)){
            try {
                DaoUser daoUser = DaoFactory.getDaoUser();
                isAnyError = !daoUser.findByUsernameAndPassword(user);
            } catch (SQLException e) {

            } catch (ServiceException e) {

            }
        } else {
            isAnyError = false;
        }
        return isAnyError;
    }

    public static boolean isCredentialsWellFormed(User user) {
        return user.getUsername() != null && user.getPassword()!= null &&
                !user.getUsername().equals("") && !user.getPassword().equals("");
    }
}
