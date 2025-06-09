package RITIGM.gokartproject.model;

import java.sql.Timestamp;

import RITIGM.gokartproject.model.usage.BoostUsage;
import RITIGM.gokartproject.model.usage.CollisionStat;
import RITIGM.gokartproject.model.usage.OffenseUsage;
import RITIGM.gokartproject.model.usage.TrapUsage;


/**
 * Create a new race record for data transfer
 */
public class RaceLog {
    // Data related 
    private String pid;
    private Timestamp raceStartTime;
    private int raceTime;
    private int racePos;
    private int mapRaced;
    private int characterUsed;
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


    /**
     * Create a new race lot
     * @param pid the player ID
     * @param raceStartTime the race start time yyyy-MM-dd HH-mm-SS
     * @param raceTime The total lap time HH-mm-ss-ns
     * @param racePos The race position
     * @param mapRaced the map raced
     * @param characterUsed the character usage
     * @param boostStat all of boost stat
     * @param collisionStat all of the collision stat
     * @param offenseStat all of the offense stat
     * @param trapUsage all of of the trap stat 
     */
    public RaceLog(String pid, Timestamp raceStartTime, int raceTime, int racePos, int mapRaced,
            int characterUsed, BoostUsage boostStat, CollisionStat collisionStat, OffenseUsage offenseStat,
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


    /**
     * Get the player id
     * @return the player id
     */
    public String getPid() {
        return pid;
    }


    /**
     * Set the new player for the log
     * @param pid
     */
    public void setPid(String pid) {
        this.pid = pid;
    }


    /**
     * Get the race start time
     * @return the race start time
     */
    public Timestamp getRaceStartTime() {
        return raceStartTime;
    }



    /**
     * Set the new race start time
     * @param raceStartTime the race start time
     */
    public void setRaceStartTime(Timestamp raceStartTime) {
        this.raceStartTime = raceStartTime;
    }


    /**
     * Get the total lap time
     * @return the total lap time
     */
    public int getRaceTime() {
        return raceTime;
    }


    /**
     * Set the new total lap time for hte log
     * @param raceTime the new lap time
     */
    public void setRaceTime(int raceTime) {
        this.raceTime = raceTime;
    }


    /**
     * Get the race position
     * @return the race position
     */
    public int getRacePos() {
        return racePos;
    }


    /**
     * Set the new race position
     * @param racePos the new race pos
     */
    public void setRacePos(Integer racePos) {
        this.racePos = racePos;
    }


    /**
     * Get the map raced
     * @return id of map raced
     */
    public int getMapRaced() {
        return mapRaced;
    }

    /**
     * Set the new map raced
     * @param mapRaced the new map raced on
     */
    public void setMapRaced(Integer mapRaced) {
        this.mapRaced = mapRaced;
    }


    /**
     * Get the character usage
     * @return which character used
     */
    public int getCharacterUsed() {
        return characterUsed;
    }

    /**
     * Set the character used for the race
     * @param characterUsed id for the character raced
     */
    public void setCharacterUsed(Integer characterUsed) {
        this.characterUsed = characterUsed;
    }


    /**
     * Get the boost items stat
     * @return the boost item stat
     */
    public BoostUsage getBoostStat() {
        return boostStat;
    }


    /**
     * set the new item boost stat
     * @param boostStat the item boost stat
     */
    public void setBoostStat(BoostUsage boostStat) {
        this.boostStat = boostStat;
    }


    /**
     * get the new collision stat
     * @return the amount of collision
     */
    public CollisionStat getCollisionStat() {
        return collisionStat;
    }


    /**
     * Set the new collision stat
     * @param collisionStat the new collision stat
     */
    public void setCollisionStat(CollisionStat collisionStat) {
        this.collisionStat = collisionStat;
    }


    /**
     * Get the new offense item usage stat
     * @return the usage stat
     */
    public OffenseUsage getOffenseStat() {
        return offenseStat;
    }


    /**
     * Set the new log for the offense item stat
     * @param offenseStat the new usage
     */
    public void setOffenseStat(OffenseUsage offenseStat) {
        this.offenseStat = offenseStat;
    }


    /**
     * 
     * Get the trap stat
     * @return the trap stat
     */
    public TrapUsage getTrapUsage() {
        return trapUsage;
    }


    /**
     * Set the new trap stat
     * @param trapUsage the new trap stat
     */
    public void setTrapUsage(TrapUsage trapUsage) {
        this.trapUsage = trapUsage;
    }


    /**
     * String formatting for the race log
     */
    @Override
    public String toString() {
        return String.format(TO_STRING_FORMAT, this.pid, this.raceStartTime,
        raceTime, this.mapRaced, this.racePos, this.characterUsed, this.boostStat,
        this.collisionStat, this.offenseStat, this.trapUsage);
    }

    /**
     * New method to check if the race log are the same
     * 
     * Checking: player ID, race start time, race run time, race position
     * Map raced on and which character used
     */
    @Override
    public boolean equals(Object obj){
        if (obj == null) return false;
        if (this == obj) return true;
        if (!(obj instanceof RaceLog)) return false;

        RaceLog other = (RaceLog) obj;

        return 
        this.pid.equals(other.pid) &&
        this.raceStartTime.equals(other.raceStartTime) &&
        this.raceTime == other.raceTime &&
        this.racePos == other.racePos &&
        this.mapRaced == other.mapRaced &&
        this.characterUsed == other.characterUsed;
    }
}
