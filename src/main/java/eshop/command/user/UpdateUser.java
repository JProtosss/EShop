package eshop.command.user;

import com.google.protobuf.ServiceException;
import eshop.command.Command;
import eshop.command.page.ToAccountPage;
import eshop.dao.DaoUser;
import eshop.entity.User;
import eshop.service.PasswordService;
import eshop.service.user.CRUDUser;
import eshop.validation.UserInfoValidation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import static eshop.service.UserFromParameters.getUserFromParameters;

/**
 * @author Евгений
 * Change user profile. Have to input password
 */
public class UpdateUser implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException, ServletException, ServiceException {
        User user = getUserFromParameters(request);
        if (user.getConfirmPassword().equals(""))
            user.setConfirmPassword(user.getPassword());
        boolean userValid = UserInfoValidation.userInfoValid(user, request);
        user.setPassword(PasswordService.passwordHash(user.getPassword()));
        DaoUser daoUser=new DaoUser();
        User bufUser=daoUser.findById(user.getId());
        user.setRole(bufUser.getRole());
        if (userValid) {
            CRUDUser.update(user);
            request.getSession().setAttribute("user", user);
            ToAccountPage toAccount = new ToAccountPage();
            toAccount.execute(request, response);
        } else
            request.getRequestDispatcher("/WEB-INF/views/account.jsp").forward(request, response);
    }
}
