package eshop.service;

import eshop.entity.User;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author Евгений
 */
public class CookieService {
    public static void addCookies(HttpServletRequest request, HttpServletResponse response, User user) {
        String cookieOn = request.getParameter("cookieOn");
        if (cookieOn != null && cookieOn.equals("on")) {
            Map<String, String> userMap = null;
            try {
                userMap = BeanUtils.describe(user);
            } catch (Exception e) {
            }

            for (String key : userMap.keySet()) {
                Cookie cookie = new Cookie(key, userMap.get(key));
                cookie.setMaxAge(900);
                cookie.setPath("/");
                response.addCookie(cookie);
            }
        }
    }
}
