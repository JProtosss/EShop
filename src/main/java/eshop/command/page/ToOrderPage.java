package eshop.command.page;

import com.google.protobuf.ServiceException;
import eshop.command.Command;
import eshop.dao.DaoProduct;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * @author Евгений
 */
public class ToOrderPage implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, MessagingException, SQLException, ServiceException, ServletException {
        int productId=Integer.parseInt(request.getParameter("product_id"));
        DaoProduct daoProduct = new DaoProduct();
        try {
            request.getSession().setAttribute("product", daoProduct.findById(productId));
        } catch (SQLException e) {
            e.printStackTrace();
            //logger.info("Product not found");
        }
        request.getRequestDispatcher("/WEB-INF/views/order.jsp").forward(request,response);
    }
}
