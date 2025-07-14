package RITIGM.gokartproject.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Timestamp;

import org.junit.jupiter.api.Test;

public class LeaderboardDataTest {
    private String expected_pid = "20";
    private int expected_raceID = 10;
    private String expected_username = "user"; 
    private Timestamp expected_raceStartTime = new Timestamp(10);
    private int expected_raceTime = 10;
    private double expected_score = 2;
    private int expected_pfp = 1;

    private LeaderboardData boardData = new LeaderboardData(expected_pid, expected_raceID, expected_username, expected_raceStartTime, expected_raceTime, expected_score, expected_pfp);

    @Test
    void testGetPid() {
        assertEquals(expected_pid, boardData.getPid());
    }

    @Test
    void testGetRaceID() {
        assertEquals(expected_raceID, boardData.getRaceID());
    }

    @Test
    void testGetRaceTime() {
        assertEquals(expected_raceTime, boardData.getRaceTime());
    }

    @Test
    void testGetUsername() {
        assertEquals(expected_username, boardData.getUsername());
    }

    @Test
    void testGetraceStartTime() {
        assertEquals(expected_raceStartTime, boardData.getraceStartTime());
    }

    

    @Test
    void testSetPid() {
        expected_pid = "21";
        boardData.setPid(expected_pid);
        assertEquals(expected_pid, boardData.getPid());
    }

    @Test
    void testSetRaceID() {
        expected_raceID = 111;
        boardData.setRaceID(expected_raceID);
        assertEquals(expected_raceID, boardData.getRaceID());
    }

    @Test
    void testSetRaceTime() {
        expected_raceTime = 21;
        boardData.setRaceTime(expected_raceTime);
        assertEquals(expected_raceTime, boardData.getRaceTime());
    }

    @Test
    void testSetUsername() {
        expected_username = "UN.OWEN was her????";
        boardData.setUsername(expected_username);
        assertEquals(expected_username, boardData.getUsername());
    }

    @Test
    void testSetraceStartTime() {
        expected_raceStartTime = new Timestamp(200);
        boardData.setraceStartTime(expected_raceStartTime);
        assertEquals(expected_raceStartTime, boardData.getraceStartTime());
    }

    @Test
    void testToString() {
        String expected_string = "\nSimple Race Log:\r\n" + //
                                "\tPID = " + expected_pid +",\r\n" + //
                                "\tRace ID = " + expected_raceID + ",\r\n" + //
                                "\tusername = "+ expected_username +",\r\n" + //
                                "\tTime Start Race = "+ expected_raceStartTime + ",\r\n" + //
                                "\tRace Time = "+ expected_raceTime +",\r\n" + //
                                "\tScore = "+String.format("%f",expected_score)+",";

        
        assertEquals(expected_string, boardData.toString());

    }

    @Test
    void testGetScore() {
        assertEquals(expected_score, boardData.getScore());
    }

    @Test
    void testSetScore() {
        boardData.setScore(1.4);
        assertEquals(1.4, boardData.getScore());
    }
}
