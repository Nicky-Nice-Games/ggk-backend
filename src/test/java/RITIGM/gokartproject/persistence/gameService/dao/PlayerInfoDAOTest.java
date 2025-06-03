package RITIGM.gokartproject.persistence.gameService.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import RITIGM.gokartproject.ReflectUtils;
import RITIGM.gokartproject.model.PlayerInfo;

public class PlayerInfoDAOTest {
    private Connection mockConn = null;
    private PlayerInfoDAO playerInfoDAO;

    @BeforeEach
    public void setupFileDAO(){
        mockConn = mock(Connection.class);
        playerInfoDAO = new PlayerInfoDAO();

        ReflectUtils.setField(this.playerInfoDAO, "conn", mockConn);
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

        when(stmt.executeUpdate()).thenReturn(1);
        when(stmtCheck.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(false);
        PlayerInfo player = playerInfoDAO.createUser("test@email.com", "password", "username");
        assertNull(player);
        when(stmt.executeUpdate()).thenReturn(1);
        when(stmtCheck.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true);
        when(resultSet.getString("pid")).thenReturn("20");
        when(resultSet.getString("Email")).thenReturn("test@email.com");
        when(resultSet.getString("Password")).thenReturn("password");
        when(resultSet.getInt("uid")).thenReturn(-1);
        when(resultSet.getString("username")).thenReturn("username");
        player = playerInfoDAO.createUser("test@email.com", "password", "username");
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

        when(stmt.executeUpdate()).thenReturn(1);
        when(stmtCheck.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(false);
        PlayerInfo player = playerInfoDAO.createUser("test@email.com", "password",1234, "username");
        assertNull(player);

        when(stmt.executeUpdate()).thenReturn(1);
        when(stmtCheck.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true);
        when(resultSet.getString("pid")).thenReturn("20");
        when(resultSet.getString("Email")).thenReturn("test@email.com");
        when(resultSet.getString("Password")).thenReturn("password");
        when(resultSet.getInt("uid")).thenReturn(1234);
        when(resultSet.getString("username")).thenReturn("username");
        player = playerInfoDAO.createUser("test@email.com", "password",1234, "username");
        assertEquals("20", player.getPid());
        assertEquals("test@email.com", player.getEmail());
        assertEquals("password", player.getPw());
        assertEquals(1234, player.getUid());
        assertEquals("username", player.getUsername());
    }

    @Test
    void testGetPlayerInfo() throws SQLException{
        PreparedStatement stmt = mock(PreparedStatement.class);
        ResultSet resultSet = mock(ResultSet.class);
        String query = "SELECT * FROM players WHERE pid = ?";

        when(mockConn.prepareStatement(query)).thenReturn(stmt);

        when(stmt.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(false);
        PlayerInfo player = playerInfoDAO.getPlayerInfo("20");
        assertNull(player);

        when(stmt.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true);
        when(resultSet.getString("pid")).thenReturn("20");
        when(resultSet.getString("Email")).thenReturn("test@email.com");
        when(resultSet.getString("Password")).thenReturn("password");
        when(resultSet.getInt("uid")).thenReturn(1234);
        when(resultSet.getString("username")).thenReturn("username");
        player = playerInfoDAO.getPlayerInfo("20");
        assertEquals("20", player.getPid());
        assertEquals("test@email.com", player.getEmail());
        assertEquals("password", player.getPw());
        assertEquals(1234, player.getUid());
        assertEquals("username", player.getUsername());

    }

    @Test
    void testGetPlayerInfoWithUsername() throws SQLException {
        PreparedStatement stmt = mock(PreparedStatement.class);
        ResultSet resultSet = mock(ResultSet.class);
        String query = "SELECT * FROM players WHERE username = ? AND Password = ?;";

        when(mockConn.prepareStatement(query)).thenReturn(stmt);

        when(stmt.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(false);
        PlayerInfo player = playerInfoDAO.getPlayerInfoWithUsername("username", "password");
        assertNull(player);

        when(stmt.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true);
        when(resultSet.getString("pid")).thenReturn("20");
        when(resultSet.getString("Email")).thenReturn("test@email.com");
        when(resultSet.getString("Password")).thenReturn("password");
        when(resultSet.getInt("uid")).thenReturn(1234);
        when(resultSet.getString("username")).thenReturn("username");
        player = playerInfoDAO.getPlayerInfoWithUsername("username", "password");
        assertEquals("20", player.getPid());
        assertEquals("test@email.com", player.getEmail());
        assertEquals("password", player.getPw());
        assertEquals(1234, player.getUid());
        assertEquals("username", player.getUsername());
    }
}
