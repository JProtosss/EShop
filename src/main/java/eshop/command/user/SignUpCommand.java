package eshop.command.user;

import com.google.protobuf.ServiceException;
import eshop.command.Command;
import eshop.dao.DaoFactory;
import eshop.dao.DaoUser;
import eshop.entity.User;
import eshop.entity.UserErrors;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * @author Евгений
 */
public class SignUpCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        User user = getUserFromParameters(request);
        request.getSession().removeAttribute("userError");
        UserErrors userErrors = new UserErrors();
        boolean isUserValid = persistUser(user, userErrors);

        if (!isUserValid) {
            request.getSession().setAttribute("userError", userErrors);
            response.sendRedirect(request.getRequestURI());
        }

        request.getSession().setAttribute("user", user);
        request.getSession().setAttribute("auth", true);
        request.getRequestDispatcher("/WEB-INF/views/startPage.jsp").forward(request,response);

    }

    private boolean persistUser(User user, UserErrors userErrors) {
        if (isUserValid(user, userErrors)) {
            try {
                DaoUser daoUser = DaoFactory.getDaoUser();
                daoUser.add(user);
                daoUser.findByEmail(user);
                return true;
            } catch (SQLException e) {
                userErrors.setEmail("DOUBLE_EMAIL");
            } catch (ServiceException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    private boolean isUserValid(User user, UserErrors userErrors) {
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

    public User getUserFromParameters(HttpServletRequest request) {
        User user = new User();
        try {
            BeanUtils.populate(user, request.getParameterMap());
        } catch (ReflectiveOperationException e) {
        }
        return user;
    }
}
