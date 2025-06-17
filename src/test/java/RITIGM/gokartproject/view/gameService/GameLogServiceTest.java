package RITIGM.gokartproject.view.gameService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import RITIGM.gokartproject.model.RaceLog;
import RITIGM.gokartproject.model.usage.BoostUsage;
import RITIGM.gokartproject.model.usage.DefenseUsage;
import RITIGM.gokartproject.model.usage.OffenseUsage;
import RITIGM.gokartproject.model.usage.TrapUsage;
import RITIGM.gokartproject.persistence.gameService.interfaces.GameLogInterface;

/**
 * Test for GameLogService in view
 * 9 total test cases
 * 
 * @author Diego Velez
 */
public class GameLogServiceTest {
    private GameLogInterface mockGameLogDAO;
    private GameLogService gameLogService;

    @BeforeEach
    void setupGameLogDAO (){
        mockGameLogDAO = mock(GameLogInterface.class);
        gameLogService = new GameLogService(mockGameLogDAO);
    }

    /**
     * Tests the retreival of race information using a player's id
     * @throws Exception
     * 3 test cases
     */
    @Test
    void testGetByPlayerID() throws Exception{
        String pid = "20";
        ArrayList<RaceLog> raceLogs = new ArrayList<RaceLog>();

        //Case: retreival was successful
        when(mockGameLogDAO.getRaceByPlayer(pid)).thenReturn(raceLogs);
        ResponseEntity<ArrayList<RaceLog>> response = gameLogService.getByPlayerID(pid);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(raceLogs, response.getBody());

        //Case: retreival failed
        when(mockGameLogDAO.getRaceByPlayer(pid)).thenReturn(null);
        response = gameLogService.getByPlayerID(pid);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull( response.getBody());

        //Case: internal server error
        when(mockGameLogDAO.getRaceByPlayer(pid)).thenThrow();
        response = gameLogService.getByPlayerID(pid);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNull( response.getBody());


    }

    /**
     * Tests retreival of receinformation by race id
     * @throws Exception
     */
    @Test
    void testGetByRaceID() throws Exception {
        int rid = 20;
        Timestamp raceStartTime = Timestamp.valueOf("2025-06-02 15:23:14.0");

        RaceLog raceLog = new RaceLog("213",        
        raceStartTime,1, 2, 3, 3, 4, 5, 6, null, null, null, null);

        //Case: retreival was successful
        when(mockGameLogDAO.getRaceInfo(rid)).thenReturn(raceLog);
        ResponseEntity<RaceLog> response = gameLogService.getByRaceID(rid);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(raceLog, response.getBody());

        //Case: retraival failed
        when(mockGameLogDAO.getRaceInfo(rid)).thenReturn(null);
        response = gameLogService.getByRaceID(rid);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull( response.getBody());

        //Case: internal server error
        when(mockGameLogDAO.getRaceInfo(rid)).thenThrow();
        response = gameLogService.getByRaceID(rid);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNull( response.getBody());
    }

    /**
     * Tests the creation of a game log
     * @throws Exception
     * 3 test cases
     */
    @Test
    void testPostMethodName() throws Exception{
        
        RaceLog raceLog = new RaceLog("20", new Timestamp(2),2, 1, 1, 1,
         1, 1, 1,
        new BoostUsage(1, 1,1,1),
        new OffenseUsage(1, 1,1,1), 
        new TrapUsage(1, 1,1,1),
        new DefenseUsage(0, 0, 0, 0));
    
        //Case: log successfully posted
        when(mockGameLogDAO.addGameLog(raceLog)).thenReturn(true);
        ResponseEntity<Void> response = gameLogService.postMethodName(raceLog);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());

        //Case: log post failure
        when(mockGameLogDAO.addGameLog(raceLog)).thenReturn(false);
        response = gameLogService.postMethodName(raceLog);
        assertEquals(HttpStatus.SERVICE_UNAVAILABLE, response.getStatusCode());

        //Case: internal server error
        when(mockGameLogDAO.addGameLog(raceLog)).thenThrow();
        response = gameLogService.postMethodName(raceLog);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());

    }
}
