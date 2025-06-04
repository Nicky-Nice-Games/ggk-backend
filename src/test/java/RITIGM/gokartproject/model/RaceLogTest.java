package RITIGM.gokartproject.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.sql.Time;
import java.sql.Timestamp;

import org.junit.jupiter.api.Test;

import RITIGM.gokartproject.model.usage.BoostUsage;
import RITIGM.gokartproject.model.usage.CollisionStat;
import RITIGM.gokartproject.model.usage.OffenseUsage;
import RITIGM.gokartproject.model.usage.TrapUsage;

/**
 * Tests the functions of a RaceLog object
 * 
 */
public class RaceLogTest {
    private String expected_pid = "20";
    private Timestamp expected_raceStartTime = new Timestamp(2);
    private Time expected_raceTime = new Time(1);
    private Integer expected_racePos = 1;
    private Integer expected_mapRaced = 3;
    private Integer expected_characterUsed = 4;
    private BoostUsage expected_boostStat = new BoostUsage(1, 1, 1, 1);
    private CollisionStat expected_collisionStat = new CollisionStat(2, 3);
    private OffenseUsage expected_offenseStat = new OffenseUsage(1, 1, 1, 1);
    private TrapUsage expected_trapUsage = new TrapUsage(1, 1, 1, 1);

    private RaceLog raceLog = new RaceLog(expected_pid, expected_raceStartTime, expected_raceTime, expected_racePos, 
    expected_mapRaced, expected_characterUsed, expected_boostStat, expected_collisionStat, expected_offenseStat, expected_trapUsage);

    /**
     * Tests data retrival of Boost stat
     */
    @Test
    void testGetBoostStat() {
        assertEquals(expected_boostStat, raceLog.getBoostStat());
    }

    /**
     * tests getter for character usage
     */
    @Test
    void testGetCharacterUsed() {
        assertEquals(expected_characterUsed, raceLog.getCharacterUsed());
    }

    /**
     * tetsts getter for collision stats
     */
    @Test
    void testGetCollisionStat() {
        assertEquals(expected_collisionStat, raceLog.getCollisionStat());
    }

    /**
     * tests getter for maps raced
     */
    @Test
    void testGetMapRaced() {
        assertEquals(expected_mapRaced, raceLog.getMapRaced());
    }

    /**
     * tests getter for offensive stats
     */
    @Test
    void testGetOffenseStat() {
        assertEquals(expected_offenseStat, raceLog.getOffenseStat());
    }

    /**
     * tests getter for the player id
     */
    @Test
    void testGetPid() {
        assertEquals(expected_pid, raceLog.getPid());
    }

    /**
     * tests getter for race postion
     */
    @Test
    void testGetRacePos() {
        assertEquals(expected_racePos, raceLog.getRacePos());
    }

    /**
     * tests getter for race start time
     */
    @Test
    void testGetRaceStartTime() {
        assertEquals(expected_raceStartTime, raceLog.getRaceStartTime());
    }

    /**
     * tests getter for race time
     */
    @Test
    void testGetRaceTime() {
        assertEquals(expected_raceTime, raceLog.getRaceTime());
    }

    /**
     * tests getter for trap usage
     */
    @Test
    void testGetTrapUsage() {
        assertEquals(expected_trapUsage, raceLog.getTrapUsage());
    }

    /**
     * tests setter for boost stats
     */
    @Test
    void testSetBoostStat() {
        expected_boostStat = new BoostUsage(9, 9, 9, 9);
        raceLog.setBoostStat(expected_boostStat);
        assertEquals(expected_boostStat, raceLog.getBoostStat());
    }

    /**
     * tests setter for charcater used
     */
    @Test
    void testSetCharacterUsed() {
        expected_characterUsed = 0;
        raceLog.setCharacterUsed(expected_characterUsed);
        assertEquals(expected_characterUsed, raceLog.getCharacterUsed());
    }

    /**
     * tests setter for collision stat
     */
    @Test
    void testSetCollisionStat() {
        expected_collisionStat = new CollisionStat(9, 9);
        raceLog.setCollisionStat(expected_collisionStat);
        assertEquals(expected_collisionStat, raceLog.getCollisionStat());
    }

