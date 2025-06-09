package RITIGM.gokartproject.persistence.webService.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import RITIGM.gokartproject.ReflectUtils;
import RITIGM.gokartproject.model.LeaderboardData;
import RITIGM.gokartproject.model.RaceLog;
import RITIGM.gokartproject.persistence.gameService.dao.GameLogDAO;

public class LeaderboardDAOTest {
    Connection mockConn;
    LeaderboardDAO leaderboardDAO;

    /**
     * Init the mock call for the leaderboard
     */
    @BeforeEach
    void setupFiles(){
        this.mockConn = mock(Connection.class);
        this.leaderboardDAO = new LeaderboardDAO();
        
        ReflectUtils.setField(this.leaderboardDAO, "conn", mockConn);
    }


    /**
     * Testing the get top leaderboard for a certain map
     * @throws SQLException
     */
    @Test
    void testGetMapLeaderboard() throws SQLException{
        PreparedStatement stmt = Mockito.mock(PreparedStatement.class);
        ResultSet data = Mockito.mock(ResultSet.class);
        LeaderboardData test = new LeaderboardData("1", 0, "2", null, 1);

        String query =
            """
            SELECT
                p.pid, racelog.raceid, p.username, racelog.racestarttime,MIN(racelog.racetime) AS leaderboardtime, mapraced
            FROM racelog
                INNER JOIN gokart.players p on racelog.pid = p.pid
            WHERE mapraced = ?
            GROUP BY p.pid
            ORDER BY leaderboardtime;        
            """;

        when(this.mockConn.prepareStatement(query)).thenReturn(stmt);
        when(stmt.executeQuery()).thenReturn(data);

        when(data.next()).thenReturn(true).thenReturn(false);
        when(data.getString("pid")).thenReturn("1");
        when(data.getInt("raceid")).thenReturn(1);
        when(data.getString("username")).thenReturn("2");
        when(data.getTimestamp("racestarttime")).thenReturn(null);
        when(data.getTime("leaderboardtime")).thenReturn(null);

        assertEquals(this.leaderboardDAO.getMapLeaderboard(1).get(0).getPid(), test.getPid());
    }


    
}
