package eshop.command.user;

import com.google.protobuf.ServiceException;
import eshop.command.Command;
import eshop.command.page.ToAccount;
import eshop.entity.User;
import eshop.entity.UserErrors;
import eshop.service.UserFromParameters;
import eshop.service.user.CRUDUser;
import eshop.validators.UserInfoValidation;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * @author Евгений
 */
public class UpdateUser implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, MessagingException, SQLException, ServiceException, ServletException {

        User user = userFromParams(request);
        UserErrors userErrors = new UserErrors();
        boolean userValid = UserInfoValidation.userInfoValid(user, userErrors);
        if (userValid) {
            CRUDUser.update(user);
            request.getSession().setAttribute("user",user);
            ToAccount toAccount=new ToAccount();
            toAccount.execute(request,response);
        }else
        response.sendRedirect(request.getRequestURI());
    }

    private User userFromParams(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        String email = request.getParameter("email");
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String address = request.getParameter("address");
        User user = new User();
        User currentUser = (User) request.getSession().getAttribute("user");
        user.setId(currentUser.getId());
        user.setUsername(username);
        if (password.equals("")) user.setPassword(currentUser.getPassword());
        user.setConfirmPassword(user.getPassword());
        user.setEmail(email);
        user.setFirstname(firstname);
        user.setLastname(lastname);
        user.setAddress(address);
        user.setRole(currentUser.getRole());
        return user;
    }
}
