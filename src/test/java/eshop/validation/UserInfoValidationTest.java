package eshop.validation;

import eshop.entity.User;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

/**
 * @author Евгений
 */
public class UserInfoValidationTest {


    @DataProvider(name = "userProvider")
    public static Object[][] symbolParserTestData(){
        return new Object[][]{
                {new User(1,"bot123bot","j23@y.com","firstname","lastname","address")},
                {new User(1,"bot1234","jpy@edu.by","firstname","lastname","address")},
                {new User(1,"imnotabot","epam@epam.com","firstname","lastname","address")}
        };
    }


    @Test(dataProvider = "userProvider")
    public void testUsername(User user) {
        boolean validUsername=user.getUsername().matches("^[a-zA-Z]\\w{6,45}$");
        assertTrue(validUsername);
    }

    @Test(dataProvider = "userProvider")
    public void testEmail(User user) {
        boolean validEmail=user.getEmail().matches(".+@.+\\..+");
        assertTrue(validEmail);
    }

}