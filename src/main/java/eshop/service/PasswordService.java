package eshop.service;

import com.google.protobuf.ServiceException;
import eshop.dao.DaoUser;
import eshop.entity.User;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

/**
 * @author Евгений
 */
public class PasswordService {

    public static String resetPassword() throws ServiceException, SQLException {

        DaoUser daoUser = new DaoUser();
            String newPassword = Long.toHexString(Double.doubleToLongBits(Math.random()));
            return  passwordHash(newPassword);

    }

    public static String passwordHash(String password) throws ServiceException {
        try {
            MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
            byte[] passwordByte = sha256.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < passwordByte.length; i++) {
                String s = Integer.toHexString(passwordByte[i] & 0xff);
                if (s.length() == 1) {
                    s = "0" + s;
                }
                sb.append(s);
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new ServiceException(e);
        }
    }
    public static String generateNewPassword(User user) throws SQLException, ServiceException {
        String newPassword=user.getUsername()+user.getId();
        user.setPassword(newPassword);
        return newPassword;
    }
}
