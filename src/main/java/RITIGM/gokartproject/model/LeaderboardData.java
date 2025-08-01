package RITIGM.gokartproject.model;

import java.sql.Timestamp;


/**
 * Template class for getting the leaderboard info
 */
public class LeaderboardData {
    private String pid;
    private int raceID;
    private String username; 
    private Timestamp raceStartTime;
    private int raceTime;
    private double score;
    private int pfp;

    private static final String TO_STRING_FORMAT = "\nSimple Race Log:\r\n" + //
                                                        "\tPID = %s,\r\n" + //
                                                        "\tRace ID = %d,\r\n" + //
                                                        "\tusername = %s,\r\n" + //
                                                        "\tTime Start Race = %s,\r\n" + //
                                                        "\tRace Time = %d,\r\n" + //
                                                        "\tScore = %f,";

    /**
     * Contructor for the returning template for leaderboard
     * @param pid player id
     * @param raceID race id
     * @param username player username
     * @param raceStartTime race start time (as TimeStamp)
     * @param raceTime race time (as integer, in miliseconds)
     */
    public LeaderboardData(String pid, int raceID, String username, Timestamp raceStartTime, int raceTime, double score, int pfp){
        this.pid = pid;
        this.raceID = raceID;
        this.username = username;
        this.raceStartTime = raceStartTime;
        this.raceTime = raceTime;
        this.score = score;
        this.pfp = pfp;
    }

    /**
     * Getter for player id
     * @return player id
     */
    public String getPid() {
        return pid;
    }

    /**
     * setter for player id
     * @param pid player id
     */
    public void setPid(String pid) {
        this.pid = pid;
    }

    /**
     * getter for race id
     * @return race id
     */
    public int getRaceID() {
        return raceID;
    }

    /**
     * setter for race id
     * @param raceID race id
     */
    public void setRaceID(int raceID) {
        this.raceID = raceID;
    }

    /**
     * getter for player username
     * @return player username
     */
    public String getUsername() {
        return username;
    }

    /**
     * setter for player username
     * @param username player username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * getter for race start time
     * @return race start time
     */
    public Timestamp getraceStartTime() {
        return raceStartTime;
    }

    /**
     * setter for race start time
     * @param raceStartTime race start time
     */
    public void setraceStartTime(Timestamp raceStartTime) {
        this.raceStartTime = raceStartTime;
    }

    /**
     * getter for race time
     * @return race time (in miliseconds)
     */
    public int getRaceTime() {
        return raceTime;
    }

    /**
     * setter for race time
     * @param raceTime race time (in miliseconds)
     */
    public void setRaceTime(int raceTime) {
        this.raceTime = raceTime;
    }

    

    /**
     * 
     * @return
     */
    public double getScore() {
        return score;
    }

    /**
     * 
     * @param score
     */
    public void setScore(double score) {
        this.score = score;
    }

    
    /**
     * 
     * @return
     */
    public int getPfp() {
        return pfp;
    }

    /**
     * 
     * @param pfp
     */
    public void setPfp(int pfp) {
        this.pfp = pfp;
    }

    /**
     * Tostring method for leaderboard data
     */
    @Override
    public String toString(){
        return String.format(TO_STRING_FORMAT, pid,raceID,username, raceStartTime, raceTime, score);
    }
                                    
}
