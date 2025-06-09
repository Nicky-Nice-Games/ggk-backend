package RITIGM.gokartproject.view.webService;

import java.util.logging.Logger;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import RITIGM.gokartproject.persistence.webService.interfaces.LeaderboardInterface;
import RITIGM.gokartproject.model.LeaderboardData;
import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("webservice/leaderboard")
public class LeaderboardService {
    private static final Logger log = Logger.getLogger(LeaderboardService.class.getName());
    private LeaderboardInterface leaderboard;

    public LeaderboardService(LeaderboardInterface leaderboard){
        this.leaderboard = leaderboard;
    }

    /**
     * Use to get entry for the Leaderboard for a certain map
     * @param mapid the map id
     * @return all of the leaderboard for that map
     */
    @GetMapping("/{mapid}")
    public ResponseEntity<ArrayList<LeaderboardData>> getMapLeaderboard(@RequestParam int mapid) {
        try {
            ArrayList<LeaderboardData> data = this.leaderboard.getMapLeaderboard(mapid);
            return new ResponseEntity<ArrayList<LeaderboardData>>(data, HttpStatus.OK);
        } catch (Exception e) {
            System.err.println(e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}
