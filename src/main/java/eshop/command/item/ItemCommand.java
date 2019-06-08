package eshop.command.item;

import eshop.command.CommandTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Евгений
 */
public class ItemCommand extends CommandTemplate {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response){
        request.getSession().setAttribute("item",request.getParameter("item"));
        dispatcherForward(request, response, request.getRequestDispatcher("/WEB-INF/views/item.jsp"));
    }
}
