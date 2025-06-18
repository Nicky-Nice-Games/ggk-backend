package RITIGM.gokartproject.model;

import java.sql.Timestamp;

import RITIGM.gokartproject.model.usage.BoostUsage;
import RITIGM.gokartproject.model.usage.DefenseUsage;
import RITIGM.gokartproject.model.usage.OffenseUsage;
import RITIGM.gokartproject.model.usage.TrapUsage;

public class RaceReport extends RaceLog{
    
    private int raceID;
    private double score;

    private static String TO_STRING_FORMAT =    "%s\r\n" + //
                                                "\tRace ID: %d\r\n" + //
                                                "\tScore: %.2f\r\n";
                                                
    
    public RaceReport(String pid, Timestamp raceStartTime, int raceTime, int racePos, int mapRaced,
            int characterUsed, int collisionWithPlayer, int collisionWithWall, int felloffmap,
            BoostUsage boostStat, OffenseUsage offenseStat, TrapUsage trapUsage, DefenseUsage defenseUsage, double score, int raceID){
        super(pid, raceStartTime, raceTime,racePos,mapRaced,characterUsed,collisionWithPlayer,collisionWithWall,
        felloffmap,boostStat,offenseStat,trapUsage, defenseUsage);
        this.score = score;
        this.raceID = raceID;
    }

    public RaceReport(RaceLog raceLog, double score, int raceID){
        super(raceLog.getPid(), raceLog.getRaceStartTime(), raceLog.getRaceTime(), raceLog.getRacePos(),
        raceLog.getMapRaced(), raceLog.getCharacterUsed(), raceLog.getCollisionWithPlayer(), raceLog.getCollisionWithWall(), raceLog.getFelloffmap(),
        raceLog.getBoostStat(), raceLog.getOffenseStat(), raceLog.getTrapUsage(), raceLog.getDefenseUsage());

        this.raceID = raceID;

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
        return String.format(TO_STRING_FORMAT, super.toString(), this.raceID, this.score);
    }

}
