package eshop.command.product;

import com.google.protobuf.ServiceException;
import eshop.command.Command;
import eshop.command.page.ToAccountPage;
import eshop.dao.DaoCategory;
import eshop.dao.DaoManufacturer;
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
 * Updating product info from admin page
 */
public class UpdateProductCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, MessagingException, ServletException, SQLException, ServiceException {

        Product product=new Product();
        int id= Integer.parseInt(request.getParameter("id"));
        String productName=request.getParameter("productName");
        String productPrice= request.getParameter("productPrice");
        int productAmount= Integer.parseInt(request.getParameter("productAmount"));
        String productImage=request.getParameter("productImage");
        String productDescription=request.getParameter("productDescription");
        String manufacturerName=request.getParameter("manufacturerName");
        String productType=request.getParameter("productType");

        DaoManufacturer daoManufacturer=new DaoManufacturer();
        DaoCategory daoCategory=new DaoCategory();

        product.setName(productName);
        product.setPrice(productPrice);
        product.setAmount(productAmount);
        product.setImage(productImage);
        product.setDescription(productDescription);
        product.setManufacturer(daoManufacturer.findByName(manufacturerName));
        product.setType(daoCategory.findByType(productType));

        if (id!=-1)
        {
            product.setId(id);
            CRUDProduct.update(product);
        }else
        {
          CRUDProduct.add(product);
        }
        Command command=new ToAccountPage();
        command.execute(request,response);
    }
}
