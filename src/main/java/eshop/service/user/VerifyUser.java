package eshop.service.user;

import com.google.protobuf.ServiceException;
import eshop.dao.DaoFactory;
import eshop.dao.DaoUser;
import eshop.entity.User;
import eshop.entity.UserErrors;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

/**
 * @author Евгений
 */
public class VerifyUser {
    public static boolean verifyUserParams(HttpServletRequest request, User user, UserErrors userErrors) {
        boolean isAnyError = true;
        if (isCredentialsWellFormed(user)){
            try {
                DaoUser daoUser = DaoFactory.getDaoUser();
                isAnyError = !daoUser.findByUsernameAndPassword(user);
            } catch (SQLException e) {
                userErrors.setUsername("BAD_DB_CONN");
            } catch (ServiceException e) {
                e.printStackTrace();
            }
        } else {
            isAnyError = false;
            userErrors.setLoginForm("BLANK_FIELDS");
        }
        return isAnyError;
    }

    public static boolean isCredentialsWellFormed(User user) {
        return user.getUsername() != null && user.getPassword()!= null &&
                !user.getUsername().equals("") && !user.getPassword().equals("");
    }
}
