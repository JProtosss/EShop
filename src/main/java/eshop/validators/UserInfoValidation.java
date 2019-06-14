package eshop.validators;

import eshop.entity.User;
import eshop.entity.UserErrors;

/**
 * @author Евгений
 */
public class UserInfoValidation {

    public static boolean userInfoValid(User user, UserErrors userErrors) {
        boolean isUserValid = true;
        if (!user.getPassword().equals(user.getConfirmPassword()) || user.getPassword().equals("")) {
            isUserValid = false;
            userErrors.setPassword("INCORRECT_PASSWORD");
        } else {
            if (!user.getFirstname().matches("[A-Za-zА-Яа-я]+")) {
                isUserValid = false;
                userErrors.setFirstname("BLANK_WRONG_SYMBOLS");
            }
            if (user.getFirstname().length() >= 45) {
                isUserValid = false;
                userErrors.setFirstname("BAD_LENGTH");
            }
            if (!user.getLastname().matches("[A-Za-zА-Яа-я]+")) {
                isUserValid = false;
                userErrors.setLastname("BLANK_WRONG_SYMBOLS");
            }
            if (user.getLastname().length() >= 45) {
                isUserValid = false;
                userErrors.setLastname("BAD_LENGTH");
            }
            if (!user.getEmail().matches("[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@" +
                    "(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?")) {
                isUserValid = false;
                userErrors.setEmail("WRONG_EMAIL");
            }
            if (user.getEmail().length() >= 45) {
                isUserValid = false;
                userErrors.setEmail("BAD_LENGTH");
            }
        }
        return isUserValid;
    }
}
