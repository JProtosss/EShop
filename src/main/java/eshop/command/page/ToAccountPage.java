package eshop.command.page;

import eshop.command.Command;
import eshop.dao.DaoOrder;
import eshop.dao.DaoProduct;
import eshop.dao.DaoUser;
import eshop.entity.Order;
import eshop.entity.Product;
import eshop.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static eshop.service.CookieService.addCookies;

/**
 * @author Евгений
 */
public class ToAccountPage implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        List<Product> productsList = new ArrayList<>();
        DaoProduct daoProduct = new DaoProduct();
        productsList = daoProduct.findProducts();
        request.getSession().setAttribute("productsList", productsList);
        if (request.getSession().getAttribute("role") == "admin") {
            List<User> usersList = new ArrayList<>();
            DaoUser daoUser = new DaoUser();
            DaoOrder daoOrder = new DaoOrder();
            usersList = daoUser.findAll();
            for (User user : usersList) {
                user.setAmountOfOrders(daoOrder.countOrdersByUserId(user.getId()));
            }
            request.getSession().setAttribute("product", null);
            request.getSession().setAttribute("userForUpdate", "null");
            request.getSession().setAttribute("productAdd", null);
            request.getSession().setAttribute("usersList", usersList);
            addCookies(request, response, (User) request.getSession().getAttribute("user"));
        } else {
            DaoOrder daoOrder = new DaoOrder();
            List<Order> ordersList = daoOrder.finByUserId(((User) request.getSession().getAttribute("user")).getId());
            request.getSession().setAttribute("ordersList", ordersList);
        }
        request.getRequestDispatcher("/WEB-INF/views/account.jsp").forward(request, response);
    }
}
