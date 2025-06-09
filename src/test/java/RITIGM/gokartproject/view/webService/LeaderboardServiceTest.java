package RITIGM.gokartproject.view.webService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import RITIGM.gokartproject.model.LeaderboardData;
import RITIGM.gokartproject.persistence.webService.interfaces.LeaderboardInterface;

public class LeaderboardServiceTest {
    private LeaderboardInterface mockLeaderboardDAO;
    private LeaderboardService leaderboardService;

    @BeforeEach
    public void setupDAO(){
        mockLeaderboardDAO = mock(LeaderboardInterface.class);
        leaderboardService = new LeaderboardService(mockLeaderboardDAO);
    }


    @Test
    void testGetMapLeaderboard() throws SQLException{

        ArrayList<LeaderboardData> data = new ArrayList<LeaderboardData>();

        when(mockLeaderboardDAO.getMapLeaderboard(10)).thenReturn(data);
        ResponseEntity<ArrayList<LeaderboardData>> response = leaderboardService.getMapLeaderboard(10);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(data, response.getBody());


        when(mockLeaderboardDAO.getMapLeaderboard(10)).thenThrow();
        response = leaderboardService.getMapLeaderboard(10);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNull(response.getBody());


    }
}
