package eshop.command.page;

import eshop.command.Command;
import eshop.dao.DaoProduct;
import eshop.entity.Product;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Евгений
 */
public class ToProductPage implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().setAttribute("product",request.getParameter("product"));
        DaoProduct daoProduct=new DaoProduct();
        try {
            List<Product> productsList=daoProduct.findProducts();
            request.getSession().setAttribute("productsList",productsList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher("/WEB-INF/views/product.jsp").forward(request,response);
    }
}