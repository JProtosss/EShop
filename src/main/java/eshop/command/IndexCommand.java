package eshop.command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Сергей on 22.12.2014.
 */
public class IndexCommand extends CommandTemplate {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        dispatcherForward(request, response, request.getRequestDispatcher("/WEB-INF/views/startPage.jsp"));
    }
}
