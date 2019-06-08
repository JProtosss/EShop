package eshop.controller;

import com.google.protobuf.ServiceException;
import eshop.command.Command;
import eshop.command.CommandFactory;
import eshop.dao.DaoFactory;
import eshop.dao.DaoUser;
import eshop.entity.User;
import org.apache.commons.beanutils.BeanUtils;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;


/**
 * @author Евгений
 */
public class MainServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        try {
            DaoFactory.setConnection();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Command command = CommandFactory.createCommand(request);
        try {
            command.execute(request, response);
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        setLocale(request);
        autorizeByCookies(request);
        Command command = CommandFactory.createCommand(request);
        try {
            command.execute(request, response);
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }


    private void autorizeByCookies(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies!= null && cookies.length > 1) {
            Map<String, String> userMap = new HashMap<>();
            for (Cookie cookie : cookies) {
                userMap.put(cookie.getName(), cookie.getValue());
            }
            User user = new User();
            try {
                BeanUtils.populate(user, userMap);
            } catch (ReflectiveOperationException e) {
            }
            request.getSession().setAttribute("user", user);
        }
    }

    private void setLocale(HttpServletRequest request) {
        if (request.getSession().getAttribute("lang_id") == null) {
            String language = request.getLocale().getLanguage();
            switch (language.toLowerCase()) {
                case "ru":
                    break;
                default:
                    language = "en";
            }
            request.getSession().setAttribute("lang_id", language);
            Locale locale = new Locale(language);
            Locale.setDefault(locale);
        }
    }
}
