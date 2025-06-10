package RITIGM.gokartproject.model.responseReceiver;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import RITIGM.gokartproject.model.responseReceiver.CreateUID;

public class CreateUIDTest {

    CreateUID check;

    @BeforeEach
    void test(){
        this.check = new CreateUID("1", "2", "3", 3);
    }

    @Test
    void testGetEmail() {
        assertEquals("1", check.getEmail());
    }

    @Test
    void testGetPassword() {
        assertEquals("3", check.getPassword());
    }

    @Test
    void testGetUid() {
        assertEquals(3, check.getUid());
    }

    @Test
    void testGetUsername() {
        assertEquals("2", check.getUsername());
    }

    @Test
    void testSetEmail() {
        check.setEmail("727");
        assertEquals("727", check.getEmail());
    }

    @Test
    void testSetPassword() {
        check.setPassword("727");
        assertEquals("727", check.getPassword());
    }

    @Test
    void testSetUid() {
        check.setUid(123);
        assertEquals(123, check.getUid());
    }

    @Test
    void testSetUsername() {
        check.setUsername("WYSI");
        assertEquals("WYSI", this.check.getUsername());
    }
}
