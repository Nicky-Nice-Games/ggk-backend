package RITIGM.gokartproject.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.sql.Timestamp;

import org.junit.jupiter.api.Test;

import RITIGM.gokartproject.model.usage.BoostUsage;
import RITIGM.gokartproject.model.usage.DefenseUsage;
import RITIGM.gokartproject.model.usage.OffenseUsage;
import RITIGM.gokartproject.model.usage.TrapUsage;


/**
 * Tests the functions of a RaceLog object
 * 
 */
public class RaceLogTest {
    private String expected_pid = "20";
    private Timestamp expected_raceStartTime = new Timestamp(2);
    private int expected_raceTime = 1;
    private int expected_racePos = 1;
    private int expected_mapRaced = 3;
    private int expected_characterUsed = 4;
    private int expected_wallCol = 0;
    private int expected_playerCol = 0;
    private int expected_falloff = 0;
    private BoostUsage expected_boostStat = new BoostUsage(1,1,1, 1);
    //private CollisionStat expected_collisionStat = new CollisionStat(2, 3);
    private OffenseUsage expected_offenseStat = new OffenseUsage(1, 1, 1, 1);
    private TrapUsage expected_trapUsage = new TrapUsage(1, 1, 1, 1);
    private DefenseUsage expected_defenseUsage = new DefenseUsage(1, 1, 1, 1);

    private RaceLog raceLog = new RaceLog(expected_pid, expected_raceStartTime, expected_raceTime, expected_racePos, 
    expected_mapRaced, expected_characterUsed, expected_wallCol, expected_playerCol, expected_falloff,
     expected_boostStat,  expected_offenseStat, expected_trapUsage, expected_defenseUsage);

    /**
     * Tests data retrival of Boost stat
     */
    @Test
    void testGetBoostStat() {
        assertEquals(expected_boostStat.toString(), raceLog.getBoostStat().toString());
    }

    /**
     * tests getter for character usage
     */
    @Test
    void testGetCharacterUsed() {
        assertEquals(expected_characterUsed, raceLog.getCharacterUsed());
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
        assertEquals(expected_offenseStat.toString(), raceLog.getOffenseStat().toString());
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
        assertEquals(expected_trapUsage.toString(), raceLog.getTrapUsage().toString());
    }

    /**
     * tests setter for boost stats
     */
    @Test
    void testSetBoostStat() {
        expected_boostStat = new BoostUsage(9, 9, 9, 9);
        raceLog.setBoostStat(expected_boostStat);
        assertEquals(expected_boostStat.toString(), raceLog.getBoostStat().toString());
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
        expected_offenseStat = new OffenseUsage(9,9, 9, 9);
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
        expected_raceTime = 12;
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
        assertEquals(expected_trapUsage.toString(), raceLog.getTrapUsage().toString());
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
                "\tCollision With Players: " + expected_playerCol + ",\r\n" + //
                "\tCollision With walls: " + expected_wallCol + ",\r\n" + //
                "\tFell of map: " + expected_falloff+",\r\n" + //
                "\t" + expected_boostStat + "\r\n" + //
                "\t" + expected_offenseStat + "\r\n" + //
                "\t" + expected_trapUsage;
        assertEquals(expected_string, raceLog.toString());
    }


    // /**
    //  * Tests equals override for the object
    //  */
    // @Test
    // void testRaceLog(){
    // String expected_pid = "20";
    // Timestamp expected_raceStartTime = new Timestamp(2);
    // int expected_raceTime = 1;
    // Integer expected_racePos = 1;
    // Integer expected_mapRaced = 3;
    // Integer expected_characterUsed = 4;
    // BoostUsage expected_boostStat = new BoostUsage(1, 1, 1, 1);
    // OffenseUsage expected_offenseStat = new OffenseUsage(1, 1, 1,1);
    // TrapUsage expected_trapUsage = new TrapUsage(1, 1, 1, 1);
    // DefenseUsage expected_defenseUsage = new DefenseUsage(1, 1, 1, 1);

    // RaceLog raceLog1 = new RaceLog(expected_pid, expected_raceStartTime, expected_raceTime, expected_racePos, 
    // expected_mapRaced, expected_characterUsed, expected_wallCol, expected_playerCol, expected_falloff,
    //  expected_boostStat,  expected_offenseStat, expected_trapUsage, expected_defenseUsage);
    
    // RaceLog raceLog2 = new RaceLog(expected_pid, expected_raceStartTime, expected_raceTime, expected_racePos, 
    // expected_mapRaced, expected_characterUsed, expected_wallCol, expected_playerCol, expected_falloff,
    // expected_boostStat, expected_offenseStat, expected_trapUsage, expected_defenseUsage);

    // RaceLog raceLog3 = new RaceLog(expected_pid +"1", expected_raceStartTime, expected_raceTime, expected_racePos, 
    // expected_mapRaced, expected_characterUsed, expected_wallCol, expected_playerCol, expected_falloff,
    // expected_boostStat,  expected_offenseStat, expected_trapUsage, expected_defenseUsage);


    // assertEquals(raceLog1, raceLog2); 
    // assertNotEquals(raceLog1, raceLog3);
    // }

    @Test
    void testSetCollisionWithPlayer() {
        expected_playerCol = 9;
        raceLog.setCollisionWithPlayer(expected_playerCol);
        assertEquals(expected_playerCol, raceLog.getCollisionWithPlayer());
    }

    @Test
    void testSetCollisionWithWall() {
        expected_wallCol = 9;
        raceLog.setCollisionWithWall(expected_wallCol);
        assertEquals(expected_wallCol, raceLog.getCollisionWithWall());
    }

    @Test
    void testSetFelloffmap() {
        expected_falloff = 9;
        raceLog.setFelloffmap(expected_falloff);
        assertEquals(expected_falloff, raceLog.getFelloffmap());
    }

    @Test
    void testSetDefenseUsage() {
        DefenseUsage b = new DefenseUsage(10, 19, 21, 24);
        raceLog.setDefenseUsage(b);
        assertEquals(b.toString(), raceLog.getDefenseUsage().toString());
    }
}
