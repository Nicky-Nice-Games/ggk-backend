package RITIGM.gokartproject.model.usage;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * tests the functions of the Boost Usage Objects
 */
public class BoostUsageTest {
    BoostUsage check;
    public static final String TO_STRING_FORMAT = "Boost Usage: \r\n" + //
                                                    "\t\tSpeed Boost 1 = %d, \r\n" + //
                                                    "\t\tSpeed Boost 2 = %d, \r\n" + //
                                                    "\t\tSpeed Boost 3 = %d,";

    @BeforeEach
    void initTest(){
        check = new BoostUsage(0, 1, 2);
    }

    @Test
    void testGetSpeedBoost1() {
        assertEquals(0, check.getSpeedBoost1());
    }

    @Test
    void testGetSpeedBoost2() {
        assertEquals(1, check.getSpeedBoost2());
    }

    @Test
    void testGetSpeedBoost3() {
        assertEquals(2, check.getSpeedBoost3());
    }

    @Test
    void testSetSpeedBoost1() {
        check.setSpeedBoost1(1);
        assertEquals(1, check.getSpeedBoost1());
    }

    @Test
    void testSetSpeedBoost2() {
        check.setSpeedBoost2(2);
        assertEquals(2, check.getSpeedBoost2());
    }

    @Test
    void testSetSpeedBoost3() {
        check.setSpeedBoost3(3);
        assertEquals(3, check.getSpeedBoost3());
    }

    // @Test
    // void testToString() {
    //     assertEquals(String.format(TO_STRING_FORMAT,check.getSpeedBoost1(),check.getSpeedBoost2(),check.getSpeedBoost3()), check);
    // }
}
