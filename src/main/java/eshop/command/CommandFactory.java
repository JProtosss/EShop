package eshop.command;

import eshop.command.cart.CartCommand;
import eshop.command.item.ItemCommand;
import eshop.command.user.*;
import eshop.command.user.admin.BlacklistCommand;
import eshop.command.user.admin.DeleteCommand;
import eshop.command.user.admin.EditCommand;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
    private static Map<String, Command> commands = new HashMap<>();

    static {
        commands.put("auth", new AuthCommand());
        commands.put("signup", new SignUpCommand());
        commands.put("account", new AccountCommand());
        commands.put("edit",new EditCommand());
        commands.put("blacklist",new BlacklistCommand());
        commands.put("delete",new DeleteCommand());
        commands.put("cart", new CartCommand());
        commands.put("item", new ItemCommand());
        commands.put("recover", new RecoverCommand());
        commands.put("logout", new LogOutCommand());
        commands.put("index", new IndexCommand());
    }

    public static Command createCommand(HttpServletRequest request) {

        String value = request.getParameter("command");
        String edit=request.getParameter("edit");
        String blacklist=request.getParameter("blacklist");
        String delete=request.getParameter("delete");
        String item = request.getParameter("item");

        if (item != null) {
            return commands.get("item");
        }
        if (value != null) {
            return getCommandByParameter(value);
        }
        if (blacklist!=null)
        {
            return commands.get("blacklist");
        }

        return commands.get("index");
    }

    private static String buildPathForSearch(HttpServletRequest request) {
        String[] path = request.getServletPath().split("/");
        if (path.length > 2) {
            return path[1] + "/" + path[2];
        }
        if (path.length == 2)
            return path[1];
        return "";
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
            case "item":
                return commands.get("item");
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
