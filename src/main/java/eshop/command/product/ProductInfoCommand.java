package eshop.command.product;

import eshop.command.Command;
import eshop.dao.DaoCategory;
import eshop.dao.DaoManufacturer;
import eshop.dao.DaoProduct;
import eshop.entity.Product;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * @author Евгений
 */
public class ProductInfoCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
int productId=Integer.parseInt(request.getParameter("infoProduct"));
        request.removeAttribute("infoProduct");
        DaoManufacturer daoManufacturer = new DaoManufacturer();
        DaoCategory daoCategory = new DaoCategory();
        try{
        request.getSession().setAttribute("manufacturers", daoManufacturer.findAll());
        request.getSession().setAttribute("types", daoCategory.findAll());}
        catch (SQLException e){
            e.printStackTrace();
            //logger.info("Cannot find in db");
        }
        if (productId==-1){request.getSession().setAttribute("productAdd",new Product());
            request.getSession().setAttribute("product", null);}
        else
        {
           DaoProduct daoProduct = new DaoProduct();
           try {
               request.getSession().setAttribute("productAdd",null);
               request.getSession().setAttribute("product", daoProduct.findById(productId));
           } catch (SQLException e) {
               e.printStackTrace();
               //logger.info("Product not found");
           }
       }
        request.getRequestDispatcher("/WEB-INF/views/updateElement.jsp").forward(request,response);
    }
}
