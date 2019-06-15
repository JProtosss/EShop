package eshop.command.user;

import com.google.protobuf.ServiceException;
import eshop.command.Command;
import eshop.service.EmailService;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * @author Евгений
 */
public class SendMessage implements Command {
    final static ResourceBundle resourceBundle = ResourceBundle.getBundle("mail");
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, MessagingException, SQLException, ServiceException, ServletException {
        String clientName=request.getParameter("name");
        String email=request.getParameter("email");
        String subject=request.getParameter("subject");
        String message=request.getParameter("message");
        EmailService emailService = new EmailService();
        emailService.send(subject,"Client name: "+clientName+"\nEmail: "+email+"\nMessage: "+message,resourceBundle.getString("username"));
        response.sendRedirect(request.getRequestURI());
    }
}
