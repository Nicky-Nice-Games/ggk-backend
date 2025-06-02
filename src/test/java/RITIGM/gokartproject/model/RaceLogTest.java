package RITIGM.gokartproject.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Time;
import java.sql.Timestamp;

import org.junit.jupiter.api.Test;

import RITIGM.gokartproject.model.usage.BoostUsage;
import RITIGM.gokartproject.model.usage.CollisionStat;
import RITIGM.gokartproject.model.usage.OffenseUsage;
import RITIGM.gokartproject.model.usage.TrapUsage;

public class RaceLogTest {
    private Integer expected_pid = 20;
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
    @Test
    void testGetBoostStat() {
        assertEquals(expected_boostStat, raceLog.getBoostStat());
    }

    @Test
    void testGetCharacterUsed() {
        assertEquals(expected_characterUsed, raceLog.getCharacterUsed());
    }

    @Test
    void testGetCollisionStat() {
        assertEquals(expected_collisionStat, raceLog.getCollisionStat());
    }

    @Test
    void testGetMapRaced() {
        assertEquals(expected_mapRaced, raceLog.getMapRaced());
    }

    @Test
    void testGetOffenseStat() {
        assertEquals(expected_offenseStat, raceLog.getOffenseStat());
    }

    @Test
    void testGetPid() {
        assertEquals(expected_pid, raceLog.getPid());
    }

    @Test
    void testGetRacePos() {
        assertEquals(expected_racePos, raceLog.getRacePos());
    }

    @Test
    void testGetRaceStartTime() {
        assertEquals(expected_raceStartTime, raceLog.getRaceStartTime());
    }

    @Test
    void testGetRaceTime() {
        assertEquals(expected_raceTime, raceLog.getRaceTime());
    }

    @Test
    void testGetTrapUsage() {
        assertEquals(expected_trapUsage, raceLog.getTrapUsage());
    }

    @Test
    void testSetBoostStat() {
        expected_boostStat = new BoostUsage(9, 9, 9, 9);
        assertEquals(expected_boostStat, raceLog.getBoostStat());
    }

    @Test
    void testSetCharacterUsed() {
        expected_characterUsed = 0;
        assertEquals(expected_characterUsed, raceLog.getCharacterUsed());
    }

    @Test
    void testSetCollisionStat() {
        expected_collisionStat = new CollisionStat(9, 9);
        assertEquals(expected_collisionStat, raceLog.getCollisionStat());
    }

    @Test
    void testSetMapRaced() {
                expected_mapRaced = 0;
        assertEquals(expected_mapRaced, raceLog.getMapRaced());
    }

    @Test
    void testSetOffenseStat() {

    }

    @Test
    void testSetPid() {

    }

    @Test
    void testSetRacePos() {

    }

    @Test
    void testSetRaceStartTime() {

    }

    @Test
    void testSetRaceTime() {

    }

    @Test
    void testSetTrapUsage() {

    }

    @Test
    void testToString() {

    }
}
