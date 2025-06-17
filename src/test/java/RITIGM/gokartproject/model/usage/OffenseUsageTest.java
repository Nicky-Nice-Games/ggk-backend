package RITIGM.gokartproject.model.usage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import jakarta.validation.constraints.AssertTrue;
/**
 * Tests the functions of the OffenseUsage object
 */
public class OffenseUsageTest {
    private OffenseUsage check;
    public static final String TO_STRING_FORMAT = "Offense Usage:\r\n" + //
                                                    "\t\tPuck 1 = %d, \r\n" + //
                                                    "\t\tPuck 2 = %d, \r\n";
    @BeforeEach
    void init(){
        this.check = new OffenseUsage(1,2, 3, 4);
    }

    @Test
    void testGetPuck1() {
        assertEquals(1, check.getPuck1());
    }

    @Test
    void testGetPuck2() {
        assertEquals(2, check.getPuck2());
    }

    @Test
    void testSetPuck1() {
        check.setPuck1(3);
        assertEquals(3, check.getPuck1());
    }

    @Test
    void testSetPuck2() {
        check.setPuck2(4);
        assertEquals(4, check.getPuck2());
    }

}
