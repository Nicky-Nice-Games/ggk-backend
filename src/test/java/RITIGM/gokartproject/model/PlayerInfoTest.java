package RITIGM.gokartproject.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Tests the functions of the PlayerInfo object
 */
public class PlayerInfoTest {
    private String expected_email = "test@test.com";
    private String expected_pid = "20";
    private String expected_pw = "tester";
    private Integer expected_uid = 20;
    private String expected_username = "test_person";
    private int expected_pfp = 1;
    private PlayerInfo playerinfo = new PlayerInfo(expected_pid, expected_email, expected_pw, expected_uid, expected_username, expected_pfp);

    /**
     * Tests Getter for player email
     */
    @Test
    void testGetEmail() {
        
        assertEquals(expected_email, playerinfo.getEmail());
    }

    /**
     * tests getter for player id
     */
    @Test
    void testGetPid() {
        
        assertEquals(expected_pid, playerinfo.getPid());
    }

    /**
     * tests getter for password
     */
    @Test
    void testGetPw() {
        
        assertEquals(expected_pw, playerinfo.getPw());
    }

    /**
     * tests getter for uid
     */
    @Test
    void testGetUid() {

        assertEquals(expected_uid, playerinfo.getUid());
    }

    /**
     * tests getter for username
     */
    @Test
    void testGetUsername() {

        assertEquals(expected_username, playerinfo.getUsername());
    }

    /**
     * tests setter for email
     */
    @Test
    void testSetEmail() {
        expected_email = "test2@test.com";
        playerinfo.setEmail(expected_email);
        assertEquals(expected_email, playerinfo.getEmail());
    }

    /**
     * tests setter for player Id
     */
    @Test
    void testSetPid() {
        expected_pid = "25";
        playerinfo.setPid(expected_pid);
        assertEquals(expected_pid,playerinfo.getPid());
    }

    /**
     * tests setter for password
     */
    @Test
    void testSetPw() {
        expected_pw = "testPassword";
        playerinfo.setPw(expected_pw);
        assertEquals(expected_pw, playerinfo.getPw());
    }

    /**
     * tests setter for user id
     */
    @Test
    void testSetUid() {
        expected_uid = 2;
        playerinfo.setUid(expected_uid);
        assertEquals(expected_uid,playerinfo.getUid());
    }

    /**
     * tests setter for username
     */
    @Test
    void testSetUsername() {
        expected_username = "Loosername!";
        playerinfo.setUsername(expected_username);
        assertEquals(expected_username, playerinfo.getUsername());
    }

    /**
     * tests object's to string
     */
    @Test
    void testToString(){
        String expected_string = "\nPlayer Info:\r\n" + //
                                "\tPID = " + expected_pid +",\r\n" + //
                                "\tEmail = " + expected_email + ",\r\n" + //
                                "\tpw = " + expected_pw + ",\r\n" + //
                                "\tUID = " + expected_uid + ",\r\n" + //
                                "\tUsername = " + expected_username + ",";
        assertEquals(expected_string, playerinfo.toString());
    }
}
