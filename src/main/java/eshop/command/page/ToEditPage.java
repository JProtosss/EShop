package eshop.command.page;

import eshop.command.Command;
import eshop.dao.DaoCategory;
import eshop.dao.DaoManufacturer;
import eshop.dao.DaoProduct;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * @author Евгений
 */
public class ToEditPage implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.removeAttribute("editProduct");
        DaoManufacturer daoManufacturer = new DaoManufacturer();
        DaoCategory daoCategory = new DaoCategory();
        try{
            request.getSession().setAttribute("manufacturers", daoManufacturer.findAll());
            request.getSession().setAttribute("types", daoCategory.findAll());}
        catch (SQLException e){
            e.printStackTrace();
            //logger.info("Cannot find in db");
        }
        if (request.getParameter("product_id")==null){
            request.getSession().setAttribute("product", null);}
        else
        {
            int productId=Integer.parseInt(request.getParameter("product_id"));
            DaoProduct daoProduct = new DaoProduct();
            try {
                request.getSession().setAttribute("product", daoProduct.findById(productId));
            } catch (SQLException e) {
                e.printStackTrace();
                //logger.info("Product not found");
            }
        }
        request.getRequestDispatcher("/WEB-INF/views/updateElement.jsp").forward(request,response);
    }
}
