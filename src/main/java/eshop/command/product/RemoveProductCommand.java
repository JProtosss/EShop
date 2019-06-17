package eshop.command.product;

import com.google.protobuf.ServiceException;
import eshop.command.Command;
import eshop.command.page.ToAccountPage;
import eshop.dao.DaoProduct;
import eshop.entity.Product;
import eshop.service.product.CRUDProduct;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * @author Евгений
 * Deleting product from catalog
 */
public class RemoveProductCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, MessagingException, SQLException, ServiceException, ServletException {
        int product_id=Integer.parseInt(request.getParameter("product_id"));
        DaoProduct daoProduct=new DaoProduct();
        Product product=daoProduct.findById(product_id);
        CRUDProduct.delete(product);
        Command command=new ToAccountPage();
        command.execute(request,response);
    }
}
