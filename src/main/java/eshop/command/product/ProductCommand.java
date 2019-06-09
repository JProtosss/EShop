package eshop.command.product;

import eshop.command.CommandTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Евгений
 */
public class ProductCommand extends CommandTemplate {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response){
        request.getSession().setAttribute("product",request.getParameter("product"));
        dispatcherForward(request, response, request.getRequestDispatcher("/WEB-INF/views/product.jsp"));
    }
}
