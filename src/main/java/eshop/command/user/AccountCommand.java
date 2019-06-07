package eshop.command.user;

import eshop.command.CommandTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Евгений
 */
public class AccountCommand extends CommandTemplate {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        dispatcherForward(request, response, request.getRequestDispatcher("/WEB-INF/views/account.jsp"));
    }
}
