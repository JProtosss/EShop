package eshop.command;


import eshop.entity.User;
import org.apache.commons.beanutils.BeanUtils;

import javax.mail.MessagingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CommandTemplate implements Command {


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, MessagingException, ServletException {
        infoRedirect(request, response, "BAD_COMMAND" );
    }

    public void infoRedirect(HttpServletRequest request, HttpServletResponse response, String message) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(request.getContextPath());
        request.setAttribute("info", message);
       dispatcherForward(request,response,requestDispatcher);
    }

    public void dispatcherForward(HttpServletRequest request, HttpServletResponse response, RequestDispatcher requestDispatcher) {
        try {
            requestDispatcher.forward(request, response);
        } catch (Exception e) {

        }
    }

    public boolean isAccessNotPermitted(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null || !user.getRole().equals("admin")  ) {
            infoRedirect(request, response, "LOG_IN_WARN");
            return true;
        }
        return false;
    }


    public User getUserFromParameters(HttpServletRequest request) {
        User user = new User();
        try {
            BeanUtils.populate(user, request.getParameterMap());
        } catch (ReflectiveOperationException e) {
        }
        return user;
    }

    public RequestDispatcher getSamePageDispatcher(HttpServletRequest request) {

        return request.getRequestDispatcher(request.getContextPath());
    }
}
