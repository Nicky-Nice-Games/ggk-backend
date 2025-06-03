package RITIGM.gokartproject.model;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

import RITIGM.gokartproject.model.usage.BoostUsage;
import RITIGM.gokartproject.model.usage.CollisionStat;
import RITIGM.gokartproject.model.usage.OffenseUsage;
import RITIGM.gokartproject.model.usage.TrapUsage;

public class RaceLog {
    private String pid;
    private Timestamp raceStartTime;
    private Time raceTime;
    private Integer racePos;
    private Integer mapRaced;
    private Integer characterUsed;
    private BoostUsage boostStat;
    private CollisionStat collisionStat;
    private OffenseUsage offenseStat;
    private TrapUsage trapUsage;

    private static final String TO_STRING_FORMAT = "Race Log Info:\r\n" + //
                "\tPID: %S,\r\n" + //
                "\tRace Start Time: %s,\r\n" + //
                "\tRace Length: %s,\r\n" + //
                "\tMap Raced: %d,\r\n" + //
                "\tRace Pos: %d\r\n" + //
                "\tCharacter Used: %d,\r\n" + //
                "\t%s\r\n" + //
                "\t%s\r\n" + //
                "\t%s\r\n" + //
                "\t%s";


    public RaceLog(String pid, Timestamp raceStartTime, Time raceTime, Integer racePos, Integer mapRaced,
            Integer characterUsed, BoostUsage boostStat, CollisionStat collisionStat, OffenseUsage offenseStat,
            TrapUsage trapUsage) {
        this.pid = pid;
        this.raceStartTime = raceStartTime;
        this.raceTime = raceTime;
        this.racePos = racePos;
        this.mapRaced = mapRaced;
        this.characterUsed = characterUsed;
        this.boostStat = boostStat;
        this.collisionStat = collisionStat;
        this.offenseStat = offenseStat;
        this.trapUsage = trapUsage;
    }


    public String getPid() {
        return pid;
    }


    public void setPid(String pid) {
        this.pid = pid;
    }


    public Timestamp getRaceStartTime() {
        return raceStartTime;
    }


    public void setRaceStartTime(Timestamp raceStartTime) {
        this.raceStartTime = raceStartTime;
    }


    public Time getRaceTime() {
        return raceTime;
    }


    public void setRaceTime(Time raceTime) {
        this.raceTime = raceTime;
    }


    public int getRacePos() {
        return racePos;
    }


    public void setRacePos(Integer racePos) {
        this.racePos = racePos;
    }


    public int getMapRaced() {
        return mapRaced;
    }


    public void setMapRaced(Integer mapRaced) {
        this.mapRaced = mapRaced;
    }


    public int getCharacterUsed() {
        return characterUsed;
    }


    public void setCharacterUsed(Integer characterUsed) {
        this.characterUsed = characterUsed;
    }


    public BoostUsage getBoostStat() {
        return boostStat;
    }


    public void setBoostStat(BoostUsage boostStat) {
        this.boostStat = boostStat;
    }


    public CollisionStat getCollisionStat() {
        return collisionStat;
    }


    public void setCollisionStat(CollisionStat collisionStat) {
        this.collisionStat = collisionStat;
    }


    public OffenseUsage getOffenseStat() {
        return offenseStat;
    }


    public void setOffenseStat(OffenseUsage offenseStat) {
        this.offenseStat = offenseStat;
    }


    public TrapUsage getTrapUsage() {
        return trapUsage;
    }


    public void setTrapUsage(TrapUsage trapUsage) {
        this.trapUsage = trapUsage;
    }


    @Override
    public String toString() {
        return String.format(TO_STRING_FORMAT, this.pid, this.raceStartTime,
        raceTime, this.mapRaced, this.racePos, this.characterUsed, this.boostStat,
        this.collisionStat, this.offenseStat, this.trapUsage);
    }

    @Override
    public boolean equals(Object obj){
        if (obj == null) return false;
        if (this == obj) return true;
        if (!(obj instanceof RaceLog)) return false;

        RaceLog other = (RaceLog) obj;

        return 
        this.pid.equals(other.pid) &&
        this.raceStartTime.equals(other.raceStartTime) &&
        this.raceTime.equals(other.raceTime) &&
        this.racePos.equals(other.racePos) &&
        this.mapRaced.equals(other.mapRaced) &&
        this.characterUsed.equals(other.characterUsed);
    }

    /*
    public static void main(String[] args) {
        BoostUsage boostTest = new BoostUsage(1, 2, 3, 4);
        CollisionStat collisiontest = new CollisionStat(1, 2);
        OffenseUsage offenseTest = new OffenseUsage(1, 2, 3, 4);
        TrapUsage trapTest = new TrapUsage(1, 4, 2, 3);

        Date date = new Date();
        Timestamp raceStartTime = new Timestamp(date.getTime());
        Time raceTime = Time.valueOf("01:22:30"); 

        RaceLog log = new RaceLog("    public void setPid(Stroing pid) {\r\n" + //
                        "", raceStartTime, raceTime, 1, 2, 4, 
        boostTest, collisiontest, offenseTest, trapTest);

        System.out.println(log);
    } */   

}
