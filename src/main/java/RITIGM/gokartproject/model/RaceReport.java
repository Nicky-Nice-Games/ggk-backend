package RITIGM.gokartproject.model;

import java.sql.Timestamp;

import RITIGM.gokartproject.model.usage.BoostUsage;
import RITIGM.gokartproject.model.usage.OffenseUsage;
import RITIGM.gokartproject.model.usage.TrapUsage;

public class RaceReport extends RaceLog{
    
    private double score;

    private static String TO_STRING_FORMAT =    "%s\r\n" + //
                                                "\tScore: %.2f\r\n";
    
    public RaceReport(String pid, Timestamp raceStartTime, int raceTime, int racePos, int mapRaced,
            int characterUsed, int collisionWithPlayer, int collisionWithWall, int felloffmap,
            BoostUsage boostStat, OffenseUsage offenseStat, TrapUsage trapUsage, double score){
        super(pid, raceStartTime, raceTime,racePos,mapRaced,characterUsed,collisionWithPlayer,collisionWithWall,
        felloffmap,boostStat,offenseStat,trapUsage);
        this.score = score;
    }

    public RaceReport(RaceLog raceLog, double score){
        super(raceLog.getPid(), raceLog.getRaceStartTime(), raceLog.getRaceTime(), raceLog.getRacePos(),
        raceLog.getMapRaced(), raceLog.getCharacterUsed(), raceLog.getCollisionWithPlayer(), raceLog.getCollisionWithWall(), raceLog.getFelloffmap(),
        raceLog.getBoostStat(), raceLog.getOffenseStat(), raceLog.getTrapUsage());

        this.score = score;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public static String getTO_STRING_FORMAT() {
        return TO_STRING_FORMAT;
    }

    @Override
    public String toString() {
        return String.format(TO_STRING_FORMAT, super.toString(), this.score);
    }

    public static void main(String[] args) {
        String expected_pid = "20";
        Timestamp expected_raceStartTime = new Timestamp(2);
        int expected_raceTime = 1;
        int expected_racePos = 1;
        int expected_mapRaced = 3;
        int expected_characterUsed = 4;
        int expected_wallCol = 0;
        int expected_playerCol = 0;
        int expected_falloff = 0;
        BoostUsage expected_boostStat = new BoostUsage(1,1,1);
        OffenseUsage expected_offenseStat = new OffenseUsage(1, 1);
        TrapUsage expected_trapUsage = new TrapUsage(1, 1);

        RaceLog raceLog = new RaceLog(expected_pid, expected_raceStartTime, expected_raceTime, expected_racePos, 
        expected_mapRaced, expected_characterUsed, expected_wallCol, expected_playerCol, expected_falloff,
        expected_boostStat,  expected_offenseStat, expected_trapUsage);

        RaceReport check = new RaceReport(raceLog, 20000.023);

        System.out.println(check);
    }
}
