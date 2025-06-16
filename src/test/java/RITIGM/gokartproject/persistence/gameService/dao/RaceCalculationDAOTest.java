package RITIGM.gokartproject.persistence.gameService.dao;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import RITIGM.gokartproject.ReflectUtils;

public class RaceCalculationDAOTest {

    private Connection mockConn = null;
    private RaceCalculationDAO raceCalculation;

    @BeforeEach
    public void init(){
        mockConn = mock(Connection.class);
        raceCalculation = new RaceCalculationDAO();

        ReflectUtils.setField(this.raceCalculation, "conn", mockConn);
    }

    @Test
    void testProfileRecalculation() throws SQLException{
        String mapQuery = 
            """
            SELECT racelog.score as playscore
            FROM racelog
            WHERE pid = ? and mapraced = ?
            ORDER BY playscore DESC
            LIMIT 5;        
            """;

        PreparedStatement stmtScore = mock(PreparedStatement.class);
        when(this.mockConn.prepareStatement(mapQuery)).thenReturn(stmtScore);
        ResultSet scoreData = mock(ResultSet.class);
        when(stmtScore.executeQuery()).thenReturn(scoreData);

        when(scoreData.next()).thenReturn(true).thenReturn(true).thenReturn(true).thenReturn(true).thenReturn(true).thenReturn(false);
        when(scoreData.getDouble("playscore")).thenReturn(1.0).thenReturn(2.0).thenReturn(3.0).thenReturn(4.0).thenReturn(5.0);


        String setNewScore = 
            """
                UPDATE players
                SET score = ?
                WHERE pid = ?;
            """;
        
        PreparedStatement stmtUpdate = mock (PreparedStatement.class);
        when(this.mockConn.prepareStatement(setNewScore)).thenReturn(stmtUpdate);
        when(stmtUpdate.executeUpdate()).thenReturn(1);

        boolean test = this.raceCalculation.profileRecalculation("1");
        assertTrue(test);
    }
}
