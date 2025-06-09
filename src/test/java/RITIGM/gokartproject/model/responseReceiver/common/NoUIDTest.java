package RITIGM.gokartproject.model.responseReceiver.common;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class NoUIDTest {
    NoUID check;

    @BeforeEach
    void test(){
        this.check = new NoUID("1", "2", "3");
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
    void testSetUsername() {
        check.setUsername("WYSI");
        assertEquals("WYSI", this.check.getUsername());
    }
}
