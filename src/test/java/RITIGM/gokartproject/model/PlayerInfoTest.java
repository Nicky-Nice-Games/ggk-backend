package RITIGM.gokartproject.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class PlayerInfoTest {
    private String expected_email = "test@test.com";
    private Integer expected_pid = 20;
    private String expected_pw = "tester";
    private Integer expected_uid = 20;
    private String expected_username = "test_person";
    private PlayerInfo playerinfo = new PlayerInfo(expected_pid, expected_email, expected_pw, expected_uid, expected_username);

    @Test
    void testGetEmail() {
        
        assertEquals(expected_email, playerinfo.getEmail());
    }

    @Test
    void testGetPid() {
        
        assertEquals(expected_pid, playerinfo.getPid());
    }

    @Test
    void testGetPw() {
        
        assertEquals(expected_pw, playerinfo.getPw());
    }

    @Test
    void testGetUid() {

        assertEquals(expected_uid, playerinfo.getUid());
    }

    @Test
    void testGetUsername() {

        assertEquals(expected_username, playerinfo.getUsername());
    }

    @Test
    void testSetEmail() {
        expected_email = "test2@test.com";
        playerinfo.setEmail(expected_email);
        assertEquals(expected_email, playerinfo.getEmail());
    }

    @Test
    void testSetPid() {
        expected_pid = 25;
        playerinfo.setPid(expected_pid);
        assertEquals(expected_pid,playerinfo.getPid());
    }

    @Test
    void testSetPw() {
        expected_pw = "testPassword";
        playerinfo.setPw(expected_pw);
        assertEquals(expected_pw, playerinfo.getPw());
    }

    @Test
    void testSetUid() {
        expected_uid = 2;
        playerinfo.setUid(expected_uid);
        assertEquals(expected_uid,playerinfo.getUid());
    }

    @Test
    void testSetUsername() {
        expected_username = "Loosername!";
        playerinfo.setUsername(expected_username);
        assertEquals(expected_username, playerinfo.getUsername());
    }

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
