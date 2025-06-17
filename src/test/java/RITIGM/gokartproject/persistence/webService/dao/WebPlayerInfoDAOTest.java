package RITIGM.gokartproject.persistence.webService.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import RITIGM.gokartproject.ReflectUtils;
import RITIGM.gokartproject.model.PlayerInfo;
import RITIGM.gokartproject.model.RaceLog;
import RITIGM.gokartproject.model.RaceReport;
import RITIGM.gokartproject.model.usage.BoostUsage;
import RITIGM.gokartproject.model.usage.OffenseUsage;
import RITIGM.gokartproject.model.usage.TrapUsage;

public class WebPlayerInfoDAOTest {
    private Connection mockConn = null;
    private WebPlayerInfoDAO wpInfoDAO;

    @BeforeEach
    public void setupFileDAO(){
        mockConn = mock(Connection.class);
        wpInfoDAO = new WebPlayerInfoDAO();

        ReflectUtils.setField(this.wpInfoDAO, "conn", mockConn);
    }


    @Test
    void testCreateUser() throws SQLException{
        PreparedStatement stmt = mock(PreparedStatement.class);
        PreparedStatement stmtCheck = mock(PreparedStatement.class);
        ResultSet resultSet = mock(ResultSet.class);
        String query = "INSERT INTO players (Email, Password, username) VALUE (?, ?, ?);";
        String queryLookUp = "SELECT * FROM players WHERE Email = ? AND Password = ? AND username = ?;";

        when(mockConn.prepareStatement(query)).thenReturn(stmt);
        when(mockConn.prepareStatement(queryLookUp)).thenReturn(stmtCheck);

        //Case: player creation failed
        when(stmt.executeUpdate()).thenReturn(1);
        when(stmtCheck.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(false);
        PlayerInfo player = wpInfoDAO.createUser("test@email.com", "password", "username");
        assertNull(player);

        //case: player creation successful
        when(stmt.executeUpdate()).thenReturn(1);
        when(stmtCheck.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true);
        when(resultSet.getString("pid")).thenReturn("20");
        when(resultSet.getString("Email")).thenReturn("test@email.com");
        when(resultSet.getString("Password")).thenReturn("password");
        when(resultSet.getInt("uid")).thenReturn(-1);
        when(resultSet.getString("username")).thenReturn("username");
        player = wpInfoDAO.createUser("test@email.com", "password", "username");
        assertEquals("20", player.getPid());
        assertEquals("test@email.com", player.getEmail());
        assertEquals("password", player.getPw());
        assertEquals("username", player.getUsername());
    }

    @Test
    void testCreateUser2() throws SQLException {
        PreparedStatement stmt = mock(PreparedStatement.class);
        PreparedStatement stmtCheck = mock(PreparedStatement.class);
        ResultSet resultSet = mock(ResultSet.class);
        String query = "INSERT INTO players (Email, Password, username, uid) VALUE (?, ?, ?, ?);";
        String queryLookUp = "SELECT * FROM players WHERE Email = ? AND Password = ? AND username = ? AND uid = ?;";

        when(mockConn.prepareStatement(query)).thenReturn(stmt);
        when(mockConn.prepareStatement(queryLookUp)).thenReturn(stmtCheck);

        //creation failed
        when(stmt.executeUpdate()).thenReturn(1);
        when(stmtCheck.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(false);
        PlayerInfo player = wpInfoDAO.createUser("test@email.com", "password",1234, "username");
        assertNull(player);

        //creation succesful
        when(stmt.executeUpdate()).thenReturn(1);
        when(stmtCheck.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true);
        when(resultSet.getString("pid")).thenReturn("20");
        when(resultSet.getString("Email")).thenReturn("test@email.com");
        when(resultSet.getString("Password")).thenReturn("password");
        when(resultSet.getInt("uid")).thenReturn(1234);
        when(resultSet.getString("username")).thenReturn("username");
        player = wpInfoDAO.createUser("test@email.com", "password",1234, "username");
        assertEquals("20", player.getPid());
        assertEquals("test@email.com", player.getEmail());
        assertEquals("password", player.getPw());
        assertEquals(1234, player.getUid());
        assertEquals("username", player.getUsername());
    }

    @Test
    void testGetPlayerInfo() throws SQLException {
        PreparedStatement stmt = mock(PreparedStatement.class);
        ResultSet resultSet = mock(ResultSet.class);
        String query = "SELECT * FROM players WHERE pid = ?";

        when(mockConn.prepareStatement(query)).thenReturn(stmt);

        //case: retreival failed
        when(stmt.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(false);
        PlayerInfo player = wpInfoDAO.getPlayerInfo("20");
        assertNull(player);

        //case: retrieval successful
        when(stmt.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true);
        when(resultSet.getString("pid")).thenReturn("20");
        when(resultSet.getString("Email")).thenReturn("test@email.com");
        when(resultSet.getString("Password")).thenReturn("password");
        when(resultSet.getInt("uid")).thenReturn(1234);
        when(resultSet.getString("username")).thenReturn("username");
        player = wpInfoDAO.getPlayerInfo("20");
        assertEquals("20", player.getPid());
        assertEquals("test@email.com", player.getEmail());
        assertEquals("password", player.getPw());
        assertEquals(1234, player.getUid());
        assertEquals("username", player.getUsername());
    }

    @Test
    void testGetPlayerInfoWithUsername() throws SQLException{
        PreparedStatement stmt = mock(PreparedStatement.class);
        ResultSet resultSet = mock(ResultSet.class);
        String query = "SELECT * FROM players WHERE username = ? AND Password = ?;";

        when(mockConn.prepareStatement(query)).thenReturn(stmt);

        //case: retreival failed
        when(stmt.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(false);
        PlayerInfo player = wpInfoDAO.getPlayerInfoWithUsername("username", "password");
        assertNull(player);

        //Case: reteival successful
        when(stmt.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true);
        when(resultSet.getString("pid")).thenReturn("20");
        when(resultSet.getString("Email")).thenReturn("test@email.com");
        when(resultSet.getString("Password")).thenReturn("password");
        when(resultSet.getInt("uid")).thenReturn(1234);
        when(resultSet.getString("username")).thenReturn("username");
        player = wpInfoDAO.getPlayerInfoWithUsername("username", "password");
        assertEquals("20", player.getPid());
        assertEquals("test@email.com", player.getEmail());
        assertEquals("password", player.getPw());
        assertEquals(1234, player.getUid());
        assertEquals("username", player.getUsername());
    }


    @Test
    void testVerifyEmail() throws SQLException{
        PreparedStatement stmt = mock(PreparedStatement.class);
        ResultSet resultSet = mock(ResultSet.class);
        boolean result;

        String query = "SELECT EXISTS (\n" + //
                        "  SELECT 1\n" + //
                        "  FROM players\n" + //
                        "  WHERE Email = ?\n" + //
                        ") AS EmailExists;";

        when(mockConn.prepareStatement(query)).thenReturn(stmt);
        when(stmt.executeQuery()).thenReturn(resultSet);


        //Email already exists in use
        when(resultSet.next()).thenReturn(true);
        when(resultSet.getBoolean("EmailExists")).thenReturn(true);
        result = wpInfoDAO.verifyEmail("test@email.com");
        assertEquals(true, result);

        //email does not exist in use
        when(resultSet.next()).thenReturn(true);
        when(resultSet.getBoolean("EmailExists")).thenReturn(false);
        result = wpInfoDAO.verifyEmail("test@email.com");
        assertEquals(false, result);

        //no emails recorded
        when(resultSet.next()).thenReturn(false);
        result = wpInfoDAO.verifyEmail("test@email.com");
        assertEquals(false, result);


    }


    @Test
    void testGetRecentGames() throws SQLException {
        PreparedStatement stmt = mock(PreparedStatement.class);
        ResultSet check = mock(ResultSet.class);
        Timestamp stamp = new Timestamp(20);
        RaceReport log = new RaceReport(new RaceLog("20", 
         stamp, 
         0, 0, 0, 0, 
         0, 0, 0,
         new BoostUsage(0, 0, 0), 
         new OffenseUsage(0, 0), 
         new TrapUsage(0, 0)),
        0, 0);
        ArrayList<RaceReport> expected = new ArrayList<RaceReport>(5);
        expected.add(log);
        ArrayList<RaceReport> listLogs;

        String query = "SELECT *\n" + //
                        "FROM racelog\n" + //
                        "WHERE pid = ?\n" + //
                        "ORDER BY racestarttime, raceid DESC\n" + //
                        "LIMIT 5;";

        when(mockConn.prepareStatement(query)).thenReturn(stmt);
        when(stmt.executeQuery()).thenReturn(check);
        when(check.next()).thenReturn(true).thenReturn(false);
        when(check.getString("pid")).thenReturn("20");
        when(check.getTimestamp("racestarttime")).thenReturn(stamp);
        listLogs = wpInfoDAO.getRecentGames("20");
        assertEquals(expected, listLogs);

        when(check.next()).thenReturn(false);
        listLogs = wpInfoDAO.getRecentGames("20");
        assertEquals(new ArrayList<RaceLog>(5), listLogs);
        
    }


    @Test
    void testGetSpecificTrackData() throws SQLException{
        PreparedStatement stmt1 = mock(PreparedStatement.class);
        PreparedStatement stmt2 = mock(PreparedStatement.class);
        ResultSet check = mock(ResultSet.class);
        ResultSet check2 = mock(ResultSet.class);

        String query1 = "SELECT MIN(racelog.racepos) as topPos\n" + //
                        "FROM racelog\n" + //
                        "WHERE pid = ?\n" + //
                        "AND mapraced = ?;";
        String query2 = "SELECT MIN(racelog.racetime) as fastestTime\n" + //
                        "FROM racelog\n" + //
                        "WHERE pid = ?\n" + //
                        "AND mapraced = ?;";
        
        ArrayList<Integer> results = new ArrayList<>(Arrays.asList(1,1));

        //Both information points exist
        when(mockConn.prepareStatement(query1)).thenReturn(stmt1);
        when(stmt1.executeQuery()).thenReturn(check);
        when(mockConn.prepareStatement(query2)).thenReturn(stmt2);
        when(stmt2.executeQuery()).thenReturn(check2);
        when(check.next()).thenReturn(true);
        when(check2.next()).thenReturn(true);
        when(check.getInt("topPos")).thenReturn(1);
        when(check2.getInt("fastestTime")).thenReturn(1);
        ArrayList<Integer> actual = wpInfoDAO.getSpecificTrackData("20", 4);
        assertEquals(results, actual);

        //one information point (fastest time) missing
        when(mockConn.prepareStatement(query1)).thenReturn(stmt1);
        when(stmt1.executeQuery()).thenReturn(check);
        when(mockConn.prepareStatement(query2)).thenReturn(stmt2);
        when(stmt2.executeQuery()).thenReturn(check2);
        when(check.next()).thenReturn(true);
        when(check2.next()).thenReturn(false);
        when(check.getInt("topPos")).thenReturn(1);
        actual = wpInfoDAO.getSpecificTrackData("20", 4);
        assertNull(actual);

        //both information points missing
        when(mockConn.prepareStatement(query1)).thenReturn(stmt1);
        when(stmt1.executeQuery()).thenReturn(check);
        when(mockConn.prepareStatement(query2)).thenReturn(stmt2);
        when(stmt2.executeQuery()).thenReturn(check2);
        when(check.next()).thenReturn(false);
        when(check2.next()).thenReturn(false);
        actual = wpInfoDAO.getSpecificTrackData("20", 4);
        assertNull(actual);

    }
}
