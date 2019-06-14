package eshop.command.product;

import com.google.protobuf.ServiceException;
import eshop.command.Command;
import eshop.dao.DaoProduct;
import eshop.entity.Order;
import eshop.entity.Product;
import eshop.entity.User;
import eshop.service.order.CRUDOrder;
import eshop.service.product.CRUDProduct;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * @author Евгений
 */
public class MakeOrderCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, MessagingException, SQLException, ServiceException, ServletException {
        Product product= (Product) request.getSession().getAttribute("product");
        product.setAmount(product.getAmount()-1);
        CRUDProduct.update(product);
        int user_id=((User)request.getSession().getAttribute("user")).getId();
        Order order=new Order(product.getId(),user_id);
        CRUDOrder.add(order);
        request.getRequestDispatcher("/WEB-INF/views/account.jsp").forward(request,response);
    }
}
