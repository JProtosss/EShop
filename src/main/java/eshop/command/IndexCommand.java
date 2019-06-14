package eshop.command;

import com.google.protobuf.ServiceException;
import eshop.command.page.ToAccount;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class IndexCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, MessagingException, ServiceException, SQLException {
//        if (request.getSession().getAttribute("user") == null) {
//            User user = new User();
//            user.setRole("null");
//            request.getSession().setAttribute("user", user);
//            request.getSession().setAttribute("auth",false);
//        }
        boolean flag=true;
        if (request.getSession().getAttribute("role")=="admin")
        {
            flag=false;
            Command command=new ToAccount();
            command.execute(request,response);
        }
        if (flag)  request.getRequestDispatcher("/WEB-INF/views/startPage.jsp").forward(request,response);
        //dispatcherForward(request, response, request.getRequestDispatcher("/WEB-INF/views/startPage.jsp"));
    }

}
