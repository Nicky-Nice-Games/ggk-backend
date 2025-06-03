package RITIGM.gokartproject.model.usage;

import static org.junit.jupiter.api.Assertions.assertEquals;

import RITIGM.gokartproject.model.*;

import org.junit.jupiter.api.Test;

public class TrapUsageTest {
    
    private int trapItem1 = 0;
    private int trapItem2 = 0;
    private int trapItem3 = 0;
    private int trapItem4 = 0;

    private TrapUsage trapUsage = new TrapUsage(trapItem1, trapItem2, trapItem3, trapItem4);
    @Test
    void testGetTrapItem1() {
        assertEquals(trapItem1,trapUsage.getTrapItem1());
    }

    @Test
    void testGetTrapItem2() {
        assertEquals(trapItem2,trapUsage.getTrapItem2());
    }

    @Test
    void testGetTrapItem3() {
        assertEquals(trapItem3,trapUsage.getTrapItem3());
    }

    @Test
    void testGetTrapItem4() {
        assertEquals(trapItem4,trapUsage.getTrapItem4());
    }

    @Test
    void testSetTrapItem1() {
        trapItem1 = 9;
        trapUsage.setTrapItem1(trapItem1);
        assertEquals(trapItem1,trapUsage.getTrapItem1());
    }

    @Test
    void testSetTrapItem2() {
        trapItem2 = 9;
        trapUsage.setTrapItem2(trapItem2);
        assertEquals(trapItem2,trapUsage.getTrapItem2());
    }

    @Test
    void testSetTrapItem3() {
        trapItem3 = 9;
        trapUsage.setTrapItem3(trapItem3);
        assertEquals(trapItem3,trapUsage.getTrapItem3());
    }

    @Test
    void testSetTrapItem4() {
        trapItem4 = 9;
        trapUsage.setTrapItem4(trapItem4);
        assertEquals(trapItem4,trapUsage.getTrapItem4());
    }

    @Test
    void testToString() {
        String expected_string = "Trap Usage:\r\n" + //
                                "\t\tItem 1 = "+ trapItem1 +", \r\n" + //
                                "\t\tItem 2 = "+ trapItem2 +", \r\n" + //
                                "\t\tItem 3 = "+ trapItem3 +", \r\n" + //
                                "\t\tItem 4 = "+ trapItem4 +",";
        assertEquals(expected_string,trapUsage.toString());
    }

    
}
