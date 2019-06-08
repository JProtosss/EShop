package eshop.command.user;

import eshop.command.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Евгений
 */
public class LogOutCommand implements Command {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        clearSessionInvalidUser(request);
        request.getSession().invalidate();
        try {
            response.sendRedirect("/");
        } catch (IOException e) {
            logger.error("Redirect", e);
        }
    }

    private void clearSessionInvalidUser(HttpServletRequest request) {
        request.getSession().removeAttribute("user");
        request.getSession().removeAttribute("auth");
        request.getSession().removeAttribute("userError");
        request.getSession().removeAttribute("usersList");
        request.getSession().removeAttribute("productsList");
    }
}