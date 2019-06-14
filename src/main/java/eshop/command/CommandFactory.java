package eshop.command;

import eshop.command.page.*;
import eshop.command.product.MakeOrderCommand;
import eshop.command.product.UpdateProductCommand;
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
        commands.put("account", new ToAccount());
        commands.put("editProduct", new ToEditPage());
        commands.put("updateProduct", new UpdateProductCommand());
        commands.put("removeProduct", new RemoveProductCommand());
        commands.put("removeUser", new RemoveUserCommand());
        commands.put("blacklist", new BlacklistCommand());
        commands.put("cart", new ToCart());
        commands.put("orderPage",new ToOrderPage());
        commands.put("order",new MakeOrderCommand());
        commands.put("product", new ToProductPage());
        commands.put("recover", new RecoverCommand());
        commands.put("logout", new LogOutCommand());
        commands.put("index", new IndexCommand());
    }

    public static Command createCommand(HttpServletRequest request) {

        String value = request.getParameter("command");

        if (value != null) {
            return getCommandByParameter(value);
        }

        return commands.get("index");
    }

    private static Command getCommandByParameter(String value) {
        switch (value.toLowerCase()) {
            case "auth":
                return commands.get("auth");
            case "orderpage":
                return commands.get("orederPage");
            case "editproduct":
                return commands.get("editProduct");
            case "removeproduct":
                return commands.get("removeProduct");
            case "signup":
                return commands.get("signup");
            case "account":
                return commands.get("account");
            case "cart":
                return commands.get("cart");
            case "removeuser":
                return commands.get("removeUser");
            case "blacklist":
                return commands.get("blacklist");
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
