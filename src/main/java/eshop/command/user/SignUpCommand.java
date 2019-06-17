package eshop.command.user;

import com.google.protobuf.ServiceException;
import eshop.command.Command;
import eshop.dao.DaoUser;
import eshop.entity.User;
import eshop.service.UserFromParameters;
import eshop.service.user.CRUDUser;
import eshop.validation.UserInfoValidation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * @author Евгений
 * User registration form. Checking valid and then @AuthCommand
 */
public class SignUpCommand implements Command {

    final static ResourceBundle resourceBundle = ResourceBundle.getBundle("language");
    private static final Logger logger = LogManager.getLogger();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, MessagingException, ServiceException, SQLException {
        User user = UserFromParameters.getUserFromParameters(request);
        request.getSession().removeAttribute("userError");
        boolean isUserValid = persistUser(user, request);
        boolean flag = true;
        if (!isUserValid) {
            response.sendRedirect(request.getRequestURI());
            flag = false;
        }
        if (flag) {
            Command command = new AuthCommand();
            command.execute(request, response);
        }
    }

    private boolean persistUser(User user, HttpServletRequest request) {
        if (UserInfoValidation.userInfoValid(user, request)) {
            try {
                DaoUser daoUser = new DaoUser();
                CRUDUser crudUser=new CRUDUser();
                daoUser.findByEmail(user);
                crudUser.add(user);
                logger.info("User " + user.getUsername() + " registered");
                return true;
            } catch (SQLException | ServiceException e) {
                request.getSession().setAttribute("userInfoError", resourceBundle.getString("userExist"));
            }
        }
        return false;
    }

}
