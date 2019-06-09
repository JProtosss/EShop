package eshop.command.user;

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
public class EditUserCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, MessagingException, SQLException, ServiceException, ServletException {

        Command command=new AccountCommand();
        command.execute(request,response);
    }
}
