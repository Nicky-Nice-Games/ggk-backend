package RITIGM.gokartproject.view.webService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import RITIGM.gokartproject.model.PlayerInfo;
import RITIGM.gokartproject.model.PlayerStat;
import RITIGM.gokartproject.model.RaceLog;
import RITIGM.gokartproject.model.RaceReport;
import RITIGM.gokartproject.model.responseReceiver.CreateUID;
import RITIGM.gokartproject.model.responseReceiver.LoginCreds;
import RITIGM.gokartproject.model.responseReceiver.NoUID;
import RITIGM.gokartproject.model.usage.BoostUsage;
import RITIGM.gokartproject.model.usage.OffenseUsage;
import RITIGM.gokartproject.model.usage.TrapUsage;
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
        //LoginCreds info = new LoginCreds("username", "password");
        
        //case: player successfully retrieved
        when(mockWebPlayerDAO.getPlayerInfoWithUsername("username", "password")).thenReturn(player);
        ResponseEntity<PlayerInfo> response = wpInfoService.getPlayerByUsername("username", "password");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(player, response.getBody());

        //case: player does not exist or wasn't properly retrieved
        when(mockWebPlayerDAO.getPlayerInfoWithUsername("username", "password")).thenReturn(null);
        response = wpInfoService.getPlayerByUsername("username", "password");
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());

        //case: internal server error
        when(mockWebPlayerDAO.getPlayerInfoWithUsername("username", "password")).thenThrow();
        response = wpInfoService.getPlayerByUsername("username", "password");
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNull(response.getBody());

    }

    @Test
    void testGetPlayerDetailInfo() throws SQLException {
        OffenseUsage offense = new OffenseUsage(9,10);
        TrapUsage trap = new TrapUsage(11, 12);
        BoostUsage boost = new BoostUsage(13, 14, 15);
         PlayerStat check = new PlayerStat("1", "2", "3", 4, "5", 6, 7, 8,18,19,
         offense, trap, boost, 16.0, 17.0);

        when(playerStatInterface.getPlayerStat("1")).thenReturn(check);
        ResponseEntity<PlayerStat> response = wpInfoService.getPlayerDetailInfo("1");
        assertEquals(check, response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());

        when(playerStatInterface.getPlayerStat("1")).thenReturn(null);
        response = wpInfoService.getPlayerDetailInfo("1");
        assertNull( response.getBody());
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

        when(playerStatInterface.getPlayerStat("1")).thenThrow();
        response = wpInfoService.getPlayerDetailInfo("1");
        assertNull( response.getBody());
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

    @Test
    void testGetRecentRaces() throws SQLException{
        ResponseEntity<ArrayList<RaceReport>> response;
        ArrayList<RaceReport> logTest = new ArrayList<RaceReport>(5);

        when(mockWebPlayerDAO.getRecentGames("20")).thenReturn(logTest);
        response = wpInfoService.getRecentRaces("20");
        assertEquals(logTest, response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());

        when(mockWebPlayerDAO.getRecentGames("20")).thenThrow(new SQLException());
        response = wpInfoService.getRecentRaces("20");
        assertNull(response.getBody());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    void testGetPlayerTrackData() throws SQLException{
        ResponseEntity<ArrayList<Integer>> response;
        ArrayList<Integer> results = new ArrayList<>(Arrays.asList(1,1));

        when(mockWebPlayerDAO.getSpecificTrackData("20", 4)).thenReturn(results);
        response = wpInfoService.getPlayerTrackData("20", 4);
        assertEquals(results, response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());

        when(mockWebPlayerDAO.getSpecificTrackData("20", 4)).thenReturn(null);
        response = wpInfoService.getPlayerTrackData("20", 4);
        assertNull(response.getBody());
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

        when(mockWebPlayerDAO.getSpecificTrackData("20", 4)).thenThrow();
        response = wpInfoService.getPlayerTrackData("20", 4);
        assertNull(response.getBody());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());

    }
}
