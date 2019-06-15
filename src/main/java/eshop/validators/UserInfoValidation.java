package eshop.validators;

import eshop.entity.User;
import eshop.entity.UserErrors;

import javax.servlet.http.HttpServletRequest;
import java.util.ResourceBundle;

/**
 * @author Евгений
 */
public class UserInfoValidation {

    final static ResourceBundle resourceBundle = ResourceBundle.getBundle("language");

    public static boolean userInfoValid(User user, HttpServletRequest request) {
        boolean isUserValid = true;
        if (!user.getPassword().equals(user.getConfirmPassword()) || !user.getPassword().matches("^[a-zA-Z]\\w{8,45}$")) {
            isUserValid = false;
            request.getSession().setAttribute("userInfoError", resourceBundle.getString("invalid-password"));
        }
        if (!user.getUsername().matches("^[a-zA-Z]\\w{6,45}$") && user.getFirstname().length() >= 45) {
            isUserValid = false;
            request.getSession().setAttribute("userInfoError", resourceBundle.getString("invalid-username"));
        }
        if (!user.getFirstname().matches("[A-Za-zА-Яа-я]+") && user.getFirstname().length() >= 45) {
            isUserValid = false;
            request.getSession().setAttribute("userInfoError", resourceBundle.getString("invalid-firstname/surname"));
        }
        if (!user.getLastname().matches("[A-Za-zА-Яа-я]+") && user.getLastname().length() >= 45) {
            isUserValid = false;
            request.getSession().setAttribute("userInfoError", resourceBundle.getString("userExist"));
        }
        if (!user.getEmail().matches("\"^(.{0,20})@(.{0,10})\\\\.(.{2,10})$\"") && user.getEmail().length() >= 45) {
            isUserValid = false;
            request.getSession().setAttribute("userInfoError", resourceBundle.getString("invalid-email"));
        }
        return isUserValid;
    }
}
