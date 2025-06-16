package RITIGM.gokartproject.model.scoreCalculation;

import java.sql.Timestamp;
import java.util.HashMap;

import RITIGM.gokartproject.model.RaceLog;
import RITIGM.gokartproject.model.usage.BoostUsage;
import RITIGM.gokartproject.model.usage.OffenseUsage;
import RITIGM.gokartproject.model.usage.TrapUsage;

public class RaceScore{

    private static final HashMap<String, Double> trackOne = new HashMap<String, Double>();
    private static final HashMap<Integer, HashMap<String, Double>> scoreRecord = new HashMap<>();

    private RaceLog raceLog;

    public RaceScore(RaceLog raceLog){
        this.raceLog = raceLog;
        
        // Init arraylist to record perfect time and item usage for the track

        trackOne.put("time", 250000.0);
        trackOne.put("item", 10.0);

        scoreRecord.put(1,trackOne);
    }

    public double scoreCalculation(){
        double totalScore = (trackPerformaceScoring() + placementScoring() + itemBonus());
        return totalScore * (1-(0.02 * this.raceLog.getFelloffmap()));
    }

    public double trackPerformaceScoring(){
        double trackTime = scoreRecord.get(raceLog.getMapRaced()).get("time");
        double raceTime = raceLog.getRaceTime();
        if (raceTime <= 0){
            return 0; // Impossible case for negative number
        }
        double mapScore =  trackTime / raceTime * 8500;
        return Math.min(8500, mapScore);
    }


    public double placementScoring(){
        double pos = raceLog.getRacePos();
        if (pos <= 0){
            return 0; // Like how you even get 0th place
        }
        return (12 - pos + 1) / 12 * 1000; 
    }

    public double itemBonus(){
        double totalItemUsage = 
        this.raceLog.getBoostStat().getSpeedBoost1() +
        this.raceLog.getBoostStat().getSpeedBoost2() +
        this.raceLog.getBoostStat().getSpeedBoost3() +
        this.raceLog.getOffenseStat().getPuck1() +
        this.raceLog.getOffenseStat().getPuck2() + 
        this.raceLog.getTrapUsage().getOilSpill1() + 
        this.raceLog.getTrapUsage().getOilSpill2();

        double expectedUsage = scoreRecord.get(raceLog.getMapRaced()).get("item");

        totalItemUsage = Math.min(expectedUsage, totalItemUsage);
        return totalItemUsage / expectedUsage * 500;
    }


    public static void main(String[] args) {
        RaceLog test = new RaceLog("1", new Timestamp(2), 358203, 2, 1, 3, 3, 0, 0,
         new BoostUsage(1, 1, 1), new OffenseUsage(0, 0), new TrapUsage(0, 0));

        RaceScore check = new RaceScore(test);
        Double scoreTotal = check.scoreCalculation();

        System.out.println("\n\n\nSCORE CALCULATION:  " + scoreTotal +"\n\n\n");
    }

}
