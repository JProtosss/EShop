package eshop.command.cart;

import eshop.command.CommandTemplate;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Евгений
 */
public class CartCommand extends CommandTemplate {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, MessagingException, ServletException {
        dispatcherForward(request, response, request.getRequestDispatcher("/WEB-INF/views/cart.jsp"));
    }
}
