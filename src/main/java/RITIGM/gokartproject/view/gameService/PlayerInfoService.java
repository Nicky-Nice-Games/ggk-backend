package RITIGM.gokartproject.view.gameService;

import RITIGM.gokartproject.model.PlayerInfo;
import RITIGM.gokartproject.model.responseReceiver.CreateUID;
import RITIGM.gokartproject.model.responseReceiver.LoginCreds;
import RITIGM.gokartproject.model.responseReceiver.NoUID;
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
import org.springframework.web.bind.annotation.RequestParam;


/**
 * Handle any accesing of player data on the website side
 * 
 * {@literal @}RestController Spring annotation identifies this class as a REST API
 * method handler to the Spring framework
 * 
 * @author Diego Velez
 */
@RestController
@RequestMapping("gameservice/playerlog")
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
    @GetMapping("/login/pid/{pid}")
    public ResponseEntity<PlayerInfo> getPlayerByID(@PathVariable String pid){
        try{
            PlayerInfo playerInfo = this.playerInfoDAO.getPlayerInfo(pid);
            System.err.println(playerInfo);
            if(playerInfo != null){
                return new ResponseEntity<PlayerInfo>(playerInfo, HttpStatus.OK);
            }
            else{
                 return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }catch(Exception e){
            System.err.println(e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Retrieves player by username
     * @param username player's username
     * @param password player's password
     * @return the player data corresponding to the username and password if successful
     */
    @GetMapping("/login/{username}/{password}")
    public ResponseEntity<PlayerInfo> getPlayerByUsername(@PathVariable String username, @PathVariable String password){
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
            System.err.println(e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Creates a new user in the database
     * @param info object containing all login credentials without uid
     * @return the new user if they were succesfully created
     */
    @PostMapping("/create")
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
            System.err.println(e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Overload of user creation that accepts an additional uid parameter
     * @param info object containing all login info with UID
     * @return the new user if they were successfully added
     */
    @PostMapping("/create/uid")
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
            System.err.println(e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    /**
     * Login with UID
     * @param uid uid used for login
     * @return player info corresponding to UID
     */
    @GetMapping("/login/uid/{uid}")
    public ResponseEntity<PlayerInfo> loginUID(@PathVariable int uid) {
        if (uid <= 0){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else{
            try {
                PlayerInfo info = playerInfoDAO.loginWithUID(uid);
                if (info != null){
                    return new ResponseEntity<PlayerInfo>(info, HttpStatus.OK);
                } else{
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }
            } catch (Exception e) {
                System.err.println(e);
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        
    }

    
    /**
     * Checks to see if an email put in for account creation already exists
     * @param email email to check
     * @return true if email already is in use, false otherwise
     */
    @GetMapping("/{email}")
    public ResponseEntity<Boolean> checkEmail(@RequestBody String email){
        try{
            boolean email_status = playerInfoDAO.verifyEmail(email);
            return new ResponseEntity<Boolean>(email_status, HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    
    
}