package RITIGM.gokartproject.persistence.gameService.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.sql.Timestamp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import RITIGM.gokartproject.ReflectUtils;
import RITIGM.gokartproject.model.RaceLog;
import RITIGM.gokartproject.model.usage.BoostUsage;
import RITIGM.gokartproject.model.usage.CollisionStat;
import RITIGM.gokartproject.model.usage.OffenseUsage;
import RITIGM.gokartproject.model.usage.TrapUsage;

/**
 * Tests the usage of GameLogDAO
 * 3 test cases total
 * 
 * @author Peter Dang
 */
public class GameLogDAOTest {
    Connection mockConn;
    GameLogDAO testDAO;
    RaceLog sampleEntry;


    /**
     * Creates object for testing purposes
     * mocks parameter objects 
     * mocks connection
     */
    @BeforeEach
    public void setUpFileDAO(){
        this.mockConn = mock(Connection.class);
        this.testDAO = new GameLogDAO();
        
        ReflectUtils.setField(this.testDAO, "conn", mockConn);

        BoostUsage boostTest = new BoostUsage(1, 2, 3, 4);
        CollisionStat collisiontest = new CollisionStat(1, 2);
        OffenseUsage offenseTest = new OffenseUsage(1, 2, 3, 4);
        TrapUsage trapTest = new TrapUsage(1, 4, 2, 3);

        Timestamp raceStartTime = Timestamp.valueOf("2025-06-02 15:23:14.0");
        int raceTime = 12; 

        sampleEntry = new RaceLog("Checker",
        raceStartTime, raceTime, 1, 2, 4, 
        boostTest, collisiontest, offenseTest, trapTest);
    }


    /**
     * Tests the succesful implementation of adding a game log
     * 1 test case
     * @throws SQLException
     */
    @Test
    void testAddGameLogSuccessful() throws SQLException {
        PreparedStatement stmtUpdateMainLog = Mockito.mock(PreparedStatement.class);
        PreparedStatement stmtGetRaceID = Mockito.mock(PreparedStatement.class);
        PreparedStatement stmtBoostStat = Mockito.mock(PreparedStatement.class);
        PreparedStatement stmtCollision = Mockito.mock(PreparedStatement.class);
        PreparedStatement stmtOffense = Mockito.mock(PreparedStatement.class);
        PreparedStatement stmtTrap = Mockito.mock(PreparedStatement.class);


        ResultSet resultSet = Mockito.mock(ResultSet.class);

        when(resultSet.next()).thenReturn(true);
        when(resultSet.getInt("raceId")).thenReturn(31);

        String queryInsert = 
        "INSERT INTO racelog (pid, racestarttime, racetime, racepos, mapraced, characterused) VALUES (?,?,?,?,?,?);"; 
                            
        when(this.mockConn.prepareStatement(queryInsert))
        .thenReturn(stmtUpdateMainLog);
        when(stmtUpdateMainLog.executeUpdate()).thenReturn(1);
            
        when(this.mockConn.prepareStatement("SELECT LAST_INSERT_ID() as 'raceId' from racelog LIMIT 1;"))
        .thenReturn(stmtGetRaceID);
        when(stmtGetRaceID.executeQuery()).thenReturn(resultSet);

        when(this.mockConn.prepareStatement("INSERT INTO boostatt VALUES (?,?,?,?,?);"))
        .thenReturn(stmtBoostStat);
        when(stmtBoostStat.executeUpdate()).thenReturn(1);
        
        when(this.mockConn.prepareStatement("INSERT INTO collision VALUES (?,?,?);"))
        .thenReturn(stmtCollision);
        when(stmtCollision.executeUpdate()).thenReturn(1);

        when(this.mockConn.prepareStatement("INSERT INTO offsensestat VALUES(?,?,?,?,?);"))
        .thenReturn(stmtOffense);
        when(stmtOffense.executeUpdate()).thenReturn(1);

        when(this.mockConn.prepareStatement("INSERT INTO trapatt VALUES(?,?,?,?,?);"))
        .thenReturn(stmtTrap);
        when(stmtTrap.executeUpdate()).thenReturn(1);

        assertTrue(this.testDAO.addGameLog(this.sampleEntry));

    }

