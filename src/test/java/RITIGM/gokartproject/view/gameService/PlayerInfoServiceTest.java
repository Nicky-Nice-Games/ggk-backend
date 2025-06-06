package RITIGM.gokartproject.view.gameService;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import RITIGM.gokartproject.persistence.gameService.dao.PlayerInfoDAO;
import RITIGM.gokartproject.persistence.gameService.interfaces.PlayerInfoInterface;
import RITIGM.gokartproject.model.PlayerInfo;

/**
 * Test for playerInfoService in view
 * 12 total test cases
 * 
 * @author Diego Velez
 */
public class PlayerInfoServiceTest {
    private PlayerInfoInterface mockPlayerDAO;
    private PlayerInfoService  playerInfoService;

    /**
     * Creates a mock object to test with
     * with mocked parameters
     */
    @BeforeEach
    public void setupPlayerInfoDAO(){
        mockPlayerDAO = mock(PlayerInfoInterface.class);
        playerInfoService = new PlayerInfoService(mockPlayerDAO);
    }

    /**
     * Tests user creation without uid
     * @throws Exception
     * 3 test cases
     */
    // @Test
    // void testCreateUser() throws Exception {
    //     PlayerInfo player = new PlayerInfo("20", "test@email.com", "password", -1, "username");

    //     //Case: creation succesful
    //     when(mockPlayerDAO.createUser("test@email.com", "password", "username")).thenReturn(player);
    //     ResponseEntity<PlayerInfo> response = playerInfoService.createUser("test@email.com", "password", "username");
    //     assertEquals(HttpStatus.OK, response.getStatusCode());
    //     assertEquals(player, response.getBody());

    //     //Case: creation unsuccesful
    //     when(mockPlayerDAO.createUser("test@email.com", "password", "username")).thenReturn(null);
    //     response = playerInfoService.createUser("test@email.com", "password", "username");
    //     assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    //     assertNull(response.getBody());

    //     //Case: Internal server error
    //     when(mockPlayerDAO.createUser("test@email.com", "password", "username")).thenThrow();
    //     response = playerInfoService.createUser("test@email.com", "password", "username");
    //     assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    //     assertNull(response.getBody());
    // }

    // /**
    //  * Tests user creation with UID
    //  * @throws Exception
    //  * 3 tests cases
    //  */
    // @Test
    // void testCreateUser2() throws Exception {
    //     PlayerInfo player = new PlayerInfo("20", "test@email.com", "password", 123456789, "username");

    //     //Case: creation succesful
    //     when(mockPlayerDAO.createUser("test@email.com", "password",123456789, "username")).thenReturn(player);
    //     ResponseEntity<PlayerInfo> response = playerInfoService.createUser("test@email.com", "password",123456789, "username");
    //     assertEquals(HttpStatus.OK, response.getStatusCode());
    //     assertEquals(player, response.getBody());

    //     //Case: creation unsuccesful
    //     when(mockPlayerDAO.createUser("test@email.com", "password",123456789, "username")).thenReturn(null);
    //     response = playerInfoService.createUser("test@email.com", "password",123456789, "username");
    //     assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    //     assertNull(response.getBody());

    //     //Case: Internal server error
    //     when(mockPlayerDAO.createUser("test@email.com", "password",123456789, "username")).thenThrow();
    //     response = playerInfoService.createUser("test@email.com", "password",123456789, "username");
    //     assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    //     assertNull(response.getBody());
    // }

    // /**
    //  * tests player information retreival using ID
    //  * @throws Exception
    //  * 3 test cases
    //  */
    // @Test
    // void testGetPlayerByID() throws Exception {
    //     PlayerInfo player = new PlayerInfo("20", "test@email.com", "password", -1, "username");
    //     String id = "20";
        
    //     //case: player successfully retrieved
    //     when(mockPlayerDAO.getPlayerInfo(id)).thenReturn(player);
    //     ResponseEntity<PlayerInfo> response = playerInfoService.getPlayerByID(id);
    //     assertEquals(HttpStatus.OK, response.getStatusCode());
    //     assertEquals(player, response.getBody());

    //     //case: player does not exist or wasn't properly retrieved
    //     when(mockPlayerDAO.getPlayerInfo(id)).thenReturn(null);
    //     response = playerInfoService.getPlayerByID(id);
    //     assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    //     assertNull(response.getBody());

    //     //case: internal server error
    //     when(mockPlayerDAO.getPlayerInfo(id)).thenThrow();
    //     response = playerInfoService.getPlayerByID(id);
    //     assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    //     assertNull(response.getBody());

    // }

    // /**
    //  * Tests player information retreival using login information
    //  * @throws Exception
    //  * 3 test cases
    //  */
    // @Test
    // void testGetPlayerByUsername() throws Exception {
    //     PlayerInfo player = new PlayerInfo("20", "test@email.com", "password", -1, "username");
    //     String un = "username";
    //     String pw = "password";
        
    //     //case: player successfully retrieved
    //     when(mockPlayerDAO.getPlayerInfoWithUsername(un, pw)).thenReturn(player);
    //     ResponseEntity<PlayerInfo> response = playerInfoService.getPlayerByUsername(un, pw);
    //     assertEquals(HttpStatus.OK, response.getStatusCode());
    //     assertEquals(player, response.getBody());

    //     //case: player does not exist or wasn't properly retrieved
    //     when(mockPlayerDAO.getPlayerInfoWithUsername(un, pw)).thenReturn(null);
    //     response = playerInfoService.getPlayerByUsername(un, pw);
    //     assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    //     assertNull(response.getBody());

    //     //case: internal server error
    //     when(mockPlayerDAO.getPlayerInfoWithUsername(un, pw)).thenThrow();
    //     response = playerInfoService.getPlayerByUsername(un, pw);
    //     assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    //     assertNull(response.getBody());
    // }
}
