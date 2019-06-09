package eshop.command.cart;

import eshop.command.Command;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Евгений
 */
public class CartCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, MessagingException, ServletException {
        request.getRequestDispatcher("/WEB-INF/views/cart.jsp").forward(request,response);
    }
}
