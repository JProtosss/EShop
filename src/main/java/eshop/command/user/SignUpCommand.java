package eshop.command.user;

import com.google.protobuf.ServiceException;
import eshop.command.Command;
import eshop.dao.DaoFactory;
import eshop.dao.DaoUser;
import eshop.entity.User;
import eshop.entity.UserErrors;
import eshop.service.UserFromParameters;
import eshop.validators.UserInfoValidation;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * @author Евгений
 */
public class SignUpCommand implements Command {

    final static ResourceBundle resourceBundle = ResourceBundle.getBundle("language");

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, MessagingException, ServiceException, SQLException {
        User user = UserFromParameters.getUserFromParameters(request);
        request.getSession().removeAttribute("userError");
        UserErrors userErrors = new UserErrors();
        boolean isUserValid = persistUser(user,request);
        boolean flag=true;
        if (!isUserValid) {
            response.sendRedirect(request.getRequestURI());
            flag=false;
        }
        if (flag) {
           Command command=new AuthCommand();
           command.execute(request,response);
        }
    }

    private boolean persistUser(User user,HttpServletRequest request) {
        if (UserInfoValidation.userInfoValid(user,request)) {
            DaoUser daoUser = null;
            try {
                daoUser = DaoFactory.getDaoUser();
                daoUser.findByEmail(user);
                daoUser.add(user);
                return true;
            } catch (SQLException | ServiceException e) {
                request.getSession().setAttribute("userInfoError",resourceBundle.getString("userExist"));
            }
        }
        return false;
    }

}
