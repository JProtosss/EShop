package eshop.command.user;

import com.google.protobuf.ServiceException;
import eshop.command.Command;
import eshop.command.CommandTemplate;
import eshop.dao.DaoFactory;
import eshop.dao.DaoUser;
import eshop.entity.User;
import eshop.entity.UserErrors;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import static eshop.service.user.VerifyUser.verifyUserParams;

/**
 * @author Евгений
 */

public class AuthCommand extends CommandTemplate {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, MessagingException, SQLException, ServiceException, ServletException {
        User user = getUserFromParameters(request);
        UserErrors userErrors = new UserErrors();
        boolean isAnyError = verifyUserParams(request, user, userErrors);
        if (!isAnyError) {
            request.setAttribute("cookieOn","on");
            request.getSession().setAttribute("auth", true);
            request.getSession().setAttribute("user", user);
            logger.info("user logined");
            addAuthCookies(request, response, user);
            if (user.getRole().equals("admin")) {
                Command command = new AccountCommand();
                command.execute(request, response);
            }
        } else response.sendRedirect(request.getRequestURI());
        dispatcherForward(request, response, request.getRequestDispatcher("/WEB-INF/views/startPage.jsp"));
    }

    private void addAuthCookies(HttpServletRequest request, HttpServletResponse response, User user) {
        String cookieOn = request.getParameter("cookieOn");
        if (cookieOn != null && cookieOn.equals("on")) {
            Map<String, String> userMap = null;
            try {
                userMap = BeanUtils.describe(user);
            } catch (Exception e) {
            }

            for (String key : userMap.keySet()) {
                Cookie cookie = new Cookie(key, userMap.get(key));
                cookie.setMaxAge(900);
                cookie.setPath("/");
                response.addCookie(cookie);
            }
        }
    }


    private void cleanSession(HttpServletRequest request) {
        request.getSession().removeAttribute("userError");
        request.getSession().removeAttribute("auth");
        request.getSession().removeAttribute("user");
    }
}
