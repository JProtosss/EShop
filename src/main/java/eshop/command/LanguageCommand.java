package eshop.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;

/**
 * @author Евгений
 * internationalization
 */
public class LanguageCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
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
