package eshop.command;

import eshop.command.cart.CartCommand;
import eshop.command.product.AddProductCommand;
import eshop.command.product.ProductCommand;
import eshop.command.product.ProductInfoCommand;
import eshop.command.product.RemoveProductCommand;
import eshop.command.user.*;
import eshop.command.user.admin.BlacklistCommand;
import eshop.command.user.admin.RemoveUserCommand;


import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
    private static Map<String, Command> commands = new HashMap<>();

    static {
        commands.put("auth", new AuthCommand());
        commands.put("signup", new SignUpCommand());
        commands.put("account", new AccountCommand());
        commands.put("infoUser",new UserInfoCommand());
        commands.put("editProduct",new ProductInfoCommand());
        commands.put("updateProduct",new AddProductCommand());
        commands.put("removeProduct",new RemoveProductCommand());
        commands.put("removeUser",new RemoveUserCommand());
        commands.put("blacklist",new BlacklistCommand());
        commands.put("cart", new CartCommand());
        commands.put("product", new ProductCommand());
        commands.put("recover", new RecoverCommand());
        commands.put("logout", new LogOutCommand());
        commands.put("index", new IndexCommand());
    }

    public static Command createCommand(HttpServletRequest request) {

        String value = request.getParameter("command");
        String removeUser=request.getParameter("removeUser");
        String editProduct=request.getParameter("editProduct");
        String removeProduct=request.getParameter("removeProduct");
        String blacklist=request.getParameter("blacklist");
        String product = request.getParameter("product");

        if (value != null) {
            return getCommandByParameter(value);
        }

        if (removeProduct!=null)
        {
            return commands.get("removeProduct");
        }

        if (removeUser!=null)
        {
            return commands.get("removeUser");
        }


        if (editProduct!=null)
        {
            return commands.get("editProduct");
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
            case "updateproduct":
                return commands.get("updateProduct");
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
