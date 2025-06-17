package RITIGM.gokartproject.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Timestamp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import RITIGM.gokartproject.model.usage.BoostUsage;
import RITIGM.gokartproject.model.usage.DefenseUsage;
import RITIGM.gokartproject.model.usage.OffenseUsage;
import RITIGM.gokartproject.model.usage.TrapUsage;

public class RaceReportTest {

private String expected_pid = "20";

    private RaceLog raceLog;
    private RaceReport raceReport;

    private static String TO_STRING_FORMAT =    "%s\r\n" + //
                                                "\tRace ID: %d\r\n" + //
                                                "\tScore: %.2f\r\n";
    
    @BeforeEach
    void init(){
    Timestamp expected_raceStartTime = new Timestamp(2);
    int expected_raceTime = 1;
    int expected_racePos = 1;
    int expected_mapRaced = 3;
    int expected_characterUsed = 4;
    int expected_wallCol = 0;
    int expected_playerCol = 0;
    int expected_falloff = 0;
    BoostUsage expected_boostStat = new BoostUsage(1,1,1, 1);
    OffenseUsage expected_offenseStat = new OffenseUsage(1, 1, 1, 1);
    TrapUsage expected_trapUsage = new TrapUsage(1, 1, 1, 1);
    DefenseUsage expected_defenseUsage = new DefenseUsage(1, 1, 1, 1);

    this.raceLog = new RaceLog(expected_pid, expected_raceStartTime, expected_raceTime, expected_racePos, 
    expected_mapRaced, expected_characterUsed, expected_wallCol, expected_playerCol, expected_falloff,
     expected_boostStat,  expected_offenseStat, expected_trapUsage, expected_defenseUsage);

    this.raceReport = new RaceReport(this.raceLog, 20.00, 700);
    
    }

    @Test
    void testGetScore() {
        assertEquals(700, this.raceReport.getScore());
    }

    @Test
    void testSetScore() {
        this.raceReport.setScore(123123);
        assertEquals(123123, this.raceReport.getScore());
    }

    @Test
    void testToString() {
        assertEquals(String.format(TO_STRING_FORMAT, this.raceLog.toString(), 700, 20.00), this.raceReport.toString());
    }
}
