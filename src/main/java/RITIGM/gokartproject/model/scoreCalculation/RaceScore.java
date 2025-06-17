package RITIGM.gokartproject.model.scoreCalculation;

import java.sql.Timestamp;
import java.util.HashMap;

import RITIGM.gokartproject.model.RaceLog;
import RITIGM.gokartproject.model.usage.BoostUsage;
import RITIGM.gokartproject.model.usage.OffenseUsage;
import RITIGM.gokartproject.model.usage.TrapUsage;

/**
 * Templatge scoring
 */
public class RaceScore{

    private static final HashMap<String, Double> trackOne = new HashMap<String, Double>();
    private static final HashMap<Integer, HashMap<String, Double>> scoreRecord = new HashMap<>();

    private RaceLog raceLog;

    /**
     * Construcotr
     * @param raceLog race log to build from
     */
    public RaceScore(RaceLog raceLog){
        this.raceLog = raceLog;
        
        // Init arraylist to record perfect time and item usage for the track

        trackOne.put("time", 250000.0);
        trackOne.put("item", 10.0);

        scoreRecord.put(1,trackOne);
    }

    /**
     * calculates score
     * @return calculated score
     */
    public double scoreCalculation(){
        double totalScore = (trackPerformaceScoring() + placementScoring() + itemBonus());
        return totalScore * (1-(0.02 * this.raceLog.getFelloffmap()));
    }

    /**
     * calculates the points for the time
     * @return score for time portion
     */
    public double trackPerformaceScoring(){
        double trackTime = scoreRecord.get(raceLog.getMapRaced()).get("time");
        double raceTime = raceLog.getRaceTime();
        if (raceTime <= 0){
            return 0; // Impossible case for negative number
        }
        double mapScore =  trackTime / raceTime * 8500;
        return Math.min(8500, mapScore);
    }

    /**
     * Calculates score based on placement
     * @return placement portion of score
     */
    public double placementScoring(){
        double pos = raceLog.getRacePos();
        if (pos <= 0){
            return 0; // Like how you even get 0th place
        }
        return (12 - pos + 1) / 12 * 1000; 
    }

    /**
     * caculates bonuses based on itme usage
     * @return item usage bonus
     */
    public double itemBonus(){
        double totalItemUsage = 
        this.raceLog.getBoostStat().getSpeedBoost1() +
        this.raceLog.getBoostStat().getSpeedBoost2() +
        this.raceLog.getBoostStat().getSpeedBoost3() +
        this.raceLog.getBoostStat().getSpeedBoost4() +
        this.raceLog.getOffenseStat().getPuck1() +
        this.raceLog.getOffenseStat().getPuck2() + 
        this.raceLog.getOffenseStat().getPuck3() +
        this.raceLog.getOffenseStat().getPuck4() +
        this.raceLog.getTrapUsage().getOilSpill1() + 
        this.raceLog.getTrapUsage().getBrickwall() +
        this.raceLog.getTrapUsage().getConfuseritchie() +
        this.raceLog.getTrapUsage().getFakepowerupbrick() +
        this.raceLog.getDefenseUsage().getDefense1() +
        this.raceLog.getDefenseUsage().getDefense2() +
        this.raceLog.getDefenseUsage().getDefense3() +
        this.raceLog.getDefenseUsage().getDefense4();

        double expectedUsage = scoreRecord.get(raceLog.getMapRaced()).get("item");

        totalItemUsage = Math.min(expectedUsage, totalItemUsage);
        return totalItemUsage / expectedUsage * 500;
    }



}
