package RITIGM.gokartproject.model.responseReceiver;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import RITIGM.gokartproject.model.responseReceiver.NoUID;


/**
 * Testing the model Template class for the UID test
 */
public class NoUIDTest {
    NoUID check;

    /**
     * Init a sample class for the NoUID object
     */
    @BeforeEach
    void test(){
        this.check = new NoUID("1", "2", "3", 4);
    }

    //Testing all of the setters and getter for the game
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
