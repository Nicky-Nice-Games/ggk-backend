package RITIGM.gokartproject.view.gameService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import java.sql.Time;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import RITIGM.gokartproject.model.PlayerInfo;
import RITIGM.gokartproject.model.RaceLog;
import RITIGM.gokartproject.model.usage.BoostUsage;
import RITIGM.gokartproject.model.usage.CollisionStat;
import RITIGM.gokartproject.model.usage.OffenseUsage;
import RITIGM.gokartproject.model.usage.TrapUsage;
import RITIGM.gokartproject.persistence.gameService.interfaces.GameLogInterface;

public class GameLogServiceTest {
    private GameLogInterface mockGameLogDAO;
    private GameLogService gameLogService;

    @BeforeEach
    void setupGameLogDAO (){
        mockGameLogDAO = mock(GameLogInterface.class);
        gameLogService = new GameLogService(mockGameLogDAO);
    }

    @Test
    void testGetByPlayerID() throws Exception{
        String pid = "20";
        ArrayList<RaceLog> raceLogs = new ArrayList<RaceLog>();

        when(mockGameLogDAO.getRaceByPlayer(pid)).thenReturn(raceLogs);
        ResponseEntity<ArrayList<RaceLog>> response = gameLogService.getByPlayerID(pid);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(raceLogs, response.getBody());

        when(mockGameLogDAO.getRaceByPlayer(pid)).thenReturn(null);
        response = gameLogService.getByPlayerID(pid);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull( response.getBody());

        when(mockGameLogDAO.getRaceByPlayer(pid)).thenThrow();
        response = gameLogService.getByPlayerID(pid);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNull( response.getBody());


    }

    @Test
    void testGetByRaceID() throws Exception {
        int rid = 20;
        RaceLog raceLog = new RaceLog(null,null,null, null, null, null, null, null, null, null);

        when(mockGameLogDAO.getRaceInfo(rid)).thenReturn(raceLog);
        ResponseEntity<RaceLog> response = gameLogService.getByRaceID(rid);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(raceLog, response.getBody());

        when(mockGameLogDAO.getRaceInfo(rid)).thenReturn(null);
        response = gameLogService.getByRaceID(rid);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull( response.getBody());

        when(mockGameLogDAO.getRaceInfo(rid)).thenThrow();
        response = gameLogService.getByRaceID(rid);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNull( response.getBody());
    }

    @Test
    void testPostMethodName() throws Exception{
        
        RaceLog raceLog = new RaceLog("20", new Timestamp(2),new Time(2), 1, 1, 1, 
        new BoostUsage(1, 1, 1, 1), 
        new CollisionStat(1, 1), 
        new OffenseUsage(1, 1, 1, 1), 
        new TrapUsage(1, 1, 1, 1));
    
        when(mockGameLogDAO.addGameLog(raceLog)).thenReturn(true);
        ResponseEntity<Void> response = gameLogService.postMethodName(raceLog);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());

        when(mockGameLogDAO.addGameLog(raceLog)).thenReturn(false);
        response = gameLogService.postMethodName(raceLog);
        assertEquals(HttpStatus.SERVICE_UNAVAILABLE, response.getStatusCode());

        when(mockGameLogDAO.addGameLog(raceLog)).thenThrow();
        response = gameLogService.postMethodName(raceLog);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());

    }
}
