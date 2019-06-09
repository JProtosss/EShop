package eshop.command;

import eshop.command.cart.CartCommand;
import eshop.command.product.EditProductCommand;
import eshop.command.product.ProductCommand;
import eshop.command.product.ProductInfoCommand;
import eshop.command.user.*;
import eshop.command.user.admin.BlacklistCommand;
import eshop.command.user.admin.DeleteCommand;


import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
    private static Map<String, Command> commands = new HashMap<>();

    static {
        commands.put("auth", new AuthCommand());
        commands.put("signup", new SignUpCommand());
        commands.put("account", new AccountCommand());
        commands.put("editProduct",new EditProductCommand());
        commands.put("editUser",new EditUserCommand());
        commands.put("infoUser",new UserInfoCommand());
        commands.put("infoProduct",new ProductInfoCommand());
        commands.put("blacklist",new BlacklistCommand());
        commands.put("delete",new DeleteCommand());
        commands.put("cart", new CartCommand());
        commands.put("product", new ProductCommand());
        commands.put("recover", new RecoverCommand());
        commands.put("logout", new LogOutCommand());
        commands.put("index", new IndexCommand());
    }

    public static Command createCommand(HttpServletRequest request) {

        String value = request.getParameter("command");
        String editProduct=request.getParameter("editProduct");
        String editUser=request.getParameter("editUser");
        String infoProduct=request.getParameter("infoProduct");
        String infoUser=request.getParameter("infoUser");
        String blacklist=request.getParameter("blacklist");
        String delete=request.getParameter("delete");
        String product = request.getParameter("product");

        if (value != null) {
            return getCommandByParameter(value);
        }

        if (editUser!=null)
        {
            return commands.get("editUser");
        }

        if (editProduct!=null)
        {
            return commands.get("editProduct");
        }

        if (infoProduct!=null)
        {
            return commands.get("infoProduct");
        }

        if (infoUser!=null)
        {
            return commands.get("infoUser");
        }

        if (product != null) {
            return commands.get("product");
        }

        if (blacklist!=null)
        {
            return commands.get("blacklist");
        }

        return commands.get("index");
    }

    private static Command getCommandByParameter(String value) {
        switch (value.toLowerCase()) {
            case "auth":
                return commands.get("auth");
            case "signup":
                return commands.get("signup");
            case "account":
                return commands.get("account");
            case "cart":
                return commands.get("cart");
            case "product":
                return commands.get("product");
            case "recover":
                return commands.get("recover");
            case "language":
                return commands.get("language");
            case "logout":
                return commands.get("logout");
            default:
                return commands.get("index");
        }
    }
}
