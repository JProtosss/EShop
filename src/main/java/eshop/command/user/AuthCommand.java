package eshop.command.user;

import eshop.command.CommandTemplate;
import eshop.dao.DaoFactory;
import eshop.dao.DaoUser;
import eshop.entity.User;
import eshop.entity.UserErrors;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.Map;

public class AuthCommand extends CommandTemplate {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {

        User user = getUserFromParameters(request);
        cleanSession(request);
        UserErrors userErrors = new UserErrors();
        RequestDispatcher requestDispatcher = getSamePageDispatcher(request);
        boolean isAnyError = verifyUserParams(request, user, userErrors);
        if (isAnyError){
            request.getSession().setAttribute("userError", userErrors);
            request.getSession().setAttribute("auth", true);
            logger.info("user logined");
        } else {
            addAuthCookies(request, response, user);
        }

        dispatcherForward(request, response, requestDispatcher);

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
                cookie.setMaxAge(604800);
                cookie.setPath("/");
                response.addCookie(cookie);
            }
        }
    }

    private boolean verifyUserParams(HttpServletRequest request, User user, UserErrors userErrors) {
        boolean isAnyError = false;
        if (isCredentialsWellFormed(user)){
            try {
                DaoUser daoUser = DaoFactory.getDaoUser();
                logger.info("user found");
                isAnyError = !daoUser.findByUsernameAndPassword(user);
                if (isAnyError) {
                    boolean isRegistered = daoUser.findByUsername(user);
                    if (isRegistered){
                        userErrors.setPassword("WRONG_PASSWORD");
                    } else {
                        userErrors.setUsername("WRONG_USERNAME");
                    }
                }
            } catch (SQLException e) {
                userErrors.setEmail("BAD_DB_CONN");
            }
        } else {
            isAnyError = true;
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
