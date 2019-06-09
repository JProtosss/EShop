package eshop.command.product;

import eshop.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Евгений
 */
public class ProductCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().setAttribute("product",request.getParameter("product"));
        request.getRequestDispatcher("/WEB-INF/views/product.jsp").forward(request,response);
    }
}
