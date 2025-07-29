package RITIGM.gokartproject.model.scoreCalculation;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Timestamp;

import org.junit.jupiter.api.Test;

import RITIGM.gokartproject.model.RaceLog;
import RITIGM.gokartproject.model.usage.BoostUsage;
import RITIGM.gokartproject.model.usage.DefenseUsage;
import RITIGM.gokartproject.model.usage.OffenseUsage;
import RITIGM.gokartproject.model.usage.TrapUsage;

public class RaceScoreTest {



    @Test
    void testScoreCalculation() {

    String expected_pid = "20";
    Timestamp expected_raceStartTime = new Timestamp(2);
    int expected_raceTime = 386998 ;
    int expected_racePos = 1;
    int expected_mapRaced = 1;
    int expected_characterUsed = 4;
    int expected_wallCol = 0;
    int expected_playerCol = 0;
    int expected_falloff = 0;
    BoostUsage expected_boostStat = new BoostUsage(1,1,1, 1);
    OffenseUsage expected_offenseStat = new OffenseUsage(0,0, 0, 0);
    TrapUsage expected_trapUsage = new TrapUsage(0,0, 0, 0);
    DefenseUsage expected_defenseUsage = new DefenseUsage(1, 1, 1, 1);

    RaceLog raceLog = new RaceLog(expected_pid, expected_raceStartTime, expected_raceTime, expected_racePos, 
    expected_mapRaced, expected_characterUsed, expected_wallCol, expected_playerCol, expected_falloff,
     expected_boostStat,  expected_offenseStat, expected_trapUsage, expected_defenseUsage);

    RaceScore checker = new RaceScore(raceLog);

    double totalScore = (checker.trackPerformaceScoring() + checker.placementScoring() + checker.itemBonus());
    // assertEquals(checker.scoreCalculation(), totalScore *(1-(0.02 * raceLog.getFelloffmap())));
    }
}
