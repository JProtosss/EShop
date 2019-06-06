package eshop.command;


import org.apache.commons.beanutils.BeanUtils;

import eshop.entity.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CommandTemplate implements Command {


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        infoRedirect(request, response, "BAD_COMMAND" );
    }

    public void infoRedirect(HttpServletRequest request, HttpServletResponse response, String message){
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/info.tiles");
        request.setAttribute("info", message);
        dispatcherForward(request, response, requestDispatcher);
    }

    public void dispatcherForward(HttpServletRequest request, HttpServletResponse response, RequestDispatcher requestDispatcher) {
        try {
            requestDispatcher.forward(request, response);
        } catch (Exception e) {

        }
    }

    public boolean isAccessNotPermitted(HttpServletRequest request, HttpServletResponse response) {
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
        String[] path = request.getServletPath().split("/");
        if (path.length < 2)
            return request.getRequestDispatcher("/index" +".tiles");
        return request.getRequestDispatcher("/"+ path[1] +".tiles");
    }
}