    /**
     * Tests the retreival of race data from a player ID
     * 1 test case
     * @throws SQLException
     */
    @Test
    void testGetRaceByPlayer() throws SQLException {
        ArrayList<RaceLog> sample = new ArrayList<>();
        sample.add(this.sampleEntry);

        String query = 
        """
        SELECT * from racelog
        INNER JOIN boostatt on racelog.raceid = boostatt.raceid
        INNER JOIN collision on racelog.raceid = collision.raceid
        INNER JOIN offsensestat on racelog.raceid = offsensestat.raceid
        INNER JOIN trapatt on racelog.raceid = trapatt.raceid
        WHERE pid = ?;      
        """;
        PreparedStatement checker = Mockito.mock(PreparedStatement.class);
        ResultSet setCheck = Mockito.mock(ResultSet.class);

        when(this.mockConn.prepareStatement(query)).thenReturn(checker);
        when(checker.executeQuery()).thenReturn(setCheck);

        when(setCheck.next()).thenReturn(true).thenReturn(false);
        

        when(setCheck.getInt("boostitem1")).thenReturn(1);
        when(setCheck.getInt("boostitem2")).thenReturn(2);
        when(setCheck.getInt("boostitem3")).thenReturn(3);
        when(setCheck.getInt("boostitem4")).thenReturn(4);
        when(setCheck.getInt("offenseitem1")).thenReturn(1);
        when(setCheck.getInt("offenseitem2")).thenReturn(2);
        when(setCheck.getInt("offenseitem3")).thenReturn(3);
        when(setCheck.getInt("offenseitem4")).thenReturn(4);
        when(setCheck.getInt("wallcol")).thenReturn(1);
        when(setCheck.getInt("playercol")).thenReturn(2);
        when(setCheck.getInt("trapitem1")).thenReturn(1);
        when(setCheck.getInt("trapitem2")).thenReturn(4);
        when(setCheck.getInt("trapitem3")).thenReturn(2);
        when(setCheck.getInt("trapitem4")).thenReturn(3);
        when(setCheck.getString("racelog.raceid"))
        .thenReturn("Checker");
        when(setCheck.getTimestamp("racestarttime"))
        .thenReturn(Timestamp.valueOf("2025-06-02 15:23:14.0"));
        when(setCheck.getInt("racetime"))
        .thenReturn(12);
        when(setCheck.getInt("racepos")).thenReturn(1);
        when(setCheck.getInt("mapraced")).thenReturn(2);
        when(setCheck.getInt("characterused")).thenReturn(4);

        assertEquals(sample.get(0),
         this.testDAO.getRaceByPlayer("Checker").get(0));
    }

    /**
     * Tests the retreival of race information from a race id
     * 1 tests case
     * @throws SQLException
     */
    @Test
    void testGetRaceInfo() throws SQLException{
        ArrayList<RaceLog> sample = new ArrayList<>();
        sample.add(this.sampleEntry);

        String query = 
        """
            SELECT * from racelog
            INNER JOIN boostatt on racelog.raceid = boostatt.raceid
            INNER JOIN collision on racelog.raceid = collision.raceid
            INNER JOIN offsensestat on racelog.raceid = offsensestat.raceid
            INNER JOIN trapatt on racelog.raceid = trapatt.raceid
            WHERE racelog.raceid = ?;     
            """;
        PreparedStatement checker = Mockito.mock(PreparedStatement.class);
        ResultSet setCheck = Mockito.mock(ResultSet.class);

        when(this.mockConn.prepareStatement(query)).thenReturn(checker);
        when(checker.executeQuery()).thenReturn(setCheck);

        when(setCheck.next()).thenReturn(true).thenReturn(false);
        

        when(setCheck.getInt("boostitem1")).thenReturn(1);
        when(setCheck.getInt("boostitem2")).thenReturn(2);
        when(setCheck.getInt("boostitem3")).thenReturn(3);
        when(setCheck.getInt("boostitem4")).thenReturn(4);
        when(setCheck.getInt("offenseitem1")).thenReturn(1);
        when(setCheck.getInt("offenseitem2")).thenReturn(2);
        when(setCheck.getInt("offenseitem3")).thenReturn(3);
        when(setCheck.getInt("offenseitem4")).thenReturn(4);
        when(setCheck.getInt("wallcol")).thenReturn(1);
        when(setCheck.getInt("playercol")).thenReturn(2);
        when(setCheck.getInt("trapitem1")).thenReturn(1);
        when(setCheck.getInt("trapitem2")).thenReturn(4);
        when(setCheck.getInt("trapitem3")).thenReturn(2);
        when(setCheck.getInt("trapitem4")).thenReturn(3);
        when(setCheck.getString("racelog.raceid"))
        .thenReturn("Checker");
        when(setCheck.getTimestamp("racestarttime"))
        .thenReturn(Timestamp.valueOf("2025-06-02 15:23:14.0"));
        when(setCheck.getInt("racetime"))
        .thenReturn(12);
        when(setCheck.getInt("racepos")).thenReturn(1);
        when(setCheck.getInt("mapraced")).thenReturn(2);
        when(setCheck.getInt("characterused")).thenReturn(4);

        assertEquals(sample.get(0),
         this.testDAO.getRaceInfo(0));
    }


}
