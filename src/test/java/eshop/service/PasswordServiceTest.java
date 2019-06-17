package eshop.service;

import com.google.protobuf.ServiceException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * @author Евгений
 */
public class PasswordServiceTest {


    String password;

    @BeforeTest
    public void setUp() throws ServiceException {
        password = "ThisIsPassword";
        password = PasswordService.passwordHash(password);
    }

    @Test
    public void testPasswordHash() {
        assertEquals(password, "9e4095417158189ad876acbba90c1c61d5767c2e1a507910c4a37cd8a7271d12");
    }
}