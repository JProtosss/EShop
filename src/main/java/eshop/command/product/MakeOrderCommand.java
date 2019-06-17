package eshop.command.product;

import com.google.protobuf.ServiceException;
import eshop.command.Command;
import eshop.command.page.ToProductPage;
import eshop.entity.Order;
import eshop.entity.Product;
import eshop.entity.User;
import eshop.service.order.CRUDOrder;
import eshop.service.product.CRUDProduct;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
    private static final Logger logger = LogManager.getLogger();
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, MessagingException, SQLException, ServiceException, ServletException {
        Product product= (Product) request.getSession().getAttribute("product");
        product.setAmount(product.getAmount()-1);
        CRUDProduct.update(product);
        User user=(User)request.getSession().getAttribute("user");
        Order order=new Order(product.getId(),user.getId());
        CRUDOrder.add(order);
        logger.info("User "+user.getUsername()+" has bought "+product.getName()+" for "+product.getPrice());
        request.setAttribute("product",product.getType().getType());
        Command command=new ToProductPage();
        command.execute(request,response);
    }
}
