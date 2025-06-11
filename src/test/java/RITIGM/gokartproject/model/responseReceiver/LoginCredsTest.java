package RITIGM.gokartproject.model.responseReceiver;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class LoginCredsTest {
    private String expected_username = "yousername";
    private String expected_password = "payswerd";
    private LoginCreds creds = new LoginCreds(expected_username, expected_password);


    @Test
    void testGetPassword() {
        assertEquals(expected_password, creds.getPassword());
    }

    @Test
    void testGetUsername() {
        assertEquals(expected_username, creds.getUsername());
    }

    @Test
    void testSetPassword() {
        creds.setPassword("bassword");
        assertEquals("bassword", creds.getPassword());
    }

    @Test
    void testSetUsername() {
        creds.setUsername("usirnem");
        assertEquals("usirnem", creds.getUsername());
    }
}
