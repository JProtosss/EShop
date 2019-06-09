package eshop.command.product;

import com.google.protobuf.ServiceException;
import eshop.command.CommandTemplate;
import eshop.dao.DaoCategory;
import eshop.dao.DaoManufacturer;
import eshop.dao.DaoProduct;
import eshop.entity.Product;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * @author Евгений
 */
public class ProductInfoCommand extends CommandTemplate {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        DaoProduct daoProduct=new DaoProduct();
        DaoManufacturer daoManufacturer=new DaoManufacturer();
        DaoCategory daoCategory=new DaoCategory();
        try {
            request.getSession().setAttribute("product",daoProduct.findById(Integer.parseInt(request.getParameter("infoProduct"))));
            request.getSession().setAttribute("manufacturers",daoManufacturer.findAll());
            request.getSession().setAttribute("types",daoCategory.findAll());
            request.getSession().setAttribute("user",null);
        }
        catch (SQLException e) {
            e.printStackTrace();
            //logger.info("Product not found");
        }
        dispatcherForward(request,response,request.getRequestDispatcher("/WEB-INF/views/updateElement.jsp"));
    }
}
