package RITIGM.gokartproject.persistence.gameService.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

/**
 * Test for playerInfoDAO in persistence
 * 
 * @author Diego Velez
 */
public class PlayerInfoDAOTest {
    private Connection mockConn = null;
    private PlayerInfoDAO playerInfoDAO;

    @BeforeEach
    public void setupFileDAO(){
        mockConn = mock(Connection.class);
        playerInfoDAO = new PlayerInfoDAO();

        ReflectUtils.setField(this.playerInfoDAO, "conn", mockConn);
    }

    /**
     * Test for Create user without UID
     * @throws SQLException
     */
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
        PlayerInfo player = playerInfoDAO.createUser("test@email.com", "password", "username");
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
        player = playerInfoDAO.createUser("test@email.com", "password", "username");
        assertEquals("20", player.getPid());
        assertEquals("test@email.com", player.getEmail());
        assertEquals("password", player.getPw());
        assertEquals("username", player.getUsername());



    }

    /**
     * Test for Creating user with UID
     * @throws SQLException
     */
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
        PlayerInfo player = playerInfoDAO.createUser("test@email.com", "password",1234, "username");
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
        player = playerInfoDAO.createUser("test@email.com", "password",1234, "username");
        assertEquals("20", player.getPid());
        assertEquals("test@email.com", player.getEmail());
        assertEquals("password", player.getPw());
        assertEquals(1234, player.getUid());
        assertEquals("username", player.getUsername());
    }

    /**
     * Test for retriveal of player information using player ID
     * @throws SQLException
     */
    @Test
    void testGetPlayerInfo() throws SQLException{
        PreparedStatement stmt = mock(PreparedStatement.class);
        ResultSet resultSet = mock(ResultSet.class);
        String query = "SELECT * FROM players WHERE pid = ?";

        when(mockConn.prepareStatement(query)).thenReturn(stmt);

        //case: retreival failed
        when(stmt.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(false);
        PlayerInfo player = playerInfoDAO.getPlayerInfo("20");
        assertNull(player);

        //case: retrieval successful
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

    /**
     * Test for retreival of player information using username and password
     * @throws SQLException
     */
    @Test
    void testGetPlayerInfoWithUsername() throws SQLException {
        PreparedStatement stmt = mock(PreparedStatement.class);
        ResultSet resultSet = mock(ResultSet.class);
        String query = "SELECT * FROM players WHERE username = ? AND Password = ?;";

        when(mockConn.prepareStatement(query)).thenReturn(stmt);

        //case: retreival failed
        when(stmt.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(false);
        PlayerInfo player = playerInfoDAO.getPlayerInfoWithUsername("username", "password");
        assertNull(player);

        //Case: reteival successful
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
