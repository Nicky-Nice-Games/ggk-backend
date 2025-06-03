package RITIGM.gokartproject.persistence.gameService.dao;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import RITIGM.gokartproject.model.PlayerInfo;

public class PlayerInfoDAOTest {
    private Connection mockConn = null;
    private PlayerInfoDAO playerInfoDAO;

    @BeforeEach
    public void setupFileDAO(){
        mockConn = mock(Connection.class);
        playerInfoDAO = new PlayerInfoDAO();
    }

    @Test
    void testCreateUser() throws SQLException{
        PreparedStatement stmt = mock(PreparedStatement.class);
        ResultSet check = mock(ResultSet.class);

        when(stmt.executeUpdate()).thenReturn(2);
        PlayerInfo player = playerInfoDAO.createUser("test@email.com", "password", "username");
        assertNull(player);
        when(stmt.executeUpdate()).thenReturn(1);
        when(check.next()).thenReturn(false);
        player = playerInfoDAO.createUser("test@email.com", "password", "username");
        assertNull(player);

        when(stmt.executeUpdate()).thenReturn(1);
        when(check.next()).thenReturn(false);



    }

    @Test
    void testCreateUser2() {

    }

    @Test
    void testGetPlayerInfo() {


    }

    @Test
    void testGetPlayerInfoWithUsername() {

    }
}
