package RITIGM.gokartproject.model;

import java.sql.Timestamp;

import RITIGM.gokartproject.model.usage.BoostUsage;
import RITIGM.gokartproject.model.usage.DefenseUsage;
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
    private int collisionWithPlayer;
    private int collisionWithWall;
    private int felloffmap;
    private BoostUsage boostStat;
    private OffenseUsage offenseStat;
    private TrapUsage trapUsage;
    private DefenseUsage defenseUsage;

    private static final String TO_STRING_FORMAT = "Race Log Info:\r\n" + //
                "\tPID: %S,\r\n" + //
                "\tRace Start Time: %s,\r\n" + //
                "\tRace Length: %s,\r\n" + //
                "\tMap Raced: %d,\r\n" + //
                "\tRace Pos: %d\r\n" + //
                "\tCharacter Used: %d,\r\n" + //
                "\tCollision With Players: %d,\r\n" + //
                "\tCollision With walls: %d,\r\n" + //
                "\tFell of map: %d,\r\n" + //
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
     * @param collisionWithPlayer collsions with racers
     * @param collisionWithWall collisions with wall
     * @param felloffmap falls off of map
     * @param boostStat all of boost stat
     * @param offenseStat all of the offense stat
     * @param trapUsage all of of the trap stat 
     */
    public RaceLog(String pid, Timestamp raceStartTime, int raceTime, int racePos, int mapRaced,
            int characterUsed, int collisionWithPlayer, int collisionWithWall, int felloffmap,
            BoostUsage boostStat, OffenseUsage offenseStat, TrapUsage trapUsage, DefenseUsage defenseUsage) {
        this.pid = pid;
        this.raceStartTime = raceStartTime;
        this.raceTime = raceTime;
        this.racePos = racePos;
        this.mapRaced = mapRaced;
        this.characterUsed = characterUsed;
        this.boostStat = boostStat;
        this.offenseStat = offenseStat;
        this.trapUsage = trapUsage;
        this.collisionWithPlayer = collisionWithPlayer;
        this.defenseUsage = defenseUsage;
    }


    public DefenseUsage getDefenseUsage() {
        return defenseUsage;
    }


    public void setDefenseUsage(DefenseUsage defenseUsage) {
        this.defenseUsage = defenseUsage;
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
     * Get the map raced
     * @return id of map raced
     */
    public int getMapRaced() {
        return mapRaced;
    }


    /**
     * Get the character usage
     * @return which character used
     */
    public int getCharacterUsed() {
        return characterUsed;
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

    public void setRacePos(int racePos) {
        this.racePos = racePos;
    }


    public void setMapRaced(int mapRaced) {
        this.mapRaced = mapRaced;
    }


    public void setCharacterUsed(int characterUsed) {
        this.characterUsed = characterUsed;
    }


    public int getCollisionWithPlayer() {
        return collisionWithPlayer;
    }


    public void setCollisionWithPlayer(int collisionWithPlayer) {
        this.collisionWithPlayer = collisionWithPlayer;
    }


    public int getCollisionWithWall() {
        return collisionWithWall;
    }


    public void setCollisionWithWall(int collisionWithWall) {
        this.collisionWithWall = collisionWithWall;
    }


    public int getFelloffmap() {
        return felloffmap;
    }


    public void setFelloffmap(int felloffmap) {
        this.felloffmap = felloffmap;
    }



    /**
     * String formatting for the race log
     */
    @Override
    public String toString() {
        return String.format(TO_STRING_FORMAT, this.pid, this.raceStartTime,
        raceTime, this.mapRaced, this.racePos, this.characterUsed, 
        this.collisionWithPlayer, this.collisionWithWall, this.felloffmap,this.boostStat, this.offenseStat, this.trapUsage);
    }

    /**
     * New method to check if the race log are the same
     * 
     * Checking: player ID, race start time, race run time, race position
     * Map raced on and which character used
     * @param obj comparator (don't care if that's a real word, you know what I mean)
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
