package eshop.command;

import eshop.command.user.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
    private static Map<String, Command> commands = new HashMap<>();

    static {
        commands.put("auth", new AuthCommand());
        commands.put("signup", new SignUpCommand());
        commands.put("account", new AccountCommand());
        commands.put("cart", new CartCommand());
        commands.put("item", new ItemCommand());
        commands.put("recover", new RecoverCommand());
        commands.put("logout", new LogOutCommand());
        commands.put("index", new IndexCommand());
    }

    public static Command createCommand(HttpServletRequest request) {

        String value = request.getParameter("command");
        String item = request.getParameter("item");

        if (item != null) {
            return commands.get("item");
        }
        if (value != null) {
            return getCommandByParameter(value);
        }
        return getCommandByRequestPath(request);
    }

    private static Command getCommandByRequestPath(HttpServletRequest request) {
        String mainPath = buildPathForSearch(request);
        switch (mainPath) {
            default:
                return commands.get("index");
        }
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
