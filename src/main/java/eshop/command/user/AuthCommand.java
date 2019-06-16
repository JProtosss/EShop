package eshop.command.user;

import com.google.protobuf.ServiceException;
import eshop.command.Command;
import eshop.command.page.ToAccount;
import eshop.entity.User;
import eshop.entity.UserErrors;
import eshop.util.VerifyUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static eshop.service.CookieService.addCookies;
import static eshop.service.UserFromParameters.getUserFromParameters;
import static eshop.service.user.VerifyUser.verifyUserParams;

/**
 * @author Евгений
 */

public class AuthCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    final static ResourceBundle resourceBundle = ResourceBundle.getBundle("language");

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, MessagingException, SQLException, ServiceException, ServletException {
        User user = getUserFromParameters(request);
        UserErrors userErrors = new UserErrors();
        boolean isAnyError = verifyUserParams(user);
        if (!isAnyError) {
            String gRecaptchaResponse = request.getParameter("g-recaptcha-response");
            // Verify CAPTCHA.
            isAnyError = VerifyUtils.verify(gRecaptchaResponse);
        } else request.getSession().setAttribute("loginError", resourceBundle.getString("loginError"));
        boolean flag = true;
        if (!isAnyError) {
            request.getSession().setAttribute("loginError", null);
            request.getSession().setAttribute("userInfoError", null);
            request.getSession().setAttribute("auth", true);
            request.getSession().setAttribute("user", user);
            addCookies(request, response, user);
            if (user.getRole().equals("admin")) {
                flag = false;
                request.getSession().setAttribute("role", "admin");
                Command command = new ToAccount();
                command.execute(request, response);
            } else request.getSession().setAttribute("role", "client");
        }
        if (flag) response.sendRedirect(request.getRequestURI());
    }


    private void cleanSession(HttpServletRequest request) {
        request.getSession().removeAttribute("userError");
        request.getSession().removeAttribute("auth");
        request.getSession().removeAttribute("user");
    }
}
