package RITIGM.gokartproject.model.scoreCalculation;

import java.util.HashMap;

import RITIGM.gokartproject.model.RaceLog;

public class RaceScore{

    private static final HashMap<String, Integer> trackOne = new HashMap<String, Integer>();
    private static final HashMap<Integer, HashMap<String, Integer>> scoreRecord = new HashMap<>();

    private RaceLog raceLog;

    public RaceScore(RaceLog raceLog){
        this.raceLog = raceLog;
        
        trackOne.put("time", 250000);
        trackOne.put("item", 10);

        scoreRecord.put(1,trackOne);
    }

    public double scoreCalculation(){
        double totalScore = (trackPerformaceScoring() + placementScoring() + itemBonus());
        return totalScore * (1-(0.02 * this.raceLog.getFelloffmap()));
    }

    public double trackPerformaceScoring(){
        int trackTime = scoreRecord.get(raceLog.getMapRaced()).get("time");
        int raceTime = raceLog.getRaceTime();
        if (raceTime <= 0){
            return 0; // Impossible case for negative number
        }
        double mapScore =  trackTime / raceTime * 8500;
        return Math.max(8500, mapScore);
    }


    public double placementScoring(){
        int pos = raceLog.getRacePos();
        if (pos <= 0){
            return 0; // Like how you even get 0th place
        }
        return (12 - pos + 1) / 12 * 1000; 
    }

    public double itemBonus(){
        int totalItemUsage = 
        this.raceLog.getBoostStat().getSpeedBoost1() +
        this.raceLog.getBoostStat().getSpeedBoost2() +
        this.raceLog.getBoostStat().getSpeedBoost3() +
        this.raceLog.getOffenseStat().getPuck1() +
        this.raceLog.getOffenseStat().getPuck2() + 
        this.raceLog.getTrapUsage().getOilSpill1() + 
        this.raceLog.getTrapUsage().getOilSpill2();

        int expectedUsage = scoreRecord.get(raceLog.getMapRaced()).get("item");

        totalItemUsage = Math.max(expectedUsage, totalItemUsage);
        return totalItemUsage / expectedUsage * 500;
    }



}
