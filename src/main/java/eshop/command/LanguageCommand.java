package eshop.command;

import com.google.protobuf.ServiceException;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Locale;

/**
 * @author Евгений
 */
public class LanguageCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, MessagingException, SQLException, ServiceException, ServletException {
        String language = request.getParameter("lang_id");
        if (language.equals("en"))
            language="ru";else
                language="en";
        if ((language) != null) {
            request.getSession().setAttribute("lang_id", language);
            Locale locale = new Locale(language);
            Locale.setDefault(locale);
        }
        response.sendRedirect("/");
    }
}
