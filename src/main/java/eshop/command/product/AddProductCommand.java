package eshop.command.product;

import com.google.protobuf.ServiceException;
import eshop.command.Command;
import eshop.command.user.AccountCommand;
import eshop.dao.DaoCategory;
import eshop.dao.DaoManufacturer;
import eshop.entity.Manufacturer;
import eshop.entity.Product;
import eshop.entity.Type;
import eshop.service.product.CRUDProduct;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;

/**
 * @author Евгений
 */
public class AddProductCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, MessagingException, ServletException, SQLException, ServiceException {

        Product product=new Product();
        String productName=request.getParameter("productName");
        String productPrice= request.getParameter("productPrice");
        int productAmount= Integer.parseInt(request.getParameter("productAmount"));
        String productDescription=request.getParameter("productDescription");
        String manufacturerName=request.getParameter("manufacturerName");
        String productType=request.getParameter("productType");

        DaoManufacturer daoManufacturer=new DaoManufacturer();
        DaoCategory daoCategory=new DaoCategory();

        product.setName(productName);
        product.setPrice(productPrice);
        product.setAmount(productAmount);
        product.setDescription(productDescription);
        product.setManufacturer(daoManufacturer.findByName(manufacturerName));
        product.setType(daoCategory.findByType(productType));


        if (request.getSession().getAttribute("product") !=null)
        {
            product.setId(((Product)request.getSession().getAttribute("product")).getId());
            CRUDProduct.update(product);
        }else
        {
          CRUDProduct.add(product);
        }
        Command command=new AccountCommand();
        command.execute(request,response);
    }
}
