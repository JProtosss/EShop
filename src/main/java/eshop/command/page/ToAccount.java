package eshop.command.page;

import eshop.command.Command;
import eshop.dao.DaoOrder;
import eshop.dao.DaoProduct;
import eshop.dao.DaoUser;
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
public class ToAccount implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        request.getSession().removeAttribute("command");
        if (request.getSession().getAttribute("role")=="admin") {
            List<User> usersList = new ArrayList<>();
            List<Product> productsList=new ArrayList<>();
            DaoUser daoUser = new DaoUser();
            DaoProduct daoProduct=new DaoProduct();
            DaoOrder daoOrder=new DaoOrder();
            try {
                usersList = daoUser.findAll();
                productsList=daoProduct.findProducts();

            } catch (SQLException e) {
                e.printStackTrace();
            }
            for (User user:usersList) {
                user.setAmountOfOrders(daoOrder.countOrdersByUserId(user.getId()));
            }
            request.getSession().setAttribute("product", null);
            request.getSession().setAttribute("userForUpdate","null");
            request.getSession().setAttribute("productAdd",null);
            request.getSession().setAttribute("usersList", usersList);
            request.getSession().setAttribute("productsList", productsList);
            addCookies(request, response, (User) request.getSession().getAttribute("user"));
        }
        request.getRequestDispatcher("/WEB-INF/views/account.jsp").forward(request,response);
        //dispatcherForward(request, response, request.getRequestDispatcher("/WEB-INF/views/account.jsp"));
    }
}
