package eshop.service.user;

import com.google.protobuf.ServiceException;
import eshop.dao.DaoUser;
import eshop.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;

/**
 * @author Евгений
 */
public class VerifyUser {
    private static final Logger logger = LogManager.getLogger();
    public static boolean verifyUserParams(User user) {
        boolean isAnyError = true;
        if (isCredentialsWellFormed(user)){
            try {
                DaoUser daoUser = new DaoUser();
                isAnyError = !daoUser.findByUsernameAndPassword(user);
            } catch (SQLException | ServiceException e) {
                logger.error("Was attempt to find by username and password but user was not found. user("+user.getUsername()+")");
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
