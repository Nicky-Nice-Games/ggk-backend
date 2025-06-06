package RITIGM.gokartproject.view.gameService;

import RITIGM.gokartproject.model.PlayerInfo;
import RITIGM.gokartproject.model.responseReceiver.common.CreateUID;
import RITIGM.gokartproject.model.responseReceiver.common.NoUID;
import RITIGM.gokartproject.persistence.gameService.interfaces.PlayerInfoInterface;

import java.util.logging.Logger;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Handle any accesing of player data on the website side
 * 
 * {@literal @}RestController Spring annotation identifies this class as a REST API
 * method handler to the Spring framework
 * 
 * @author Diego Velez
 */
@RestController
@RequestMapping("gameserivce/gameservice")
public class PlayerInfoService {
    private static final Logger log = Logger.getLogger(GameLogService.class.getName());
    private PlayerInfoInterface playerInfoDAO;


    public PlayerInfoService(PlayerInfoInterface playerInfoDAO){
        this.playerInfoDAO = playerInfoDAO;
    }

    /**
     * Retrives player information by ID
     * @param pid player ID
     * @return the player corresponding to if succesful 
     */
    @GetMapping("/{pid}")
    public ResponseEntity<PlayerInfo> getPlayerByID(@RequestBody String pid){
        log.info("GET /gameservice/playerinfo/" + pid);
        try{
            PlayerInfo playerInfo = this.playerInfoDAO.getPlayerInfo(pid);
            if(playerInfo != null){
                return new ResponseEntity<PlayerInfo>(playerInfo, HttpStatus.OK);
            }
            else{
                 return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Retrieves player by username
     * @param username player's username
     * @param password player's password
     * @return the player data corresponding to the username and password if successful
     */
    @GetMapping("/{username}")
    public ResponseEntity<PlayerInfo> getPlayerByUsername(@RequestBody String username,String password){
        log.info("GET /gameservice/playerinfo/" + username);
        try {
            PlayerInfo playerInfo = this.playerInfoDAO.getPlayerInfoWithUsername(username, password);
            if(playerInfo != null){
                return new ResponseEntity<PlayerInfo>(playerInfo, HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Creates a new user in the database
     * @param email user email
     * @param password user password
     * @param username user password
     * @return the new user if they were succesfully created
     */
    @PostMapping("")
    public ResponseEntity<PlayerInfo> createUser(@RequestBody NoUID info){
        log.info("POST /gameservice/playerinfo/" + info.getUsername());
        try {
            PlayerInfo new_player = playerInfoDAO.createUser(info.getEmail(), info.getPassword(), info.getUsername());

            if(new_player != null){
                return new ResponseEntity<PlayerInfo>(new_player, HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Overload of user creation that accepts an additional uid parameter
     * @param email user email
     * @param password user password
     * @param uid user id 
     * @param username username
     * @return the new user if they were successfully added
     */
    @PostMapping("/uid")
    public ResponseEntity<PlayerInfo> createUser(@RequestBody CreateUID info){
        log.info("POST /gameservice/playerinfo/" + info.getUsername());
        try {
            PlayerInfo new_player = playerInfoDAO.createUser(info.getEmail(), info.getPassword(), info.getUid(), info.getUsername());

            if(new_player != null){
                return new ResponseEntity<PlayerInfo>(new_player, HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}