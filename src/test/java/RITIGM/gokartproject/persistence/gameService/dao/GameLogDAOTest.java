package RITIGM.gokartproject.persistence.gameService.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.util.ArrayList;

import java.sql.Timestamp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import RITIGM.gokartproject.ReflectUtils;
import RITIGM.gokartproject.model.RaceLog;
import RITIGM.gokartproject.model.usage.BoostUsage;
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
     * Setting up a new mock connection and the trap usage for the class before each test case
     */
    @BeforeEach
    void init(){
        // Setting up the connectio
        mockConn = mock(Connection.class);
        testDAO = new GameLogDAO();

        // Putting the mock connection to the private class field
        ReflectUtils.setField(this.testDAO, "conn", mockConn);

        // Init sample racelog class
        BoostUsage boost = new BoostUsage(1, 2, 3);
        OffenseUsage offense = new OffenseUsage(1, 2);
        TrapUsage trap = new TrapUsage(1, 2);

        sampleEntry = new RaceLog("1", new Timestamp(2), 3, 4, 5,
         6, 7, 8, 9, boost, offense, trap);

    }

    /**
     * Testing adding a new gamelog into the class
     * @throws SQLException if the connection somehow fail
     */
    @Test
    void testAddGameLog() throws SQLException{

        // Testing a successful connection data insertion to the database
        String query = 
            """
            INSERT INTO racelog 
            (pid, racestarttime, racetime, racepos, mapraced, characterused, collisionwithplayers, collisionwithwalls,
            fellofmap,speedboost1,speedboost2,speedboost3, puck1, puck2, oilspill1, oilspill2) 
            VALUES 
            (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);        
            """;
        
        PreparedStatement stmt = mock(PreparedStatement.class);

        when(this.mockConn.prepareStatement(query)).
        thenReturn(stmt);

        when(stmt.executeUpdate()).thenReturn(1);

        assertTrue(this.testDAO.addGameLog(sampleEntry));


        // Testing faill insertion or incorrect amount of insertion to the database
        when(stmt.executeUpdate()).thenReturn(0);
        assertFalse(this.testDAO.addGameLog(sampleEntry));


        // Testing throwing the exception
        when(stmt.executeUpdate()). 
        thenThrow(SQLException.class);

        assertThrows(SQLException.class, () -> this.testDAO.addGameLog(sampleEntry));
    }
    @Test
    void testGetRaceByPlayer() throws SQLException{
        // Testing successful fetching data from the database

        String query = 
            """
            SELECT * FROM racelog WHERE pid = ?;
            """;

        PreparedStatement stmt = mock(PreparedStatement.class);
        ResultSet result = mock(ResultSet.class);
        when(this.mockConn.prepareStatement(query)).thenReturn(stmt);
        when(stmt.executeQuery()). thenReturn(result);
        when(result.next()).thenReturn(true).thenReturn(false);

        // Making mock call to insert values into objects
        when(result.getInt("speedboost1")).
            thenReturn(1);
        when(result.getInt("speedboost2")).
            thenReturn(2);
        when(result.getInt("speedboost3")).
            thenReturn(3);

        when(result.getInt("puck1")).
            thenReturn(1);
        when(result.getInt("puck2")).
            thenReturn(2);

        when(result.getInt("oilspill1")).
            thenReturn(1);
        when(result.getInt("oilspill2")).
            thenReturn(2);

        when(result.getString("pid")).
            thenReturn("1");
        when(result.getTimestamp("racestarttime")).
            thenReturn(new Timestamp(2));
        when(result.getInt("racetime")).
            thenReturn(3);
        when(result.getInt("racepos")).
            thenReturn(4);
        when(result.getInt("mapraced")).
            thenReturn(5);
        when(result.getInt("characterused")).
            thenReturn(6);
        when(result.getInt("collisionwithplayers")).
            thenReturn(7);
        when(result.getInt("collisionwithwalls")).
            thenReturn(8);
        when(result.getInt("fellofmap")).
            thenReturn(9);

        assertEquals(this.testDAO.getRaceByPlayer("1").get(0),
         this.sampleEntry);
    }
    @Test
    void testGetRaceInfo() throws SQLException{
        // Testing successful fetching data from the database
        String query = 
            """
            SELECT * FROM racelog WHERE raceid = ?;
            """;

        PreparedStatement stmt = mock(PreparedStatement.class);
        ResultSet result = mock(ResultSet.class);
        when(this.mockConn.prepareStatement(query)).thenReturn(stmt);
        when(stmt.executeQuery()). thenReturn(result);
        when(result.next()).thenReturn(true);

        // Mocking inserting data into the object
        when(result.getInt("speedboost1")).
            thenReturn(1);
        when(result.getInt("speedboost2")).
            thenReturn(2);
        when(result.getInt("speedboost3")).
            thenReturn(3);

        when(result.getInt("puck1")).
            thenReturn(1);
        when(result.getInt("puck2")).
            thenReturn(2);

        when(result.getInt("oilspill1")).
            thenReturn(1);
        when(result.getInt("oilspill2")).
            thenReturn(2);

        when(result.getString("pid")).
            thenReturn("1");
        when(result.getTimestamp("racestarttime")).
            thenReturn(new Timestamp(2));
        when(result.getInt("racetime")).
            thenReturn(3);
        when(result.getInt("racepos")).
            thenReturn(4);
        when(result.getInt("mapraced")).
            thenReturn(5);
        when(result.getInt("characterused")).
            thenReturn(6);
        when(result.getInt("collisionwithplayers")).
            thenReturn(7);
        when(result.getInt("collisionwithwalls")).
            thenReturn(8);
        when(result.getInt("fellofmap")).
            thenReturn(9);

        assertEquals(this.testDAO.getRaceInfo(1),
         this.sampleEntry);

        // Mocking no object with that ID
        when(result.next()).thenReturn(false);
        assertEquals(this.testDAO.getRaceInfo(1), null);

        // Mocking connection failed
        when(this.mockConn.prepareStatement(query)).thenThrow(SQLException.class);

        assertEquals(this.testDAO.getRaceInfo(1), null);
    }

}
