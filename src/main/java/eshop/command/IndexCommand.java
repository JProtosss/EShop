package eshop.command;

import com.google.protobuf.ServiceException;
import eshop.command.user.AccountCommand;
import eshop.entity.User;
import eshop.entity.UserErrors;

import javax.mail.MessagingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import static eshop.service.user.VerifyUser.verifyUserParams;

public class IndexCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, MessagingException, ServiceException, SQLException {
//        if (request.getSession().getAttribute("user") == null) {
//            User user = new User();
//            user.setRole("null");
//            request.getSession().setAttribute("user", user);
//            request.getSession().setAttribute("auth",false);
//        }
        if (request.getSession().getAttribute("role")=="admin")
        {
            Command command=new AccountCommand();
            command.execute(request,response);
        }
        System.out.println(request.getRequestURI());
        request.getRequestDispatcher("/WEB-INF/views/startPage.jsp").forward(request,response);
        //dispatcherForward(request, response, request.getRequestDispatcher("/WEB-INF/views/startPage.jsp"));
    }

}
