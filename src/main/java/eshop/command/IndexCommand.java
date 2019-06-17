package eshop.command;

import com.google.protobuf.ServiceException;
import eshop.command.page.ToAccountPage;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * @author Евгений
 * forwarding to startPage
 */

public class IndexCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, MessagingException, ServiceException, SQLException {
        boolean flag=true;
        if (request.getSession().getAttribute("lang_id")==null)
        {
            request.getSession().setAttribute("lang_id","en");
        }
        if (request.getSession().getAttribute("role")=="admin")
        {
            flag=false;
            Command command=new ToAccountPage();
            command.execute(request,response);
        }
        if (flag)  request.getRequestDispatcher("/WEB-INF/views/startPage.jsp").forward(request,response);
    }

}