    /**
     * tests setter for map raced
     */
    @Test
    void testSetMapRaced() {
        expected_mapRaced = 0;
        raceLog.setMapRaced(expected_mapRaced);
        assertEquals(expected_mapRaced, raceLog.getMapRaced());
    }

    /**
     * tests setter for offesinve statss
     */
    @Test
    void testSetOffenseStat() {
        expected_offenseStat = new OffenseUsage(9,9,9,9);
        raceLog.setOffenseStat(expected_offenseStat);
        assertEquals(expected_mapRaced, raceLog.getMapRaced());
    }

    /**
     * tests setter for player id
     */
    @Test
    void testSetPid() {
        expected_pid = "25";
        raceLog.setPid(expected_pid);
        assertEquals(expected_pid, raceLog.getPid());
    }

    /**
     * tests setter for race postion
     */
    @Test
    void testSetRacePos() {
        expected_racePos = 0;
        raceLog.setRacePos(expected_racePos);
        assertEquals(expected_racePos, raceLog.getRacePos());
    }

    /**
     * tests setter for start time
     */
    @Test
    void testSetRaceStartTime() {
        expected_raceStartTime = new Timestamp(12);
        raceLog.setRaceStartTime(expected_raceStartTime);
        assertEquals(expected_raceStartTime, raceLog.getRaceStartTime());
    }

    /**
     * tests setter for race time
     */
    @Test
    void testSetRaceTime() {
        expected_raceTime = new Time(12);
        raceLog.setRaceTime(expected_raceTime);
        assertEquals(expected_raceTime, raceLog.getRaceTime());
    }

    /**
     * tests setter for trap usage stats
     */
    @Test
    void testSetTrapUsage() {
        expected_trapUsage = new TrapUsage(9, 9, 9, 9);
        raceLog.setTrapUsage(expected_trapUsage);
        assertEquals(expected_trapUsage, raceLog.getTrapUsage());
    }

    /**
     * tests tostring method for the object
     */
    @Test
    void testToString() {
        String expected_string = "Race Log Info:\r\n" + //
                "\tPID: " + expected_pid + ",\r\n" + //
                "\tRace Start Time: " + expected_raceStartTime +",\r\n" + //
                "\tRace Length: " + expected_raceTime + ",\r\n" + //
                "\tMap Raced: " + expected_mapRaced + ",\r\n" + //
                "\tRace Pos: "+ expected_racePos + "\r\n" + //
                "\tCharacter Used: " + expected_characterUsed + ",\r\n" + //
                "\t" + expected_boostStat + "\r\n" + //
                "\t" + expected_collisionStat + "\r\n" + //
                "\t" + expected_offenseStat + "\r\n" + //
                "\t" + expected_trapUsage;
        assertEquals(expected_string, raceLog.toString());
    }


    /**
     * Tests equals override for the object
     */
    @Test
    void testRaceLog(){
    String expected_pid = "20";
    Timestamp expected_raceStartTime = new Timestamp(2);
    Time expected_raceTime = new Time(1);
    Integer expected_racePos = 1;
    Integer expected_mapRaced = 3;
    Integer expected_characterUsed = 4;
    BoostUsage expected_boostStat = new BoostUsage(1, 1, 1, 1);
    CollisionStat expected_collisionStat = new CollisionStat(2, 3);
    OffenseUsage expected_offenseStat = new OffenseUsage(1, 1, 1, 1);
    TrapUsage expected_trapUsage = new TrapUsage(1, 1, 1, 1);

    RaceLog raceLog1 = new RaceLog(expected_pid, expected_raceStartTime, expected_raceTime, expected_racePos, 
    expected_mapRaced, expected_characterUsed, expected_boostStat, expected_collisionStat, expected_offenseStat, expected_trapUsage);
    
    RaceLog raceLog2 = new RaceLog(expected_pid, expected_raceStartTime, expected_raceTime, expected_racePos, 
    expected_mapRaced, expected_characterUsed, expected_boostStat, expected_collisionStat, expected_offenseStat, expected_trapUsage);

    RaceLog raceLog3 = new RaceLog(expected_pid +"1", expected_raceStartTime, expected_raceTime, expected_racePos, 
    expected_mapRaced, expected_characterUsed, expected_boostStat, expected_collisionStat, expected_offenseStat, expected_trapUsage);


    assertEquals(raceLog1, raceLog2); 
    assertNotEquals(raceLog1, raceLog3);
    }
}
