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

    private static final String TO_STRING_FORMAT = "\nSimple Race Log:\r\n" + //
                                                        "\tPID = %s,\r\n" + //
                                                        "\tRace ID = %d,\r\n" + //
                                                        "\tusername = %d,\r\n" + //
                                                        "\tTime Start Race = %s,\r\n" + //
                                                        "\tRace Time = %s,";

    /**
     * Contructor for the returning template for leaderboard
     * @param pid
     * @param raceID
     * @param username
     * @param raceStartTime
     * @param raceTime
     */
    public LeaderboardData(String pid, int raceID, String username, Timestamp raceStartTime, int raceTime){
        this.pid = pid;
        this.raceID = raceID;
        this.username = username;
        this.raceStartTime = raceStartTime;
        this.raceTime = raceTime;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public int getRaceID() {
        return raceID;
    }

    public void setRaceID(int raceID) {
        this.raceID = raceID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Timestamp getraceStartTime() {
        return raceStartTime;
    }

    public void setraceStartTime(Timestamp raceStartTime) {
        this.raceStartTime = raceStartTime;
    }

    public int getRaceTime() {
        return raceTime;
    }

    public void setRaceTime(int raceTime) {
        this.raceTime = raceTime;
    }

    @Override
    public String toString(){
        return String.format(TO_STRING_FORMAT, pid,raceID,username, raceStartTime, raceTime);
    }
                                    
}
