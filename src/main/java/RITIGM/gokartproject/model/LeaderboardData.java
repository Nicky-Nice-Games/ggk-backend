package RITIGM.gokartproject.model;

import java.sql.Time;
import java.sql.Timestamp;

public class LeaderboardData {
    private String pid;
    private int raceID;
    private String username; 
    private Timestamp raceStartTime;
    private Time raceTime;

    private static final String TO_STRING_FORMAT = "\nSimple Race Log:\r\n" + //
                                                        "\tPID = %s,\r\n" + //
                                                        "\tRace ID = %d,\r\n" + //
                                                        "\tusername = %d,\r\n" + //
                                                        "\tTime Start Race = %s,\r\n" + //
                                                        "\tRace Time = %s,";

    public LeaderboardData(String pid, int raceID, String username, Timestamp raceStartTime, Time raceTime){
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

    public Time getRaceTime() {
        return raceTime;
    }

    public void setRaceTime(Time raceTime) {
        this.raceTime = raceTime;
    }

    @Override
    public String toString(){
        return String.format(TO_STRING_FORMAT, pid,raceID,username, raceStartTime, raceTime);
    }
                                    
}
