package eshop.command.user;

import com.google.protobuf.ServiceException;
import eshop.command.Command;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * @author Евгений
 */
public class ItemCommand implements Command {
    @Override
    public void execute(HttpServletRequest httpRequest, HttpServletResponse httpResponse) throws IOException, MessagingException, SQLException, ServiceException {

    }
}
