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
import java.util.Date;
import java.sql.Time;
import java.sql.Timestamp;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import RITIGM.gokartproject.ReflectUtils;
import RITIGM.gokartproject.model.RaceLog;
import RITIGM.gokartproject.model.usage.BoostUsage;
import RITIGM.gokartproject.model.usage.CollisionStat;
import RITIGM.gokartproject.model.usage.OffenseUsage;
import RITIGM.gokartproject.model.usage.TrapUsage;
import ch.qos.logback.core.testUtil.MockInitialContext;

public class GameLogDAOTest {
    Connection mockConn = null;
    GameLogDAO testDAO = null;
    RaceLog sampleEntry = null;


    @BeforeEach
    public void setUpFileDAO(){
        this.testDAO = new GameLogDAO();
        this.mockConn = mock(Connection.class);
        ReflectUtils.setField(this.testDAO, "conn", mockConn);

        BoostUsage boostTest = new BoostUsage(1, 2, 3, 4);
        CollisionStat collisiontest = new CollisionStat(1, 2);
        OffenseUsage offenseTest = new OffenseUsage(1, 2, 3, 4);
        TrapUsage trapTest = new TrapUsage(1, 4, 2, 3);

        Timestamp raceStartTime = Timestamp.valueOf("2025-06-02 15:23:14.0");
        Time raceTime = Time.valueOf("01:22:30"); 

        sampleEntry = new RaceLog("26ec3c183fe511f08cc9ac1f6bbcd350",
        raceStartTime, raceTime, 1, 2, 4, 
        boostTest, collisiontest, offenseTest, trapTest);
    }


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

        String queryInsert = "INSERT INTO racelog (pid, racestarttime, racetime, racepos, mapraced, characterused) VALUES (?,?,?,?,?,?);"; 
                            
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

    // @Test
    // void testWrongEntryCount1(){
    //     try {
    //     PreparedStatement stmtUpdateMainLog = Mockito.mock(PreparedStatement.class);
    //     ResultSet resultSet = Mockito.mock(ResultSet.class);
    //     resultSet.moveToInsertRow();
    //     resultSet.updateInt("raceId", 1);

    //     String queryInsert = 
    //                         """
    //                         INSERT INTO racelog
    //                             (pid, racestarttime, racetime, racepos, mapraced, characterused)
    //                         VALUES
    //                             ('26ec3c183fe511f08cc9ac1f6bbcd350','2025-06-03 14:03:01.122','01:22:30',1,2,4);
    //                         """;
    //     when(this.mockConn.prepareStatement(queryInsert))
    //     .thenReturn(stmtUpdateMainLog);
    //     when(stmtUpdateMainLog.executeUpdate()).thenReturn(100000);


    //     assertFalse(this.testDAO.addGameLog(this.sampleEntry));

    //     } catch (SQLException e) {
    //         System.out.println("The test had failed somehow: " + e);
    //     }
    // }

    // @Test
    // void testWrongEntryCount2() throws SQLException{
    //     PreparedStatement stmtUpdateMainLog = Mockito.mock(PreparedStatement.class);
    //     PreparedStatement stmtGetRaceID = Mockito.mock(PreparedStatement.class);
    //     PreparedStatement stmtBoostStat = Mockito.mock(PreparedStatement.class);
    //     ResultSet resultSet = Mockito.mock(ResultSet.class);
    //     resultSet.moveToInsertRow();
    //     resultSet.updateInt("raceId", 1);

    //     String queryInsert = 
    //                         """
    //                         INSERT INTO racelog
    //                             (pid, racestarttime, racetime, racepos, mapraced, characterused)
    //                         VALUES
    //                             ('26ec3c183fe511f08cc9ac1f6bbcd350','2025-06-03 14:03:01.122','01:22:30',1,2,4);
    //                         """;
    //     when(this.mockConn.prepareStatement(queryInsert))
    //     .thenReturn(stmtUpdateMainLog);
    //     when(stmtUpdateMainLog.executeUpdate()).thenReturn(1);

    //     when(this.mockConn.prepareStatement("SELECT LAST_INSERT_ID() as 'raceId' from racelog LIMIT 1;"))
    //     .thenReturn(stmtGetRaceID);
    //     when(stmtGetRaceID.executeQuery()).thenReturn(resultSet);

    //     when(this.mockConn.prepareStatement("INSERT INTO boostatt VALUES (7,1,2,3,4);"))
    //     .thenReturn(stmtBoostStat);
    //     when(stmtBoostStat.executeUpdate()).thenReturn(2);
    //     assertFalse(this.testDAO.addGameLog(this.sampleEntry));
    // }

    // @Test
    // void testGetRaceByPlayer() {

    // }

    // @Test
    // void testGetRaceInfo() {

    // }


}
