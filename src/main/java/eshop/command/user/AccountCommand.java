package eshop.command.user;

import eshop.command.CommandTemplate;
import eshop.dao.DaoFactory;
import eshop.dao.DaoProduct;
import eshop.dao.DaoUser;
import eshop.entity.Product;
import eshop.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static eshop.service.CookieService.addCookies;

/**
 * @author Евгений
 */
public class AccountCommand extends CommandTemplate {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        if (request.getSession().getAttribute("role")=="admin") {
            List<User> usersList = new ArrayList<>();
            List<Product> productsList=new ArrayList<>();
            DaoUser daoUser = new DaoUser();
            DaoProduct daoProduct=new DaoProduct();
            try {
                usersList = daoUser.findAll();
                productsList=daoProduct.findProducts();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            request.getSession().setAttribute("userForUpdate","null");
            request.getSession().setAttribute("usersList", usersList);
            request.getSession().setAttribute("productsList", productsList);
            addCookies(request, response, (User) request.getSession().getAttribute("user"));
        }
        dispatcherForward(request, response, request.getRequestDispatcher("/WEB-INF/views/account.jsp"));
    }
}
