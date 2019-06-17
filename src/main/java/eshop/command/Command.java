package eshop.command;


import com.google.protobuf.ServiceException;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public interface Command {
    void execute(HttpServletRequest httpRequest, HttpServletResponse httpResponse) throws IOException, MessagingException, SQLException, ServiceException, ServletException;
}
