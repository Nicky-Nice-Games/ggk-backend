package RITIGM.gokartproject.model.usage;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * tests the functions of the Boost Usage Objects
 */
public class BoostUsageTest {
    private int boostItem1 = 0;
    private int boostItem2 = 0;
    private int boostItem3 = 0;
    private int boostItem4 = 0;

    private BoostUsage boostUsage = new BoostUsage(boostItem1, boostItem2, boostItem3, boostItem4);
    /**
     * tests getter for boost item
     */
    @Test
    void testGetBoostItem1() {
        assertEquals(boostItem1,boostUsage.getBoostItem1());
    }

    /**
     * tests getter for boost item
     */
    @Test
    void testGetBoostItem2() {
        assertEquals(boostItem2,boostUsage.getBoostItem2());
    }

    /**
     * tests getter for boost item
     */
    @Test
    void testGetBoostItem3() {
        assertEquals(boostItem3,boostUsage.getBoostItem3());
    }
    
    /**
     * tests getter for boost item
     */
    @Test
    void testGetBoostItem4() {
        assertEquals(boostItem4,boostUsage.getBoostItem4());
    }

    /**
     * tests setter for boost item
     */
    @Test
    void testSetBoostItem1() {
        boostItem1 = 9;
        boostUsage.setBoostItem1(boostItem1);
        assertEquals(boostItem1,boostUsage.getBoostItem1());
    }

    /**
     * tests setter for boost item
     */
    @Test
    void testSetBoostItem2() {
        boostItem2 = 9;
        boostUsage.setBoostItem2(boostItem2);
        assertEquals(boostItem2,boostUsage.getBoostItem2());
    }

    /**
     * tests setter for boost item
     */
    @Test
    void testSetBoostItem3() {
        boostItem3 = 9;
        boostUsage.setBoostItem3(boostItem3);
        assertEquals(boostItem3,boostUsage.getBoostItem3());
    }

    /**
     * tests setter for boost item
     */
    @Test
    void testSetBoostItem4() {
        boostItem4 = 9;
        boostUsage.setBoostItem4(boostItem4);
        assertEquals(boostItem4,boostUsage.getBoostItem4());
    }

    /**
     * tests to string for object
     */
    @Test
    void testToString() {
        String expected_string = "Boost Usage: \r\n" + //
                                "\t\tItem 1 = "+ boostItem1 +", \r\n" + //
                                "\t\tItem 2 = "+ boostItem2 +", \r\n" + //
                                "\t\tItem 3 = "+ boostItem3 +", \r\n" + //
                                "\t\tItem 4 = "+ boostItem4 +",";
        assertEquals(expected_string, boostUsage.toString());
    }
}
