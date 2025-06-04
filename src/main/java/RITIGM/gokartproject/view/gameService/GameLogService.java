package RITIGM.gokartproject.view.gameService;

import RITIGM.gokartproject.model.RaceLog;
import RITIGM.gokartproject.persistence.gameService.interfaces.*;

import java.util.ArrayList;
import java.util.logging.Logger;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Handle any related function to the game logging from the game side
 * 
 * Include:
 * Add a new game race to the record
 * Find races by the raceID
 * Find all races by the playerID
 * 
 * {@literal @}RestController Spring annotation identifies this class as a REST API
 * method handler to the Spring framework
 * 
 * @author: Peter Dang
 */


@RestController
@RequestMapping("gameserivce/gamelog")
public class GameLogService {
    private static final Logger log = Logger.getLogger(GameLogService.class.getName());
    private GameLogInterface gameLogDAO;


    public GameLogService(GameLogInterface gameLogDAO){
        this.gameLogDAO = gameLogDAO;
    }

    /**
     * Add a new race into the database
     * @param raceLog the info about that race
     * @return: status if the race added successfully
     */
    @PostMapping("")
    public ResponseEntity<Void> postMethodName(@RequestBody RaceLog raceLog) {
        log.info("POST /gameserivce/gamelog" + raceLog);
        
        try {
            Boolean check = this.gameLogDAO.addGameLog(raceLog);
            if (check){
                return new ResponseEntity<>(HttpStatus.CREATED);
            } else{
                return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    /**
     * Fetch the race by the raceID
     * @param id the raceId that needed to lookup
     * @return the race corresponding to that id
     */
    @GetMapping("/{rid}")
    public ResponseEntity<RaceLog> getByRaceID(@PathVariable int rid){
        log.info("GET /gameserivce/gamelog/" + rid);
        try {
            RaceLog raceLog = this.gameLogDAO.getRaceInfo(rid);
            if(raceLog != null){
                return new ResponseEntity<RaceLog>(raceLog, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    

    /**
     * Fetch the race by that playerID
     * @param id the ID of that player
     * @return all of the race instance of that player
     */
    @GetMapping("/player")
    public ResponseEntity<ArrayList<RaceLog>> getByPlayerID(@RequestBody String pid){
        log.info("GET /gameserivce/gamelog/player/" + pid);
        try {
            ArrayList<RaceLog> raceLogs = this.gameLogDAO.getRaceByPlayer(pid);
            if (raceLogs != null){
                return new ResponseEntity<ArrayList<RaceLog>>(raceLogs, HttpStatus.OK);
            } else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
