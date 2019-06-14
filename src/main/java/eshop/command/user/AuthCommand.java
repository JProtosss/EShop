package eshop.command.user;

import com.google.protobuf.ServiceException;
import eshop.command.Command;
import eshop.command.page.ToAccount;
import eshop.entity.User;
import eshop.entity.UserErrors;
import eshop.util.VerifyUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import static eshop.service.CookieService.addCookies;
import static eshop.service.user.VerifyUser.verifyUserParams;

/**
 * @author Евгений
 */

public class AuthCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, MessagingException, SQLException, ServiceException, ServletException {
        User user = getUserFromParameters(request);
        UserErrors userErrors = new UserErrors();
        boolean isAnyError = verifyUserParams(request, user, userErrors);
        if (!isAnyError) {
            String gRecaptchaResponse = request.getParameter("g-recaptcha-response");

            System.out.println("gRecaptchaResponse=" + gRecaptchaResponse);

            // Verify CAPTCHA.
            isAnyError = VerifyUtils.verify(gRecaptchaResponse);
        }
        boolean flag = true;
        if (!isAnyError) {
            request.getSession().setAttribute("auth", true);
            request.getSession().setAttribute("user", user);
            request.getSession().setAttribute("logined", true);
            addCookies(request, response, user);
            if (user.getRole().equals("admin")) {
                flag = false;
                request.getSession().setAttribute("role", "admin");
                Command command = new ToAccount();
                command.execute(request, response);
            } else request.getSession().setAttribute("role", "client");
        } else request.getSession().setAttribute("logined", true);
        if (flag) response.sendRedirect("/");

    }

    public User getUserFromParameters(HttpServletRequest request) {
        User user = new User();
        try {
            BeanUtils.populate(user, request.getParameterMap());
        } catch (ReflectiveOperationException e) {
        }
        return user;
    }


    private void cleanSession(HttpServletRequest request) {
        request.getSession().removeAttribute("userError");
        request.getSession().removeAttribute("auth");
        request.getSession().removeAttribute("user");
    }
}
