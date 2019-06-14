package eshop.command.product;

import com.google.protobuf.ServiceException;
import eshop.command.Command;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * @author Евгений
 */
public class MakeOrderCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, MessagingException, SQLException, ServiceException, ServletException {
        request.getRequestDispatcher("/WEB-INF/views/account.jsp").forward(request,response);
    }
}
