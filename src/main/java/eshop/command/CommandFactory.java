package eshop.command;

import eshop.command.user.AuthCommand;
import eshop.command.user.LogOutCommand;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
    private static Map<String, Command> commands = new HashMap<>();
    static{
        commands.put("auth", new AuthCommand());
        commands.put("logout", new LogOutCommand());
    }

    public static Command createCommand(HttpServletRequest request){


        String value = request.getParameter("command");

        if (value != null) {
            return getCommandByParameter(value);
        }

        return getCommandByRequestPath(request);


    }

    private static Command getCommandByRequestPath(HttpServletRequest request) {
        String mainPath = buildPathForSearch(request);

        switch (mainPath){
            case "user":return commands.get("user");
            default:return commands.get("index");
        }
    }

    private static String buildPathForSearch(HttpServletRequest request) {
        String[] path = request.getServletPath().split("/");
        if (path.length>2){
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
            case "language":
                return commands.get("language");
            case "logout":
                return commands.get("logout");
            default:
                return commands.get("bad");
        }
    }
}
