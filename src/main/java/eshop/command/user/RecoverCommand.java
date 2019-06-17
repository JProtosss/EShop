package eshop.command.user;

import com.google.protobuf.ServiceException;
import eshop.command.Command;
import eshop.dao.DaoUser;
import eshop.entity.User;
import eshop.service.EmailService;
import eshop.service.PasswordService;
import eshop.service.user.CRUDUser;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * @author Евгений
 * Recovering password for user. New password is concat of username and id.
 */
public class RecoverCommand implements Command {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException, ServiceException {
        User user = getUserFromParameters(request);
        boolean userExist = findUser(user);
        if (userExist) {
            logger.info("User "+user.getUsername()+" is trying to recover password");
            EmailService emailService = new EmailService();
            PasswordService.generateNewPassword(user);
            emailService.send("Recover Password", "There is your new password: "+user.getPassword() , user.getEmail());
            user.setPassword(PasswordService.passwordHash(user.getPassword()));
            CRUDUser.update(user);
            logger.info("Recover message send to"+user.getUsername());
        }else logger.info("Recover was not succesfull for "+user.getUsername());
            response.sendRedirect(request.getRequestURI());
    }

    public User getUserFromParameters(HttpServletRequest request) {
        User user = new User();
        try {
            BeanUtils.populate(user, request.getParameterMap());
        } catch (ReflectiveOperationException e) {
        }
        return user;
    }

    private boolean findUser(User user) throws SQLException, ServiceException {
        DaoUser daoUser = new DaoUser();
        return daoUser.findByUsernameAndEmail(user);
    }
}
