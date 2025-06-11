package RITIGM.gokartproject.model.usage;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * tests fucntions of Trap Ussage object
 */
public class TrapUsageTest {
    public static final String TO_STRING_FORMAT = "Trap Usage: \r\n" + //
                                                    "\t\tOil Spill 1 = %d, \r\n" + //
                                                    "\t\tOil Spill 2 = %d, \r\n";
    TrapUsage check;

    @BeforeEach
    void init(){
        this.check = new TrapUsage(1, 2);
    }

    @Test
    void testGetOilSpill1() {  
        assertEquals(check.getOilSpill1(), 1);        
    }

    @Test
    void testGetOilSpill2() {
        assertEquals(check.getOilSpill2(), 2);
    }

    @Test
    void testSetOilSpill1() {
        check.setOilSpill1(0);
        assertEquals(check.getOilSpill1(), 0);
    }

    @Test
    void testSetOilSpill2() {
        check.setOilSpill2(5);
        assertEquals(check.getOilSpill2(), 5);
    }

    // @Test
    // void testToString() {
    //     assertEquals(String.format(TO_STRING_FORMAT, 1,2), check);
    // }
    
}
