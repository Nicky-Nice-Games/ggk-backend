package RITIGM.gokartproject.view.webService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.SQLException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import RITIGM.gokartproject.model.PlayerInfo;
import RITIGM.gokartproject.model.responseReceiver.CreateUID;
import RITIGM.gokartproject.model.responseReceiver.LoginCreds;
import RITIGM.gokartproject.model.responseReceiver.NoUID;
import RITIGM.gokartproject.persistence.webService.dao.PlayerStatDAO;
import RITIGM.gokartproject.persistence.webService.interfaces.PlayerStatInterface;
import RITIGM.gokartproject.persistence.webService.interfaces.WebPlayerInfoInterface;

public class WebPlayerInfoServiceTest {

    private WebPlayerInfoInterface mockWebPlayerDAO;
    private WebPlayerInfoService wpInfoService;
    private PlayerStatInterface playerStatInterface;
    private PlayerStatDAO playerStatDAO;


    /**
     * Init mock object for the class
     */
    @BeforeEach
    public void setupPlayerInfo(){
        mockWebPlayerDAO = mock(WebPlayerInfoInterface.class);
        this.playerStatInterface = mock(PlayerStatInterface.class);

        this.playerStatDAO = mock(PlayerStatDAO.class);
        this.

        wpInfoService = new WebPlayerInfoService(mockWebPlayerDAO, this.playerStatInterface);
    }

    /**
     * Crete user with no UID
     * @throws SQLException throwing SQL exeception
     */
    @Test
    void testCreateUser() throws SQLException{
        PlayerInfo player = new PlayerInfo("20", "test@email.com", "password", -1, "username");
        NoUID info  = new NoUID("test@email.com", "username", "password");


        when(mockWebPlayerDAO.createUser("test@email.com", "password", "username")).thenReturn(player);
        ResponseEntity<PlayerInfo> response = wpInfoService.createUser(info);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(player, response.getBody());

        when(mockWebPlayerDAO.createUser("test@email.com", "password", "username")).thenReturn(null);
        response = wpInfoService.createUser(info);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNull(response.getBody());

        when(mockWebPlayerDAO.createUser("test@email.com", "password", "username")).thenThrow();
        response = wpInfoService.createUser(info);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNull(response.getBody());
    }

    /**
     * Crete user with UID
     * @throws SQLException throwing SQL exeception
     */
    @Test
    void testCreateUser2() throws SQLException{
        PlayerInfo player = new PlayerInfo("20", "test@email.com", "password", 123456789, "username");
        CreateUID info  = new CreateUID("test@email.com", "username","password",   123456789);


        when(mockWebPlayerDAO.createUser("test@email.com", "password", 123456789, "username")).thenReturn(player);
        ResponseEntity<PlayerInfo> response = wpInfoService.createUser(info);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(player, response.getBody());

        when(mockWebPlayerDAO.createUser("test@email.com", "password",123456789, "username")).thenReturn(null);
        response = wpInfoService.createUser(info);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNull(response.getBody());

        when(mockWebPlayerDAO.createUser("test@email.com", "password",123456789, "username")).thenThrow();
        response = wpInfoService.createUser(info);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNull(response.getBody());
    }

    /**
     * Create get simple player info by PID
     * @throws SQLException
     */
    @Test
    void testGetPlayerByID() throws SQLException{
        PlayerInfo player = new PlayerInfo("20", "test@email.com", "password", -1, "username");
        String id = "20";
        
        //case: player successfully retrieved
        when(mockWebPlayerDAO.getPlayerInfo(id)).thenReturn(player);
        ResponseEntity<PlayerInfo> response = wpInfoService.getPlayerByID(id);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(player, response.getBody());

        //case: player does not exist or wasn't properly retrieved
        when(mockWebPlayerDAO.getPlayerInfo(id)).thenReturn(null);
        response = wpInfoService.getPlayerByID(id);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());

        //case: internal server error
        when(mockWebPlayerDAO.getPlayerInfo(id)).thenThrow();
        response = wpInfoService.getPlayerByID(id);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNull(response.getBody());
    }

    /**
     * Get player info from username and password
     * @throws SQLException
     */
    @Test
    void testGetPlayerByUsername() throws SQLException {
        PlayerInfo player = new PlayerInfo("20", "test@email.com", "password", -1, "username");
        LoginCreds info = new LoginCreds("username", "password");
        
        //case: player successfully retrieved
        when(mockWebPlayerDAO.getPlayerInfoWithUsername(info.getUsername(), info.getPassword())).thenReturn(player);
        ResponseEntity<PlayerInfo> response = wpInfoService.getPlayerByUsername(info);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(player, response.getBody());

        //case: player does not exist or wasn't properly retrieved
        when(mockWebPlayerDAO.getPlayerInfoWithUsername(info.getUsername(), info.getPassword())).thenReturn(null);
        response = wpInfoService.getPlayerByUsername(info);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());

        //case: internal server error
        when(mockWebPlayerDAO.getPlayerInfoWithUsername(info.getUsername(), info.getPassword())).thenThrow();
        response = wpInfoService.getPlayerByUsername(info);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNull(response.getBody());

    }

    @Test
    void testGetPlayerDetailInfo() {
        
    }

    @Test
    void testCheckEmail() throws SQLException {
         ResponseEntity<Boolean> response;

        when(mockWebPlayerDAO.verifyEmail("test@email.com")).thenReturn(true);
        response = wpInfoService.checkEmail("test@email.com");
        assertEquals(true, response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());

        when(mockWebPlayerDAO.verifyEmail("test@email.com")).thenThrow();
        response = wpInfoService.checkEmail("test@email.com");
        assertNull(response.getBody());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        
    }

    @Test
    void testCheckEmail() throws SQLException {
         ResponseEntity<Boolean> response;

        when(mockWebPlayerDAO.verifyEmail("test@email.com")).thenReturn(true);
        response = wpInfoService.checkEmail("test@email.com");
        assertEquals(true, response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());

        when(mockWebPlayerDAO.verifyEmail("test@email.com")).thenThrow();
        response = wpInfoService.checkEmail("test@email.com");
        assertNull(response.getBody());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        
    }
}
