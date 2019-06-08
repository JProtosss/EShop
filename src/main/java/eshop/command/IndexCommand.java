package eshop.command;

import eshop.entity.User;
import eshop.entity.UserErrors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static eshop.service.user.VerifyUser.verifyUserParams;

public class IndexCommand extends CommandTemplate {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("user") == null) {
            User user = new User();
            user.setRole("client");
            request.getSession().setAttribute("user", user);
        }
        dispatcherForward(request, response, request.getRequestDispatcher("/WEB-INF/views/startPage.jsp"));
    }
}
