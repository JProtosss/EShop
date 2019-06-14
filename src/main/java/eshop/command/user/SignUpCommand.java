package eshop.command.user;

import com.google.protobuf.ServiceException;
import eshop.command.Command;
import eshop.dao.DaoFactory;
import eshop.dao.DaoUser;
import eshop.entity.User;
import eshop.entity.UserErrors;
import eshop.service.UserFromParameters;
import eshop.validators.UserInfoValidation;
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
        User user = UserFromParameters.getUserFromParameters(request);
        request.getSession().removeAttribute("userError");
        UserErrors userErrors = new UserErrors();
        boolean isUserValid = persistUser(user, userErrors);
        boolean flag=true;
        if (!isUserValid) {
            request.getSession().setAttribute("userError", userErrors);
            response.sendRedirect(request.getRequestURI());
            flag=false;
        }
        if (flag) {
            request.getSession().setAttribute("user", user);
            request.getSession().setAttribute("auth", true);
            request.getRequestDispatcher("/WEB-INF/views/startPage.jsp").forward(request, response);
        }
    }

    private boolean persistUser(User user, UserErrors userErrors) {
        if (UserInfoValidation.userInfoValid(user, userErrors)) {
            DaoUser daoUser = null;
            try {
                daoUser = DaoFactory.getDaoUser();
                daoUser.add(user);
                daoUser.findByEmail(user);
                return true;
            } catch (SQLException | ServiceException e) {
                e.printStackTrace();
            }

        }
        return false;
    }

}
