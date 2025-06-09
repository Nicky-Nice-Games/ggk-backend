package RITIGM.gokartproject.view.webService;

import java.util.logging.Logger;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import RITIGM.gokartproject.model.PlayerInfo;
import RITIGM.gokartproject.persistence.gameService.interfaces.PlayerInfoInterface;
import RITIGM.gokartproject.persistence.webService.interfaces.WebPlayerInfoInterface;
import RITIGM.gokartproject.view.gameService.GameLogService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("webservice/playerinfo")
public class WebPlayerInfoService {
    private static final Logger log = Logger.getLogger(GameLogService.class.getName());
    private WebPlayerInfoInterface webPlayerInfoDAO;

    public WebPlayerInfoService(WebPlayerInfoInterface webPlayerInfoDAO){
        this.webPlayerInfoDAO = webPlayerInfoDAO;
    }


    /**
     * Retrives player information by ID
     * @param pid player ID
     * @return the player corresponding to if succesful 
     */
    @GetMapping("/{pid}")
    public ResponseEntity<PlayerInfo> getPlayerByID(@RequestBody String pid){
        log.info("GET /webservice/playerinfo/" + pid);
        try{
            PlayerInfo playerInfo = this.webPlayerInfoDAO.getPlayerInfo(pid);
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
    public ResponseEntity<PlayerInfo> getPlayerByUsername(@RequestBody String username, @RequestBody String password){
        log.info("GET /gameservice/playerinfo/" + username);
        try {
            PlayerInfo playerInfo = this.webPlayerInfoDAO.getPlayerInfoWithUsername(username, password);
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
    public ResponseEntity<PlayerInfo> createUser(@RequestBody String email, @RequestBody String password, @RequestBody String username ){
        log.info("POST /gameservice/playerinfo/" + username);
        try {
            PlayerInfo new_player = webPlayerInfoDAO.createUser(email, password, username);

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
    public ResponseEntity<PlayerInfo> createUser(@RequestBody String email, @RequestBody String password, @RequestBody int uid, @RequestBody String username ){
        log.info("POST /gameservice/playerinfo/" + username);
        try {
            PlayerInfo new_player = webPlayerInfoDAO.createUser(email, password, uid, username);

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
