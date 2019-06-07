package eshop.command.user;

import com.google.protobuf.ServiceException;
import eshop.command.CommandTemplate;
import eshop.dao.DaoFactory;
import eshop.dao.DaoUser;
import eshop.entity.User;
import eshop.entity.UserErrors;
import eshop.service.PasswordService;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

/**
 * @author Евгений
 */

public class AuthCommand extends CommandTemplate {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

        User user = getUserFromParameters(request);
        cleanSession(request);
        UserErrors userErrors = new UserErrors();
        boolean isAnyError = verifyUserParams(request, user, userErrors);
        if (!isAnyError){
            request.getSession().setAttribute("auth", true);
            request.getSession().setAttribute("user", user);
            logger.info("user logined");
            addAuthCookies(request, response, user);
        }else response.sendRedirect(request.getRequestURI());
        dispatcherForward(request, response, request.getRequestDispatcher("/WEB-INF/views/startPage.jsp"));
    }

    private void addAuthCookies(HttpServletRequest request, HttpServletResponse response, User user) {
        String cookieOn = request.getParameter("cookieOn");
        if (cookieOn != null && cookieOn.equals("on")){
            Map<String, String> userMap = null;
            try {
                userMap = BeanUtils.describe(user);
            } catch (Exception e) {
            }

            for (String key: userMap.keySet()){
                Cookie cookie = new Cookie(key, userMap.get(key));
                cookie.setMaxAge(900);
                cookie.setPath("/");
                response.addCookie(cookie);
            }
        }
    }

    private boolean verifyUserParams(HttpServletRequest request, User user, UserErrors userErrors) {
        boolean isAnyError = true;
        if (isCredentialsWellFormed(user)){
            try {
                DaoUser daoUser = DaoFactory.getDaoUser();
                isAnyError = !daoUser.findByUsernameAndPassword(user);
                } catch (SQLException e) {
                userErrors.setUsername("BAD_DB_CONN");
            } catch (ServiceException e) {
                e.printStackTrace();
            }
        } else {
            isAnyError = false;
            userErrors.setUsername("BLANK_FIELDS");
        }
        request.getSession().setAttribute("user", user);
        return isAnyError;
    }

    private boolean isCredentialsWellFormed(User user) {
        return user.getUsername() != null && user.getPassword()!= null &&
                !user.getUsername().equals("") && !user.getPassword().equals("");
    }

    private void cleanSession(HttpServletRequest request) {
        request.getSession().removeAttribute("userError");
        request.getSession().removeAttribute("auth");
    }
}
