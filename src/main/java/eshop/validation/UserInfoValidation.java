package eshop.validation;

import eshop.entity.User;

import javax.servlet.http.HttpServletRequest;
import java.util.ResourceBundle;

/**
 * @author Евгений
 */
public class UserInfoValidation {

    final static ResourceBundle resourceBundle = ResourceBundle.getBundle("language");

    public static boolean userInfoValid(User user, HttpServletRequest request) {
        if (!user.getPassword().equals(user.getConfirmPassword()) || !user.getPassword().matches("^[a-zA-Z]\\w{7,45}$")) {
            request.getSession().setAttribute("userInfoError", resourceBundle.getString("invalid-password"));
            return false;
        }
        if (!user.getUsername().matches("^[a-zA-Z]\\w{6,45}$") || user.getUsername().length() >= 45) {
            request.getSession().setAttribute("userInfoError", resourceBundle.getString("invalid-username"));
            return false;
        }
        if (!user.getFirstname().matches("[A-Za-zА-Яа-я]+") || user.getFirstname().length() >= 45) {
            request.getSession().setAttribute("userInfoError", resourceBundle.getString("invalid-firstname/surname"));
            return false;
        }
        if (!user.getLastname().matches("[A-Za-zА-Яа-я]+") || user.getLastname().length() >= 45) {
            request.getSession().setAttribute("userInfoError", resourceBundle.getString("userExist"));
            return false;
        }
        if (!user.getEmail().matches(".+@.+\\..+") || user.getEmail().length() >= 45) {
            request.getSession().setAttribute("userInfoError", resourceBundle.getString("invalid-email"));
            return false;
        }
        return true;
    }
}
